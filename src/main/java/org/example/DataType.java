package org.example;

public class DataType {

    /**
     * Private Instance Variables
     * name: holds the name of the data type
     * javaType: holds the corresponding Java class type
     */
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

