
insert into accounts (USER_NAME, ACTIVE, ENCRYTED_PASSWORD, USER_ROLE)
values ('admin1', 1, '$2a$10$Gh.3UWeLfCyNRfXeRwFfX.QfObU9skGgLWqkU6uZZEW4u6MdH1Nra', 'ROLE_MANAGER');
insert into accounts (USER_NAME, ACTIVE, ENCRYTED_PASSWORD, USER_ROLE)
values ('employee1', 1, '$2a$10$Gh.3UWeLfCyNRfXeRwFfX.QfObU9skGgLWqkU6uZZEW4u6MdH1Nra', 'ROLE_EMPLOYEE');

insert into products (NAME, BRAND, PRICE, IMAGE)
values ('IPhone X', 'APPLE', 20000000, FILE_READ('D:/laptrinh/phonestore/src/main/resources/static/images/1.jpg'));

insert into products (NAME, BRAND, PRICE, IMAGE)
values ('IPhone 11', 'APPLE', 25000000, FILE_READ('D:/laptrinh/phonestore/src/main/resources/static/images/2.jpg'));

insert into products (NAME, BRAND, PRICE, IMAGE)
values ('IPhone 12', 'APPLE', 30000000, FILE_READ('D:/laptrinh/phonestore/src/main/resources/static/images/3.jpg'));

insert into products (NAME, BRAND, PRICE, IMAGE)
values ('Galaxy S10', 'SAMSUNG', 15000000, FILE_READ('D:/laptrinh/phonestore/src/main/resources/static/images/4.jpg'));

insert into products (NAME, BRAND, PRICE, IMAGE)
values ('Galaxy S20', 'SAMSUNG', 20000000, FILE_READ('D:/laptrinh/phonestore/src/main/resources/static/images/5.jpg'));

insert into products (NAME, BRAND, PRICE, IMAGE)
values ('Galaxy S20+', 'SAMSUNG', 22000000, FILE_READ('D:/laptrinh/phonestore/src/main/resources/static/images/6.jpg'));

insert into products (NAME, BRAND, PRICE, IMAGE)
values ('Xperia Z1', 'SONY', 1000000, FILE_READ('D:/laptrinh/phonestore/src/main/resources/static/images/7.jpg'));

insert into products (NAME, BRAND, PRICE, IMAGE)
values ('Xperia Z2', 'SONY', 2000000, FILE_READ('D:/laptrinh/phonestore/src/main/resources/static/images/8.jpg'));

insert into products (NAME, BRAND, PRICE, IMAGE)
values ('Xperia Z3', 'SONY', 3000000, FILE_READ('D:/laptrinh/phonestore/src/main/resources/static/images/9.jpg'));

insert into products (NAME, BRAND, PRICE, IMAGE)
values ('Xperia Z4', 'SONY', 4000000, FILE_READ('D:/laptrinh/phonestore/src/main/resources/static/images/10.jpg'));





