package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Error;

@XStreamAlias("OpenSessionResult")
public class OpenSessionResponse {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public String SessionID;

}