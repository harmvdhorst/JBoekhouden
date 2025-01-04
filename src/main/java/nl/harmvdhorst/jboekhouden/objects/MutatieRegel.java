package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import nl.harmvdhorst.jboekhouden.verifier.Max;
import nl.harmvdhorst.jboekhouden.verifier.Required;
import nl.harmvdhorst.jboekhouden.verifier.Verifiable;

@Builder(setterPrefix = "set")
@XStreamAlias("cMutatieRegel")
public class MutatieRegel implements Verifiable {

    @Required
    public Float BedragInvoer;
    @Required
    public Float BedragExclBTW;
    @Required
    public Float BedragBTW;
    @Required
    public Float BedragInclBTW;
    @Required
    public BTWCode BTWCode;
    @Required
    public Float BTWPercentage;
    @Required
    @Max(10)
    public String TegenrekeningCode;
    public Long KostenplaatsID;

    public String Factuurnummer;

}
