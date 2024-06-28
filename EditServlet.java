package com.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/editScreen")
public class EditServlet extends HttpServlet{
	private static final String query ="select BOOKNAME,BOOKEDITION,BOOKPRICE from bookdata where id=?";
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
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			pw.println("<form action='editurl?id="+id+"' method='post'>");
			pw.println("<table align='center'>");
			pw.println("<tr>");
			pw.println("<td>Book Name</td>");
			pw.println("<td><input type='text' name='bookName' value='"+rs.getString(1)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Book Edition</td>");
			pw.println("<td><input type='text' name='bookEdition' value='"+rs.getString(2)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Book Price</td>");
			pw.println("<td><input type='text' name='bookPrice' value='"+rs.getString(3)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td><input type='submit' value='Edit'></td>");
			pw.println("<td><input type='reset' value='Cancle'></td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</form");
			
		
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
		
	}
	   @Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req,resp);
			
		}
}
