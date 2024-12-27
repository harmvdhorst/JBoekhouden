import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import nl.harmvdhorst.jboekhouden.JBoekhouden;
import nl.harmvdhorst.jboekhouden.objects.BTWCode;
import nl.harmvdhorst.jboekhouden.objects.Error;
import nl.harmvdhorst.jboekhouden.objects.FactuurRegel;
import nl.harmvdhorst.jboekhouden.objects.IncassoMachtigingSoort;
import nl.harmvdhorst.jboekhouden.request.AddFactuurRequest;
import nl.harmvdhorst.jboekhouden.response.AddFactuurResponse;

public class Test {

    public static void main(String[] args) {

        JBoekhouden api = new JBoekhouden.Builder()
                .setUsername("test")
                .setSecurityCodes("code 1", "code 2")
                .build();
//
//        AddFactuurRequest request = new AddFactuurRequest();
//
//        request.IncassoMachtigingSoort = IncassoMachtigingSoort.D;
//
//        FactuurRegel regel1 = new FactuurRegel();
//        regel1.BTWCode = BTWCode.AFST_VERK;
//        FactuurRegel regel2 = new FactuurRegel();
//        regel2.BTWCode = BTWCode.AFW_VERK;
//
//        request.Regels.add(regel1);
//        request.Regels.add(regel2);
//
//        api.addFactuur(request);

        AddFactuurResponse response = new AddFactuurResponse();

//        response.error = new Error();
//        response.error.LastErrorCode = "test";
//        response.error.LastErrorDescription = "yo";

        response.Factuurnummer = "F001";

        String s = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "  <soap:Body>" +
                "    <AddFactuurResponse xmlns=\"http://www.e-boekhouden.nl/soap\">" +
                "      <AddFactuurResult>" +
                "        <ErrorMsg>" +
                "          <LastErrorCode>yo</LastErrorCode>" +
                "          <LastErrorDescription>uiuidrg</LastErrorDescription>" +
                "        </ErrorMsg>" +
                "        <Factuurnummer>F001</Factuurnummer>" +
                "      </AddFactuurResult>" +
                "    </AddFactuurResponse>" +
                "  </soap:Body>" +
                "</soap:Envelope>";

        String xml = api.decodeResponse("AddFactuurResponse", s.getBytes());

        //System.out.println(xml);

        XStream stream = new XStream();
        stream.allowTypesByWildcard(new String[]{"nl.harmvdhorst.jboekhouden.**"});
        stream.processAnnotations(AddFactuurResponse.class);
        stream.processAnnotations(Error.class);

        AddFactuurResponse response1 = (AddFactuurResponse) stream.fromXML(xml);

        //System.out.println(response1.error);
    }

}
