package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cGrootboekrekening")
public class GrootboekRekening {

    public Long ID;
    public String Code;
    public String Omschrijving;
    public Categorie Categorie;
    public String Groep;

}
