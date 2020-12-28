insert into institutions (name, description) values ('Dbam o Zdrowie', 'Pomoc dzieciom z ubogich rodzin.');
insert into institutions (name, description) values ('A kogo', 'Pomoc w wybudzaniu dzieci ze śpiączki.');
insert into institutions (name, description) values ('Dla dzieci', 'Pomoc osobom znajdującym się w trudnej sytuacji życiowej.');
insert into institutions (name, description) values ('Bez domu', 'Pomoc dla osób nie posiadających miejsca zamieszkania.');



insert into categories (name) values ('ubrania, które nadają się do ponownego użycia');
insert into categories (name) values ('ubrania, do wyrzucenia');
insert into categories (name) values ('zabawki');
insert into categories (name) values ('książki');
insert into categories (name) values ('inne');

insert into roles(role) value ('ROLE_ADMIN');
insert into roles(role) value ('ROLE_USER');


insert into users(email, first_name, last_name, password, enabled) values ('szymek.fafrowicz@gmail.com', 'Szymon', 'Fąfrowicz','$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6', true);

insert into users_roles(user_id, role_id) values (1, 2);