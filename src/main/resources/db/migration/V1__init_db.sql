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
    profession_id integer references profession_titles(id)
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
            COMPETENCE
 */


 create table if not exists competence_categories (
    id serial primary key,
    name varchar(1024)
 );

create table if not exists competences (
    id serial primary key,
    category_id integer references competence_categories(id),
    name varchar(1024)
);

create table if not exists competence_templates (
    id serial primary key,
    name varchar(1024)
);

create table if not exists competence_lists (
    id serial primary key,
    template_id integer references competence_templates(id),
    competence_id integer references competences(id),
    level smallint
);

create table if not exists staff_competences (
    id serial primary key,
    staff_id integer references staffs(id),
    competence_list_id integer references competence_lists(id)
);

create table if not exists employee_competences (
    id serial primary key,
    employee_id integer references employees(id),
    competence_id integer references competences(id),
    level smallint,
    date_update date
);


/*
            RETRO
 */


create table if not exists retros (
    id serial primary key,
    date_event date,
    employee_staff_id integer references employee_staff(id),
    variant1 varchar(2048),
    variant2 varchar(2048),
    variant3 varchar(2048),
    variant4 varchar(2048)
);

create table if not exists pdps (
    id serial primary key,
    date_create date,
    employee_staff_id integer references employee_staff(id)
);

create table if not exists pdp_list (
    id serial primary key,
    pdp_id integer references pdps(id),
    text varchar(2048),
    achieved boolean
);

/*
            360
 */

create table if not exists reviews (
    id serial primary key,
    employee_staff_id integer references employee_staff(id)
);

create table if not exists review_list (
    id serial primary key,
    review_id integer references reviews(id),
    employee_id integer references employees(id), -- reviewer
    comment varchar(2048)
);

create table if not exists criterias (
    id serial primary key,
    text varchar(1024),
    max_grade integer
);

create table if not exists criteria_list (
    id serial primary key,
    criteria_id integer references criterias(id),
    review_id integer references reviews(id),
    max_assessment integer
);

create table if not exists reviewers_grades(
    id serial primary key,
    review_list_id integer references review_list(id),
    criteria_id integer references criterias(id),
    assessment integer,
    comment varchar(2048)
);