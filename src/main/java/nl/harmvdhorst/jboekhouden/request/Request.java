package nl.harmvdhorst.jboekhouden.request;

import com.thoughtworks.xstream.XStream;

public abstract class Request implements Verifiable {

    /**
     * Verify + serialize data to xml
     *
     * @return xml as String
     */
    public String serialize(){

        verify();

        XStream stream = new XStream();
        stream.processAnnotations(getClass());

        return stream.toXML(this);

    }

}
