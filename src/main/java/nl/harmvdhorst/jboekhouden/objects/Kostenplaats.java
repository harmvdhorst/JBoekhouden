package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cKostenplaats")
public class Kostenplaats {

    public Long KostenplaatsId;
    public String Omschrijving;
    public Long KostenplaatsParentId;

}
