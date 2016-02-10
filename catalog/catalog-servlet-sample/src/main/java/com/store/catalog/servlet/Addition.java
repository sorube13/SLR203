package com.store.catalog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Addition
 */
public class Addition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		try{
			int one = Integer.parseInt(request.getParameter("param1"));
	        int two = Integer.parseInt(request.getParameter("param2"));
	        int result = one + two;
	        writer.println(one + " + " + two + " = " + result);
		}catch(Exception e){
			writer.println("There is an error in th URL.\nPlease try again using the following syntax for the URL:\nURL: http://localhost:8080/sample/add?param1=XXXX&param2=YYYY");
		}
       
	}
	//curl -X GET 'http://localhost:8080/sample/add?param1=53&param2=2'

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		try{
			String opp = request.getReader().readLine();
			opp = opp.replaceAll("\\+", " +");
			String[] numbers = opp.split(" +");
			int result = Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);
			writer.println("The result is: " +result);
		}catch(Exception e){
			writer.println("There is an error in the request\nThe body of your post must contain the following:\n'XXXX + YYYY'");
		}
	}
	//curl -X POST 'http://localhost:8080/sample/add' -d '150+58950'

}
