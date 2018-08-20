insert into products(name, scale, description, quantityInStock, quantityInOrder, buyPrice, productlineId, version)
values('testProduct', 'testScale', 'testDescription', 20,11,10,(select id from productlines where name = 'testProductline'), 1);
insert into products(name, scale, description, quantityInStock, quantityInOrder, buyPrice, productlineId, version)
values('testProduct2', 'testScale2', 'testDescription2', 21,12,11,(select id from productlines where name = 'testProductline'), 2);