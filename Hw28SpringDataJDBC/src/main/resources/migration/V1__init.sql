create table categories (
    id bigserial primary key,
    title varchar(255)
);

create table authors (
    id bigserial primary key,
    full_name varchar(255)
);

create table books (
    id bigserial primary key,
    title varchar(255),
    genre varchar(255),
    author_id bigint,
    foreign key (author_id) references authors (id)
);

create table books_details (
    id bigserial primary key,
    book_id bigint,
    description varchar(255),
    foreign key (book_id) references books (id)
);

create table reviews (
    id bigserial primary key,
    book_id bigint,
    reviewer_name varchar(255),
    review_text varchar(255),
    rating int,
    review_date date
);

create view top10books as
select
    b.id,
    b.title,
    b.genre,
    a.full_name as author_name,
    bd.description,
    ifnull (avg(r.rating), 0) as avg_rating
from
    BOOKS b
    left join AUTHORS a on b.author_id = a.id
    left join BOOKS_DETAILS bd on bd.book_id = b.id
    left join REVIEWS r on r.book_id = b.id
    and review_date >= DATEADD(MONTH, -1, current_date())
group by
    b.id,
    b.title,
    b.genre,
    a.full_name,
    bd.description
order by
    6 desc
limit
    10;

create view v_books as
select
    b.id,
    b.title,
    b.genre,
    a.full_name as author_name,
    bd.description,
    COALESCE(avg(r.rating), 0) as avg_rating
from
    BOOKS b
    left join AUTHORS a on b.author_id = a.id
    left join BOOKS_DETAILS bd on bd.book_id = b.id
    left join REVIEWS r on r.book_id = b.id
group by
    b.id,
    b.title,
    b.genre,
    a.full_name,
    bd.description;

insert into
    categories (title)
values
    ('Категория 1'),
    ('Категория 2'),
    ('Категория 3');

insert into
    authors (full_name)
values
    ('Толкиен'),
    ('Роулинг'),
    ('Сандерсон'),
    ('Азимов');

insert into
    books (title, author_id, genre)
values
    ('Властелин колец: Братство кольца', 1, 'FANTASY'),
    ('Гарри Поттер и Философский камень',2,'FANTASY'),
    ('Рожденный туманом: Пепел и сталь', 3, 'FANTASY'),
    ('Рожденный туманом: Источник вознесения',3,'FANTASY'),
    ('Рожденный туманом: Герой веков', 3, 'FANTASY'),
    ('Архив Буресвета: Путь королей', 3, 'FANTASY'),
    ('Академия', 4, 'SCIFI'),
    ('Гарри Поттер и Тайная комната', 2, 'FANTASY'),
    ('Гарри Поттер и Узник Азкабана', 2, 'FANTASY'),
    ('Гарри Поттер и Кубок огня', 2, 'FANTASY'),
    ('Гарри Поттер и Орден феникса', 2, 'FANTASY'),
    ('Гарри Поттер и Принц полукровка', 2, 'FANTASY'),
    ('Гарри Поттер и Дары смерти', 2, 'FANTASY');

insert into
    books_details (book_id, description)
values
    (1, 'Книга про Властелина колец'),
    (2, 'Книга про Гарри Поттера'),
    (3, 'Книга про Рожденного туманом'),
    (4, 'Книга про Рожденного туманом'),
    (5, 'Книга про Рожденного туманом'),
    (6, 'Книга про Архив Буресвета'),
    (7, 'Книга про Академию. Основная трилогия'),
    (8, 'Книга про Гарри Поттера'),
    (9, 'Книга про Гарри Поттера'),
    (10, 'Книга про Гарри Поттера'),
    (11, 'Книга про Гарри Поттера'),
    (12, 'Книга про Гарри Поттера'),
    (13, 'Книга про Гарри Поттера');

insert into
    reviews (book_id, reviewer_name, review_text, rating, review_date)
values
    (1, 'Вася', 'Норм', 3, CURRENT_DATE()),
    (2, 'Вася', 'Норм', 3, CURRENT_DATE() -1),
    (3, 'Вася', 'Норм', 3, CURRENT_DATE() + 2),
    (4, 'Вася', 'Норм', 3, CURRENT_DATE() -5),
    (5, 'Вася', 'Норм', 3, CURRENT_DATE() -6),
    (1, 'Коля', 'Отлично', 10, CURRENT_DATE() -10),
    (2, 'Коля', 'Отлично', 10, CURRENT_DATE() + 8),
    (4, 'Коля', 'Отлично', 10, CURRENT_DATE() -25),
    (5, 'Маша', 'Хорошо', 8, CURRENT_DATE() -250),
    (3, 'Маша', 'Хорошо', 8, CURRENT_DATE() -14),
    (1, 'Маша', 'Хорошо', 8, CURRENT_DATE() + 23),
    (7, 'Маша', 'Хорошо', 8, CURRENT_DATE() -58),
    (4, 'Маша', 'Хорошо', 8, CURRENT_DATE() -61),
    (2, 'Даша', 'Неплохо', 4, CURRENT_DATE() -56),
    (1, 'Даша', 'Неплохо', 5, CURRENT_DATE() + 75),
    (3, 'Даша', 'Неплохо', 4, CURRENT_DATE() -35);