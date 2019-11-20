SET MODE MySQL;
SET IGNORECASE = TRUE;



-- -----------------------------------------------------
-- Table `admiral_user`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `admiral_user`
(
    `admiral_user_id`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `first_name`                    VARCHAR(45)  NOT NULL,
    `last_name`                     VARCHAR(45)  NOT NULL,
    `admiral_user_email`            VARCHAR(45)  NOT NULL,
    `password`                      VARCHAR(45)  NOT NULL,
    `status`                        BOOLEAN  NOT NULL,

    PRIMARY KEY (`admiral_user_id`)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `admiral_role`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `admiral_role`
(
    `admiral_role_id`                   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `admiral_role_name`                 VARCHAR(45)  NOT NULL,
    `admiral_role_description`          VARCHAR(250)  NOT NULL,


    PRIMARY KEY (`admiral_role_id`)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `admiral_user_role`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `admiral_user_role`
(
    `admiral_user_id`                   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `admiral_role_id`                   INT UNSIGNED NOT NULL AUTO_INCREMENT,

    FOREIGN KEY (`admiral_user_id`) REFERENCES admiral_user (`admiral_user_id`),
    FOREIGN KEY (`admiral_role_id`) REFERENCES admiral_role (`admiral_role_id`)

)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `time_sheets`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `timesheet`
(
    `timesheet_id`                   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `admiral_role_id`                  INT  NOT NULL,
    `number_days`                    INT NOT NULL,
    `overtime`                       INT  NOT NULL,
    `start_date`                     DATE  NOT NULL,
    `end_date`                       DATE  NOT NULL,
    `date_submitted`                 DATE  NOT NULL,
    `notes`                          VARCHAR(100),
    `status`                         VARCHAR(25) NOT NULL,


    PRIMARY KEY (`timesheet_id`),
    FOREIGN KEY (`admiral_role_id`) REFERENCES admiral_role (`admiral_role_id`)

)
    ENGINE = InnoDB;

-- -- -----------------------------------------------------
-- -- Table `admiral_staff`
-- -- -----------------------------------------------------
--
-- CREATE TABLE IF NOT EXISTS `admiral_staff`
-- (
--     `admiral_staff_id`              INT UNSIGNED NOT NULL AUTO_INCREMENT,
--     `admiral_staff_email`           VARCHAR(45)  NOT NULL,
--     `password`                      VARCHAR(45)  NOT NULL,
--     `first_name`                    VARCHAR(45)  NOT NULL,
--     `last_name`                     VARCHAR(45)  NOT NULL,
--
--     PRIMARY KEY (`id`)
-- )
--     ENGINE = InnoDB;
--
-- -- -----------------------------------------------------
-- -- Table `managers`
-- -- -----------------------------------------------------
--
-- CREATE TABLE IF NOT EXISTS `managers`
-- (
--     `manager_id`                   INT UNSIGNED NOT NULL AUTO_INCREMENT,
--     `manager_email`                VARCHAR(45)  NOT NULL,
--     `password`                      VARCHAR(45)  NOT NULL,
--     `first_name`                    VARCHAR(45)  NOT NULL,
--     `last_name`                     VARCHAR(45)  NOT NULL,
--     `status`                        BIT  NOT NULL,
--
--
--     PRIMARY KEY (`managers_id`)
-- )
--     ENGINE = InnoDB;
--
-- -- -----------------------------------------------------
-- -- Table `contractors`
-- -- -----------------------------------------------------
--
-- CREATE TABLE IF NOT EXISTS `contractors`
-- (
--     `contractor_id`                   INT UNSIGNED NOT NULL AUTO_INCREMENT,
--     `contractor_email`                VARCHAR(45)  NOT NULL,
--     `password`                      VARCHAR(45)  NOT NULL,
--     `first_name`                    VARCHAR(45)  NOT NULL,
--     `last_name`                     VARCHAR(45)  NOT NULL,
--     `managers_id`                   INT  NOT NULL,
--     `status`                        BIT  NOT NULL,
--
--
--     PRIMARY KEY (`contractor_id`)
--     FOREIGN KEY (`managers_id`) REFERENCES managers (`managers_id`)
--
-- )
--     ENGINE = InnoDB;
--
--
-- -- -----------------------------------------------------
-- -- Table `time_sheets`
-- -- -----------------------------------------------------
--
-- CREATE TABLE IF NOT EXISTS `timesheet`
-- (
--     `timesheet_id`                   INT UNSIGNED NOT NULL AUTO_INCREMENT,
--     `contractor_id`                  INT  NOT NULL,
--     `number_days`                    INT NOT NULL,
--     `overtime`                       INT  NOT NULL,
--     `start_date`                     DATE  NOT NULL,
--     `end_date`                       DATE  NOT NULL,
--     `date_submitted`                 DATE  NOT NULL,
--     `notes`                          VARCHAR(100),
--     `status`                         VARCHAR(25) NOT NULL,
--
--
--     PRIMARY KEY (`timesheet_id`)
--     FOREIGN KEY (`contractor_id`) REFERENCES contractors (`contractor_id`)
--
-- )
--     ENGINE = InnoDB;
