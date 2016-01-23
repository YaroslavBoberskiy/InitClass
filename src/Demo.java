import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YB on 23.01.2016.
 */
public class Demo {

    public static void main(String [ ] args)
    {
        Map <String,Object> hm = new HashMap<String, Object>();

        ArrayList ar = new ArrayList();
        Class c = ar.getClass();
        hm.put("size", 45);
        initClass(c, hm);
    }

    public static <T> void initClass (Class<T> c, Map<String, Object> map) {
        try {
            T obj = c.newInstance();
            Field [] fs = obj.getClass().getDeclaredFields();
            for (String key : map.keySet()) {
                for (Field f : fs) {
                    if (f.getName().equals(key)) {
                        System.out.println("YES!");
                        f.setAccessible(true);
                        f.set(obj, map.get(key));
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
