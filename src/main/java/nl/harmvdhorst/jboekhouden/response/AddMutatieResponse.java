package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Error;

@XStreamAlias("AddMutatieResult")
public class AddMutatieResponse extends Response {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public Long Mutatienummer;

}
