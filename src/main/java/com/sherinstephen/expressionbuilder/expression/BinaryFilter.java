package com.sherinstephen.expressionbuilder.expression;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class BinaryFilter<T, V> extends AbstractFilter<T> {
    @JsonProperty
    private final V value1;

    @JsonProperty
    private final V value2;

    public BinaryFilter(Op op, T col, V value1, V value2) {
        super(col, op);
        this.value2 = value2;
        this.value1 = value1;
    }

    public BinaryFilter() {
        value1 = value2 = null;
    }

    public V getValue1() {
        return value1;
    }

    public V getValue2() {
        return value2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BinaryFilter that = (BinaryFilter) o;
        return value1.equals(that.value1) &&
            value2.equals(that.value2) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2);
    }

    @Override
    public String toString() {
        return super.toString() +  "BinaryFilter{" +
            "value1='" + value1 + '\'' +
            ", value2='" + value2 + '\'' +
            '}';
    }
}
