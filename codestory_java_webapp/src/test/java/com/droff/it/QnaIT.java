package com.droff.it;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class QnaIT extends TestCase
{
    private static final String baseurl = "http://localhost:8080/codestory_java_webapp/";
    //private static final String baseurl = "http://codestory.thatsocliche.cloudbees.net/";
 
    public void testSimpleAnswersFromSimpleQuestions() throws HttpException, IOException
    {
        testSimpleAnswerFromSimpleGetQuestion("Quelle est ton adresse email", "francois.le.droff@gmail.com");
        testSimpleAnswerFromSimpleGetQuestion("Es tu abonne a la mailing list(OUI/NON)", "OUI");    
        testSimpleAnswerFromSimpleGetQuestion("Es tu heureux de participer(OUI/NON)", "OUI");   
        testSimpleAnswerFromSimpleGetQuestion("Es tu pret a recevoir une enonce au format markdown par http post(OUI/NON)", "OUI");   
        testSimpleAnswerFromSimpleGetQuestion("Est ce que tu reponds toujours oui(OUI/NON)", "NON");   
        testSimpleAnswerFromSimpleGetQuestion("As tu bien recu le premier enonce(OUI/NON)", "OUI");   
        testSimpleAnswerFromSimpleGetQuestion("1 1", "2.0");
        testSimpleAnswerFromSimpleGetQuestion("(1+2)/2", "1.5");
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
    
    public void testCanIPost() throws HttpException, IOException
    {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(baseurl);         
        StringRequestEntity requestEntity = new StringRequestEntity("testing a post", "plain/text", null);   
        method.setRequestEntity(requestEntity);        
        int statusCode = client.executeMethod(method);
        assertTrue(statusCode == HttpStatus.SC_CREATED);
    }
}
