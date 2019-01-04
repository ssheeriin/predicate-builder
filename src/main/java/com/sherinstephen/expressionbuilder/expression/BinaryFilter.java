package com.sherinstephen.expressionbuilder.expression;

public class BinaryFilter<ID, V> extends AbstractFilter<ID, V> {

    @Override
    public void setValues(V[] values) {
        if(values != null && values.length != 2) {
            throw new IllegalArgumentException("Binary Filter expects two values");
        }
        super.setValues(values);
    }
}
