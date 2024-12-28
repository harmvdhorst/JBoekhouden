package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Error;
import nl.harmvdhorst.jboekhouden.objects.FactuurList;

import java.util.List;

@XStreamAlias("GetFacturenResult")
public class GetFacturenResponse extends Response {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public List<FactuurList> Facturen;

}
