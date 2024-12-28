package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Error;
import nl.harmvdhorst.jboekhouden.objects.Relatie;

import java.util.List;

@XStreamAlias("GetRelatiesResult")
public class GetRelatiesResponse extends Response {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public List<Relatie> Relaties;

}
