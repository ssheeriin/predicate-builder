package com.sherinstephen.expressionbuilder.expression;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

abstract class AbstractCondition<ID> implements Condition<ID> {
    @JsonProperty
    private Op operator;

    @JsonProperty private List<Filter<ID>> filters;

    AbstractCondition(Op op) {
        this.operator = op;
        filters = new LinkedList<>();
    }

    public Op getOperator() {
        return operator;
    }

    public void setOperator(Op op) {
        this.operator = op;
    }

    public List<Filter<ID>> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter<ID>> filters) {
        this.filters = filters;
    }

    @Override
    public Condition<ID> add(Filter<ID> f) {
        filters.add(f);
        return this;
    }

    @Override
    public final Condition<ID> addAll(Filter<ID>[] f) {
        for (Filter<ID> filter  : f) {
            add(filter);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AbstractCondition that = (AbstractCondition) o;
        return operator == that.operator &&
            Objects.equals(filters, that.filters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator, filters);
    }

    @Override
    public String toString() {
        return "AbstractCondition{" +
            "op=" + operator +
            ", filter<ID,?>s=" + filters +
            '}';
    }


}
