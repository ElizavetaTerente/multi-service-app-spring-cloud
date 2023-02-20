create table if not exists users (user_name varchar(255), password varchar(255),name varchar(255), surname varchar(255),adress varchar(255), phone_number varchar(255), email varchar(255), primary key (user_name))
create table if not exists user_role (user_name varchar(255), roles varchar(255),primary key (user_name,roles))

insert into users values ('user', '$2a$10$P6aWpRZYESDlkl1Zw24QIeu4jJMYhvW.pCHK8r2y/XD3G9uw6.lni','Elizaveta','Terente','Tschurtschenthalerstrasse 7','+436704085535','veronika.viskey@yandex.ru')
insert into user_role (user_name, roles) values ('user', 'USER')
insert into users values ('admin', '$2a$10$P6aWpRZYESDlkl1Zw24QIeu4jJMYhvW.pCHK8r2y/XD3G9uw6.lni','admin_name','admin_surname','admin_adress','admin_phone_number','admin_email')
insert into user_role (user_name, roles) values ('admin', 'ADMIN')