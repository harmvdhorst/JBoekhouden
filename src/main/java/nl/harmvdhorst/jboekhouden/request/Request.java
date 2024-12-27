package nl.harmvdhorst.jboekhouden.request;

import com.thoughtworks.xstream.XStream;

public abstract class Request {

    public String serialize(){

        XStream stream = new XStream();
        stream.processAnnotations(getClass());

        return stream.toXML(this);

    }

}
