<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import= "oracle.jdbc.*"%>
<%@ page import= "oracle.jdbc.pool.OracleDataSource"%>
<%@ page import= "java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
		OracleDataSource ods;
		HashMap<String,String> admin = new HashMap<String,String>();
		ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:dst07/dst07@192.168.10.11:1521:DB19"); 
		Connection conn = ods.getConnection();
		conn.setAutoCommit(false);
		String sql = "SELECT userid, passwd FROM login";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			admin.put(rs.getString("userid"),rs.getString("passwd"));
		}
		String getId = request.getParameter("id");
		String getPw = request.getParameter("pass");
		pstmt.close();
		
		if(admin.containsKey(getId)&&admin.containsValue(getPw)){
			request.setAttribute("id",getId);
			request.getRequestDispatcher("main.jsp").forward(request,response);
		} else {
			//로그인 실패시 화면 리셋
			request.setAttribute("fail","f");
			request.getRequestDispatcher("loginmain.jsp").forward(request,response);
		}
%>
