package com.sherinstephen.expressionbuilder.expression;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXISTING_PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = EXISTING_PROPERTY, property = "op")
@JsonSubTypes({
    @JsonSubTypes.Type(value = UnaryFilter.class, name = "EQ"),
    @JsonSubTypes.Type(value = UnaryFilter.class, name = "GT"),
    @JsonSubTypes.Type(value = UnaryFilter.class, name = "LT"),
    @JsonSubTypes.Type(value = UnaryFilter.class, name = "GE"),
    @JsonSubTypes.Type(value = UnaryFilter.class, name = "LE"),
    @JsonSubTypes.Type(value = BinaryFilter.class, name="BETWEEN"),
    @JsonSubTypes.Type(value=AndCondition.class, name = "AND"),
    @JsonSubTypes.Type(value=OrCondition.class, name = "OR")
})
public interface Filter {
    Op getOp();
}
