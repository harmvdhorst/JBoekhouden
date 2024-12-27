package nl.harmvdhorst.jboekhouden.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import nl.harmvdhorst.jboekhouden.objects.Administratie;
import nl.harmvdhorst.jboekhouden.objects.Error;
import nl.harmvdhorst.jboekhouden.request.Request;

import java.util.List;

@XStreamAlias("GetAdministratiesResult")
public class GetAdministratiesResponse extends Request {

    @XStreamAlias("ErrorMsg")
    public Error error;

    public List<Administratie> Administraties;

}
