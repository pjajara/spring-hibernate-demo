<%@include file="taglib_includes.jsp" %>

<html>
<head>
<script type="text/javascript" src="js/customers.js"></script>
<title><spring:message code="App.Title"></spring:message></title>
</head>
<body style="font-family: Arial; font-size: smaller;">

	<table bgcolor="grey" width="750" height="500" align="center"
		style="border-collapse: collapse;" border="1" bordercolor="#006699">
		<tr>
			<td align="center"><h3>Edit Customer Form</h3></td>
		</tr>
		<tr valign="top" align="center">
			<td align="center"><form:form action="saveCustomer.do"
					method="post" commandName="newCustomer">

					<table width="500" style="border-collapse: collapse;" border="0"
						bordercolor="#006699" cellspacing="2" cellpadding="2">
						<tr>
							<td width="100" align="right">FirstName</td>
							<td width="150"><form:input path="firstName" /></td>
							<td align="left"><form:errors path="firstName"
									cssStyle="color:red"></form:errors></td>
						</tr>
						
						<tr>
							<td width="100" align="right">LastName</td>
							<td width="150"><form:input path="lastName" /></td>
							<td align="left"><form:errors path="lastName"
									cssStyle="color:red"></form:errors></td>
						</tr>

						<tr>
							<td width="100" align="right">age</td>
							<td><form:input path="age" /></td>
							<td align="left"><form:errors path="age"
									cssStyle="color:red"></form:errors></td>
						</tr>
						<tr>
							<td width="100" align="right">Sex</td>
							<td><form:select path="sex">
									<form:option value="M" label="Male" />
									<form:option value="F" label="Female" />
								</form:select></td>
							<td></td>
						</tr>
						<tr>
							<td width="100" align="right">Address</td>
							<td><form:input path="address" /></td>
							<td align="left"><form:errors path="address"
									cssStyle="color:red"></form:errors></td>
						</tr>
						<tr>
							<td width="100" align="right">Email</td>
							<td><form:input path="email" /></td>
							<td align="left"><form:errors path="email"
									cssStyle="color:red"></form:errors></td>
						</tr>
						<tr>
							<td width="100" align="right">Phone</td>
							<td><form:input path="phone" /></td>
							<td align="left"><form:errors path="phone"
									cssStyle="color:red"></form:errors></td>
						</tr>
						
						<tr>
							<td width="100" align="right">Company</td>
							<td><form:input path="company" /></td>
							<td align="left"><form:errors path="company"
									cssStyle="color:red"></form:errors></td>
						</tr>
						<tr>
							<td width="100" align="right">Salary</td>
							<td><form:input path="salary" /></td>
							<td align="left"><form:errors path="salary"
									cssStyle="color:red"></form:errors></td>
						</tr>
						<tr>
							<td colspan="3" align="center"><input type="submit" name=""
								value="Save"> &nbsp;&nbsp; <input type="reset" name=""
								value="Reset"> &nbsp;&nbsp; <input type="button"
								value="Back" onclick="javascript:go('viewAllCustomers.do');">
							</td>
						</tr>
					</table>
				</form:form></td>
		</tr>
	</table>
</body>
</html>
