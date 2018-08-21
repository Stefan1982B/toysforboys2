<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<title>Orders</title>
<meta name='viewport' content='width=device-width,initial-scale=1'>
<link rel='stylesheet' href='<c:url value="/css/toysforboys.css"/>'>
</head>
<body>
	<h1>Unshipped orders</h1>
	<c:if test='${not empty param.aantalMislukt}'>
		<h3>Shipping failed for order(s) ${param.aantalMislukt} : not enough stock</h3>
	</c:if>

	<form action='${url}' method='post' id='orderTabelFrom'>
		<table>
			<tr>
				<th>ID</th>
				<th>Ordered</th>
				<th>Required</th>
				<th>Customer</th>
				<th>Comments</th>
				<th>Status</th>
				<th>Ship</th>
			</tr>
			<c:forEach var='order' items='${orders}'>
				<tr>
					<td><spring:url var='url' value='/orders/{id}'>
							<spring:param name='id' value='${order.id}' />
						</spring:url><a href='${url}'>${order.id}</a></td>
					<td>${order.orderDate}</td>
					<td>${order.requiredDate}</td>
					<td>${order.customer.name}</td>
					<td>${order.comments}</td>
					<td class="lowercase"><img src="images/${order.status}.png"
						alt="${order.status}"> ${order.status}</td>
					<td><input type='checkbox' name='shippedId'
						value='${order.id}'></td>
				</tr>
			</c:forEach>

		</table>
		<input type='submit' value='Set as Shipped' id='shippedKnop'>
	</form>


	<script>
		document.getElementById('orderTabelForm').onsubmit = function() {
			document.getElementById('shippedKnop').disabled = true;
		};
	</script>

</body>
</html>
