insert into products(name, scale, description, quantityInStock, quantityInOrder, buyPrice, productlineId, version)
values('testProduct', 'testScale', 'testDescription', 20,5,10,(select id from productlines where name = 'testProductline'), 1);