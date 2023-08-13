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

    public Object parseValue(String value) {
        if (this == STRING) {
            if (value.startsWith("'") && value.endsWith("'")) {
                return value.substring(1, value.length() - 1); // Remove single quotes
            }
            throw new IllegalArgumentException("Invalid string value format: " + value);
        } else if (this == INT) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid int value format: " + value);
            }
        } else if (this == FLOAT) {
            try {
                return Float.parseFloat(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid float value format: " + value);
            }
        } else {
            throw new IllegalArgumentException("Unsupported data type: " + this.name);
        }
    }
}
