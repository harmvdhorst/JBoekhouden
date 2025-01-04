package nl.harmvdhorst.jboekhouden.exeption;

public class FieldOutOfBoundsException extends JBoekhoudenException{

    public FieldOutOfBoundsException(String clazz, String field, int current, int max) {
        super("Field " + field + " in class " + clazz + " is out of bounds. (current: " + current + " | max: " + max + ")");
    }

}
