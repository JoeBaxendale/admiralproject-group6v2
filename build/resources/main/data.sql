/* ADMIRAL USER DATA */
insert into admiral_user (admiral_user_id, first_name, last_name, admiral_staff_email, password, status) values (1,'Victoria', 'Smith', 'victoria@hotmail.com', 'victoria123', 'true');

/* ADMIRAL ROLE DATA */
insert into admiral_role (admiral_role_id, admiral_role_name, admiral_role_description) values (1, 'Manager', 'This user has ultimate rights for everything')
insert into admiral_role (admiral_role_id, admiral_role_name, admiral_role_description) values (2, 'Admin', 'This user has admin rights for administrative work')
insert into admiral_role (admiral_role_id, admiral_role_name, admiral_role_description) values (3, 'Contractor', 'This user has access to site, after login')

/* ADMIRAL USER_ROLE DATA */
insert into admiral_user_role (admiral_user_id, admiral_role_id) values (1, 1)
insert into admiral_user_role (admiral_user_id, admiral_role_id) values (1, 2)
insert into admiral_user_role (admiral_user_id, admiral_role_id) values (1, 3)


/* ADMIRAL STAFF DATA */
--
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (1, 'victoria@hotmail.com', 'victoria123', 'Victoria', 'Smith');
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (2, 'adams@hotmail.com', 'adams111', 'Adams', 'Court');
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (3, 'fernando@gmail.com', 'fernando122', 'Fernando', 'Barbosa');
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (4, 'jhon@hotmail.com', 'jhon333', 'Jhon', 'Barco');
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (5, 'amelia@hotmail.com', 'amelia654', 'Amelia', 'Brown');
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (6, 'stiles@gmail.com', 'stiles987', 'Stiles', 'Cooper');
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (7, 'allison@hotmail.com', 'allison223', 'Allison', 'Argent');
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (8, 'scot@hotmail.com', 'scot222', 'Scot', 'Stanesby');
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (9, 'josseth@gmail.com', 'josseth299', 'Josseth', 'Clarck');
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (10, 'julian@hotmail.com', 'julian000', 'Julian', 'Roberts');
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (11, 'jackson@gmail.com', 'jackson111', 'Jackson', 'Jones');
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (12, 'harper@hotmail.com', 'p123', 'Harper', 'Lawson');
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (13, 'avery@gmail.com', 'p123', 'Avery', 'Corbyn');
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (14, 'maddison@hotmail.com', 'p123', 'Maddison', 'Wilson');
-- insert into admiral_staff (admiral_staff_id, admiral_staff_email, password, first_name, last_name) values (15, 'riley@gmail.com', 'riley777', 'Riley', 'Brown');
--
-- /*MANAGER DATA*/
--
-- insert into managers (manager_id, manager_email, password, first_name, last_name, status) values (1, 'dave@gmail.com', 'dave12', 'Dave', 'Harris', 1);
-- insert into managers (manager_id, manager_email, password, first_name, last_name, status) values (2, 'joe@gmail.com', 'joe51', 'Joe', 'Young', 1);
-- insert into managers (manager_id, manager_email, password, first_name, last_name, status) values (3, 'dan@outlook.com', 'dan123', 'Dan', 'Brown', 1);
-- insert into managers (manager_id, manager_email, password, first_name, last_name, status) values (4, 'matthew@hotmail.co.uk', 'matthew423!', 'Matthew', 'Mort', 1);
-- insert into managers (manager_id, manager_email, password, first_name, last_name, status) values (5, 'james@yahoo.com', 'james#312', 'James', 'Smith', 1);
-- insert into managers (manager_id, manager_email, password, first_name, last_name, status) values (6, 'kyle@gmail.co.uk', 'kyle1', 'Kyle', 'Adams', 0);
--
--
-- /* CONTRACTOR DATA */
--
-- insert into contractors (contractor_id, contractor_email, password, first_name, last_name, managers_id, status) values (1, 'Peter@gmail.co.uk', 'kPeter1', 'Peter', 'Bridge', 1, 1);
-- insert into contractors (contractor_id, contractor_email, password, first_name, last_name, managers_id, status) values (2, 'Jason@gmail.co.uk', 'kPJason1', 'Jason', 'Bridger', 1, 1);
-- insert into contractors (contractor_id, contractor_email, password, first_name, last_name, managers_id, status) values (3, 'Zach@gmail.co.uk', 'kZach1', 'Zach', 'matters', 2, 1);
-- insert into contractors (contractor_id, contractor_email, password, first_name, last_name, managers_id, status) values (4, 'Sam@gmail.co.uk', 'kSam1#', 'Sam', 'brokes', 3, 1);
-- insert into contractors (contractor_id, contractor_email, password, first_name, last_name, managers_id, status) values (5, 'Euan@gmail.co.uk', 'Euan98', 'Euan', 'Evans', 4, 1);
-- insert into contractors (contractor_id, contractor_email, password, first_name, last_name, managers_id, status) values (6, 'Finn@gmail.co.uk', 'Finn8', 'Finn', 'Tomson', 4, 1);
-- insert into contractors (contractor_id, contractor_email, password, first_name, last_name, managers_id, status) values (7, 'Bill@gmail.co.uk', 'Bill3', 'Bill', 'Gates', 4, 1);
-- insert into contractors (contractor_id, contractor_email, password, first_name, last_name, managers_id, status) values (8, 'Mark@gmail.co.uk', 'Mark5', 'Mark', 'Zuck', 4, 1);
-- insert into contractors (contractor_id, contractor_email, password, first_name, last_name, managers_id, status) values (9, 'Mike@gmail.co.uk', 'Mike73', 'Mike', 'Jones', 4, 1);
-- insert into contractors (contractor_id, contractor_email, password, first_name, last_name, managers_id, status) values (10, 'Jane@gmail.co.uk', 'Jane72!', 'Jane', 'Smith', 5, 0);
-- insert into contractors (contractor_id, contractor_email, password, first_name, last_name, managers_id, status) values (11, 'Sarah@gmail.co.uk', 'Shar333', 'Sarah', 'Smith', 5, 1);
-- insert into contractors (contractor_id, contractor_email, password, first_name, last_name, managers_id, status) values (12, 'Mike@gmail.co.uk', 'Mike62626', 'Mike', 'Zuch', 5, 1);


--/* TIME SHEET DATA */

insert into timesheet(timesheet_id, admiral_role_id, number_days, overtime, start_date, end_date, date_submitted, notes, status) values (1,1,5,3,2019-11-11,2019-11-15,2019-11-16,,'Pending');
insert into timesheet(timesheet_id, admiral_role_id, number_days, overtime, start_date, end_date, date_submitted, notes, status) values (2,2,3,1,2019-11-1,2019-11-4,2019-11-7,'Wrong Overtime','Rejected');
insert into timesheet(timesheet_id, admiral_role_id, number_days, overtime, start_date, end_date, date_submitted, notes, status) values (3,6,2,0,2019-11-13,2019-11-15,2019-11-18,,'Pending');
insert into timesheet(timesheet_id, admiral_role_id, number_days, overtime, start_date, end_date, date_submitted, notes, status) values (4,2,4,0,2019-11-2,2019-11-6,2019-11-7,,'Approved');
insert into timesheet(timesheet_id, admiral_role_id, number_days, overtime, start_date, end_date, date_submitted, notes, status) values (5,9,5,0,2019-11-11,2019-11-15,2019-11-17,,'Pending');