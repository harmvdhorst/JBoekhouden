package nl.harmvdhorst.jboekhouden.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import nl.harmvdhorst.jboekhouden.objects.Categorie;

@Builder(setterPrefix = "set")
@XStreamAlias("oGb")
public class AddGrootboekrekeningRequest extends Request {

    public Integer ID;
    public String Code;
    public String Omschrijving;
    public Categorie Categorie;
    public String Groep;

}
