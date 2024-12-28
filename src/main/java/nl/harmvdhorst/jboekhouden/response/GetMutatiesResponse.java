package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Error;
import nl.harmvdhorst.jboekhouden.objects.MutatieList;

import java.util.List;

@XStreamAlias("GetMutatiesResult")
public class GetMutatiesResponse extends Response {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public List<MutatieList> Mutaties;

}
