package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import nl.harmvdhorst.jboekhouden.request.Verifiable;

@Builder(setterPrefix = "set")
@XStreamAlias("cMutatieRegel")
public class MutatieRegel implements Verifiable {

    // TODO handle decimal type using custom converter?

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
    public String TegenrekeningCode;
    public Long KostenplaatsID;

    public String Factuurnummer;

}
