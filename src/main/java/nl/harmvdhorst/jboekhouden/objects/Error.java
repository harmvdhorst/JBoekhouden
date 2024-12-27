package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ErrorMsg")
public class Error {

    public String LastErrorCode;
    public String LastErrorDescription;

}
