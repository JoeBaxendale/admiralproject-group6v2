SET MODE MySQL;
SET IGNORECASE = TRUE;

-- -----------------------------------------------------
-- Table `admiral_staff`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `admiral_staff`
(
    `admiral_staff_id`              INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `admiral_staff_email`           VARCHAR(45)  NOT NULL,
    `password`                      VARCHAR(45)  NOT NULL,
    `first_name`                    VARCHAR(45)  NOT NULL,
    `last_name`                     VARCHAR(45)  NOT NULL,

    PRIMARY KEY (`admiral_staff_id`)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `managers`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `managers`
(
    `manager_id`                   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `manager_email`                VARCHAR(45)  NOT NULL,
    `password`                      VARCHAR(45)  NOT NULL,
    `first_name`                    VARCHAR(45)  NOT NULL,
    `last_name`                     VARCHAR(45)  NOT NULL,
    `status`                        BIT  NOT NULL,


    PRIMARY KEY (`manager_id`)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `contractors`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `contractors`
(
    `contractor_id`                   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `contractor_email`                VARCHAR(45)  NOT NULL,
    `password`                      VARCHAR(45)  NOT NULL,
    `first_name`                    VARCHAR(45)  NOT NULL,
    `last_name`                     VARCHAR(45)  NOT NULL,
    `managers_id`                   INT  NOT NULL,
    `status`                        BIT  NOT NULL,


    PRIMARY KEY (`contractor_id`)
    FOREIGN KEY (`managers_id`) REFERENCES managers (`managers_id`)

)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `time_sheets`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `timesheet`
(
    `timesheet_id`                   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `contractor_id`                  INT  NOT NULL,
    `number_days`                    INT NOT NULL,
    `overtime`                       INT  NOT NULL,
    `start_date`                     DATE  NOT NULL,
    `end_date`                       DATE  NOT NULL,
    `date_submitted`                 DATE  NOT NULL,
    `notes`                          VARCHAR(100),
    `status`                         VARCHAR(25) NOT NULL,


    PRIMARY KEY (`timesheet_id`)
    FOREIGN KEY (`contractor_id`) REFERENCES contractors (`contractor_id`)

)
    ENGINE = InnoDB;
