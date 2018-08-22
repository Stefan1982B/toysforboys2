insert into orders(orderDate, requiredDate, shippedDate, comments, customerId, status, version) 
values ('2019-01-01', '2019-02-01', '2019-02-01', 'testComment', 
(select id from customers where name = 'testCustomer'), 'WAITING', 1);
insert into orders(orderDate, requiredDate, shippedDate, comments, customerId, status, version) 
values ('2019-08-08', '2019-09-09', '2019-10-10', 'testComment2', 
(select id from customers where name = 'testCustomer'), 'WAITING', 1);
insert into orderdetails(orderId, productId, quantityOrdered, priceEach)
values((select id from orders where comments = 'testComment'), (select id from products where name = 'testProduct'), 5, 10);
insert into orderdetails(orderId, productId, quantityOrdered, priceEach)
values((select id from orders where comments = 'testComment'), (select id from products where name = 'testProduct2'), 7, 11);
insert into orderdetails(orderId, productId, quantityOrdered, priceEach)
values((select id from orders where comments = 'testComment2'), (select id from products where name = 'testProduct2'), 8, 20);