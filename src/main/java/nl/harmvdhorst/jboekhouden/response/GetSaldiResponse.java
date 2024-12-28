package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Error;
import nl.harmvdhorst.jboekhouden.objects.Saldo;

import java.util.List;

@XStreamAlias("GetSaldiResult")
public class GetSaldiResponse extends Response {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public List<Saldo> Saldi;

}
