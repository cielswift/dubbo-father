package com.ciel.provider.j8.aa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Ang extends Bng<String,Integer> {

    public static void main(String[] args) {

        Type type = Ang.class.getGenericSuperclass(); //获取父类的泛型

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] types = parameterizedType.getActualTypeArguments();
            if (types.length > 0) {

                String simpleName = ((Class) types[0]).getSimpleName();
                System.out.println(simpleName);
            }
        }


    }

    @Override
    public String aaa(String s) {
        return null;
    }
}

abstract class Bng<T,V> {

    public abstract String aaa(T t);

}