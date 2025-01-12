package nl.harmvdhorst.jboekhouden;

import com.thoughtworks.xstream.XStream;

import nl.harmvdhorst.jboekhouden.converter.DateTimeConverter;
import nl.harmvdhorst.jboekhouden.exeption.LoginException;
import nl.harmvdhorst.jboekhouden.objects.Filter;
import nl.harmvdhorst.jboekhouden.objects.MutatieRegel;
import nl.harmvdhorst.jboekhouden.objects.OpSoort;
import nl.harmvdhorst.jboekhouden.request.AddFactuurRequest;
import nl.harmvdhorst.jboekhouden.request.AddGrootboekrekeningRequest;
import nl.harmvdhorst.jboekhouden.request.AddMutatieRequest;
import nl.harmvdhorst.jboekhouden.request.AddRelatieRequest;
import nl.harmvdhorst.jboekhouden.response.*;

import okhttp3.*;
import okhttp3.Response;

import java.io.IOException;

public class JBoekhouden {

    private final OkHttpClient httpClient = new OkHttpClient();
    private final XStream xStream = new XStream();

    private final String username;
    private final String securityCode1;
    private final String securityCode2;
    private final String source;
    private final String administratieGUID;
    private final boolean shouldVerify;

    private String sessionId;

    private JBoekhouden(String username, String securityCode1, String securityCode2, String source, String administratieGUID, boolean shouldVerify) {
        this.username = username;
        this.securityCode1 = securityCode1;
        this.securityCode2 = securityCode2;
        this.source = source;
        this.administratieGUID = administratieGUID;
        this.shouldVerify = shouldVerify;
        
        xStream.allowTypesByWildcard(new String[]{"nl.harmvdhorst.jboekhouden.**"});
        xStream.registerConverter(new DateTimeConverter());
    }

    /**
     * Voer een nieuwe factuur in
     *
     * @param request as AddFactuurRequest
     * @return response as AddFactuurResponse
     */
    public AddFactuurResponse addFactuur(AddFactuurRequest request){
        return sendHttpRequest("AddFactuur", request.serialize(shouldVerify), true, AddFactuurResponse.class);
    }

    /**
     * Voeg een nieuwe grootboekrekening toe
     *
     * @param request as AddGrootboekrekeningRequest
     * @return response as AddGrootboekrekeningResponse
     */
    public AddGrootboekrekeningResponse addGrootboekrekening(AddGrootboekrekeningRequest request){
        return sendHttpRequest("AddGrootboekrekening", request.serialize(shouldVerify), true, AddGrootboekrekeningResponse.class);
    }

    /**
     * Voeg een boekhoudmutatie toe
     *
     * @param request as AddMutatieRequest
     * @return response as AddMutatieResponse
     */
    public AddMutatieResponse addMutatie(AddMutatieRequest request){
        return sendHttpRequest("AddMutatie", request.serialize(shouldVerify), true, AddMutatieResponse.class);
    }

    /**
     * Voeg een relatie toe
     *
     * @param request as AddRelatieRequest
     * @return response as AddRelatieResponse
     */
    public AddRelatieResponse addRelatie(AddRelatieRequest request){
        return sendHttpRequest("AddRelatie", request.serialize(shouldVerify), true, AddRelatieResponse.class);
    }

    /**
     * Haal gekoppelde administraties op
     *
     * @return response as GetAdministatiesResponse
     */
    public GetAdministratiesResponse getAdministraties(){
        return sendHttpRequest("GetAdministraties", "", true, GetAdministratiesResponse.class);
    }

    /**
     * Haal artikelen van een administratie op
     * Voor filter opties zie e-boekhouden documentatie
     *
     * @param filter as Filter
     * @return response as GetArtikelenResponse
     */
    public GetArtikelenResponse getArtikelen(Filter filter){
        return sendHttpRequest("GetArtikelen", filter.serialize(shouldVerify), true, GetArtikelenResponse.class);
    }

    /**
     * Haal 1 of meerdere facturen op
     * Voor filter opties zie e-boekhouden documentatie
     *
     * @param filter as Filter
     * @return response as GetFacturenResponse
     */
    public GetFacturenResponse getFacturen(Filter filter){
        return sendHttpRequest("GetFacturen", filter.serialize(shouldVerify), true, GetFacturenResponse.class);
    }

