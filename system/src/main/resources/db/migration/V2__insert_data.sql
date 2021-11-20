insert into employees(id, first_name, last_name, middle_name, email, phone_number) VALUES (1, 'Симон', 'Симонов', 'Симонович', 'simonov@gmail.com', '+79119119314');
insert into employees(id, first_name, last_name, middle_name, email, phone_number) VALUES (2, 'Иван', 'Иванов', 'Иванович', 'ivanov@gmail.com', '+79119119191');
insert into employees(id, first_name, last_name, middle_name, email, phone_number) VALUES (3, 'Петр', 'Петров', 'Петрович', 'petrov@gmail.com', '+79109109090');
insert into employees(id, first_name, last_name, middle_name, email, phone_number) VALUES (4, 'Сидор', 'Сидоров', 'Сидорович', 'sidorov@gmail.com', '+79129129292');
insert into employees(id, first_name, last_name, middle_name, email, phone_number) VALUES (5, 'Валерий', 'Валериев', 'Валерьевич', 'valeriev@gmail.com', '+79129129293');
insert into employees(id, first_name, last_name, middle_name, email, phone_number) VALUES (6, 'Контсантин', 'Контсантинов', 'Контсантинович', 'konstantinov@gmail.com', '+79129129983');
insert into employees(id, first_name, last_name, middle_name, email, phone_number) VALUES (7, 'Владимир', 'Владимиров', 'Владимирович', 'vladimirov@gmail.com', '+78974129841');
insert into employees(id, first_name, last_name, middle_name, email, phone_number) VALUES (8, 'Олег', 'Олегов', 'Олегович', 'olegov@gmail.com', '+78479010284');
insert into employees(id, first_name, last_name, middle_name, email, phone_number) VALUES (9, 'Марк', 'Марков', 'Маркович', 'markov@gmail.com', '+78884442493');
insert into employees(id, first_name, last_name, middle_name, email, phone_number) VALUES (10, 'Алексей', 'Алексеев', 'Алексеевич', 'alekseev@gmail.com', '+71236576512');
insert into employees(id, first_name, last_name, middle_name, email, phone_number) VALUES (11, 'Лев', 'Львов', 'Львович', 'lvov@gmail.com', '+73516323123');
insert into employees(id, first_name, last_name, middle_name, email, phone_number) VALUES (12, 'Максим', 'Максимов', 'Максимович', 'maximov@gmail.com', '+71234567890');
insert into employees(id, first_name, last_name, middle_name, email, phone_number) VALUES (13, 'Дмитрий', 'Дмитров', 'Дмитриевич', 'dmitrov@gmail.com', '+78534750192');
insert into employees(id, first_name, last_name, middle_name, email, phone_number) VALUES (14, 'Евгений', 'Евгениев', 'Евгениевич', 'evgeniev@gmail.com', '+78459347683');

alter sequence employees_id_seq restart with 15;

insert into departments(id, parent_id, name) VALUES (1, null, 'ЦФТ');
insert into departments(id, parent_id, name) VALUES (2, 1, 'Какой-то департамент 1');
insert into departments(id, parent_id, name) VALUES (3, 1, 'Какой-то департамент 2');
insert into departments(id, parent_id, name) VALUES (4, 2, 'Какой-то отдел 1');
insert into departments(id, parent_id, name) VALUES (5, 2, 'Какой-то отдел 2');
insert into departments(id, parent_id, name) VALUES (6, 3, 'Какой-то отдел 3');
insert into departments(id, parent_id, name) VALUES (7, 3, 'Какой-то отдел 4');

alter sequence departments_id_seq restart with 8;

insert into profession_titles(id, name) VALUES (1, 'Ведущий инженер-проектировщик');
insert into profession_titles(id, name) VALUES (2, 'Старший инженер-проектировщик');
insert into profession_titles(id, name) VALUES (3, 'Главный инженер');
insert into profession_titles(id, name) VALUES (4, 'Начальник отдела');

alter sequence profession_titles_id_seq restart with 5;

