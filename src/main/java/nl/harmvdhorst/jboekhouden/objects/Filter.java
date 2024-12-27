package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.request.Request;

import java.util.Date;

@XStreamAlias("cFilter")
public class Filter extends Request {

    // GetArtikelen
    public Long ArtikelID;
    public String ArtikelOmschrijving;
    public String ArtikelCode;
    public String GroepOmschrijving;
    public String GroepCode;

    // GetFacturen
    public String Relatiecode;

    // GetMutaties
    public Long MutatieNr;
    public Long MutatieNrVan;
    public Long MutatieNrTm;

    // GetFacturen / GetMutaties
    // TODO handle dateTime type
    public String Factuurnummer;
    public Date DatumTm;

    // GetFacturen / GetMutaties / GetSaldi / GetSaldo
    public Date DatumVan;

    // GetKostenplaatsen
    public Long KostenplaatsID;
    public Long KostenplaatsParentID;
    public String Omschrijving;

    // GetGrootboekrekeningen / GetSaldi
    public Categorie Categorie;

    // GetRelaties
    public String Trefwoord;

    // GetGrootboekrekeningen / GetRelaties
    public Long ID;
    public String Code;

    // GetSaldi / GetSaldo
    public Long KostenPlaatsId;
    public Date DatumTot;

    // GetSaldo
    public String GbCode;




}