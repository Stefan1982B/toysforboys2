insert into products(name, scale, description, quantityInStock, quantityInOrder, buyPrice, productlineId, version)
values('testProduct', 'testScale', 'testDescription', 20,5,10,(select id from productlines where name = 'testProductline'), 1);
insert into products(name, scale, description, quantityInStock, quantityInOrder, buyPrice, productlineId, version)
values('testProduct2', 'testScale2', 'testDescription2', 21,6,11,(select id from productlines where name = 'testProductline'), 2);