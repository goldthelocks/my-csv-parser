package com.eraine.data;

import java.util.Objects;

public class CSVValue<T> {

    private final T value;

    public CSVValue(T value) {
        this.value = value;
    }

    public String getAsString() {
        if (value instanceof String) {
            return (String) value;
        }
        return null;
    }

    public Number getAsNumber() {
        if (value instanceof Number) {
            return (Number) value;
        }
        return null;
    }

    public T getRaw() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CSVValue<?> csvValue = (CSVValue<?>) o;
        return Objects.equals(value, csvValue.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
