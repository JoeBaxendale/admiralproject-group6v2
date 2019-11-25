SET MODE MySQL;
SET IGNORECASE = TRUE;



-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `users`
(
    `user_id`                       INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `first_name`                    VARCHAR(45)  NOT NULL,
    `last_name`                     VARCHAR(45)  NOT NULL,
    `email`                         VARCHAR(45)  NOT NULL,
    `password`                      VARCHAR(70)  NOT NULL,
    `role_id`                       INT NOT NULL,
    `active`                        BIT NOT NULL,

    PRIMARY KEY (`user_id`),
    FOREIGN KEY (`role_id`) REFERENCES roles (`role_id`)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `roles`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `roles`
(
    `role_id`                   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `role_name`                 VARCHAR(45)  NOT NULL,
    `role_description`          VARCHAR(250) NOT NULL,

    PRIMARY KEY (`role_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `managers`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `managers`
(
    `manager_id`             INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`                INT UNSIGNED NOT NULL,

    PRIMARY KEY (`manager_id`),
    FOREIGN KEY (`user_id`) REFERENCES users (`user_id`)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `contractors`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `contractors`
(
    `contractor_id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`                INT UNSIGNED NOT NULL,
    `manager_id`             INT UNSIGNED NOT NULL,


    PRIMARY KEY (`contractor_id`),
    FOREIGN KEY (`user_id`) REFERENCES users (`user_id`),
    FOREIGN KEY (`manager_id`) REFERENCES managers (`manager_id`)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `time_sheets`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `timesheet`
(
    `timesheet_id`                   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `contractor_id`                  INT NOT NULL,
    `number_days`                    INT NOT NULL,
    `overtime`                       INT NOT NULL,
    `start_date`                     DATE NOT NULL,
    `end_date`                       DATE NOT NULL,
    `date_submitted`                 DATE NOT NULL,
    `notes`                          VARCHAR(100),
    `status`                         VARCHAR(25) NOT NULL,

    PRIMARY KEY (`timesheet_id`),
    FOREIGN KEY (`contractor_id`) REFERENCES contractors (`contractor_id`)

)
    ENGINE = InnoDB;