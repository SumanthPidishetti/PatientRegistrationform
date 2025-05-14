package com.hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext;

public class AddPatient extends HttpServlet {
	public void service(HttpServletRequest request ,HttpServletResponse response)  throws IOException{
		PrintWriter out = response.getWriter();
		Connection con = null;
		PreparedStatement ptmt = null;
		response.setContentType("text/html");
		String s1 = request.getParameter("id");
		String s2 = request.getParameter("fullname");
		String s3 = request.getParameter("dateofbirth");
		String s4 = request.getParameter("gender");
		String s5 = request.getParameter("Age");
		String s6 = request.getParameter("Email");
		String s7 = request.getParameter("Address");
		String s8 = request.getParameter("MaritalStatus");
		String s9 = request.getParameter("Phone");
		String s10 = request.getParameter("EmergencyContact");
		String s11 = request.getParameter("Reasonforvisit");
		if(s1==null|s2==null|s3==null|s4==null|s5==null|s6==null|s7==null|s8==null|s9==null|s10==null|s11==null) {
			out.println("<h3>All details are required</h3>");
			return;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital","root","SUMANTH");
			ptmt =con.prepareStatement("INSERT INTO ADD_PATIENT(patient_id, fullname , DateofBirth ,gender , Age, email, address, Marital_Status, phone, Emergency_Contact, Reason_for_Visit) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ptmt.setInt(1, Integer.parseInt(s1));
			ptmt.setString(2, s2);
			ptmt.setString(3, s3);
			ptmt.setString(4, s4);
			ptmt.setString(5, s5);
			ptmt.setString(6, s6);
			ptmt.setString(7, s7);
			ptmt.setString(8, s8);
			ptmt.setString(9, s9);
			ptmt.setString(10, s10);
			ptmt.setString(11, s11);
			int rows = ptmt.executeUpdate();
			if(rows > 0) {
				out.println("record inserted");
			}
			else 
				out.println("failed to insert");
		
		out.println("<a href=\"/Hospital/add-patient.html\">Back to Login </a>");
	}
	catch(Exception e) {
		out.println("<h3>Error:" + e.getMessage() + "</h3>");
	}finally{
		try {
			if (ptmt != null)
				ptmt.close();

		} catch (Exception e) {

		}
		try {
			if (con != null)
				con.close();

		} catch (Exception e) {

		}
	}
}
}
