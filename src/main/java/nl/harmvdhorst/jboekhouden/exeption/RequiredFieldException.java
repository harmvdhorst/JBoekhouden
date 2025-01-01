package nl.harmvdhorst.jboekhouden.exeption;

public class RequiredFieldException extends JBoekhoudenException {

    public RequiredFieldException(String clazz, String field) {
        super("Required field " + field + " is null for class " + clazz);
    }

}
