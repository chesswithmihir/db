package org.example;

public class DataType {
    public static final DataType STRING = new DataType("string", String.class);
    public static final DataType INT = new DataType("int", Integer.class);
    public static final DataType FLOAT = new DataType("float", Float.class);

    private String name;
    private Class<?> javaType;

    private DataType(String name, Class<?> javaType) {
        this.name = name;
        this.javaType = javaType;
    }

    public String getName() {
        return name;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public static DataType fromString(String typeName) {
        switch (typeName.toLowerCase()) {
            case "string":
                return STRING;
            case "int":
                return INT;
            case "float":
                return FLOAT;
            default:
                throw new IllegalArgumentException("Invalid data type: " + typeName);
        }
    }
}
