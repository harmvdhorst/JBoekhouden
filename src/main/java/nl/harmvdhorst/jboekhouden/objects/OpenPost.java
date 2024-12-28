package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Date;

@XStreamAlias("cOpenPost")
public class OpenPost {

    public Date MutDatum;
    public String MutFactuur;
    public String RelCode;
    public String RelBedrijf;
    public Double Bedrag;
    public Double Voldaan;
    public Double Openstaand;

}
