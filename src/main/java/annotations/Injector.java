package annotations;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.stream.Collectors;

@Configuration(packages = {"sort"})
public class Injector {

    public static <T> T inject(T object) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Injectable.class)) {
                field.setAccessible(true);
                if (Collection.class.isAssignableFrom(field.getType())) {
                    ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
                    Class<?> typeOfCollection = (Class<?>) stringListType.getActualTypeArguments()[0];
                    var classes = new Reflections(Injector.class.getAnnotation(Configuration.class).packages()[0]).getSubTypesOf(typeOfCollection)
                            .stream().collect(Collectors.toList());
                    var collection = field.getType().getConstructor().newInstance();
                    var add = collection.getClass().getDeclaredMethod("add", Object.class);
                    for (Class<?> obj : classes) {
                        add.invoke(collection, obj.getConstructor().newInstance());
                    }
                    field.set(object, collection);
                } else {
                    var classes = new Reflections(Injector.class.getAnnotation(Configuration.class).packages()[0]).getSubTypesOf(field.getType())
                            .stream().collect(Collectors.toList());
                    if (classes.size() != 1) {
                        throw new RuntimeException();
                    } else {
                        field.set(object, classes.get(0).getConstructor().newInstance());
                    }
                }
            }
        }
        return object;
    }
}
