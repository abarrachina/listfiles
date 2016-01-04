<html>
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
		 
		</div>  
	</body>
</html> 