    /**
     * Haal lijst met grootboekrekeningen op
     * Voor filter opties zie e-boekhouden documentatie
     *
     * @param filter as Filter
     * @return response as GetGrootboekrekeningenResponse
     */
    public GetGrootboekrekeningenResponse getGrootboekrekeningen(Filter filter){
        return sendHttpRequest("GetGrootboekrekeningen", filter.serialize(shouldVerify), true, GetGrootboekrekeningenResponse.class);
    }

    /**
     * Haal lijst met kostenplaatsen op
     * Voor filter opties zie e-boekhouden documentatie
     *
     * @param filter as Filter
     * @return response as GetKostenplaatsenResponse
     */
    public GetKostenplaatsenResponse getKostenplaatsen(Filter filter){
        return sendHttpRequest("GetKostenplaatsen", filter.serialize(shouldVerify), true, GetKostenplaatsenResponse.class);
    }

    /**
     * Haal lijst met mutaties op (max. 500)
     * Voor filter opties zie e-boekhouden documentatie
     *
     * @param filter as Filter
     * @return response as GetMutatiesResponse
     */
    public GetMutatiesResponse getMutaties(Filter filter){
        xStream.alias("cMutatieListRegel", MutatieRegel.class);
        return sendHttpRequest("GetMutaties", filter.serialize(shouldVerify), true, GetMutatiesResponse.class);
    }

    /**
     * Haal lijst met openposten op
     * Keuze om te zoeken: Debiteuren of Crediteuren
     *
     * @param OpSoort as OpSoort
     * @return response as GetOpenPostenResponse
     */
    public GetOpenPostenResponse getOpenPosten(OpSoort OpSoort){
        return sendHttpRequest("GetOpenPosten", "<OpSoort>" + OpSoort + "</OpSoort>", true, GetOpenPostenResponse.class);
    }

    /**
     * Haal lijst met relaties op
     * Voor filter opties zie e-boekhouden documentatie
     *
     * @param filter as Filter
     * @return response as GetRelatiesResponse
     */
    public GetRelatiesResponse getRelaties(Filter filter){
        return sendHttpRequest("GetRelaties", filter.serialize(shouldVerify), true, GetRelatiesResponse.class);
    }

    /**
     * Haal 1 of meerdere saldi op van specifieke grootboekrekeningen
     * Voor filter opties zie e-boekhouden documentatie
     *
     * @param filter as Filter
     * @return response as GetSaldiResponse
     */
    public GetSaldiResponse getSaldi(Filter filter){
        return sendHttpRequest("GetSaldi", filter.serialize(shouldVerify), true, GetSaldiResponse.class);
    }

    /**
     * Haal het saldo op voor een grootboekrekening of kostenplaats
     * Voor filter opties zie e-boekhouden documentatie
     *
     * @param filter as Filter
     * @return response as GetSaldoResponse
     */
    public GetSaldoResponse getSaldo(Filter filter){
        return sendHttpRequest("GetSaldo", filter.serialize(shouldVerify), true, GetSaldoResponse.class);
    }

    /**
     * Pas een grootboekrekening aan
     *
     * @param request as AddGrootboekrekeningRequest
     * @return response as UpdateGrootboekrekeningResponse
     */
    public UpdateGrootboekrekeningResponse updateGrootboekrekening(AddGrootboekrekeningRequest request){
        return sendHttpRequest("UpdateGrootboekrekening", request.serialize(shouldVerify), true, UpdateGrootboekrekeningResponse.class);
    }

    /**
     * Pas een relatie aan
     *
     * @param request as AddRelatieRequest
     * @return response as UpdateRelatieResponse
     */
    public UpdateRelatieResponse updateRelatie(AddRelatieRequest request){
        return sendHttpRequest("UpdateRelatie", request.serialize(shouldVerify), true, UpdateRelatieResponse.class);
    }

