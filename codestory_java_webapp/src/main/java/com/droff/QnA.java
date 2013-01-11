package com.droff;

public class QnA
{
    private String question;
    
    public QnA(String question)
    {
        this.question = question;
    }
    
    public String getQuestion()
    {
        return question;
    }
    public void setQuestion(String question)
    {
        this.question = question;
    }
    public String getAnswer()
    {
        return answer;
    }
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
    private String answer;

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((question == null) ? 0 : question.hashCode());
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
        QnA other = (QnA) obj;
        if (question == null)
        {
            if (other.question != null)
                return false;
        }
        else if (!question.equals(other.question))
            return false;
        return true;
    }
    
    
}
