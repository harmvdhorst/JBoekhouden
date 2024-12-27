package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cArtikel")
public class Artikel {

    // TODO handle decimal type

    public Long ArtikelID;
    public String ArtikelOmschrijving;
    public String ArtikelCode;
    public String GroepOmschrijving;
    public String GroepCode;
    public String Eenheid;
    public Float InkoopprijsExclBTW;
    public Float VerkoopprijsExclBTW;
    public Float VerkoopprijsInclBTW;
    public BTWCode BTWCode;
    public String TegenrekeningCode;
    public Float BtwPercentage;
    public Long KostenplaatsID;
    public Boolean Actief;

}
