package com.droff.qna;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.droff.GsonUtil;

public class QnAService
{
    public static final String UNKNWON = "I took a note of this, please get back to me later";
    public String answer(String question) throws IOException 
    {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("simpleQNAs.json");
            List<QnA> simpleQnAs = Arrays.asList(GsonUtil.fromJson(IOUtils.toString(stream), QnA[].class));
            int indexOftheAnswer = simpleQnAs.indexOf(new QnA(question));
            if (indexOftheAnswer >= 0)
            {
                return simpleQnAs.get(indexOftheAnswer).getAnswer();
            }
            else
                return UNKNWON; 
    }
}
