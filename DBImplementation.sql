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
insert into users (user_id, first_name, last_name, email, password, role_id, active) values (2,'Peter', 'Bridge', 'peter@hotmail.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 1, 1);

insert into users (user_id, first_name, last_name, email, password, role_id, active) values (3,'Joe', 'Baxendale', 'joe@hotmail.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 2, 1);

insert into users (user_id, first_name, last_name, email, password, role_id, active) values (4,'Daniel', 'Addis', 'daniel@hotmail.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 3, 1);
insert into users (user_id, first_name, last_name, email, password, role_id, active) values (5,'Dexter', 'Gordon', 'dexter@hotmail.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 3, 1);
insert into users (user_id, first_name, last_name, email, password, role_id, active) values (6,'Dave', 'Lance', 'dave@hotmail.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 3, 1);
insert into users (user_id, first_name, last_name, email, password, role_id, active) values (7,'Sara', 'Beckket', 'sara@hotmail.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 3, 1);

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

INSERT INTO contractors(contractor_id,user_id,manager_id) VALUES (1,4,1);
INSERT INTO contractors(contractor_id,user_id,manager_id) VALUES (2,5,2);
INSERT INTO contractors(contractor_id,user_id,manager_id) VALUES (3,6,2);
INSERT INTO contractors(contractor_id,user_id,manager_id) VALUES (4,7,2);

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


-- -----------------------------------------------------
-- Table `timesheet` Triggers
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Table `timesheet_before_delete` Trigger
-- -----------------------------------------------------
DELIMITER //
CREATE TRIGGER `timesheet_before_delete` BEFORE DELETE ON `timesheet` FOR EACH ROW
/**
 * Before delete trigger for the timesheet to do the following:
 *   - Prevent deletion of timesheet entries that have been pending, approved
 *     or rejected.
 */
BEGIN
    DECLARE ErrorMessageText VARCHAR(500);
    -- Declare error conditions
    DECLARE Error_CantDeleteSubmittedApprovedOrRejected CONDITION FOR SQLSTATE '45000';

    -- Cannot delete a timesheet entry that is pending, approved or rejected
    IF (LOWER(OLD.status) = 'pending' OR
        LOWER(OLD.status) = 'approved' OR
        LOWER(OLD.status) = 'rejected') THEN
        -- Signal an error which will cause the delete statement to fail
        SET ErrorMessageText = CONCAT('Deleting ', LOWER(OLD.status),
                                      ' Timesheet record (id: ',
                                      OLD.timesheet_id,
                                      ') is not allowed');

        SIGNAL Error_CantDeleteSubmittedApprovedOrRejected
            SET MESSAGE_TEXT = ErrorMessageText;
    END IF;

END//
DELIMITER ;

-- -----------------------------------------------------
-- Table `timesheet_before_update` Trigger
-- -----------------------------------------------------
DELIMITER //
CREATE TRIGGER `timesheet_before_update` BEFORE UPDATE ON `timesheet` FOR EACH ROW
/**
 * Before update trigger to do the following:
 *   - Prevent changes to timesheet entries if it has been approved
 */
BEGIN
  DECLARE ErrorMessageText VARCHAR(500);
  -- Declare error conditions
  DECLARE Error_CantUpdateApproved CONDITION FOR SQLSTATE '45000';

  -- Cannot change a timesheet entry that has been approved
  IF LOWER(OLD.status) = 'approved' THEN
    -- Signal an error which will cause the update statement to fail
    SET ErrorMessageText = CONCAT('Updating an approved Timesheet record (id: ',
                                  OLD.timesheet_id,
                                  ') is not allowed');

    SIGNAL Error_CantUpdateApproved
      SET MESSAGE_TEXT = ErrorMessageText;
  END IF;

END//
DELIMITER ;


-- -----------------------------------------------------
-- STORED PROCEDURES
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Store Procedure: CreateTimeSheetUser
-- -----------------------------------------------------
/**
 * Stored procedure to create a user in the Admiral timesheet system.
 * Parameters:
 *   FirstName, LastName, Email, Password
 *   Role. This is the name of the role as stored in the roles table
 *   ManagerEmail. Only applicable if Role = "Contractor". The email address of the contractor's manager.
 *   UserId, ContractorId, ManagerId. OUT parameters which will be set to the relevant generated primary key ids
 * Error handling:
 *   Role name must exist in the roles table
 *   ManagerEmail must have exist in the database with a row in the users table and a row in the managers table.
 */

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `CreateTimesheetUser`(
    IN `FirstName` VARCHAR(50),
    IN `LastName` VARCHAR(50),
    IN `Email` VARCHAR(128),
    IN `Password` VARCHAR(100),
    IN `Role` VARCHAR(10),
    IN `ManagerEmail` VARCHAR(128),
    OUT `UserId` INT,
    OUT `ContractorId` INT,
    OUT `ManagerId` INT
)
    LANGUAGE SQL
    CONTAINS SQL
    COMMENT 'Create a user for the timesheet system'
BEGIN
    DECLARE ErrorMessageText VARCHAR(500);
    -- Declare error condition
    DECLARE Error_NoUserCreated CONDITION FOR SQLSTATE '45000';

    -- Insert the user (get the role id associated with the Role parameter)
    INSERT INTO users (first_name,last_name,email,password,role_id,active)
    SELECT FirstName, LastName, Email, Password, rl.role_id, 1
    FROM roles rl
    WHERE rl.role_name = Role;
    -- Check if the users row wasn't inserted (it may not have been if the supplied Role
    -- was incorrect). If it wasn't signal an error
    IF ROW_COUNT() = 0 THEN
        SET ErrorMessageText = CONCAT('Could not create the user. The supplied Role ( ',
                                      Role,
                                      ') may not be correct');

        SIGNAL Error_NoUserCreated
            SET MESSAGE_TEXT = ErrorMessageText;
    END IF;

    -- The user was created. Get the auto generated user id value
    SELECT LAST_INSERT_ID() INTO UserId;

    -- Additional inserts needed depending on specified Role (ManagerEmail parameter is only needed when Role is Contractor)
    CASE Role
        WHEN 'Contractor' THEN
            BEGIN
                -- Manager id key is obtained using the supplied manager email address via the users table
                INSERT INTO contractors (user_id, manager_id)
                SELECT UserID, mgr.manager_id
                FROM managers mgr
                         INNER JOIN users usr
                                    ON (mgr.user_id = usr.user_id)
                WHERE usr.email = ManagerEmail;
                -- Check if the contractor record was inserted. If it wasn't then this maybe due to an
                -- incorrect ManagerEmail and so signal an error.
                IF ROW_COUNT() = 0 THEN
                    SET ErrorMessageText = CONCAT('Could not create the user. The supplied ManagerEmail ( ',
                                                  ManagerEmail,
                                                  ') may not be correct');

                    SIGNAL Error_NoUserCreated
                        SET MESSAGE_TEXT = ErrorMessageText;
                END IF;

                -- The contractor was created. Get the auto generated contractor id value
                SELECT LAST_INSERT_ID() INTO ContractorId;

            END;
        WHEN 'Manager' THEN
            BEGIN
                INSERT INTO managers (user_id) VALUES (UserID);
                -- Get the auto generated manager id value
                SELECT LAST_INSERT_ID() INTO ManagerId;
            END;
        ELSE
            BEGIN
            END;
        END CASE;
END//
DELIMITER ;

-- --------------------------------------------------------
-- End of Database, Table, Trigger, Stored procedure script
-- --------------------------------------------------------

-- -----------------------------------------------------
-- TESTS
-- The tests must be run so that the script continues when errors occurs
-- -----------------------------------------------------
SET autocommit=OFF;
START TRANSACTION;

-- -----------------------------------------------------
-- CreateTimesheetUser stored procedure tests
-- -----------------------------------------------------

/**
 * Test successful creation of a user for each of the roles
 */
CALL CreateTimesheetUser ('ManagerFirstName', 'NewLastName', 'testmanager@address.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 'Manager', '',@UserId,@ContractorId,@ManagerId);
SELECT @UserId,@ContractorId,@ManagerId;
SELECT * FROM users WHERE user_id = @UserId;
SELECT * FROM managers WHERE manager_id = @ManagerId;
CALL CreateTimesheetUser ('ContractorFirstName', 'NewLastName', 'testcontractor@address.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 'Contractor', 'testmanager@address.com',@UserId,@ContractorId,@ManagerId);
SELECT @UserId,@ContractorId,@ManagerId;
SELECT * FROM users WHERE user_id = @UserId;
SELECT * FROM contractors WHERE contractor_id = @ContractorId;
CALL CreateTimesheetUser ('AdminFirstName', 'NewLastName', 'testadmin@address.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 'Admin', '',@UserId,@ContractorId,@ManagerId);
SELECT @UserId,@ContractorId,@ManagerId;
SELECT * FROM users WHERE user_id = @UserId;
ROLLBACK;

/**
 * Test error conditions for the CreateTimesheetUser stored procedure
 */
START TRANSACTION;
-- Test that SQL error thrown if incorrect role is passed
-- This is intentionally failling because no role has being supply
CALL CreateTimesheetUser ('AnotherFirstName', 'NewLastName', 'Email3@address.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 'Admn', '',@UserId,@ContractorId,@ManagerId);

-- Test that SQL error thrown if incorrect manager email is passed
CALL CreateTimesheetUser ('AnotherFirstName', 'NewLastName', 'Email3@address.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 'Contractor', 'incorrect@email.com',@UserId,@ContractorId,@ManagerId);
ROLLBACK;


/**
 * Test the timesheet table triggers
 * Script must be run so that it doesn't stop on errors
 */
START TRANSACTION;
-- First setup some test data. This includes users and timesheet entries
-- Test users
CALL CreateTimesheetUser ('Manager', 'LastName', 'manager@address.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 'Manager', '',@UserId,@ContractorId,@ManagerId);
CALL CreateTimesheetUser ('Contractor', 'LastName', 'contractor@address.com', '{bcrypt}$2a$10$B188I9BfwGLsWGU9eF4wPOV6O6z.MgEbNxcErNEKb8xwM.4ChBT7G', 'Contractor', 'manager@address.com',@UserId,@ContractorId,@ManagerId);

-- Test timesheet entries.

INSERT INTO timesheet(contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, status)
VALUES (@ContractorId,5,3,'2019-11-11','2019-11-15','2019-11-16','','Pending');
SELECT LAST_INSERT_ID() INTO @tsheetpending;
INSERT INTO timesheet(contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, status)
VALUES (@ContractorId,3,1,'2019-11-1','2019-11-4','2019-11-7','Wrong Overtime','Rejected');
SELECT LAST_INSERT_ID() INTO @tsheetrejected;
INSERT INTO timesheet(contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, status)
VALUES (@ContractorId,2,0,'2019-11-13','2019-11-15','2019-11-18','','Approved');
SELECT LAST_INSERT_ID() INTO @tsheetapproved;
SELECT * FROM timesheet WHERE timesheet_id IN (@tsheetpending,@tsheetrejected,@tsheetapproved);
-- Test timesheet updates. It should not be possible to update any Approved timesheets

-- 1: Update Pending timesheet
SELECT overtime FROM timesheet WHERE timesheet_id = @tsheetpending;
UPDATE timesheet
SET overtime = 0
WHERE timesheet_id = @tsheetpending;
SELECT overtime FROM timesheet WHERE timesheet_id = @tsheetpending;
-- 2: Update Rejected timesheet
SELECT number_days FROM timesheet WHERE timesheet_id = @tsheetrejected;
UPDATE timesheet
SET number_days = 6
WHERE timesheet_id = @tsheetrejected;
SELECT number_days FROM timesheet WHERE timesheet_id = @tsheetrejected;

-- Try to update the Approved timesheet. An SQL error will be raised.
SELECT * FROM timesheet WHERE STATUS = 'Approved';
UPDATE timesheet
SET number_days = 5,
    overtime = 0,
    start_date = '2010-01-01',
    end_date = '2010-01-01',
    notes = 'Update approved notes'
WHERE timesheet_id = @tsheetapproved;
-- The Approved rows will not have been updated
SELECT * FROM timesheet WHERE STATUS = 'Approved';

-- -----------------------------------------------------
-- QUERIES
-- -----------------------------------------------------

-- This code is from our project but has been modified to work outside JDBC --
SELECT * FROM managers INNER JOIN users ON managers.user_id = users.user_id;
SELECT email, password, '1' AS enabled FROM users WHERE email= 'karla@hotmail.com' AND active='1';
SELECT role_id, role_name FROM roles WHERE role_id IN (SELECT role_id FROM users WHERE email= 'karla@hotmail.com');

-- -----------------------------------------------------
-- FUNCTIONS 
-- -----------------------------------------------------

SELECT role_name, length(role_name) FROM roles;
SELECT COUNT(*) AS 'Number of users' FROM users;
SELECT MIN(user_id) FROM users;



ROLLBACK;
SET autocommit=ON;



