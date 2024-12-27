package nl.harmvdhorst.jboekhouden.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import nl.harmvdhorst.jboekhouden.objects.InExBTW;
import nl.harmvdhorst.jboekhouden.objects.MutatieRegel;
import nl.harmvdhorst.jboekhouden.objects.Soort;

import java.util.List;

@Builder(setterPrefix = "set")
@XStreamAlias("oMut")
public class AddMutatieRequest extends Request {

    public Long MutatieNr;
    public Soort Soort;
    public String Rekening;
    public String RelatieCode;
    public String Factuurnummer;
    public String Boekstuk;
    public String Omschrijving;
    public String Betalingstermijn;
    public String Betalingskenmerk;
    public InExBTW InExBTW;

    public List<MutatieRegel> MutatieRegels;

}
