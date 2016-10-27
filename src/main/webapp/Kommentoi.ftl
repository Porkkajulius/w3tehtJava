<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create project</title>
</head>
<body>
      <form name="kommentti" action="uusi" method="POST">
    <br><br>
<table>
<tr>
 
<input type="hidden" name="kommenttiID" value="${kommentti.kommenttiID}"/>
<input type="hidden" name="elokuvaID" value="${kommentti.elokuvaID}"/>
<td>Kommentti elokuvasta: <input type="text" name="kommenttiTeksti"/></td>
</tr>
<tr>
    	
    	<td><input type="submit"/></td>
    	</tr>
    	</table>
    </form>
</body>
</html>