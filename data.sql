CREATE DATABASE IF NOT EXISTS example;

USE example;

INSERT IGNORE INTO `permissions` (permissionId, permissionName, description)
VALUES (1, 'GetAllUser', 'Allow to use GetAllUser API'),
       (2, 'GetCurrentUser', 'Allow to use GetCurrentUser API'),
       (3, 'AddPermission', 'Allow to use AddPermissionApi'),
       (4, 'DeletePermission', 'Allow to use DeletePermission API');

INSERT IGNORE INTO `roles` (roleId, roleName)
VALUES (1, 'Admin'),
       (2, 'Member'),
       (3, 'Custom');

INSERT IGNORE INTO `rolepermissions` (rolePermissionId, roleId, permissionId)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3),
       (4, 1, 4),
       (5, 2, 1),
       (6, 2, 2),
       (11, 3, 2);

INSERT IGNORE INTO `users` (userId, userName, password, description)
VALUES (1, 'admin', '123456', 'This is an admin user'),
       (2, 'linh.hua', '123456', 'This is an member');

INSERT IGNORE INTO `userroles` (userRoleId, userId, roleId)
VALUES (1, 1, 1),
       (2, 2, 2);

