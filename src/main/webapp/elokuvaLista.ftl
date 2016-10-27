<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create project</title>
</head>
<body>
<table>
<tr><td>Elokuvan nimi</tr></td>

<#list elokuvat as e>

<tr><td>${e.nimi}</td>
<td><a href ="/elokuva/kommentit/${e.elokuvaID}"><button type="button">Lue kommentit</button></a></td>
<td><a href ="/elokuva/kommentoi/${e.elokuvaID}"><button type="button">Kirjoita kommentti</button></a></td>

 </tr>

</#list>
<table>
<br>
<br>
<br>
<br>
<td><a href ="/elokuva/uusi"><button type="button">Lisää uusi elokuva</button></a></td>
</body>
</html>