package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Error;
import nl.harmvdhorst.jboekhouden.objects.Kostenplaats;

import java.util.List;

@XStreamAlias("GetKostenplaatsenResult")
public class GetKostenplaatsenResponse extends Response {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public List<Kostenplaats> Kostenplaatsen;

}
