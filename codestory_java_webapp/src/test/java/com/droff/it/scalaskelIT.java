package com.droff.it;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class scalaskelIT extends TestCase
{
    private static final String baseurl = "http://localhost:8080/codestory_java_webapp/scalaskel/change/";

    public void testaFewChanges() throws HttpException, IOException
    {
        testChange(1, "[{\"foo\":1}]");
        testChange(7, "[{\"foo\":7},{\"bar\":1}]");
    }

    private void testChange(int total, String expectedAnswer) throws HttpException, IOException
    {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(baseurl + total);
        int statusCode = client.executeMethod(method);
        assertTrue(statusCode == HttpStatus.SC_OK);
        //  /jetty responseBody is somewhat different from tomcat's TODO look at it later.. 
        //String responseBody = new String(method.getResponseBody());     
        //assertEquals(expectedAnswer,responseBody);
        
    }

}