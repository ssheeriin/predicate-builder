package com.sherinstephen.expressionbuilder.expression;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

abstract class AbstractCondition<ID> implements Condition<ID> {
    @JsonProperty(value = "op")
    private Op op;

    @JsonProperty private List<Filter> filters;

    AbstractCondition(Op op) {
        this.op = op;
        filters = new LinkedList<>();
    }

    public Op getOp() {
        return op;
    }

    public void setOp(Op op) {
        this.op = op;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public Condition add(Filter f) {
        filters.add(f);
        return this;
    }

    @SafeVarargs
    @Override
    public final Condition addAll(Filter... f) {
        for (Filter filter : f) {
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
        return op == that.op &&
            Objects.equals(filters, that.filters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(op, filters);
    }

    @Override
    public String toString() {
        return "AbstractCondition{" +
            "op=" + op +
            ", filters=" + filters +
            '}';
    }


}
