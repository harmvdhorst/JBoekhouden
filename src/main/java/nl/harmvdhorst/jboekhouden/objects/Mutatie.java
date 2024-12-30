package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Date;
import java.util.List;

@XStreamAlias("cMutatieList")
public class Mutatie {

    public Long MutatieNr;
    public Soort Soort;
    public Date Datum;
    public String Rekening;
    public String RelatieCode;
    public String Factuurnummer;
    public String Boekstuk;
    public String Omschrijving;
    public String Betalingstermijn;
    public InExBTW InExBTW;

    public List<MutatieRegel> MutatieRegels;

}
