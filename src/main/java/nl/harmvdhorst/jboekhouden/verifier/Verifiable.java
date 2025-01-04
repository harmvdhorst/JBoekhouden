package nl.harmvdhorst.jboekhouden.verifier;

import nl.harmvdhorst.jboekhouden.exeption.FieldOutOfBoundsException;
import nl.harmvdhorst.jboekhouden.exeption.RequiredFieldException;

import java.lang.reflect.Field;
import java.util.List;

public interface Verifiable {

    /**
     * Verify if all required fields != null
     */
    default void verify(){

        try {
            Field[] fields = this.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                if(field.getType() == List.class){
                    List list = (List) field.get(this);

                    if(list == null) continue;

                    for (Object o : list) {
                        if(o instanceof Verifiable){
                            ((Verifiable) o).verify();
                        }
                    }
                }

                if(field.isAnnotationPresent(Required.class)){
                    if(field.get(this) == null){
                        throw new RequiredFieldException(this.getClass().getSimpleName(), field.getName());
                    }
                }

                if(field.isAnnotationPresent(Max.class) && field.getType() == String.class){
                    int max = field.getAnnotation(Max.class).value();
                    if(field.get(this) != null){
                        String value = (String) field.get(this);
                        if(value.length() > max){
                            throw new FieldOutOfBoundsException(getClass().getSimpleName(), field.getName(), value.length(), max);
                        }
                    }
                }

            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}
