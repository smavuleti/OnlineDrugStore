
<%@ include file="./include.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/Styles.css" />

<title>Drug List Page</title>
</head>
<body>
<div class ="container">
<header>
<img src='resources/images/drugs-main.jpg' width="100%" height="40%"/>
</header>
<section">
<form:form action="./addProductsForm" method="POST" commandName="orderform">

<h3>Please select the drugss and quantity</h3></br>
<table>
<tr><td align="center">drug Name<br></td><td align="center">Drug Price<br></td><td align="center">Quantity<br></td></tr>
	<c:forEach var = "oi" items = "${orderform.oiList}" varStatus="status">
	<tr><td>
        <c:out value = "${oi.product.name}" /> </td>
        <td>
        <c:out value = "${oi.product.price}" /> </td>
        <td>
        <form:input path="oiList[${status.index}].quantity" type="text" /> <br></td></tr>
        <form:hidden path="oiList[${status.index}].product.name"/>
        <form:hidden path="oiList[${status.index}].product.price"/>
    </c:forEach>
    <tr><td align="center"><br>
<input type="submit" value="Add to cart">
</td></tr></table>
</form:form>
</section>
</div>

</body>
</html>