USE example;

CREATE TABLE `permissions`
(
    `permissionId`   int(11) NOT NULL AUTO_INCREMENT,
    `permissionName` varchar(100) NOT NULL,
    `description`    text,
    PRIMARY KEY (`permissionId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `permissions`
VALUES (1, 'GetAllUser', 'Allow to use GetAllUser API'),
       (2, 'GetCurrentUser', 'Allow to use GetCurrentUser API'),
       (3, 'AddPermission', 'Allow to use AddPermissionApi'),
       (4, 'DeletePermission', 'Allow to use DeletePermission API');



DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`
(
    `roleId`   int(11) NOT NULL AUTO_INCREMENT,
    `roleName` varchar(100) NOT NULL,
    PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `roles`
VALUES (1, 'Admin'),
       (2, 'Member'),
       (3, 'Custom');



CREATE TABLE `rolepermissions`
(
    `rolePermissionId` int(11) NOT NULL AUTO_INCREMENT,
    `roleId`           int(11) NOT NULL,
    `permissionId`     int(11) NOT NULL,
    PRIMARY KEY (`rolePermissionId`),
    UNIQUE KEY `rolepermissions_unique` (`roleId`,`permissionId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

INSERT INTO `rolepermissions`
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3),
       (4, 1, 4),
       (5, 2, 1),
       (6, 2, 2),
       (11, 3, 2);



DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `userId`      int(11) NOT NULL AUTO_INCREMENT,
    `userName`    varchar(100) NOT NULL,
    `password`    varchar(255) NOT NULL,
    `description` text,
    PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `users`
VALUES (1, 'admin', '123456', 'This is an admin user'),
       (2, 'linh.hua', '123456', 'This is an member');



DROP TABLE IF EXISTS `userroles`;
CREATE TABLE `userroles`
(
    `userRoleId` int(11) NOT NULL AUTO_INCREMENT,
    `userId`     int(11) NOT NULL,
    `roleId`     int(11) NOT NULL,
    PRIMARY KEY (`userRoleId`),
    UNIQUE KEY `userroles_unique` (`userId`,`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `userroles`
VALUES (1, 1, 1),
       (2, 2, 2);

