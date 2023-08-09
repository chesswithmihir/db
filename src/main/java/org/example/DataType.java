package org.example;

public class DataType {
    private String name;
    private Class<?> javaType;

    public DataType(String name, Class<?> javaType) {
        this.name = name;
        this.javaType = javaType;
    }

    public String getName() {
        return name;
    }

    public Class<?> getJavaType() {
        return javaType;
    }
}

