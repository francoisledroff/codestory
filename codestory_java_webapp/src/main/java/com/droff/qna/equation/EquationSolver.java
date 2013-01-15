package com.droff.qna.equation;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Dijkstra's two-stack algorithm implementation doing Equation just like my HP: using "Reverse Polish" alike values and operators stacks
 */
public class EquationSolver
{

    static final Character OPENING_PARANTHESIS = '(';
    static final Character CLOSING_PARANTHESIS = ')';
    static final Character PLUS = '+';
    static final Character MINUS = '-';
    static final Character DIVIDE = '/';
    static final Character TIMES = '*';
    static Map<Character, Integer> OPERATORS_PRECEDENCES;
    static
    {
        Map<Character, Integer> operators_precendences = new TreeMap<Character, Integer>();
        operators_precendences.put(OPENING_PARANTHESIS, 0);
        operators_precendences.put(CLOSING_PARANTHESIS, 0);
        operators_precendences.put(PLUS, 1);
        operators_precendences.put(MINUS, 1);
        operators_precendences.put(TIMES, 2);
        operators_precendences.put(DIVIDE, 2);
        OPERATORS_PRECEDENCES = Collections.unmodifiableMap(operators_precendences);
    }

    private static void computeOperation(Stack<BigDecimal> valuesStack, char operator)
    {
        BigDecimal val2 = valuesStack.pop();
        BigDecimal val1 = valuesStack.pop();
        valuesStack.push(computeOperation(operator, val1, val2));
    }

    private static BigDecimal computeOperation(char operator, BigDecimal operand1, BigDecimal operand2)
    {
        if (operator == PLUS)
            return operand1.add(operand2);
        if (operator == MINUS)
            return operand1.min(operand2);
        if (operator == DIVIDE)
            return operand1.divide(operand2);
        if (operator == TIMES)
            return operand1.multiply(operand2);
        else
            throw new RuntimeException("Invalid operator " + operator);
    }

    private static List<String> getTokens(String equation)
    {
        List<String> tokens = new ArrayList<String>();
        String currentTokenReader = "";
        for (int i = 0; i < equation.length(); i++)
        {
            char currentChar = equation.charAt(i);
            if (OPERATORS_PRECEDENCES.containsKey(currentChar))
            {
                if (currentTokenReader.length() > 0)
                {
                    addANumber(tokens, currentTokenReader);
                    currentTokenReader = ""; // reset the reader
                }
                tokens.add("" + currentChar); // this char is an operator
            }
            else
            {
                currentTokenReader += currentChar;
            }
        }
        if (currentTokenReader.length() > 0)
        {
            addANumber(tokens, currentTokenReader); // last token was hopefully a value
        }
        return tokens;
    }

    private static void addANumber(List<String> tokens, String number)
    {
        if (tokens.size() >= 1
                && tokens.get(tokens.size() - 1).charAt(0)==MINUS.charValue()
                && (tokens.size() == 1 || (tokens.get(tokens.size() - 2).length() == 1 && OPERATORS_PRECEDENCES
                        .containsKey(tokens.get(tokens.size() - 2).charAt(0)))))
            tokens.set(tokens.size() - 1, "-" + number); // last token was a negative value
        else                        
            tokens.add(number); // last token was a positive value
    }
    
    public static String evaluateAndFormat(String urlFormatedEquation)
    {
        String equation = urlFormatedEquation.replace(' ', '+').replace(',', '.');
        BigDecimal result = evaluate(equation);
        DecimalFormat df = new DecimalFormat(
                "0.################################################################################################################"); // removing
                                                                                                                                       // trailing
                                                                                                                                       // zeros
        String formatted = df.format(result.stripTrailingZeros()).replace('.', ','); // is it a french bot ?
        return formatted;
    }

    static BigDecimal evaluate(String equation)
    {
        Stack<Character> operatorsStack = new Stack<Character>();
        Stack<BigDecimal> valuesStack = new Stack<BigDecimal>();
        Iterator<String> tokensIterator = getTokens(equation).iterator();
        while (tokensIterator.hasNext())
        {
            String token = tokensIterator.next();
            if (token.length() != 1 || !OPERATORS_PRECEDENCES.containsKey(token.charAt(0)))
            {
                valuesStack.push(new BigDecimal(token)); // the token is a value
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
                    else if (valuesStack.size() >= 1)
                    {
                        computeOperation(valuesStack, operator);
                    }
                }
            }
        }
        while (!operatorsStack.isEmpty())
        {
            computeOperation(valuesStack, operatorsStack.pop());
        }
        BigDecimal value = valuesStack.pop();
        assert valuesStack.isEmpty();
        assert operatorsStack.isEmpty();
        return value;
    }

}
