package com.hsiaoweiyun.designpatterns.builder;

import java.util.Objects;

import static com.hsiaoweiyun.designpatterns.builder.Pizza.Topping.ONION;
import static com.hsiaoweiyun.designpatterns.builder.Pizza.Topping.PEPPER;

public class NyPizza extends Pizza {

    public enum Size {SMALL, MEDIUM, LARGE}

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {

        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        Pizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public NyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }

    public static void main(String[] args) {
        Pizza nyPizza = new NyPizza.Builder(Size.SMALL).addTopping(PEPPER).addTopping(ONION).build();
    }

}
