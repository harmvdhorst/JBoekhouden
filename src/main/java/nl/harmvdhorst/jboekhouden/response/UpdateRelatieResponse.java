package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("UpdateRelatieResult")
public class UpdateRelatieResponse extends Response {

    public String LastErrorCode;
    public String LastErrorDescription;

}
