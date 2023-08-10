package org.example;

public class Column {

    /**
     * name: holds the name of the column
     * dataType: holds the dataType of the column
     * */
    private String name;
    private DataType dataType;

    public Column(String name, DataType dataType) {
        this.name = name;
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public DataType getDataType() {
        return dataType;
    }
}