    /**
     * Open een sessie (nodig om andere query's uit te voeren)
     * Als er een AdministratieGUID is ingevoerd wordt een een Sub Session gestart
     */
    public void openSession(){

        xStream.alias("OpenSessionSubResult", OpenSessionResponse.class);

        OpenSessionResponse response = sendHttpRequest((administratieGUID == null ? "OpenSession" : "OpenSessionSub"), getLoginXml(), false, OpenSessionResponse.class);

        if(response.error.LastErrorCode.isEmpty()){
            this.sessionId = response.SessionID;
        } else {
            throw new LoginException(response.error, false);
        }

    }

    /**
     * Sluit huidige session
     */
    public void closeSession(){
        sendHttpRequest("CloseSession", "<SessionID>" + sessionId + "</SessionID>", false, String.class);
    }

    /**
     * Nodig om single sign-on mogelijk te maken
     * Voor meer informatie zie e-boekhouden documentatie
     *
     * @return Token as String
     */
    public String autoLogin(){

        AutoLoginResponse response = sendHttpRequest("AutoLogin", "<Username>" + username + "</Username>", true, AutoLoginResponse.class);

        if(response.error.LastErrorCode != null){
            throw new LoginException(response.error, true);
        }

        return response.Token;
    }

    /**
     * Used to send http request to api + parse the response using the type param
     *
     * @param action
     * @param xml
     * @param withSession
     * @param response
     * @return
     * @param <T>
     */
    private <T> T sendHttpRequest(String action, String xml, boolean withSession, Class<T> response){

        String rawResponseString = null;
        T rawResponse = null;

        RequestBody body = RequestBody.create(getXml(action, xml, withSession), MediaType.get("text/xml"));
        Request request = new Request.Builder()
                .header("SOAPAction", "http://www.e-boekhouden.nl/soap/" + action)
                .url("https://soap.e-boekhouden.nl/soap.asmx")
                .post(body)
                .build();

        try (Response rResponse = httpClient.newCall(request).execute()){
            if(!action.equals("CloseSession")){
                String parsedResponse = decodeResponse(action + "Response", rResponse.body().bytes());
                if(response != String.class){
                    xStream.processAnnotations(response);
                    rawResponse = (T) xStream.fromXML(parsedResponse);
                } else {
                    rawResponseString = parsedResponse;
                }
            } else {
                rawResponseString = rResponse.body().string();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response != String.class ? rawResponse : (T) rawResponseString;

    }

    /**
     * Used to decode the xml response
     *
     * @param type
     * @param xml
     * @return
     */
    private String decodeResponse(String type, byte[] xml){
        return new String(xml).split("<" + type + " xmlns=\"http://www.e-boekhouden.nl/soap\">")[1].split("</" + type)[0];
    }

    /**
     * Used to generate the xml for the http request
     * Also adds sessions when necessary
     *
     * @param action
     * @param xml
     * @param withSession
     * @return
     */
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

    /**
     * Generates the xml for the opensession request
     *
     * @return xml as String
     */
    private String getLoginXml(){

        String username = "<Username>" + this.username + "</Username>";
        String securityCode1 = "<SecurityCode1>" + this.securityCode1 + "</SecurityCode1>";
        String securityCode2 = "<SecurityCode2>" + this.securityCode2 + "</SecurityCode2>";
        String source = "<Source>" + this.source + "</Source>";
        String administratieGUID = "<AdministratieGUID>" + this.administratieGUID + "</AdministratieGUID>";

        return username + securityCode1 + securityCode2 + (this.source != null ? source : "") + (this.administratieGUID != null ? administratieGUID : "");

    }

    /**
     * Create new Builder
     *
     * @return builder as JBoekhoudenBuilder
     */
    public static JBoekhoudenBuilder builder(){
        return new JBoekhoudenBuilder();
    }

    public static class JBoekhoudenBuilder {

        private String username;
        private String securityCode1;
        private String securityCode2;
        private String source = null;
        private String administratieGUID = null;
        private boolean shouldVerify = true;

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

        public JBoekhoudenBuilder disableVerifier(){
            this.shouldVerify = false;
            return this;
        }

        public JBoekhouden build(){
            return new JBoekhouden(username, securityCode1, securityCode2, source, administratieGUID, shouldVerify);
        }

    }

}
