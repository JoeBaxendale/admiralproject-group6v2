DROP SCHEMA if exists admiral;

CREATE SCHEMA admiral;
 
 USE admiral;

DROP TABLE IF EXISTS timesheet;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS managers;
DROP TABLE IF EXISTS contractors;

-- -----------------------------------------------------
-- Table `roles`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS roles
(
	role_id                   INT(5) NOT NULL AUTO_INCREMENT,
    role_name                 VARCHAR(45)  NOT NULL,
    role_description          VARCHAR(250) NOT NULL,

    PRIMARY KEY (role_id)
)
    ENGINE = InnoDB;
    
insert into roles (role_id, role_name, role_description) values (1, 'Manager', 'This user has ultimate rights for everything');
insert into roles (role_id, role_name, role_description) values (2, 'Admin', 'This user has admin rights for administrative work');
insert into roles (role_id, role_name, role_description) values (3, 'Contractor', 'This user has access to site, after login');

-- -----------------------------------------------------
-- Table `users``
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS users
(
    user_id                       INT(5) NOT NULL AUTO_INCREMENT,
    first_name                    VARCHAR(45)  NOT NULL,
    last_name                     VARCHAR(45)  NOT NULL,
    email                         VARCHAR(45)  NOT NULL,
    `password`                    VARCHAR(180)  NOT NULL,
    role_id                       INT NOT NULL,
    `active`                      BIT NOT NULL,

    CONSTRAINT pk_users PRIMARY KEY (user_id),
    CONSTRAINT fk_roles FOREIGN KEY (role_id) REFERENCES roles(role_id) 
)
	ENGINE = InnoDB;

    
insert into users (user_id, first_name, last_name, email, password, role_id, active) values (1,'Karla', 'Mendivelso', 'karla@hotmail.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 1, 1);
insert into users (user_id, first_name, last_name, email, password, role_id, active) values (2,'Joe', 'Baxendale', 'joe@hotmail.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 2, 1);
insert into users (user_id, first_name, last_name, email, password, role_id, active) values (3,'Daniel', 'Addis', 'daniel@hotmail.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 3, 1);
insert into users (user_id, first_name, last_name, email, password, role_id, active) values (4,'Dexter', 'Gordon', 'dexter@hotmail.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 3, 1);

-- -----------------------------------------------------
-- Table `managers`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS managers
(
    manager_id             INT(5) NOT NULL AUTO_INCREMENT,
    user_id                INT(5) NOT NULL,

    CONSTRAINT pk_managers PRIMARY KEY (manager_id),
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users(user_id)
)
    ENGINE = InnoDB;

insert into managers(manager_id, user_id) values(1,1);
insert into managers(manager_id, user_id) values(2,2);
insert into managers(manager_id, user_id) values(3,3);
insert into managers(manager_id, user_id) values(4,4);

-- -----------------------------------------------------
-- Table `contractors`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS contractors
(
    contractor_id          INT(5) NOT NULL AUTO_INCREMENT,
    user_id                INT(5)  NOT NULL,
    manager_id             INT(5)  NOT NULL,


    PRIMARY KEY (`contractor_id`),
    FOREIGN KEY (`user_id`) REFERENCES users (`user_id`)
)
    ENGINE = InnoDB;

INSERT INTO contractors(contractor_id,user_id,manager_id) VALUES (1,1,2);
INSERT INTO contractors(contractor_id,user_id,manager_id) VALUES (2,2,1);
INSERT INTO contractors(contractor_id,user_id,manager_id) VALUES (3,3,4);
INSERT INTO contractors(contractor_id,user_id,manager_id) VALUES (4,4,3);

-- -----------------------------------------------------
-- Table `time_sheets`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS timesheet
(
	timesheet_id                   INT(5) NOT NULL AUTO_INCREMENT,
    contractor_id                  INT(5)  NOT NULL,
    number_days                    INT NOT NULL,
    overtime                       INT NOT NULL,
    start_date                     DATE NOT NULL,
    end_date                       DATE NOT NULL,
    date_submitted                 DATE NOT NULL,
    notes                          VARCHAR(100),
    `status`                       VARCHAR(25) NOT NULL,

    PRIMARY KEY (timesheet_id),
    FOREIGN KEY (contractor_id) REFERENCES contractors (contractor_id)

)
	ENGINE = InnoDB;

insert into timesheet(timesheet_id, contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, `status`) values (1,1,5,3,'2019-11-11','2019-11-15','2019-11-16','','Pending');
insert into timesheet(timesheet_id, contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, `status`) values (2,2,3,1,'2019-11-1','2019-11-4','2019-11-7','Wrong Overtime','Rejected');
insert into timesheet(timesheet_id, contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, `status`) values (3,4,2,0,'2019-11-13','2019-11-15','2019-11-18','','Pending');
insert into timesheet(timesheet_id, contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, `status`) values (4,2,4,0,'2019-11-2','2019-11-6','2019-11-7','','Approved');
insert into timesheet(timesheet_id, contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, `status`) values (5,3,5,0,'2019-11-11','2019-11-15','2019-11-17','','Pending');