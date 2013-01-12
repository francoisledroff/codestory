package com.droff;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;

import com.droff.qna.QnAService;
import com.droff.scalaskel.ChangeService;

public class MainServlet extends HttpServlet
{
    private static final long serialVersionUID = -4144852010453136192L;

    private static final String SCALASKEL_CHANGE = "/scalaskel/change/";

    private ChangeService changeService = new ChangeService();
    private QnAService qnaService = new QnAService();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintWriter out = res.getWriter();
        String path = req.getPathInfo(); // jetty case for integration test
        if (path == null) path = req.getServletPath(); // tomcat on cloudbees infra and local test.
        
        if (path.startsWith(SCALASKEL_CHANGE))
        {
            int total = Integer.parseInt(path.substring(SCALASKEL_CHANGE.length()));
            out.print(GsonUtil.toJson(changeService.makeChange(total), false));
        }
        
        else if (null != req.getParameter("q"))
        {
            out.print(lookupTheAnswer(req.getParameter("q").toString()));
        }
        out.close();
    }

    public String lookupTheAnswer(String question) throws ServletException
    {
        try
        {
            String answer = qnaService.answer(question);
            if (answer.equals(QnAService.UNKNWON))
            {
                getServletContext().log("GET q=" + question);
            }
            return answer;
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
