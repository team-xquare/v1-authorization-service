CREATE TABLE IF NOT EXISTS `tbl_authority` (
    `id` BINARY(16) NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `version` INT NOT NULL,
    `state` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `tbl_access_management_user` (
    `id` BINARY(16) NOT NULL,
    `user_id` BINARY(16) NOT NULL,
    `authority_id` BINARY(16) NOT NULL,
    `version` INT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_tbl_authority_TO_tbl_access_management_user_1` FOREIGN KEY (`authority_id`) REFERENCES `tbl_authority`(`id`),
    CONSTRAINT `UNIQUE_user_id_AND_authority_id` UNIQUE (`user_id`, `authority_id`)
);

CREATE TABLE IF NOT EXISTS `tbl_outbox` (
    `id` BINARY(16) NOT NULL,
    `aggregate_id` BINARY(16) NOT NULL,
    `aggregate_type` VARCHAR(255) NOT NULL,
    `timestamp` TIMESTAMP NOT NULL DEFAULT NOW(),
    `type` VARCHAR(255) NOT NULL,
    `payload` JSON NOT NULL
)

-- DROP INDEX `access_management_user_index`
-- CREATE INDEX `access_management_user_index` ON `tbl_access_management_user` (`user_id`);