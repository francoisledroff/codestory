package com.droff.qna.equation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Dijkstra's two-stack algorithm implementation
 * doing Equation just like my HP: using "Reverse Polish" alike values and operators stacks  
 */
public class EquationSolver
{

    static final Character OPENING_PARANTHESIS = '(';
    static final Character CLOSING_PARANTHESIS = ')';
    static final Character PLUS = ' ';
    static final Character MINUS = '-';
    static final Character DIVIDE = '/';
    static final Character TIMES = '*';
    static Map<Character, Integer> OPERATORS_PRECEDENCES;
    static {
        Map<Character, Integer> operators_precendences = new TreeMap<Character, Integer>();
        operators_precendences.put(OPENING_PARANTHESIS, 0);
        operators_precendences.put(CLOSING_PARANTHESIS, 0);
        operators_precendences.put(PLUS, 1);
        operators_precendences.put(MINUS, 1);
        operators_precendences.put(TIMES, 2);
        operators_precendences.put(DIVIDE, 2);
        OPERATORS_PRECEDENCES = Collections.unmodifiableMap(operators_precendences);
    }

    private static Integer computeOperation(char operator, Integer operand1, Integer operand2)
    {
        if (operator == PLUS)
            return operand1 + operand2;
        if (operator == MINUS)
            return operand1 - operand2;
        if (operator == DIVIDE)
            return operand1 / operand2;
        if (operator == TIMES)
            return operand1 * operand2;
        throw new RuntimeException("Invalid operator " + operator);
    }

    private static List<String> getTokens(String equation)
    {
        List<String> tokens = new ArrayList<String>();
        String currentToken = "";
        for (int i = 0; i < equation.length(); i++)
        {
            char currentChar = equation.charAt(i);
            if (OPERATORS_PRECEDENCES.containsKey(currentChar))
            {
                if (currentToken.length() > 0)
                {
                    tokens.add(currentToken); // last token was a value
                    currentToken = "";
                }
                tokens.add("" + currentChar); // this char is an operator
            }
            else
            {
                currentToken += currentChar;
            }
        }
        if (currentToken.length() > 0)
        {
            tokens.add(currentToken); // last token was a value
        }
        return tokens;
    }

    public static Integer evaluate(String equation)
    {
        Stack<Character> operatorsStack = new Stack<Character>();
        Stack<Integer> valuesStack = new Stack<Integer>();
        Iterator<String> tokensIterator = getTokens(equation).iterator();
        while (tokensIterator.hasNext())
        {
            String token = tokensIterator.next();            
            if (token.length()!=1 || !OPERATORS_PRECEDENCES.containsKey(token.charAt(0)))
            {
                valuesStack.push(Integer.parseInt(token)); // the token is a value
                continue;
            }
            else
            {
                char nextOperator = token.charAt(0); // the token is an operator
                while (true)
                {
                    if (operatorsStack.isEmpty() || nextOperator == OPENING_PARANTHESIS
                            || (OPERATORS_PRECEDENCES.get(nextOperator) > OPERATORS_PRECEDENCES.get(operatorsStack.peek())))
                    {
                        operatorsStack.push(nextOperator);
                        // here we push to the stack and don't evaluate to ensure the operator with higher precedence is evaluated first
                        break;
                    }
                    Character operator = operatorsStack.pop();
                    if (operator == OPENING_PARANTHESIS)
                    {
                        assert nextOperator == CLOSING_PARANTHESIS;
                        break; // closing paranthesis, ditching them
                    }
                    else if (valuesStack.size() > 1)
                    {
                        Integer val2 = valuesStack.pop();
                        Integer val1 = valuesStack.pop();
                        valuesStack.push(computeOperation(operator, val1, val2));
                    }
                }
            }
        }
        while (!operatorsStack.isEmpty())
        {
            Character operator = operatorsStack.pop();
            Integer val2 = valuesStack.pop();
            Integer val1 = valuesStack.pop();
            valuesStack.push(computeOperation(operator, val1, val2));
        }
        Integer value = valuesStack.pop();
        assert valuesStack.isEmpty();
        assert operatorsStack.isEmpty();
        return value;        
    }
}
