DROP TABLE if exists usuario;
DROP TABLE if exists role;
DROP TABLE if exists tarea;

CREATE TABLE role (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `codigo` char(15) NOT NULL,
    `nombre` varchar(250) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  AUTO_INCREMENT=1;
ALTER TABLE `role` ADD INDEX `IX_role_codigo` (`codigo`);

CREATE TABLE usuario (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `email` varchar(250) NOT NULL,
    `password` varchar(20) NOT NULL,
    `nombre` varchar(100) NOT NULL,
    `apellidos` varchar(100) NOT NULL,
    `role_id` int(11) unsigned NOT NULL,    
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
ALTER TABLE `usuario` ADD INDEX `IX_usuario_email`(`email`);
ALTER TABLE `usuario` ADD UNIQUE INDEX `UQ_usuario_email` (`email`);
ALTER TABLE `usuario` ADD CONSTRAINT `FK_usuario_role` FOREIGN KEY (`role_id`) REFERENCES role(`id`) ON UPDATE CASCADE ON DELETE CASCADE;

CREATE TABLE tarea (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `nombre` varchar(250) NOT NULL,
    `realizada` tinyint(1),
    `fecha_realizacion` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
ALTER TABLE `tarea` ADD INDEX `IX_tarea_nombre`(`nombre`);