package com.sherinstephen.expressionbuilder.expression;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public abstract class AbstractFilter<ID, V> implements Filter<ID> {

    @JsonProperty
    private ID id;

    @JsonProperty
    private Op operator;

    @JsonProperty
    private V[] values;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void setOperator(Op operator) {
        this.operator = operator;
    }

    public void setValues(V[] vals) {
        this.values = vals;
    }
    public Op getOperator() {
        return operator;
    }

    
    public V[] getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AbstractFilter that = (AbstractFilter) o;
        return id.equals(that.id) &&
        operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operator);
    }

    @Override
    public String toString() {
        return "Filter{" +
            "id='" + id + '\'' +
            ", op=" + operator +
            '}';
    }
}
