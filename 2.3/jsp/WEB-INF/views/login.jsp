<%@ page language="java" contentType="text/html; charset=Windows-1251" pageEncoding="Windows-1251" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=Windows-1251">
  <title>login page</title>
 </head>

 <body>
 
<%
      String referrer = (String)(request.getAttribute("referrer"));  
%>
<%=referrer%>
  <form method="post" action="login">       
     <input type="submit" value="login"></td>
	 <input type="text" name="user"> 
	 <input type="password" name="pass">
  </form>
   <form method="post" action="registration">       
     <input type="submit" value="registration"></td>
	 <input type="text" name="R_user"> 
	 <input type="password" name="R_pass">
	 <input type="text" name="R_email"> 
  </form>
 </body>

</html>