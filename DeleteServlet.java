package com.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet{
	private static final String query ="delete from bookdata where id=?";
	   @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get 
		PrintWriter pw=resp.getWriter();
		//set
		resp.setContentType("text/html");
		//get id of record
		int id=Integer.parseInt(req.getParameter("id"));
		
		
		//load jdbc driver
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//generate connection 
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///book","root","Shubham@123");
				PreparedStatement ps=con.prepareStatement(query);
				){
			ps.setInt(1, id);
			 int count=ps.executeUpdate();
			  if(count==1) {
				  pw.println("<h2>Record is Deleted Successfully</h2>");
			  }
			  else {
				  pw.println("<h2>Record is not Deleted Successfully</h2>");
			  }
		}
		catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h1>"+se.getMessage()+"</h1>");
		}
		catch(Exception ee) {
			ee.printStackTrace();
			pw.println("<h1>"+ee.getMessage()+"</h1>");
		}
		pw.println("<a href='home.html'>Home</a>");
		pw.println("<br>");
		pw.println("<a href='booklist'>Book List</a>");
	}
	   @Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req,resp);
			
		}
}
