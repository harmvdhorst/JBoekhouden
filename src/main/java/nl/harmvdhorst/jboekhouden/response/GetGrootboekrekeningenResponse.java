package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Error;
import nl.harmvdhorst.jboekhouden.objects.GrootboekRekening;

import java.util.List;

@XStreamAlias("GetGrootboekrekeningenResult")
public class GetGrootboekrekeningenResponse extends Response {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public List<GrootboekRekening> Rekeningen;

}
