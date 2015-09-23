<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<link rel="stylesheet" typ="text/css" href="stylesheet.css"/>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADB Search</title>
</head>
<body>

<div id="logo">
<img class="image" src="http://www.bredband2.com/sites/www/files/logov2.png">
</div>

<div id="search">
<h3>Sök efter adresser i databasen:</h3>


<form method="post" action="Search">
     
Gata:     <input class="formdata"  type="text" name="street" id="street"><br>
Postnummer:  <input  class="formdata" type="text" name="zip" id="zip" maxlength="5" pattern="\d*"><br>
Postadress:     <input class="formdata"  type="text" name="city" id="city"><br>
              
        <br>
        <input  type="submit" name="submit" value="Sök">
     
    </form>
</div>











<div id="info">

		<p id="tutHeader"> Välkommen till sökgränsnittet för <br>Bredband2's adressdatabas! </p>
		<p id="tutText"> Ange adress och/eller postnummer i fälten till vänster och klicka på sök.<br>
		Får du ingen träff så kan du ange en mindre del av adressen för att undvika felstavningar eller variationer i formatering (storg. / storgatan). <br><br>
		</p>
		<p id="tutMail"><b>OBS!</b> Att man inte får någon träff på en potentiell kunds adress behöver inte <br>betyda att vi inte kan leverera, det kommer alltid finnas en felmarginal.</p>
		
		<p id="tutMail">Om du har frågor eller om du hittar buggar så kan du maila <a href="mailto:carper@bredband2.com">hit.</a></p>
		
	</div>
<div id="footer">

		<p>  </p>

	</div>


<div id="header">

		<p></p>

	</div>
	
<div id="result">


<table id="table" name="Adresses">
     

<c:forEach var="address" items="${addresses}">

<tr>
<td id="tableStreet" class="row">${address.street}</td>
<td id="tableZip" class="row">${address.zip}</td>
<td id="tableCity" class="row">${address.city}</td>
<td id="tableNet">${address.cityNet}</td>
</tr>


</c:forEach>
</table>
 

</div>


</body>
</html>
