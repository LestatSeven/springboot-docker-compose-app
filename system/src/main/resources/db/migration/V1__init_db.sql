create table if not exists employees (
    id serial primary key,
    first_name varchar(1024),
    last_name varchar(1024),
    middle_name varchar(1024),
    email varchar(1024),
    phone_number varchar(15)
);


create table if not exists profession_titles (
    id serial primary key,
    name varchar(1024)
);

create table if not exists departments (
    id serial primary key,
    parent_id integer references departments(id),
    name varchar(1024)
);

create table if not exists staffs (
    id serial primary key,
    department_id integer references departments(id),
    profession_id integer references profession_titles(id),
    max_employee_count integer
);


create table if not exists employee_staff (
    id serial primary key,
    employee_id integer references employees(id),
    staff_id integer references staffs(id),
    date_begin date,
    date_end date,
    employee_type smallint
);


/*
            REPORTING
 */

create table if not exists reporting_config (
    id serial primary key,
    report_name varchar(1024),
    report_url varchar(1024)
);

create table if not exists reports_status (
    id serial primary key,
    reporting_config_id integer references reporting_config(id),
    generated_name varchar(1024),
    date_request timestamp,
    date_start timestamp,
    date_end timestamp
);