<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create project</title>
</head>
<body>
<table>

<#list kommentti as k>
 <#if k.elokuvaID == elokuva.elokuvaID>

<tr><td>${k.kommenttiTeksti}</td>
</#if>

</#list>


</body>
</html>