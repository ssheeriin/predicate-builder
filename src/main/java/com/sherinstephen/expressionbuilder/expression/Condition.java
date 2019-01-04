package com.sherinstephen.expressionbuilder.expression;

public interface Condition<ID> extends Filter<ID> {
    Condition<ID> add(Filter<ID> f);

    Condition<ID> addAll(Filter<ID>[] f);
}
