package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Error;

@XStreamAlias("AddGrootboekrekeningResult")
public class AddGrootboekrekeningResponse extends Response {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public Long Gb_ID;

}
