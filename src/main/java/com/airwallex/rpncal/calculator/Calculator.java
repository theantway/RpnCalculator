package com.airwallex.rpncal.calculator;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Calculator {
    private final Deque<BigDecimal> stack = new ArrayDeque<>();

    public boolean hasMore() {
        return stack.size() > 0;
    }

    public BigDecimal pop() {
        return stack.pop();
    }

    public void push(BigDecimal number) {
        stack.push(number);
    }

    public List<BigDecimal> stack() {
        return stack.stream().collect(toList());
    }

    public int size() {
        return stack.size();
    }
}
