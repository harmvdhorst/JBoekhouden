package nl.harmvdhorst.jboekhouden.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import nl.harmvdhorst.jboekhouden.objects.InExBTW;
import nl.harmvdhorst.jboekhouden.objects.MutatieRegel;
import nl.harmvdhorst.jboekhouden.verifier.Max;
import nl.harmvdhorst.jboekhouden.verifier.Required;
import nl.harmvdhorst.jboekhouden.objects.Soort;

import java.util.Date;
import java.util.List;

@Builder(setterPrefix = "set")
@XStreamAlias("oMut")
public class AddMutatieRequest extends Request {

    public Long MutatieNr;
    @Required
    public Soort Soort;
    @Required
    public Date Datum;
    @Required
    @Max(10)
    public String Rekening;
    @Required
    @Max(15)
    public String RelatieCode;
    @Required
    @Max(50)
    public String Factuurnummer;
    @Max(50)
    public String Boekstuk;
    @Required
    @Max(200)
    public String Omschrijving;
    @Required
    @Max(4)
    public String Betalingstermijn;
    @Max(50)
    public String Betalingskenmerk;
    @Required
    public InExBTW InExBTW;

    @Required
    public List<MutatieRegel> MutatieRegels;

}
