package com.droff.integrationtest;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

public class SimpleQnAsTest extends TestCase
{
    //private static final String baseurl = "http://localhost:8080/codestory_java_webapp/";
    private static final String baseurl = "http://codestory.thatsocliche.cloudbees.net/";

 
    public void testSimpleAnswersFromSimpleQuestions() throws HttpException, IOException
    {
        testSimpleAnswerFromSimpleGetQuestion("Quelle est ton adresse email", "francois.le.droff@gmail.com");
        testSimpleAnswerFromSimpleGetQuestion("Es tu abonne a la mailing list(OUI/NON)", "OUI");    
        testSimpleAnswerFromSimpleGetQuestion("Es tu heureux de participer(OUI/NON)", "OUI");    
    }
   
    private void testSimpleAnswerFromSimpleGetQuestion(String question, String expectedAnswer) throws HttpException, IOException
    {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(baseurl);
        NameValuePair[] params = new NameValuePair[1];
        params[0] = new NameValuePair("q", question);
        method.setQueryString(params);
        int statusCode = client.executeMethod(method);
        assertTrue(statusCode == HttpStatus.SC_OK);
        byte[] responseBody = method.getResponseBody();
        assertEquals(expectedAnswer, new String(responseBody));

    }
}
