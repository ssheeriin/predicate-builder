package com.sherinstephen.expressionbuilder.expression;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Expression<ID> {
    @JsonProperty
    private final List<Filter> filters;

    @JsonProperty
    private final Op op;

    public Expression() {
        this.filters = new ArrayList<>();
        op = Op.AND;
    }

    public Expression(final Op op) {
        this.filters = new ArrayList<>();
        this.op = op;
    }

    public Expression add(Filter f) {
        filters.add(f);
        return this;
    }

    public Expression addAll(Filter... f) {
        for (Filter filter : f) {
            filters.add(filter);
        }
        return this;
    }

    public Expression addAll(List<Filter> f) {
        filters.addAll(f);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Expression that = (Expression) o;
        return Objects.equals(filters, that.filters) &&
            op == that.op;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filters, op);
    }

    @Override
    public String toString() {
        return "Expression{" +
            "filters=" + filters +
            ", op=" + op +
            '}';
    }
}
