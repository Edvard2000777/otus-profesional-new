create table costumers (id bigserial primary key,
                            first_name varchar(255),
                            second_name varchar(255));

create table subjects (id bigserial primary key,
                            name varchar(255),
                            description varchar(255),
                            number_of_hours int);

create table teachers (id bigserial primary key,
                             first_name varchar(255),
                             second_name varchar(255));

create table exercises (id bigserial primary key,
                             exercise_date date,
                             subject_id bigint,
                             costumer_id bigint,
                             teacher_id bigint,
                             foreign key (subject_id) references subjects (id),
                             foreign key (costumer_id) references costumers (id),
                             foreign key (teacher_id) references teachers (id));

create view v_exercises as
select exercise_date, subject_id, costumer_id, teacher_id,
s.name subject_name, s.description subject_description, s.number_of_hours,
c.first_name costumer_first_name, c.second_name costumer_second_name,
t.first_name teacher_first_name, t.second_name teacher_second_name
from exercises e
left join subjects s on e.subject_id=s.id
left join costumers c on e.costumer_id=c.id
left join teachers t on e.teacher_id=t.id;

insert into costumers (first_name, second_name) values
('Иван','Иванов'),
('Петр','Петров'),
('Мария','Марьина'),
('Питер','Паркер');

insert into teachers (first_name, second_name) values
('Отто','Октавиус'),
('Курт','Конерс'),
('Клавдия','Петрова'),
('Питер','Динклейдж');

insert into subjects (name, description, number_of_hours) values
('История', 'Изучение истории что было и делаем выводы', 30),
('Математика', 'Складываем 2 и 2', 31),
('Химия', 'Химичим', 32),
('Актерское мастерство', 'Все хотят сниматься в кино', 14),
('Физика', 'Учим законы Архимеда и не только', 25);

insert into exercises (exercise_date, subject_id, costumer_id, teacher_id) values
('2023-12-17', 1, 1, 3),
('2023-12-17', 1, 2, 3),
('2023-12-17', 1, 3, 3),
('2023-12-17', 1, 4, 3),

('2023-12-18', 1, 1, 4),
('2023-12-18', 1, 2, 4),
('2023-12-18', 1, 3, 4),
('2023-12-18', 1, 3, 4),

('2023-12-17', 2, 1, 1),
('2023-12-17', 2, 2, 1),
('2023-12-17', 2, 3, 1),
('2023-12-17', 2, 4, 1),

('2023-12-17', 3, 1, 2),
('2023-12-17', 3, 2, 2),
('2023-12-17', 3, 3, 2),
('2023-12-17', 3, 4, 2),

('2023-12-17', 4, 1, 1),
('2023-12-17', 4, 2, 1),
('2023-12-17', 4, 3, 1),
('2023-12-17', 4, 4, 1),

('2023-12-18', 4, 1, 4),
('2023-12-18', 4, 2, 4),
('2023-12-18', 4, 3, 4),
('2023-12-18', 4, 4, 4),

('2023-12-18', 5, 1, 1),
('2023-12-18', 5, 2, 1),
('2023-12-18', 5, 3, 1),
('2023-12-18', 5, 4, 1),

('2023-12-19', 5, 1, 3),
('2023-12-19', 5, 2, 3),
('2023-12-19', 5, 3, 3),
('2023-12-19', 5, 4, 3);