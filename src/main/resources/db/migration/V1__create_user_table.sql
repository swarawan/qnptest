CREATE TABLE IF NOT EXISTS `user_data`
(
    `id`           bigint       NOT NULL AUTO_INCREMENT,
    `name`         varchar(256) NOT NULL,
    `email`        varchar(255) NOT NULL,
    `address`      varchar(255) NOT NULL,
    `phone_number` varchar(20)  NOT NULL,
    `is_active`    boolean      NOT NULL,
    `created_at`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`   datetime              DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at`   datetime              DEFAULT NULL,
    PRIMARY KEY (`id`)
);