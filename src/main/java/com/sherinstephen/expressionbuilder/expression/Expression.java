package com.sherinstephen.expressionbuilder.expression;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Expression<ID> {
    @JsonProperty
    private final List<Filter<ID>> filters;

    @JsonProperty
    private final Op operator;

    public Expression() {
        this.filters = new ArrayList<>();
        operator = Op.AND;
    }

    public Expression(final Op op) {
        this.filters = new ArrayList<>();
        this.operator = op;
    }

    public <V> Expression<ID > add(Filter<ID> f) {
        filters.add(f);
        return this;
    }

    public <V> Expression<ID> addAll(Filter<ID>[] f) {
        for (Filter<ID> filter : f) {
            filters.add(filter);
        }
        return this;
    }

    public <V> Expression<ID > addAll(List<Filter<ID>> f) {
        filters.addAll(f);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Expression<ID> that = (Expression<ID>) o;
        return Objects.equals(filters, that.filters) &&
        operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filters, operator);
    }

    @Override
    public String toString() {
        return "Expression{" + "filters=" + filters + ", op=" + operator + '}';
    }
}
