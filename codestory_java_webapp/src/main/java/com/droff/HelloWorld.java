package com.droff;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorld extends HttpServlet
{
    private static final long serialVersionUID = -4144852010453136192L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintWriter out = res.getWriter();
        if (null != req.getParameter("q"))
        {
            String question = req.getParameter("q").toString();
            if (question.equals("Quelle est ton adresse email"))
                out.println("francois.le.droff" + "@" + "gmail.com");
        }
        out.close();
    }
}
