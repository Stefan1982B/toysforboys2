<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value="orders" />
</c:import>
</head>
</head>
<body>
	<h1>Order ${order.id}</h1>
	<h5>Ordered</h5>
	<h2>${order.orderDate}</h2>
	<h5>Required</h5>
	<h2>${order.requiredDate}</h2>
	<h5>Customer</h5>
	<h2>${order.customer.name}</h2>
	<h2>${order.customer.address.streetAndNumber}</h2>
	<h2>${order.customer.address.postalCode}
		${order.customer.address.city}</h2>
	<h2>${order.customer.country.name}</h2>
	<h5>Comments:</h5>
	<h2>${order.comments}</h2>
	<h5>Details:</h5>
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

</body>
</html>
