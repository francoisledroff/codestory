package com.droff.qna.equation;

import junit.framework.TestCase;

public class EquationSolverTest extends TestCase
{

    public void testEquation()
    {
        testEquation("1 1", new Double(2));
        testEquation("(1 1)", new Double(2));
        testEquation("1 1 (1 2)*2", new Double(8));
        testEquation("(1 2)/2", new Double(1.5));
    }
    
    public void testEquation(String equation, Double expectedResult)
    {
        assertEquals(EquationSolver.evaluate(equation),expectedResult);
    }

}
