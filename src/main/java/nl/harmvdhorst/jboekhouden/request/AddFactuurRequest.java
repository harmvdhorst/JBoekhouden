package nl.harmvdhorst.jboekhouden.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import nl.harmvdhorst.jboekhouden.objects.FactuurRegel;
import nl.harmvdhorst.jboekhouden.objects.IncassoMachtigingSoort;
import nl.harmvdhorst.jboekhouden.verifier.Max;
import nl.harmvdhorst.jboekhouden.verifier.Required;

import java.util.Date;
import java.util.List;

@Builder(setterPrefix = "set")
@XStreamAlias("oFact")
public class AddFactuurRequest extends Request {

    @Max(50)
    public String Factuurnummer;
    @Required
    @Max(15)
    public String Relatiecode;
    @Required
    public Date Datum;
    public Long Betalingstermijn;
    @Required
    @Max(50)
    public String Factuursjabloon;
    public Boolean PerEmailVerzenden;
    public String EmailOnderwerp;
    public String EmailBericht;
    @Max(150)
    public String EmailVanAdres;
    @Max(150)
    public String EmailVanNaam;
    public Boolean AutomatischeIncasso;
    @Max(150)
    public String IncassoIBAN;
    public IncassoMachtigingSoort IncassoMachtigingSoort;
    @Max(50)
    public String IncassoMachtigingID;
    public Date IncassoMachtigingDatumOndertekening;
    public Boolean IncassoMachtigingFirst;
    @Max(150)
    public String IncassoRekeningNummer;
    @Max(150)
    public String IncassoTnv;
    @Max(150)
    public String IncassoPlaats;
    @Max(50)
    public String IncassoOmschrijvingRegel1;
    @Max(50)
    public String IncassoOmschrijvingRegel2;
    @Max(50)
    public String IncassoOmschrijvingRegel3;
    public Boolean InBoekhoudingPlaatsen;
    @Max(200)
    public String BoekhoudmutatieOmschrijving;

    @Required
    public List<FactuurRegel> Regels;


}
