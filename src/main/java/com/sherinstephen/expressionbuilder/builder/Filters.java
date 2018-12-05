package com.sherinstephen.expressionbuilder.builder;

import com.sherinstephen.expressionbuilder.expression.*;

public class Filters {

    public static <I, V> Filter eq(I col, V val) {
        return new UnaryFilter<>(Op.EQ, col, val);
    }

    public static <I, V> Filter between(I col, V val1, V val2) {
        return new BinaryFilter<>(Op.BETWEEN, col, val1, val2);
    }

    @SafeVarargs
    public static <ID, T extends Filter> Condition and(T... filters) {
        if (filters.length < 2) {
            throw new RuntimeException("AND needs minimum two operands");
        }
        return new AndCondition<ID>().addAll(filters);
    }

    @SafeVarargs
    public static <ID, T extends Filter> Condition or(T... filters) {

        if (filters.length < 2) {
            throw new RuntimeException("OR needs minimum two operands");
        }
        return new OrCondition<ID>().addAll(filters);

    }

}
