package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Artikel;
import nl.harmvdhorst.jboekhouden.objects.Error;

import java.util.List;

@XStreamAlias("GetArtikelenResult")
public class GetArtikelenResponse {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public List<Artikel> Artikelen;


}
