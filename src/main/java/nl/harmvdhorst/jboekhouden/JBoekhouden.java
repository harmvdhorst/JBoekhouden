package nl.harmvdhorst.jboekhouden;

import com.thoughtworks.xstream.XStream;

import nl.harmvdhorst.jboekhouden.request.AddFactuurRequest;
import nl.harmvdhorst.jboekhouden.response.AddFactuurResponse;
import nl.harmvdhorst.jboekhouden.response.OpenSessionResponse;

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

    private String sessionId;

    private JBoekhouden(String username, String securityCode1, String securityCode2, String source){
        this.username = username;
        this.securityCode1 = securityCode1;
        this.securityCode2 = securityCode2;
        this.source = source;
    }

    public AddFactuurResponse addFactuur(AddFactuurRequest request){
        AtomicReference<AddFactuurResponse> response = null;

        sendRequest("AddFactuur", request.serialize(), true, (httpResponse) -> {
            xStream.processAnnotations(AddFactuurRequest.class);
            response.set((AddFactuurResponse) xStream.fromXML(httpResponse, AddFactuurResponse.class));
        });

        return response.get();
    }

    public void login(){

        sendRequest("OpenSession", getLoginXml(), false, (httpResponse) -> {
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

        return username + securityCode1 + securityCode2 + (this.source != null ? source : "");

    }

    public String decodeResponse(String type, byte[] xml){
        return new String(xml).split("<" + type + " xmlns=\"http://www.e-boekhouden.nl/soap\"> ")[1].split("</" + type)[0];
    }

    public static class Builder {

        private String username;
        private String securityCode1;
        private String securityCode2;
        private String source = null;

        public Builder setUsername(String username){
            this.username = username;
            return this;
        }

        public Builder setSecurityCodes(String securityCode1, String securityCode2){
            this.securityCode1 = securityCode1;
            this.securityCode2 = securityCode2;
            return this;
        }

        public Builder setSource(String source){
            this.source = source;
            return this;
        }

        public JBoekhouden build(){
            return new JBoekhouden(username, securityCode1, securityCode2, source);
        }

    }

}
