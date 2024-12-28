package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Error;

@XStreamAlias("GetSaldoResult")
public class GetSaldoResponse extends Response {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public Double Saldo;

}
