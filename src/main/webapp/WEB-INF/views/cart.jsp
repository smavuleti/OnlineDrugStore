<%@ include file="./include.jsp"%>

<html><head>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/Styles.css" />

<title>Your Cart</title>
</head>
<body>
<div class ="container">
<header>
<img src='resources/images/drugs-main.jpg' width="100%" height="40%"/>
</header>
<section">

<h3>You have below items in the cart</h3>
<br>

<table cellspacing="10">
<tr><td align="center">Drug Name<br></td><td align="center">Drug Price<br></td><td align="center">Quantity<br></td></tr>
<c:forEach var = "oi" items = "${sessionScope.orderform.oiList}" varStatus="status">
       <tr><td>
        <c:out value = "${oi.product.name}" /> </td><td>
        <c:out value = "${oi.product.price}" /> </td><td>
        <c:out value = "${oi.quantity}"/>  </td></tr>
</c:forEach>
</table>
<br>

<a href="${context}/categoryList"><fmt:message key="back" /></a></br>
<a href="${context}/checkout"><fmt:message key="checkout" /></a>

</section>
</div>

</body>
</html> 