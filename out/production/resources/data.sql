/* USERS DATA */
insert into users (user_id, first_name, last_name, email, password, role_id, status) values (1,'Karla', 'Mendivelso', 'karla@hotmail.com', 'password', 1, 1);
insert into users (user_id, first_name, last_name, email, password, role_id, status) values (2,'Joe', 'Baxendale', 'joe@hotmail.com', 'password', 1, 1);
insert into users (user_id, first_name, last_name, email, password, role_id, status) values (3,'Daniel', 'Addis', 'daniel@hotmail.com', 'password', 1, 1);
insert into users (user_id, first_name, last_name, email, password, role_id, status) values (4,'Dexter', 'Gordon', 'dexter@hotmail.com', 'password', 1, 1);

/* ROLE DATA */
insert into roles (role_id, role_name, role_description) values (1, 'Manager', 'This user has ultimate rights for everything');
insert into roles (role_id, role_name, role_description) values (2, 'Admin', 'This user has admin rights for administrative work');
insert into roles (role_id, role_name, role_description) values (3, 'Contractor', 'This user has access to site, after login');

--/* TIME SHEET DATA */

insert into timesheet(timesheet_id, contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, status) values (1,1,5,3,2019-11-11,2019-11-15,2019-11-16,,'Pending');
insert into timesheet(timesheet_id, contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, status) values (2,2,3,1,2019-11-1,2019-11-4,2019-11-7,'Wrong Overtime','Rejected');
insert into timesheet(timesheet_id, contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, status) values (3,6,2,0,2019-11-13,2019-11-15,2019-11-18,,'Pending');
insert into timesheet(timesheet_id, contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, status) values (4,2,4,0,2019-11-2,2019-11-6,2019-11-7,,'Approved');
insert into timesheet(timesheet_id, contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, status) values (5,9,5,0,2019-11-11,2019-11-15,2019-11-17,,'Pending');
