create database shop_flipmart;
use shop_flipmart;

create table if not exists role (
	id int not null auto_increment,
    name varchar(50) not null,    
    description varchar(50),
    primary key (id)
);

create table if not exists user (
	id int not null auto_increment,
    email varchar(50) not null,
    password varchar(50) not null,
    name varchar(50) not null,
    phonenumber int,
    address varchar(70),
    address_comment varchar(100),
    role_id int,
    primary key (id)
);

create table if not exists category (
	id int not null auto_increment,
    name varchar(50) not null,
    description varchar(50),
    primary key (id)
);

create table if not exists product (
	id int not null auto_increment,
    name varchar(50) not null,
    description varchar(50),
    tag varchar(50) not null,
    price double not null,
    discount float,
    vote_everage float,
    amount int not null,
    input_date date not null,
    cate_id int,
    primary key (id)
);

create table if not exists image (
	id int not null auto_increment,
    src varchar(70) not null,
    product_id int,
    primary key (id)
);

create table if not exists compare (
	id int not null auto_increment,
    product_id_1 int,
    product_id_2 int,
    product_id_3 int,
    user_id int,
    primary key (id)
);

create table if not exists rating (
	id int not null auto_increment,
    content text,
    star int not null,
    product_id int,
    user_id int,
    primary key (id)
);

create table if not exists wishlist (
	id int not null auto_increment,    
    product_id int,
    user_id int,
    primary key (id)
);

create table if not exists coupon (
	id int not null auto_increment,    
    percent float not null,
    name varchar(50),
    primary key (id)
);

create table if not exists orders (
	id int not null auto_increment,    
    total_price double not null,
    date_order date not null,
    status varchar(30) not null,
    coupon_id int,
    user_id int,
    primary key (id)
);

create table if not exists orders_product (
	id int not null auto_increment,   
    amount int not null,
    price double not null,
    ship_price double not null,
    product_id int,
    orders_id int,
    primary key (id)
);

ALTER TABLE user ADD FOREIGN KEY (role_id) REFERENCES role (id)  ON DELETE CASCADE;
ALTER TABLE product ADD FOREIGN KEY (cate_id) REFERENCES category (id)  ON DELETE CASCADE;
ALTER TABLE compare ADD FOREIGN KEY (user_id) REFERENCES user (id)  ON DELETE CASCADE;
ALTER TABLE compare ADD FOREIGN KEY (product_id_1) REFERENCES product (id)  ON DELETE CASCADE;
ALTER TABLE compare ADD FOREIGN KEY (product_id_2) REFERENCES product (id)  ON DELETE CASCADE;
ALTER TABLE compare ADD FOREIGN KEY (product_id_3) REFERENCES product (id)  ON DELETE CASCADE;
ALTER TABLE rating ADD FOREIGN KEY (user_id) REFERENCES user (id)  ON DELETE CASCADE;
ALTER TABLE rating ADD FOREIGN KEY (product_id) REFERENCES product (id)  ON DELETE CASCADE;
ALTER TABLE wishlist ADD FOREIGN KEY (product_id) REFERENCES product (id)  ON DELETE CASCADE;
ALTER TABLE wishlist ADD FOREIGN KEY (user_id) REFERENCES user (id)  ON DELETE CASCADE;
ALTER TABLE orders ADD FOREIGN KEY (user_id) REFERENCES user (id)  ON DELETE CASCADE;
ALTER TABLE orders ADD FOREIGN KEY (coupon_id) REFERENCES coupon (id)  ON DELETE CASCADE;
ALTER TABLE orders_product ADD FOREIGN KEY (orders_id) REFERENCES orders (id)  ON DELETE CASCADE;
ALTER TABLE orders_product ADD FOREIGN KEY (product_id) REFERENCES product (id)  ON DELETE CASCADE;
ALTER TABLE image ADD FOREIGN KEY (product_id) REFERENCES product (id)  ON DELETE CASCADE;

INSERT INTO role( name, description ) VALUES ("ADMIN", "Quản trị hệ thống");
INSERT INTO role( name, description ) VALUES ("STAFF", "Nhân viên");
INSERT INTO role( name, description ) VALUES ("USER", "Người dùng");

insert into user(email, password, name, phonenumber, address, address_comment, role_id)
values ("nguyenvana@gmail.com", "123", "Nguyễn Văn A", 0123456789, "234 District 7, HCM city", "near coopmart", 1);

insert into user(email, password, name, phonenumber, address, address_comment, role_id)
values ("nguyenvanb@gmail.com", "123", "Nguyễn Văn B", 0123456789, "345 District 7, HCM city", "near coopmart", 2);

insert into user(email, password, name, phonenumber, address, address_comment, role_id)
values ("nguyenvanc@gmail.com", "123", "Nguyễn Văn C", 0123456789, "456 District 7, HCM city", "near coopmart", 3);

INSERT INTO category( name) VALUES ("Clothing");
INSERT INTO category( name) VALUES ("Electronics");
INSERT INTO category( name) VALUES ("Shoes");
INSERT INTO category( name) VALUES ("Watches");
INSERT INTO category( name) VALUES ("Jewellery");
INSERT INTO category( name) VALUES ("Health and Beauty");
INSERT INTO category( name) VALUES ("Kids and Babies");
INSERT INTO category( name) VALUES ("Sports");
INSERT INTO category( name) VALUES ("Home and Garden");

insert into product(name, description, tag, price, discount, vote_everage, amount, cate_id, input_date)
values ("Men's white long-sleeved shirt", "Natural materials protect health", "Shirt", 10, 5, 4.7, 52, 1, "2023-03-24");

insert into product(name, description, tag, price, discount, vote_everage, amount, cate_id, input_date)
values ("Women's white long-sleeved shirt", "Natural materials protect health", "Shirt", 12, 3, 3.2, 27, 1, "2023-03-25");
