package com.droff.scalaskel;

import java.util.HashSet;

public class ChangeService
{
    public static int FOO_VALUE = 1;
    public static int BAR_VALUE = 7;
    public static int QIX_VALUE = 11;
    public static int BAZ_VALUE = 21;
    
    public HashSet<Change> makeChange(int total)
    {
        HashSet<Change> changesSet= new HashSet<Change>();
        for (int nofBaz = 0; nofBaz <= total / Change.BAZ_VALUE; nofBaz++)
        {
            int total_less_baz = total - nofBaz * Change.BAZ_VALUE;
            for (int nofQix = 0; nofQix <= total_less_baz / Change.QIX_VALUE; nofQix++)
            {
                int total_less_baz_n_quix = total_less_baz - nofQix * Change.QIX_VALUE;
                for (int nofBar = 0; nofBar <= total_less_baz_n_quix / Change.BAR_VALUE; nofBar++)
                {
                    int total_less_baz_n_quix_n_bar = total_less_baz_n_quix - nofBar * Change.BAR_VALUE;
                    int nofFoo = 0;
                    while (nofFoo < total_less_baz_n_quix_n_bar / Change.FOO_VALUE)
                    {
                        nofFoo++;                       
                    }
                    changesSet.add(new Change(nofFoo,nofBar,nofQix,nofBaz));
                }
            }
        }
        return changesSet;
    }
}
