package com.sherinstephen.expressionbuilder.builder;

import com.sherinstephen.expressionbuilder.expression.*;

public class Filters {

    public static <ID, V> Filter<ID> eq(ID id, V[] vals) {
        UnaryFilter<ID,V> filter =  new UnaryFilter<>();
        filter.setId(id);
        filter.setOperator(Op.EQ);
        filter.setValues(vals);
        return  filter;
    }

    public static <ID, V> Filter<ID> between(ID id, V[] vals) {
        BinaryFilter<ID,V> filter =  new BinaryFilter<>();
        filter.setId(id);
        filter.setOperator(Op.BETWEEN);
        filter.setValues(vals);
        return filter;
    }

    @SafeVarargs
    public static <ID , V, T extends Filter<ID>> Condition<ID> and(T... filters) {
        if (filters.length < 2) {
            throw new IllegalArgumentException("AND needs minimum two operands");
        }
        return new AndCondition<ID>().addAll(filters);
    }

    @SafeVarargs
    public static <ID,V, T extends Filter<ID>> Condition<ID> or(T... filters) {

        if (filters.length < 2) {
            throw new IllegalArgumentException("OR needs minimum two operands");
        }
        return new OrCondition<ID>().addAll(filters);

    }

}
