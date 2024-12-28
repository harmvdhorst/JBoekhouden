package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Error;
import nl.harmvdhorst.jboekhouden.objects.OpenPost;

import java.util.List;

@XStreamAlias("GetOpenPostenResult")
public class GetOpenPostenResponse extends Response {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public List<OpenPost> Openposten;

}
