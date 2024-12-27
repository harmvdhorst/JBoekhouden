package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;

@Builder(setterPrefix = "set")
@XStreamAlias("cMutatieRegel")
public class MutatieRegel {

    // TODO handle decimal type using custom converter?

    public Float BedragInvoer;
    public Float BedragExclBTW;
    public Float BedragBTW;
    public Float BedragInclBTW;
    public BTWCode BTWCode;
    public Float BTWPercentage;
    public String TegenrekeningCode;
    public Long KostenplaatsID;

}
