<%@ include file="./include.jsp"%>
<html>
<head>
	<title>Home Page</title>
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/Styles.css" />
</head>
<body>
<div class ="container">
<header>
<img src='resources/images/drugs-main.jpg' width="100%" height="40%"/>
</header>
<section>
<form:form action="./categoryList" method="GET" commandName="customer">

<div align="center">

<h2>Welcome to online Drug Store</h2>
  	<form:select path="id">
		 <form:option value="0" label="Select Customer Name"/>
		 <form:options items="${custList}" itemValue="id" itemLabel="name"/>
	</form:select>
	<input type="submit" value="Sign In" > 

	  </div>
  </form:form>
  </section>
 </div>
</body>
</html>
