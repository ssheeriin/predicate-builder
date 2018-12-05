package com.sherinstephen.expressionbuilder.expression;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

abstract class AbstractFilter<T> implements Filter {

    @JsonProperty
    private final T id;

    @JsonProperty(value = "op")
    private final Op op;

    AbstractFilter() {
        this(null, Op.EQ);
    }

    AbstractFilter(T id, Op op) {
        this.id = id;
        this.op = op;
    }

    public T getId() {
        return id;
    }

    public Op getOp() {
        return op;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AbstractFilter that = (AbstractFilter) o;
        return id.equals(that.id) &&
            op == that.op;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, op);
    }

    @Override
    public String toString() {
        return "AbstractFilter{" +
            "id='" + id + '\'' +
            ", op=" + op +
            '}';
    }
}
