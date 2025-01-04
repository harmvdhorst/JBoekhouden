package nl.harmvdhorst.jboekhouden.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import nl.harmvdhorst.jboekhouden.verifier.Max;
import nl.harmvdhorst.jboekhouden.verifier.Required;

@Builder(setterPrefix = "set")
@XStreamAlias("oGb")
public class AddGrootboekrekeningRequest extends Request {

    public Integer ID;
    @Required
    @Max(10)
    public String Code;
    @Required
    @Max(50)
    public String Omschrijving;
    @Required
    @Max(10)
    public String Categorie;
    public String Groep;

}
