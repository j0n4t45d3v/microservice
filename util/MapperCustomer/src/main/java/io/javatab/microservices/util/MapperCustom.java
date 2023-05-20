package io.javatab.microservices.util;

import java.util.function.Consumer;

public class MapperCustom {
    public static <E> void updatePropererIfNotNull(Consumer<E> setter, E updatedValue ){
        if(updatedValue != null) setter.accept(updatedValue);
    }
}
