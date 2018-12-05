package com.sherinstephen.expressionbuilder.expression;

public interface Condition<T> extends Filter {
    Condition add(Filter f);
    Condition addAll(Filter... f);


}

