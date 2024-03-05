create table products (
    id bigserial primary key,
    name varchar(255)
);
insert into products (name) values ('Product 1'), ('Product 2'), ('Product 3'), ('Product 4');