package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;

import java.util.*;

import java.net.URL;

public class WebController extends HttpServlet
{
	public class Man {
	int dnaCode;

   public boolean equals(Man man) {
       return this.dnaCode ==  man.dnaCode;
	}}
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {    
    PrintWriter printWriter = null;
	String view = "login";
    try	
	{			
        printWriter = response.getWriter();
	}
	catch (Exception ex)
	{		
	}
	
	
	try
	{


int count;
	String dbHost = "db4free.net";
	String dbPort = "3306";
	String dbUser = "root";
	String dbPass = "[fPak89!XAMs<Ma+";
	String dbName = "login";
	Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	try (Connection conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/kihiki?serverTimezone=Europe/Moscow&useSSL=false", "pkihiki", "12345678")){
    // работа с базой данных
	 count = 0;
	Statement statement = conn.createStatement();
	ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
	while(resultSet.next()){
		count++;
	}
	String[][] T = new String [count][5];
	 count = 0;
	   resultSet = statement.executeQuery("SELECT * FROM users");
                while(resultSet.next()){
                      
                    //int id = resultSet.getInt(1);
                    //String user = resultSet.getString(2);
                    //String password = resultSet.getString(3);
					//int kol = resultSet.getInt(4);
					//String email = resultSet.getString(5);
                    //T[count][0] =(id, user, password,kol,email); 
					T[count][0] =resultSet.getString(1);
					T[count][1] =resultSet.getString(2);
					T[count][2] =resultSet.getString(3);
					T[count][3] =resultSet.getString(4);
					T[count][4] =resultSet.getString(5);
					count++;
                }  
				HttpSession session = request.getSession(true);	
				String rlogout= "/2/out";
				 URL url = new URL(request.getRequestURL().toString());
				 String referrer = url.getPath().toString();
				 if(referrer.equals(rlogout)){
					 Object out =null;
					 session.setAttribute("id_user", out);           
					 request.setAttribute("id_user", out);
				 }
				 Object iduser = session.getAttribute("id_user");
				 int id_user = 0;
				if(iduser==null) {
					 url = new URL(request.getRequestURL().toString());
					 referrer = url.getPath().toString();
					 session.setAttribute("referrer", referrer);           
					 request.setAttribute("referrer", referrer);
					 String rlogin= "/2/login";
					 String rreg= "/2/registration";
					 if(referrer.equals(rlogin)){
		String user = (String) request.getParameter("user");
		String pass = (String) request.getParameter("pass");
		for (int i = 0; i < T.length; i++)
		{
			if(T[i][1].equals(user) && T[i][2].equals(pass)){
				id_user = Integer.parseInt(T[i][0].trim());
				session.setAttribute("id_user", id_user);           
				request.setAttribute("id_user", id_user);
			}
		}
				}
				if(referrer.equals(rreg)){
					String R_user = (String) request.getParameter("R_user");
					String R_pass = (String) request.getParameter("R_pass");
					String R_email = (String) request.getParameter("R_email");
					if(R_user!=null && R_pass!=null){
				String sql = "INSERT INTO users (id, user, password, kol, email) VALUES (NULL, ?, ?, ?, ?);";
	 try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
		 int zagl=1;
					preparedStatement.setString(1, R_user);
                    preparedStatement.setString(2, R_pass);
                    preparedStatement.setInt(3, zagl);
					preparedStatement.setString(4, R_email);
					preparedStatement.execute();
	 }
	            session.setAttribute("id_user", count+1);           
				request.setAttribute("id_user", count+1);
				}
				}}

session = request.getSession(true);
iduser = session.getAttribute("id_user");

				if(iduser!=null){
		
		id_user=((Integer)iduser).intValue();
					view = "main";
					
				count = 0;
				String sql ="SELECT * FROM tab WHERE id_user = ?";
				try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
				preparedStatement.setInt(1, id_user);
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()){count++;}
				}
				 
				 
	 printWriter.println("Connection to ProductDB succesfull!");
	   url = new URL(request.getRequestURL().toString());
 referrer = url.getPath().toString();
 String ag =url.getQueryString().toString();
String radd= "/2/add";
String rradd= "action=saveData";
String rdel= "/2/del";
if(referrer.equals(radd))
{
	if(ag.equals(rradd)){
		//echo "I get param1 = ".$_POST['param1']." and param2 = ".$_POST['param2'];
	}
	else{
	String text1 = (String) request.getParameter("text1");
	String text2 = (String) request.getParameter("text2");
	String text3 = (String) request.getParameter("text3");
	 sql = "INSERT INTO tab (id_user, tab1, tab2, tab3, s, id) VALUES (?, ?, ?, ?, ?, NULL);";
	 try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id_user);
					preparedStatement.setString(2, text1);
                    preparedStatement.setString(3, text2);
                    preparedStatement.setString(4, text3);
					preparedStatement.setInt(5, count+1);
					preparedStatement.execute();
	 }
	}
}
if(referrer.equals(rdel))
{
	String ndelS = (String) request.getParameter("ndel");
	int ndel = Integer.parseInt(ndelS);
	if (ndelS != null && ndel<=count){
	 sql = "DELETE FROM tab WHERE s = ?  AND id_user = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, ndel);
					preparedStatement.setInt(2, id_user);
					preparedStatement.execute();

				}
	}
}
count = 0;
sql ="SELECT * FROM tab WHERE id_user = ?";
				try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
				preparedStatement.setInt(1, id_user);
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()){count++;}
				}
				String[][] Tabs = new String [count][3];
				 count = 0;
				 sql ="SELECT * FROM tab WHERE id_user = ?";
			try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
				preparedStatement.setInt(1, id_user);
				resultSet = preparedStatement.executeQuery();
				
				
                while(resultSet.next()){
                      
                    //int id = resultSet.getInt(1);
                    //String user = resultSet.getString(2);
                    //String password = resultSet.getString(3);
					//int kol = resultSet.getInt(4);
					//String email = resultSet.getString(5);
                    //T[count][0] =(id, user, password,kol,email); 
					Tabs[count][0] =resultSet.getString(2);
					Tabs[count][1] =resultSet.getString(3);
					Tabs[count][2] =resultSet.getString(4);

					count++;
                } 
			}
//HttpSession session = request.getSession(true);	
session.setAttribute("Tabs", Tabs);           
request.setAttribute("Tabs", Tabs);

session.setAttribute("referrer", referrer);           
request.setAttribute("referrer", referrer);
	}}
//HttpSession session = request.getSession(true);	
//session.setAttribute("T", T);           
//request.setAttribute("T", T);
	
	
	
	}
catch(Exception ex){
            printWriter.println("Connection failed...");
            printWriter.println(ex);
        }
	
	
	
    try
    {
		int[][] arr = new int[10][];

		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = new int[3];
			for (int j = 0; j < 3; j++)
			{
				arr[i][j] = i;
			}
		}

		
	   HttpSession session = request.getSession(true);	   
	   Object nn = session.getAttribute("n");
	   int n = 0;
       if (nn!=null)
	   {
		   n = ((Integer)nn).intValue();
	   }		   
	   n=n+1;	   	   	   	   
	   session.setAttribute("arr", arr);           
	   
	   
	   
	   request.setAttribute("arr", arr);     
	   
	   //String view = "main";
	   //String view = "login";
	   request.getRequestDispatcher("/WEB-INF/views/"+view+".jsp").forward(request,response);  	   
	  
    }    
    catch (Exception ex)
    {       
       printWriter.println("Error: "+ex.getMessage());     
    }
  }
}