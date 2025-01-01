package nl.harmvdhorst.jboekhouden.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import nl.harmvdhorst.jboekhouden.objects.Required;

@Builder(setterPrefix = "set")
@XStreamAlias("oGb")
public class AddGrootboekrekeningRequest extends Request {

    public Integer ID;
    @Required
    public String Code;
    @Required
    public String Omschrijving;
    @Required
    public String Categorie;
    public String Groep;

}
