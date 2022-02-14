//package src.main.java;

import annotations.Injector;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import repository.Repository;
import sort.ISorter;
import user.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static List<Integer> ints = List.of(1, 2 ,3);

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException {
        var injector = new Injector();
//        System.out.println(Injector.inject(new Test2()));
        var repo = new Repository();
        System.out.println(Injector.inject(repo));
        Field stringListField = Main.class.getDeclaredField("ints");
        ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
        System.out.println(stringListClass);
//        var a = new HashMap<>();
        System.out.println(ints.getClass());

        var ref = new Reflections("user");
        var a = new HashSet<>(ref.getSubTypesOf(User.class));
        var b = new ArrayList<>(a);
//        Field f = ClassLoader.class.getDeclaredField("classes");
//        var f = ClassLoader.getSystemClassLoader().getClass().getDeclaredField("classes");
//        f.setAccessible(true);
//
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        Vector<Class> classes =  (Vector<Class>) f.get(classLoader);
//        System.out.println(classes.size());
        System.out.println(b.size());
    }

    static <T> void govno(T o) {
        System.out.println(o.getClass());
    }
}