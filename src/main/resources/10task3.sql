CREATE DATABASE "university2"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'C'
    LC_CTYPE = 'C'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;


CREATE TABLE public.departments
(
    name character(80) COLLATE pg_catalog."default",
    id serial ,
    CONSTRAINT departments_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.departments
    OWNER to postgres;





CREATE TABLE public.faculties
(
    name character(80) COLLATE pg_catalog."default",
    id serial ,
    CONSTRAINT faculties_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.faculties
    OWNER to postgres;






CREATE TABLE public.groups
(
    name character(80) COLLATE pg_catalog."default",
     id serial ,
    CONSTRAINT groups_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.groups
    OWNER to postgres;



CREATE TABLE public.rooms
(
    id serial ,
    "number" character(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT room2_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.rooms
    OWNER to postgres;




CREATE TABLE public.students
(
    name character(80) COLLATE pg_catalog."default",
     id serial ,
    CONSTRAINT students_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.students
    OWNER to postgres;




CREATE TABLE public.subjects
(
    name character(80) COLLATE pg_catalog."default",
   id serial ,
    CONSTRAINT subjects_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.subjects
    OWNER to postgres;





CREATE TABLE public.teachers
(
    name character(80) COLLATE pg_catalog."default",
    id serial ,
    subject_id integer,
    CONSTRAINT teachers_pkey PRIMARY KEY (id),
    CONSTRAINT subject_id FOREIGN KEY (subject_id)
        REFERENCES public.subjects (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.teachers
    OWNER to postgres;














CREATE TABLE public.lessons
(
    "number" integer,
    date timestamp with time zone,
    id serial ,
    subject_id integer,
    teacher_id integer,
    group_id integer,
    room_id integer,
    CONSTRAINT lessons_pkey PRIMARY KEY (id),
    CONSTRAINT group_id FOREIGN KEY (group_id)
        REFERENCES public.groups (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT room_id FOREIGN KEY (room_id)
        REFERENCES public.rooms (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT subject_id FOREIGN KEY (subject_id)
        REFERENCES public.subjects (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT teacher_id FOREIGN KEY (teacher_id)
        REFERENCES public.teachers (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.lessons
    OWNER to postgres;
















CREATE TABLE public.departments_subjects
(
    department_id integer,
    subject_id integer,
    CONSTRAINT departments_subjects_department_id_fkey FOREIGN KEY (department_id)
        REFERENCES public.departments (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT departments_subjects_subject_id_fkey FOREIGN KEY (subject_id)
        REFERENCES public.subjects (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.departments_subjects
    OWNER to postgres;


CREATE TABLE public.departments_teachers
(
    department_id integer,
    teacher_id integer,
    CONSTRAINT departments_teachers_department_id_fkey FOREIGN KEY (department_id)
        REFERENCES public.departments (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT departments_teachers_teacher_id_fkey FOREIGN KEY (teacher_id)
        REFERENCES public.teachers (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.departments_teachers
    OWNER to postgres;



CREATE TABLE public.faculties_departments
(
    faculty_id integer,
    department_id integer,
    CONSTRAINT faculties_departments_department_id_fkey FOREIGN KEY (department_id)
        REFERENCES public.departments (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT faculties_departments_faculty_id_fkey FOREIGN KEY (faculty_id)
        REFERENCES public.faculties (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.faculties_departments
    OWNER to postgres;


CREATE TABLE public.faculties_groups
(
    faculty_id integer,
    group_id integer,
    CONSTRAINT faculties_groups_faculty_id_fkey FOREIGN KEY (faculty_id)
        REFERENCES public.faculties (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT faculties_groups_group_id_fkey FOREIGN KEY (group_id)
        REFERENCES public.groups (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.faculties_groups
    OWNER to postgres;


CREATE TABLE public.groups_students
(
    group_id integer,
    student_id integer,
    CONSTRAINT groups_students_group_id_fkey FOREIGN KEY (group_id)
        REFERENCES public.groups (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT groups_students_student_id_fkey FOREIGN KEY (student_id)
        REFERENCES public.students (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.groups_students
    OWNER to postgres;


