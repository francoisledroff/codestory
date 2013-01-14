package com.droff.qna.equation;

import java.math.BigDecimal;

import junit.framework.TestCase;

public class EquationSolverTest extends TestCase
{

    public void testEquation()
    {
        testEquation("1+1", new BigDecimal(2));
        testEquation("(1+1)", new BigDecimal(2));
        testEquation("1+1+(1+2)*2", new BigDecimal(8));
        testEquation("(1+2)/2", new BigDecimal(1.5));
    }
    
    public void testEquationAndFormat()
    {
        testEquationAndResultEncoding("1 1",  "2");
        testEquationAndResultEncoding("(1 1)",  "2");
        testEquationAndResultEncoding("1 1 (1 2)*2", "8");
        testEquationAndResultEncoding("(1 2)/2", "1,5");
    }
    
    private void testEquationAndResultEncoding(String equation, String result)
    {
        assertEquals(EquationSolver.evaluateAndFormat(equation),result);
    }

    public void testEquation(String equation, BigDecimal expectedResult)
    {
        assertEquals(EquationSolver.evaluate(equation).doubleValue(),expectedResult.doubleValue());
    }

}
