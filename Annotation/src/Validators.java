import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Validators {

    Map<Class <?>, Validator> registeredValidators = new HashMap<>(); // Si inseriscono le classi delle annotazioni come generici e le interfecce dei validatori

    public boolean validate (Object o) throws IllegalAccessException {

        Field[] fields = o.getClass().getDeclaredFields(); // Tutti i campi della classe (NON I VALORI)

        for(Field f : fields){ // Si itera su tutti i campi della classe

            Annotation[] annotations = f.getAnnotations(); //Si prendono tutti i campi della classe

            for(Annotation a : annotations) {

                if (registeredValidators.containsKey(a.annotationType())){ // se la key contiene effettivamente una Annotation

                   if (!registeredValidators.get(a.annotationType()).validate(f.get(o))){  //Con il metodo validate dell'interfaccia si prende dal campo f il valore dalla classe o
                       return false;
                   }
                }

            }

        }
        return  true;
    }


}
