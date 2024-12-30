package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import nl.harmvdhorst.jboekhouden.request.Verifiable;

@Builder(setterPrefix = "set")
@XStreamAlias("cFactuurRegel")
public class FactuurRegel implements Verifiable {

    public Double Aantal;
    public String Eenheid;
    @Required
    public String Code;
    @Required
    public String Omschrijving;
    public Double PrijsPerEenheid;
    @Required
    public BTWCode BTWCode;
    @Required
    public String TegenrekeningCode;
    public Long KostenplaatsID;

}
