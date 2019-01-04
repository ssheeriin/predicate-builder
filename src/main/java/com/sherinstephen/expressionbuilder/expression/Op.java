package com.sherinstephen.expressionbuilder.expression;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Op {
    LT, EQ, GT, LE, GE, BETWEEN, AND, OR;

    @JsonCreator
    public static Op forValue(String value) {
        return Op.valueOf(value);
    }
}
