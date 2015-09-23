<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" typ="text/css" href="stylesheet.css"/>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADB parser</title>
</head>
<body>

<div id="logo">
<img class="image" src="http://www.bredband2.com/sites/www/files/logov2.png">
</div>

<div id="read">
<h3>Läs in adresser i databasen:</h3>
Välj ett Stadsnät: 




<form action="Servlet" method="post" enctype="multipart/form-data">
    
    <select name="cityNets">
    <c:forEach var="item" items="${cityNets}">
        <option value="${item.id}">${item.name}</option>
    </c:forEach>
       
    </select>
    <br /><br />
    <input type="radio" name="formatAdd" value="1" checked>Format A
	<input type="radio" name="formatAdd" value="2" >Format B
	<input type="radio" name="formatAdd" value="3" >Format C
	<br /><br />
    
    <input type="file" id="send"  name="file" size="50" />
    <br /> <br />
    <input type="submit" id="send" value="Läs in!" />

    

    
</form>
</div>



<div id="delete">
<h3>Ta bort adresser ur databasen:</h3>
<form action="Servlet" method="post" enctype="multipart/form-data">
 <div id="removeInfo">	
 	<p> Om du är osäker på hur adresserna du skall ta bort är formaterade så kan du göra en sökning  <a href="search.jsp">här.</a></p> </p>
 </div>	
 	<br />
    <input type="radio" name="formatRemove" value="1" checked>Format A
	<input type="radio" name="formatRemove" value="2" >Format B
	<input type="radio" name="formatRemove" value="3" >Format C
	<br /><br />
 
 <input type="file" id="send"  name="file2" size="50" />
	<br /><br />
    <input type="submit" id="send" value="Ta bort!" />

</form>

</div>



<div id="info">

		<p id="tutHeader"> Välkommen till administrationsgränsnittet för <br>Bredband2's adressdatabas! </p>
		<p id="tutText"> Det är viktigt att filen du läser in är av formatet Excel-97 (xls) och att den inte innehåller någon extra data. Kolumn A skall 
		innehålla gatuadress med gatunummer, kolumn B postnummer och kolumn C postort.  
		Om filen du matar in är felformaterad kommer du få ett meddelande om att 0 rader påverkats. <br>
		<br>
		För att ta bort adresser använder du en fil med samma format som den du matar in adresser med. 
		De adresser som finns med i filen kommer raderas från databasen.<br> <br>
		För att uppdatera redan inlästa adresser med ny stadsnätstillhörighet så läser du bara in listan som om det var nya adresser.
		</p>
	</div>
	
	<div id="format">

		<p id="tutHeader"> De olika formaten: </p>
		<p id="tutText"> <b>Format A:</b> Hela gatuadressen skall finnas i kolumn A. <br> 
		<b>Exempel:</b> Södra Tullgatan 3a | 21140 | Malmö
		<br><br>
		<p id="tutText"> <b>Format B:</b> Gatan skall finnas i kolumn A. Gatunummer och port i kolumn B. <br> 
		<b>Exempel:</b> Södra Tullgatan | 3a | 21140 | Malmö
		<br><br>
			<p id="tutText"> <b>Format C:</b> Gatan skall finnas i kolumn A. Gatunummer i kolumn B och port i kolumn C. <br> 
		<b>Exempel:</b> Södra Tullgatan | 3 | a | 21140 | Malmö
		<br><br>

		</p>
	</div>


<div id="footer">

		<p>  </p>

	</div>


<div id="header">

		<p></p>

	</div>



</body>
</html>
