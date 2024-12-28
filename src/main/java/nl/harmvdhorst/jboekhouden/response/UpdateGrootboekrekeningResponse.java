package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("UpdateGrootboekrekeningResult")
public class UpdateGrootboekrekeningResponse extends Response {

    public String LastErrorCode;
    public String LastErrorDescription;

}
