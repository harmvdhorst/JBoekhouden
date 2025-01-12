package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Date;
import java.util.List;

@XStreamAlias("cFactuurList")
public class Factuur {

    public String Factuurnummer;
    public String Relatiecode;
    public Date Datum;
    public Long Betalingstermijn;
    public Double TotaalExclBTW;
    public Double TotaalBTW;
    public Double TotaalInclBTW;
    public Double TotaalOpenstaand;
    public String URLPDFBestand;

    public List<FactuurRegel> Regels;

}
