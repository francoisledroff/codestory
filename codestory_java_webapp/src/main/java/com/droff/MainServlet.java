package com.droff;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;

public class MainServlet extends HttpServlet
{
    private static final long serialVersionUID = -4144852010453136192L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintWriter out = res.getWriter();
        if (null != req.getParameter("q"))
        {
            out.print(lookupTheAnswer(req.getParameter("q").toString()));
        }
        out.close();
    }

    public String lookupTheAnswer(String question) throws ServletException
    {
        try
        {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("simpleQNAs.json");
            List<QnA> simpleQnAs = Arrays.asList(GsonUtil.fromJson(IOUtils.toString(stream), QnA[].class));
            int indexOftheAnswer = simpleQnAs.indexOf(new QnA(question));
            if (indexOftheAnswer >= 0)
            {
                return simpleQnAs.get(indexOftheAnswer).getAnswer();
            }
            else
            {
                getServletContext().log("GET q=" + question);
                return "I took a note of this, please get back to me later";
            }
        }
        catch (IOException e)
        {
            throw new ServletException("failed to load Q&A");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        getServletContext().log("POST:" + IOUtils.toString(req.getInputStream()));
        res.setStatus(HttpStatus.SC_CREATED);
        PrintWriter out = res.getWriter();
        out.print("Thanks for posting");
        out.close();
    }

}
