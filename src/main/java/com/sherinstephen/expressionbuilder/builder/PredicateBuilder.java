package com.sherinstephen.expressionbuilder.builder;

import com.sherinstephen.expressionbuilder.expression.Expression;
import com.sherinstephen.expressionbuilder.expression.Filter;
import com.sherinstephen.expressionbuilder.util.JSONUtil;

import java.io.IOException;
import java.util.List;

public class PredicateBuilder<ID> {

    private Expression<ID> expression = new Expression<>();

    private PredicateBuilder<ID> add(Filter filter) {
        expression.add(filter);
        return this;
    }

    public PredicateBuilder addAll(List<Filter> filters) {
        expression.addAll(filters);
        return this;
    }

    public static void main(String[] args) throws IOException {
        String json =  PredicateBuilder.<String>create()
            .add(Filters.eq("name", "sherin"))
            .add(Filters.and(Filters.between("score", "10", "20"), Filters.eq("xyz", "120")))
            .add(Filters.or(Filters.eq("age", "10"), Filters.eq("age", "20")))
            .toJSON();

        System.out.println(json);

        Expression exp = JSONUtil.toObject(json, Expression.class);
        System.out.println(exp);
    }

    private static <E> PredicateBuilder<E> create() {
        return new PredicateBuilder<>();
    }

    private String toJSON() {
        return JSONUtil.toJSON(expression);
    }


}
