<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>First JSP</title>
</head>
   
<body>
		******************************Adding Employee**********************************
		<form action= "addEmployee">
		<input type="text" name="aid"><br>
		<input type="text" name="aname"><br>
		<input type="submit"><br>
		</form>
		********************************Get Specific Record  By Name , Greater Than . Sorted********************************
		<form action= "getEmployee">
		<input type="text" name="aid"><br>
		<input type="submit"><br>
		</form>
		****************************** Get All Records  by Path Variables**********************************
		<form action= "Employees">
		<input type="submit"><br>
		</form>
	

</body>
</html>