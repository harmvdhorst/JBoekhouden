package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Error;

@XStreamAlias("AutoLoginResult")
public class AutoLoginResponse extends Response {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public String Token;

}
