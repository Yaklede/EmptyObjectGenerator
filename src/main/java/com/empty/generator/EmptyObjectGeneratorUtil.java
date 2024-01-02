package com.empty.generator;

import com.empty.generator.value.EmptyValue;

import java.util.HashMap;
import java.util.Map;

public class EmptyObjectGeneratorUtil {
    private EmptyObjectGeneratorUtil() {
    }

    private static Map<Class<?>, Object> customJavaTypeSupport = new HashMap<>();
    private static EmptyValue emptyValue =
            new EmptyValue(
                    false,
                    "empty",
                    0,
                    0L,
                    0f,
                    0.0,
                    false,
                    0,
                    0
            );

    public static <T> void generate(
            Class<T> clazz
    ) {

    }

    public static void setIsNullable(boolean isNullable) {
        emptyValue.setNullable(isNullable);
    }

    public static void setDefaultString(String defaultString) {
        emptyValue.setString(defaultString);
    }

}
