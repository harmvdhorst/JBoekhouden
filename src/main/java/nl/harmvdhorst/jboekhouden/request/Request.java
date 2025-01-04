package nl.harmvdhorst.jboekhouden.request;

import com.thoughtworks.xstream.XStream;
import nl.harmvdhorst.jboekhouden.converter.DateTimeConverter;
import nl.harmvdhorst.jboekhouden.verifier.Verifiable;

public abstract class Request implements Verifiable {

    /**
     * Verify + serialize data to xml
     *
     * @return xml as String
     */
    public String serialize(boolean verify){

        if(verify){
            verify();
        }

        XStream stream = new XStream();
        stream.processAnnotations(getClass());
        stream.registerConverter(new DateTimeConverter());

        return stream.toXML(this);

    }

}