insert into staffs(id, department_id, profession_id, max_employee_count) VALUES (1, 4, 4, 10);
insert into staffs(id, department_id, profession_id, max_employee_count) VALUES (2, 4, 1, 10);
insert into staffs(id, department_id, profession_id, max_employee_count) VALUES (3, 4, 2, 10);
insert into staffs(id, department_id, profession_id, max_employee_count) VALUES (4, 4, 2, 10);
insert into staffs(id, department_id, profession_id, max_employee_count) VALUES (5, 5, 4, 10);
insert into staffs(id, department_id, profession_id, max_employee_count) VALUES (6, 5, 3, 10);
insert into staffs(id, department_id, profession_id, max_employee_count) VALUES (7, 5, 2, 10);
insert into staffs(id, department_id, profession_id, max_employee_count) VALUES (8, 5, 1, 10);
insert into staffs(id, department_id, profession_id, max_employee_count) VALUES (9, 5, 1, 10);

alter sequence staffs_id_seq restart with 10;

insert into employee_staff(id, employee_id, staff_id, date_begin, date_end, employee_type) VALUES (1, 1, 1, to_date('01.01.2021', 'dd.mm.yyyy'), null, 1);
insert into employee_staff(id, employee_id, staff_id, date_begin, date_end, employee_type) VALUES (2, 2, 2, to_date('01.02.2021', 'dd.mm.yyyy'), null, 1);
insert into employee_staff(id, employee_id, staff_id, date_begin, date_end, employee_type) VALUES (3, 3, 3, to_date('01.03.2021', 'dd.mm.yyyy'), null, 1);
insert into employee_staff(id, employee_id, staff_id, date_begin, date_end, employee_type) VALUES (4, 4, 4, to_date('01.01.2021', 'dd.mm.yyyy'), null, 1);
insert into employee_staff(id, employee_id, staff_id, date_begin, date_end, employee_type) VALUES (5, 5, 5, to_date('01.02.2021', 'dd.mm.yyyy'), null, 1);
insert into employee_staff(id, employee_id, staff_id, date_begin, date_end, employee_type) VALUES (6, 6, 6, to_date('01.03.2021', 'dd.mm.yyyy'), null, 1);
insert into employee_staff(id, employee_id, staff_id, date_begin, date_end, employee_type) VALUES (7, 7, 7, to_date('01.01.2021', 'dd.mm.yyyy'), null, 1);
insert into employee_staff(id, employee_id, staff_id, date_begin, date_end, employee_type) VALUES (8, 8, 8, to_date('01.02.2021', 'dd.mm.yyyy'), null, 1);
insert into employee_staff(id, employee_id, staff_id, date_begin, date_end, employee_type) VALUES (9, 9, 9, to_date('01.03.2021', 'dd.mm.yyyy'), null, 1);
insert into employee_staff(id, employee_id, staff_id, date_begin, date_end, employee_type) VALUES (10, 10, 2, to_date('01.02.2021', 'dd.mm.yyyy'), null, 1);
insert into employee_staff(id, employee_id, staff_id, date_begin, date_end, employee_type) VALUES (11, 11, 2, to_date('01.02.2021', 'dd.mm.yyyy'), null, 1);
insert into employee_staff(id, employee_id, staff_id, date_begin, date_end, employee_type) VALUES (12, 12, 7, to_date('01.02.2021', 'dd.mm.yyyy'), null, 1);
insert into employee_staff(id, employee_id, staff_id, date_begin, date_end, employee_type) VALUES (13, 13, 7, to_date('01.02.2021', 'dd.mm.yyyy'), null, 1);
insert into employee_staff(id, employee_id, staff_id, date_begin, date_end, employee_type) VALUES (14, 14, 7, to_date('01.02.2021', 'dd.mm.yyyy'), null, 1);

alter sequence employee_staff_id_seq restart with 15;

insert into reporting_config(id, report_name, report_url) VALUES (1, 'Список сотрудников', '/reports/employeesList');
insert into reporting_config(id, report_name, report_url) VALUES (2, 'Штатное расписание', '/reports/staffEmployeesList');

alter sequence reporting_config_id_seq restart with 3;