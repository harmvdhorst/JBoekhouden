package nl.harmvdhorst.jboekhouden.objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cSaldo")
public class Saldo {

    public Long ID;
    public String Code;
    public Categorie Categorie;
    public Double Saldo;

}
