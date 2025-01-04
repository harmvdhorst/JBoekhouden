package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import nl.harmvdhorst.jboekhouden.verifier.Max;
import nl.harmvdhorst.jboekhouden.verifier.Required;
import nl.harmvdhorst.jboekhouden.verifier.Verifiable;

@Builder(setterPrefix = "set")
@XStreamAlias("cFactuurRegel")
public class FactuurRegel implements Verifiable {

    public Double Aantal;
    @Max(50)
    public String Eenheid;
    @Required
    @Max(50)
    public String Code;
    @Required
    public String Omschrijving;
    public Double PrijsPerEenheid;
    @Required
    public BTWCode BTWCode;
    @Required
    @Max(10)
    public String TegenrekeningCode;
    public Long KostenplaatsID;

}
