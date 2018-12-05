package com.sherinstephen.expressionbuilder.expression;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UnaryFilter<T, V> extends AbstractFilter<T> {
    @JsonProperty
    private final V value;

    public UnaryFilter() {
        value = null;
    }

    public UnaryFilter(Op op, T col, V val) {
        super(col, op);
        this.value = val;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        UnaryFilter that = (UnaryFilter) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }
}
