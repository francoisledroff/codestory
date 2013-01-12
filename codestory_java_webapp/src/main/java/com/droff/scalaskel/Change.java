package com.droff.scalaskel;

public class Change
{
    public static int FOO_VALUE = 1;  
    public static int BAR_VALUE = 7;
    public static int QIX_VALUE = 11;
    public static int BAZ_VALUE = 21;

    private Integer foo;
    private Integer bar;
    private Integer qix;
    private Integer baz;
    
    public Change(int foo, int bar, int qix, int baz)
    {
        this.foo = (foo>0)? new Integer(foo) : null;
        this.bar = (bar>0)? new Integer(bar) : null;
        this.qix = (qix>0)? new Integer(qix) : null;
        this.baz = (baz>0)? new Integer(baz) : null;
    }

    public int getTotalValue()
    {
        int fooValue = (this.foo!=null)? this.foo.intValue() * FOO_VALUE : 0;
        int barValue = (this.bar!=null)? this.bar.intValue() * BAR_VALUE : 0;
        int qixValue = (this.qix!=null)? this.qix.intValue() * QIX_VALUE : 0;
        int bazValue = (this.baz!=null)? this.baz.intValue() * BAZ_VALUE : 0;
        return fooValue + barValue + qixValue + bazValue;
    }
    
    public Integer getFoo()
    {
        return foo;
    }

    public void setFoo(Integer foo)
    {
        this.foo = foo;
    }

    public Integer getBar()
    {
        return bar;
    }

    public void setBar(Integer bar)
    {
        this.bar = bar;
    }

    public Integer getQix()
    {
        return qix;
    }

    public void setQix(Integer qix)
    {
        this.qix = qix;
    }

    public Integer getBaz()
    {
        return baz;
    }

    public void setBaz(Integer baz)
    {
        this.baz = baz;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bar == null) ? 0 : bar.hashCode());
        result = prime * result + ((baz == null) ? 0 : baz.hashCode());
        result = prime * result + ((foo == null) ? 0 : foo.hashCode());
        result = prime * result + ((qix == null) ? 0 : qix.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Change other = (Change) obj;
        if (bar == null)
        {
            if (other.bar != null)
                return false;
        }
        else if (!bar.equals(other.bar))
            return false;
        if (baz == null)
        {
            if (other.baz != null)
                return false;
        }
        else if (!baz.equals(other.baz))
            return false;
        if (foo == null)
        {
            if (other.foo != null)
                return false;
        }
        else if (!foo.equals(other.foo))
            return false;
        if (qix == null)
        {
            if (other.qix != null)
                return false;
        }
        else if (!qix.equals(other.qix))
            return false;
        return true;
    }
    @Override
    public String toString()
    {
        return "Change [foo=" + foo + ", bar=" + bar + ", qix=" + qix + ", baz=" + baz + "]";
    }

}
