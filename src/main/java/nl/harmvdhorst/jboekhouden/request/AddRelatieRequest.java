package nl.harmvdhorst.jboekhouden.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import nl.harmvdhorst.jboekhouden.objects.BP;
import nl.harmvdhorst.jboekhouden.verifier.Max;
import nl.harmvdhorst.jboekhouden.verifier.Required;

import java.util.Date;

@Builder(setterPrefix = "set")
@XStreamAlias("oRel")
public class AddRelatieRequest extends Request {

    public Integer ID;
    public Date AddDatum;
    @Required
    @Max(15)
    public String Code;
    @Required
    @Max(100)
    public String Bedrijf;
    @Max(150)
    public String Contactpersoon;
    @Max(1)
    public String Geslacht;
    @Max(150)
    public String Adres;
    @Max(50)
    public String Postcode;
    @Max(50)
    public String Plaats;
    @Max(50)
    public String Land;
    @Max(150)
    public String Adres2;
    @Max(50)
    public String Postcode2;
    @Max(50)
    public String Plaats2;
    @Max(50)
    public String Land2;
    @Max(50)
    public String Telefoon;
    @Max(50)
    public String GSM;
    @Max(50)
    public String FAX;
    @Max(150)
    public String Email;
    @Max(50)
    public String Site;
    public String Notitie;
    @Deprecated
    @Max(50)
    public String Bankrekening;
    @Deprecated
    @Max(50)
    public String Girorekening;
    @Max(50)
    public String BTWNummer;
    @Max(50)
    public String KvkNummer;
    @Max(50)
    public String Aanhef;
    @Max(50)
    public String IBAN;
    @Max(50)
    public String BIC;
    @Required
    public BP BP;
    @Max(100)
    public String Def1;
    @Max(100)
    public String Def2;
    @Max(100)
    public String Def3;
    @Max(100)
    public String Def4;
    @Max(100)
    public String Def5;
    @Max(100)
    public String Def6;
    @Max(100)
    public String Def7;
    @Max(100)
    public String Def8;
    @Max(100)
    public String Def9;
    @Max(100)
    public String Def10;
    @Max(1)
    public String LA;
    public Long Gb_ID;
    public Long GeenEmail;
    public Long NieuwsbriefgroepenCount;

}
