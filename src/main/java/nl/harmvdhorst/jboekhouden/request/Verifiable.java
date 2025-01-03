package nl.harmvdhorst.jboekhouden.request;

import nl.harmvdhorst.jboekhouden.exeption.RequiredFieldException;
import nl.harmvdhorst.jboekhouden.objects.Required;

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
                if(field.isAnnotationPresent(Required.class)){
                    field.setAccessible(true);
                    if(field.get(this) == null){
                        throw new RequiredFieldException(this.getClass().getSimpleName(), field.getName());
                    } else {
                        if(field.getType() == List.class){
                            List list = (List) field.get(this);

                            for (Object o : list) {
                                if(o instanceof Verifiable){
                                    ((Verifiable) o).verify();
                                }
                            }
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}
