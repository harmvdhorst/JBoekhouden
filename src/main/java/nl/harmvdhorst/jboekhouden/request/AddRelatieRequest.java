package nl.harmvdhorst.jboekhouden.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import nl.harmvdhorst.jboekhouden.objects.BP;

import java.util.Date;

@Builder(setterPrefix = "set")
@XStreamAlias("oRel")
public class AddRelatieRequest extends Request {

    public Integer ID;
    public Date AddDatum;
    public String Code;
    public String Bedrijf;
    public String Contactpersoon;
    public String Geslacht;
    public String Adres;
    public String Postcode;
    public String Plaats;
    public String Land;
    public String Adres2;
    public String Postcode2;
    public String Plaats2;
    public String Land2;
    public String Telefoon;
    public String GSM;
    public String FAX;
    public String Email;
    public String Site;
    public String Notitie;
    @Deprecated
    public String Bankrekening;
    @Deprecated
    public String Girorekening;
    public String BTWNummer;
    public String KvkNummer;
    public String Aanhef;
    public String IBAN;
    public String BIC;
    public BP BP;
    public String Def1;
    public String Def2;
    public String Def3;
    public String Def4;
    public String Def5;
    public String Def6;
    public String Def7;
    public String Def8;
    public String Def9;
    public String Def10;
    public String LA;
    public Long Gb_ID;
    public Long GeenEmail;
    public Long NieuwsbriefgroepenCount;

}
