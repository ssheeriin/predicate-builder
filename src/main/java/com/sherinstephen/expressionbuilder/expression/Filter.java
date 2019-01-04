package com.sherinstephen.expressionbuilder.expression;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


@JsonTypeInfo(use = Id.NAME, include = As.EXISTING_PROPERTY, property = "operator", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = UnaryFilter.class, name = "EQ"),
    @JsonSubTypes.Type(value = UnaryFilter.class, name = "GT"),
    @JsonSubTypes.Type(value = UnaryFilter.class, name = "LT"),
    @JsonSubTypes.Type(value = UnaryFilter.class, name = "GE"),
    @JsonSubTypes.Type(value = UnaryFilter.class, name = "LE"),
    @JsonSubTypes.Type(value = BinaryFilter.class, name="BETWEEN"),
    @JsonSubTypes.Type(value = AndCondition.class, name = "AND"),
    @JsonSubTypes.Type(value = OrCondition.class, name = "OR")
})
public interface Filter<ID> {
    @JsonProperty
    Op getOperator();
    @JsonProperty
    void setOperator(Op operator);
}
