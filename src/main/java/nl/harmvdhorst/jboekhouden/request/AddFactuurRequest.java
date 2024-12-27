package nl.harmvdhorst.jboekhouden.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import nl.harmvdhorst.jboekhouden.objects.FactuurRegel;
import nl.harmvdhorst.jboekhouden.objects.IncassoMachtigingSoort;

import java.util.Date;
import java.util.List;

@Builder(setterPrefix = "set")
@XStreamAlias("oFact")
public class AddFactuurRequest extends Request {

    public String FactuurNummer;
    public String RelatieCode;
    public Date Datum;
    public Long Betalingstermijn;
    public String Factuursjabloon;
    public Boolean PerEmailVerzenden;
    public String EmailOnderwerp;
    public String EmailBericht;
    public String EmailVanAdres;
    public String EmailVanNaam;
    public Boolean AutomatischeIncasso;
    public String IncassoIBAN;
    public IncassoMachtigingSoort IncassoMachtigingSoort;
    public String IncassoMachtigingID;
    public Date IncassoMachtigingDatumOndertekening;
    public Boolean IncassoMachtigingFirst;
    public String IncassoRekeningNummer;
    public String IncassoTnv;
    public String IncassoPlaats;
    public String IncassoOmschrijvingRegel1;
    public String IncassoOmschrijvingRegel2;
    public String IncassoOmschrijvingRegel3;
    public Boolean InBoekhoudingPlaatsen;
    public String BoekhoudmutatieOmschrijving;

    public List<FactuurRegel> Regels;


}
