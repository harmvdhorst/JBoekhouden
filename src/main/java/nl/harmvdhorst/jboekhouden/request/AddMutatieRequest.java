package nl.harmvdhorst.jboekhouden.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import nl.harmvdhorst.jboekhouden.objects.InExBTW;
import nl.harmvdhorst.jboekhouden.objects.MutatieRegel;
import nl.harmvdhorst.jboekhouden.objects.Required;
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
    public String Rekening;
    @Required
    public String RelatieCode;
    @Required
    public String Factuurnummer;
    public String Boekstuk;
    @Required
    public String Omschrijving;
    @Required
    public String Betalingstermijn;
    public String Betalingskenmerk;
    public InExBTW InExBTW;

    @Required
    public List<MutatieRegel> MutatieRegels;

}
