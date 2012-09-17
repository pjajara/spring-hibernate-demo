<%@include file="taglib_includes.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="App.Title"></spring:message> </title>
<script type="text/javascript" src="js/contacts.js"></script>
</head>
<body style="font-family: Arial; font-size:smaller;">
	<center>
	<form action="searchCustomersFirstname.do" method="post">		
			<table style="border-collapse: collapse;" border="0" bordercolor="#006699" width="500">
			<tr>
				<td>Enter Customer FirstName</td> 
				<td><input type="text" name="name"/>
					&nbsp;&nbsp;<input type="submit" value="Search"/>
					&nbsp;&nbsp;<input type="button" value="New Customer" onclick="javascript:go('saveCustomer.do');"/>
			</td></tr>
		</table>
	</form>
	
	<table style="border-collapse: collapse;" border="1" bordercolor="#006699" width="500">
		<tr bgcolor="grey">
			<th>Id</th>
			<th>FirstName</th>
			<th>LastName</th>
			<th>Sex</th>
			<th>Age</th>			
			<th>Address</th>
			<th>Email</th>
			<th>Phone</th>
			<th>Company</th>
			<th>Salary</th>
		</tr>
		<c:if test="${empty SEARCH_CUSTOMERS_RESULTS_KEY}">
		<tr>
			<td colspan="4">No Results found</td>
		</tr>
		</c:if>
		<c:if test="${! empty SEARCH_CUSTOMERS_RESULTS_KEY}">
			<c:forEach var="customer" items="${SEARCH_CUSTOMERS_RESULTS_KEY}">		
		    <tr>
				<td><c:out value="${customer.id}"></c:out></td>
				<td><c:out value="${customer.firstName}"></c:out></td>
				<td><c:out value="${customer.lastName}"></c:out> </td>
				<td><c:out value="${customer.sex}"></c:out> </td>
				<td><c:out value="${customer.age}"></c:out> </td>
				<td><c:out value="${customer.address}"></c:out> </td>
				<td><c:out value="${customer.email}"></c:out> </td>
				<td><c:out value="${customer.phone}"></c:out> </td>
				<td><c:out value="${customer.company}"></c:out> </td>
				<td><c:out value="${customer.salary}"></c:out> </td>
				
				<td>
					&nbsp;<a href="updateCustomer.do?id=${customer.id}">Edit</a>
					&nbsp;&nbsp;<a href="javascript:deleteCustomer('deleteCustomer.do?id=${customer.id}');">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</c:if>				
	</table>	
	</center>
		
</body>
</html>