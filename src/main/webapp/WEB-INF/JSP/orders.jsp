<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<head>
<title>Orderdetail</title>
<meta name='viewport' content='width=device-width,initial-scale=1'>
<link rel='stylesheet' href='<c:url value="/css/toysforboys.css"/>'>
</head>
</head>
<body>
	<h1>Order ${order.id}</h1>
	<h2>Ordered</h2>
	<h5>${order.orderDate}</h5>
	<h2>Required</h2>
	<h5>${order.requiredDate}</h5>
	<h2>Customer</h2>
	<h5>${order.customer.name}</h5>
	<h5>${order.customer.address.streetAndNumber}</h5>
	<h5>${order.customer.address.postalCode}
		${order.customer.address.city}</h5>
	<h5>${order.customer.country.name}</h5>
	<h2>Comments:</h2>
	<h5>${order.comments}</h5>
	<h2>Details:</h2>
	<table>
		<tr>
			<th>Product</th>
			<th>Price each</th>
			<th>Quantity</th>
			<th>Value</th>
			<th>Deliverable</th>
		</tr>
		<c:forEach var='orderdetail' items='${order.orderdetails}'>
			<tr>
				<td>${orderdetail.product.name}</td>
				<td>${orderdetail.priceEach}</td>
				<td>${orderdetail.quantityOrdered}</td>
				<td>${orderdetail.priceEach * orderdetail.quantityOrdered}</td>
				<td><c:if
						test='${orderdetail.quantityOrdered <= orderdetail.product.quantityInStock}'>   
				    &#10003  </c:if> <c:if
						test='${orderdetail.quantityOrdered > orderdetail.product.quantityInStock}'>   
				    &#10007  </c:if></td>
			</tr>
		</c:forEach>
	</table>
	
	<h2>value: ${totalePrijs}</h2>


	<!-- 	<ul> -->
	<%-- 		<c:forEach var="track" items="${album.tracks}"> --%>
	<%-- 			<li>${track.naam}${track.tijd}</li> --%>
	<%-- 		</c:forEach> --%>
	<!-- 	</ul> -->
	<%-- 	Totale tijd: ${album.tijd} --%>
</body>
</html>
