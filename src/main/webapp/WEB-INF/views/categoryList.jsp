
<%@ include file="./include.jsp"%>

<html><head>
<title>Categories List Page</title>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/Styles.css" />

</head><body>
<div class ="container">
<header>
<img src='resources/images/drugs-main.jpg' width="100%" height="50%"/>
</header>
<section>
<h3>Please Select Drugs Category</h3></br>
<c:forEach var="category" items="${catList}" >
<a href="${context}/displayProductsForm?catId=${category.catId}"><l1>${category.catName}</a></br>
</c:forEach>
</section>
</div>
</body></html>
