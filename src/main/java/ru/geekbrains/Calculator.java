package ru.geekbrains;

import java.util.*;

public class Calculator {
    private static final String operators = "+-*/";
    public static boolean flag = true;

    public static Double calc(List<String> postfix) {
        Deque<Double> stack = new ArrayDeque<>();
        for (String x : postfix) {
            switch (x) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> {
                    Double b = stack.pop(), a = stack.pop();
                    stack.push(a - b);
                }
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "/" -> {
                    Double b = stack.pop(), a = stack.pop();
                    stack.push(a / b);
                }
                case "u-" -> stack.push(-stack.pop());
                default -> stack.push(Double.valueOf(x));
            }
        }
        return stack.pop();
    }

    public static List<String> parse(String infix) {
        List<String> postfix = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, operators, true);
        String prev = "";
        String curr;
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
                System.out.println("Некорректное выражение.");
                flag = false;
                return postfix;
            }

            if (isOperator(curr)) {
                if (curr.equals("-") && (prev.equals(""))) {
                    // унарный минус
                    curr = "u-";
                }
                else {
                    while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
                        postfix.add(stack.pop());
                    }
                }
                stack.push(curr);
            }

            else {
                postfix.add(curr);
            }
            prev = curr;
        }

        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) postfix.add(stack.pop());
            else {
                System.out.println("Скобки не согласованы.");
                flag = false;
                return postfix;
            }
        }
        return postfix;
    }

    public static boolean isOperator(String token) {
        if (token.equals("u-")) return true;
        return operators.contains(token);
    }

    private static int priority(String token) {
        if (token.equals("(")) return 1;
        if (token.equals("+") || token.equals("-")) return 2;
        if (token.equals("*") || token.equals("/")) return 3;
        return 4;
    }
}
