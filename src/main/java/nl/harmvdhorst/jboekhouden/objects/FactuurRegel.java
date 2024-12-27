package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;

@Builder(setterPrefix = "set")
@XStreamAlias("cFactuurRegel")
public class FactuurRegel {

    public Double Aantal;
    public String Eenheid;
    public String Code;
    public String Omschrijving;
    public Double PrijsPerEenheid;
    public BTWCode BTWCode;
    public String TegenrekeningCode;
    public Long KostenplaatsID;

}
