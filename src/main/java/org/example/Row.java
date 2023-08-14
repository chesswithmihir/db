package org.example;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Object> values;

    public Row() {
        this.values = new ArrayList<>();
    }

    public List<Object> getValues() {
        return new ArrayList<>(values);
    }

    public void addValue(Object value) {
        values.add(value);
    }

    public Object getValue(int columnIndex) {
        if (columnIndex >= 0 && columnIndex < values.size()) {
            return values.get(columnIndex);
        }
        throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
    }
}
