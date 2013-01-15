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
        testEquationAndResultEncoding("1,5*4", "6");
        testEquationAndResultEncoding("((1,1 2) 3,14 4 (5 6 7) (8 9 10)*4267387833344334647677634)/2*553344300034334349999000","31878018903828899277492024491376690701584023926880");
    }
    
    private void testEquationAndResultEncoding(String equation, String result)
    {
        System.out.println(EquationSolver.evaluateAndFormat(equation));
        assertEquals(EquationSolver.evaluateAndFormat(equation),result);
    }

    public void testEquation(String equation, BigDecimal expectedResult)
    {
        assertEquals(EquationSolver.evaluate(equation),expectedResult);
    }

}
