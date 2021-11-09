INSERT INTO `role` (`description`, `name`)
VALUES ('ROLE_ADMIN', 'ADMIN');
INSERT INTO `role` (`description`, `name`)
VALUES ('ROLE_WORKER', 'WORKER');
INSERT INTO `role` (`description`, `name`)
VALUES ('ROLE_VISITOR', 'VISITOR');

INSERT INTO `user` (`name`, `surname`, `username`, `email`, `password`, `role_id`)
VALUES ('admin', 'admin', 'admin', 'admin@admin.com', 'admin123', '1');