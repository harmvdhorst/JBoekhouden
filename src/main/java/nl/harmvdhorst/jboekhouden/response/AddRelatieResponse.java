package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Error;

@XStreamAlias("AddRelatieResult")
public class AddRelatieResponse extends Response {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public Long Rel_ID;

}
