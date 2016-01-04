<style>
table {
    border-collapse: collapse;
}

table, th, td {
    border: 1px solid black;
}

table {
    width: 100%;
}

th {
    height: 50px;
}

th {
    background-color: #4CAF50;
    color: white;
}

th, td {
    padding: 15px;
    text-align: left;
}

tr:nth-child(even) {background-color: #f2f2f2}
</style>
<html>
<head><title>List Files</title></head>
<body>
<div id="content">
  <table class="datatable">
    <tr>
        <th>Status</th>  <th>File Name</th>
    </tr>
    <#list files as file>
    <tr>
        <td>${file.isCorrect}</td> <td>${file.name}</td>
    </tr>
    </#list>
  </table>
  
  <a href="${folderPath}" class="link">Reporter</a>
 
</div>  
</body>
</html> 