package nl.harmvdhorst.jboekhouden.exeption;

import nl.harmvdhorst.jboekhouden.objects.Error;

public class LoginException extends JBoekhoudenException {

    public LoginException(Error error, boolean autoLogin) {
        super("Something went wrong while trying " +  (!autoLogin ? "to open session." : "to send autologin request") + " reason: " + error.LastErrorDescription + " (" + error.LastErrorCode + ")");
    }

}
