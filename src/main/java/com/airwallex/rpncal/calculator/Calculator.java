package com.airwallex.rpncal.calculator;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * The Calculator class has a stack of current numbers, all the operations are based on these numbers.
 */
public class Calculator {
    private final Deque<BigDecimal> numberStack = new ArrayDeque<>();

    /**
     * check if there has numbers on stack
     * @return
     */
    public boolean hasMore() {
        return numberStack.size() > 0;
    }

    /**
     * popup the top of the stack
     * @return
     */
    public BigDecimal pop() {
        return numberStack.pop();
    }
    /**
     * return the top of the stack and DO NOT remove it from stack
     * @return
     */
    public BigDecimal peek() {
        return numberStack.peek();
    }

    /**
     * push a new number to stack
     * @param number
     */
    public void push(BigDecimal number) {
        numberStack.push(number);
    }

    /**
     * return current stack
     * @return
     */
    public List<BigDecimal> stack() {
        return new ArrayList<>(numberStack);
    }

    /**
     * return size of current stack
     * @return
     */
    public int size() {
        return numberStack.size();
    }
}
