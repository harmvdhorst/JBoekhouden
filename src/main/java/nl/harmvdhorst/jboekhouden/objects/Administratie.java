package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cAdministratie")
public class Administratie {

    public String Bedrijf;
    public String Plaats;
    public String Guid;
    public String StartBoekjaar;

}
