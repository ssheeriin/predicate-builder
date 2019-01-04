package com.sherinstephen.expressionbuilder.builder;

import com.sherinstephen.expressionbuilder.expression.Expression;
import com.sherinstephen.expressionbuilder.expression.Filter;
import com.sherinstephen.expressionbuilder.expression.Op;
import com.sherinstephen.expressionbuilder.temp.NodeUUID;
import com.sherinstephen.expressionbuilder.util.JSONUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class PredicateBuilder<ID> {

    private Expression<ID> expression = new Expression<>();

    private  PredicateBuilder<ID> add(Filter<ID> filter) {
        expression.add(filter);
        return this;
    }

    public  PredicateBuilder<ID> addAll(List<Filter<ID>> filters) {
        expression.addAll(filters);
        return this;
    }

    public static void main(String[] args) throws IOException {
        NodeUUID node1 = new NodeUUID();
        node1.setNodeId("schema#object#column");
        node1.setNodeType("LOGICAL_OBJECT");
        node1.setNodeSource("BizX");
        PredicateBuilder<NodeUUID> builder = PredicateBuilder.<NodeUUID>create()
            .add(Filters.eq(node1, new String[]{"sherin"}))
            .add(Filters.between(node1, new Date[]{new Date(), new Date()}))
            .add(Filters.or(Filters.between(node1, new Date[]{new Date(), new Date()}), Filters.eq(node1, new String[]{"120"})))
            .add(Filters.eq(node1, new String[]{"10"}));

            String json = builder.toJSON();
        System.out.println(json);

        Expression<NodeUUID> exp = JSONUtil.toObject(json, Expression.class);
        System.out.println(exp);
        String newJson = JSONUtil.toJSON(exp);
        System.out.println(newJson);

        System.out.println(json.equals(newJson));

    }

    private static <E> PredicateBuilder<E> create() {
        return new PredicateBuilder<>();
    }

    private String toJSON() {
        return JSONUtil.toJSON(expression);
    }


}
