package com.droff.qna.equation;

import junit.framework.TestCase;

public class EquationSolverTest extends TestCase
{

    public void testEquation()
    {
        testEquation("1 1", new Integer(2));
        testEquation("(1 1)", new Integer(2));
        testEquation("1 1 (1 2)*2", new Integer(8));
    }
    
    public void testEquation(String equation, Integer expectedResult)
    {
        assertEquals(EquationSolver.evaluate(equation),expectedResult);
    }

}
