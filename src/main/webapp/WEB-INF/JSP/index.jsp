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
	<ul class = 'zebra'>
		<c:forEach var='order' items='${orders}'>
			<spring:url var='url' value='/orders/{id}'>
				<spring:param name='id' value='${order.id}' />
			</spring:url>
			<li><a href='${url}'>${order.id}</a> ${order.orderDate}${order.requiredDate}${order.customer.name}${order.comments}${order.status} </li>
		</c:forEach>
	</ul>
</body>
</html>
