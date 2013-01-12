package com.droff.scalaskel;

import java.util.HashSet;

import junit.framework.TestCase;

public class ChangeServiceTest extends TestCase
{

    public void test1to100Changes()
    {
        for (int i=0; i<=100; i++)
            testMakeChange(i);       
    }

    private void testMakeChange(int total)
    {
        ChangeService changeService = new ChangeService();
        HashSet<Change> changesSet = changeService.makeChange(total);
        System.out.println(changesSet.size() + " different changes for "+ total);
        for (Change change : changesSet)
        {
            assertTrue(change.getTotalValue()==total);
            System.out.println(change.toString());
        }
    }

}