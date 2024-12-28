package nl.harmvdhorst.jboekhouden;

import com.thoughtworks.xstream.XStream;

import nl.harmvdhorst.jboekhouden.objects.Filter;
import nl.harmvdhorst.jboekhouden.objects.OpSoort;
import nl.harmvdhorst.jboekhouden.request.AddFactuurRequest;
import nl.harmvdhorst.jboekhouden.request.AddGrootboekrekeningRequest;
import nl.harmvdhorst.jboekhouden.request.AddMutatieRequest;
import nl.harmvdhorst.jboekhouden.request.AddRelatieRequest;
import nl.harmvdhorst.jboekhouden.response.*;

import okhttp3.*;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class JBoekhouden {

    private final OkHttpClient httpClient = new OkHttpClient();
    private final XStream xStream = new XStream();

    private final String username;
    private final String securityCode1;
    private final String securityCode2;
    private final String source;
    private final String administratieGUID;

    private String sessionId;

    private JBoekhouden(String username, String securityCode1, String securityCode2, String source, String administratieGUID){
        this.username = username;
        this.securityCode1 = securityCode1;
        this.securityCode2 = securityCode2;
        this.source = source;
        this.administratieGUID = administratieGUID;

        xStream.allowTypesByWildcard(new String[]{"nl.harmvdhorst.jboekhouden.**"});
    }

    public void openSession(){

        sendRequest((administratieGUID == null ? "OpenSession" : "OpenSessionSub"), getLoginXml(), false, (httpResponse) -> {
            xStream.processAnnotations(OpenSessionResponse.class);

            OpenSessionResponse response = (OpenSessionResponse) xStream.fromXML(httpResponse);

            if(response.error == null){
                this.sessionId = response.SessionID;
            } else {
                // TODO better message
                System.out.println("Something went wrong loggin in!");
            }
        });

    }

    public void logout(){
        sendRequest("CloseSession", "<SessionID>" + sessionId + "</SessionID>", false, (httpResponse) -> {});
    }

    public String autoLogin(){

        AutoLoginResponse response = sendHttpRequest("AutoLogin", "<Username>" + username + "</Username>", true, AutoLoginResponse.class);

        if(response.error != null){
            // TODO proper error handling
            System.out.println("error");
            return null;
        }

        return response.Token;
    }

    private void sendRequest(String action, String xml, boolean withSession, Consumer<String> response){
        System.out.println(getXml(action, xml, withSession));

//        RequestBody body = RequestBody.create(getXml(action, xml, withSession), MediaType.get("text/xml"));
//        Request request = new Request.Builder()
//                .header("SOAPAction", "http://www.e-boekhouden.nl/soap/" + action)
//                .url("https://soap.e-boekhouden.nl/soap.asmx")
//                .post(body)
//                .build();
//
//        try (Response httpResponse = httpClient.newCall(request).execute()) {
//            response.accept(decodeResponse(action + "Response", httpResponse.body().bytes()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    private String getXml(String action, String xml, boolean withSession){
        String extraXml = "";
        if(withSession){
            extraXml = "<SessionID>" + sessionId + "</SessionID>" +
                       "<SecurityCode2>" + securityCode2 + "</SecurityCode2>";
        }

        return
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                        "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                        "<soap:Header />" +
                        "<soap:Body>" +
                        "<" + action + " xmlns=\"http://www.e-boekhouden.nl/soap\">" +
                        extraXml +
                        xml +
                        "</" + action + ">" +
                        "</soap:Body>" +
                        "</soap:Envelope>";
    }

    private String getLoginXml(){

        String username = "<Username>" + this.username + "</Username>";
        String securityCode1 = "<SecurityCode1>" + this.securityCode1 + "</SecurityCode1>";
        String securityCode2 = "<SecurityCode2>" + this.securityCode2 + "</SecurityCode2>";
        String source = "<Source>" + this.source + "</Source>";
        String administratieGUID = "<AdministratieGUID>" + this.administratieGUID + "</AdministratieGUID>";

        return username + securityCode1 + securityCode2 + (this.source != null ? source : "") + (this.administratieGUID != null ? administratieGUID : "");

    }

    public String decodeResponse(String type, byte[] xml){
        return new String(xml).split("<" + type + " xmlns=\"http://www.e-boekhouden.nl/soap\"> ")[1].split("</" + type)[0];
    }

    public AddFactuurResponse addFactuur(AddFactuurRequest request){
        return sendHttpRequest("AddFactuur", request.serialize(), true, AddFactuurResponse.class);
    }

    public AddGrootboekrekeningResponse addGrootboekrekening(AddGrootboekrekeningRequest request){
        return sendHttpRequest("AddGrootboekrekening", request.serialize(), true, AddGrootboekrekeningResponse.class);
    }

    public AddMutatieResponse addMutatie(AddMutatieRequest request){
        return sendHttpRequest("AddMutatie", request.serialize(), true, AddMutatieResponse.class);
    }

    public AddRelatieResponse addRelatie(AddRelatieRequest request){
        return sendHttpRequest("AddRelatie", request.serialize(), true, AddRelatieResponse.class);
    }

    public GetAdministratiesResponse getAdministraties(){
        return sendHttpRequest("GetAdministraties", "", true, GetAdministratiesResponse.class);
    }

    public GetArtikelenResponse getArtikelen(Filter filter){
        return sendHttpRequest("GetArtikelen", filter.serialize(), true, GetArtikelenResponse.class);
    }

    public GetFacturenResponse getFacturen(Filter filter){
        return sendHttpRequest("GetFacturen", filter.serialize(), true, GetFacturenResponse.class);
    }

    public GetGrootboekrekeningenResponse getGrootboekrekeningen(Filter filter){
        return sendHttpRequest("GetGrootboekrekeningen", filter.serialize(), true, GetGrootboekrekeningenResponse.class);
    }

    public GetKostenplaatsenResponse getKostenplaatsen(Filter filter){
        return sendHttpRequest("GetKostenplaatsen", filter.serialize(), true, GetKostenplaatsenResponse.class);
    }

    public GetMutatiesResponse getMutaties(Filter filter){
        return sendHttpRequest("GetMutaties", filter.serialize(), true, GetMutatiesResponse.class);
    }

    public GetOpenPostenResponse getOpenPosten(OpSoort OpSoort){
        return sendHttpRequest("GetOpenPosten", "<OpSoort>" + OpSoort + "</OpSoort>", true, GetOpenPostenResponse.class);
    }

    public GetRelatiesResponse getRelaties(Filter filter){
        return sendHttpRequest("GetRelaties", filter.serialize(), true, GetRelatiesResponse.class);
    }

    public GetSaldiResponse getSaldi(Filter filter){
        return sendHttpRequest("GetSaldi", filter.serialize(), true, GetSaldiResponse.class);
    }

    public GetSaldoResponse getSaldo(Filter filter){
        return sendHttpRequest("GetSaldo", filter.serialize(), true, GetSaldoResponse.class);
    }

    public UpdateGrootboekrekeningResponse updateGrootboekrekening(AddGrootboekrekeningRequest request){
        return sendHttpRequest("UpdateGrootboekrekening", request.serialize(), true, UpdateGrootboekrekeningResponse.class);
    }

    public UpdateRelatieResponse updateRelatie(AddRelatieRequest request){
        return sendHttpRequest("UpdateRelatie", request.serialize(), true, UpdateRelatieResponse.class);
    }

    public <T> T sendHttpRequest(String action, String xml, boolean withSession, Class<T> response){

        AtomicReference<String> rawResponseString = new AtomicReference<>();
        AtomicReference<T> rawResponse = new AtomicReference<>();

        sendRequest(action, xml, withSession, (httpRes) -> {
            if(response != String.class){
                xStream.processAnnotations(response);
                rawResponse.set((T) xStream.fromXML(httpRes));
            } else {
                rawResponseString.set(httpRes);
            }
        });

        return response != String.class ? rawResponse.get() : (T) rawResponseString.get();

    }

    public static JBoekhoudenBuilder builder(){
        return new JBoekhoudenBuilder();
    }

    public static class JBoekhoudenBuilder {

        private String username;
        private String securityCode1;
        private String securityCode2;
        private String source = null;
        private String administratieGUID = null;

        public JBoekhoudenBuilder setUsername(String username){
            this.username = username;
            return this;
        }

        public JBoekhoudenBuilder setSecurityCodes(String securityCode1, String securityCode2){
            this.securityCode1 = securityCode1;
            this.securityCode2 = securityCode2;
            return this;
        }

        public JBoekhoudenBuilder setSource(String source){
            this.source = source;
            return this;
        }

        public JBoekhoudenBuilder setAdministratieGUID(String administratieGUID){
            this.administratieGUID = administratieGUID;
            return this;
        }

        public JBoekhouden build(){
            return new JBoekhouden(username, securityCode1, securityCode2, source, administratieGUID);
        }

    }

}
