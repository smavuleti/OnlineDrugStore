<%@ include file="./include.jsp"%>


<html><head>
<title><fmt:message key="checkout.title" /></title>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/Styles.css" />

</head>
<body>
<div class ="container">
<header>
<img src='resources/images/drugs-main.jpg' width="100%" height="40%"/>
</header>
<section">

<h3>Check out details</h3>
<br>

<form:form action="./paymentSuccessful" method="GET" commandName="order">
Dear <c:out value = "${order.customer.name}" />, below are your shopping details. <br><br>
<table cellspacing="10">
<tr><td align="center"><b>Item</b><br></td><td align="center"><b>Price</b><br></td><td align="center"><b>Quantity</b><br></td></tr>
<c:forEach var = "oi" items = "${order.oiList}" varStatus="status">
		<tr><td>
        <c:out value = "${oi.product.name}" /> </td><td>
        <c:out value = "${oi.product.price}" /> </td><td>
        <c:out value ="${oi.quantity}"/> </td></tr> 
</c:forEach>
<tr><td> <br>
<b>Sub Total :</b> <c:out value = "${order.subtotal}" /><br></td></tr><tr><td>
<b>Tax : </b><c:out value = "${order.tax}" /><br></td></tr><tr><td>
<b>Total :</b> <c:out value = "${order.total}" /><br></td></tr>
</table>

<a href="${context}/categoryList"><fmt:message key="back" /></a></br>
<input type="submit" value="Pay now">

</form:form>

</section>
</div>
</body>
</html>
