DROP TABLE if exists tarea;
DROP TABLE if exists lista;
DROP TABLE if exists usuario;
DROP TABLE if exists role;

CREATE TABLE role (
    `id` int(11) unsigned NOT NULL,
    `codigo` char(15) NOT NULL,
    `nombre` varchar(250) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `role` ADD INDEX `IX_role_codigo` (`codigo`);

CREATE TABLE usuario (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `email` varchar(250) NOT NULL,
    `password` varchar(20) NOT NULL,
    `nombre` varchar(100) NOT NULL,
    `apellidos` varchar(100) NOT NULL,
    `role_id` int(11) unsigned NOT NULL,    
    `fecha_creacion` datetime NOT NULL,
    `usuario_creacion` varchar(250) NOT NULL,
    `fecha_ultima_modificacion` datetime,
    `usuario_ultima_modificacion` varchar(250),        
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
ALTER TABLE `usuario` ADD CONSTRAINT `FK_usuario_role` FOREIGN KEY (`role_id`) REFERENCES role(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `usuario` ADD INDEX `IX_usuario_role`(`role_id`);
ALTER TABLE `usuario` ADD UNIQUE INDEX `UQ_usuario_email` (`email`);

CREATE TABLE lista (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `nombre` varchar(100) NOT NULL,
    `activa` tinyint(1),
    `fecha_creacion` datetime NOT NULL,
    `usuario_creacion` varchar(250) NOT NULL,
    `fecha_ultima_modificacion` datetime,
    `usuario_ultima_modificacion` varchar(250),	
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
ALTER TABLE `lista` ADD INDEX `IX_lista_nombre`(`nombre`);

CREATE TABLE tarea (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `lista_id` int(11) unsigned NOT NULL,
    `propietario_id` int(11) unsigned NOT NULL,
    `nombre` varchar(100) NOT NULL,
    `descripcion` varchar(4000),
    `completada` tinyint(1),
    `fecha_realizacion` datetime,
    `fecha_creacion` datetime NOT NULL,
    `usuario_creacion` varchar(250) NOT NULL,
    `fecha_ultima_modificacion` datetime,
    `usuario_ultima_modificacion` varchar(250),    
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
ALTER TABLE `tarea` ADD CONSTRAINT `FK_tarea_lista` FOREIGN KEY (`lista_id`) REFERENCES lista(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `tarea` ADD CONSTRAINT `FK_tarea_propietario` FOREIGN KEY (`propietario_id`) REFERENCES usuario(`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `tarea` ADD INDEX `IX_tarea_lista`(`lista_id`);
ALTER TABLE `tarea` ADD INDEX `IX_tarea_propietario`(`propietario_id`);
ALTER TABLE `tarea` ADD INDEX `IX_tarea_nombre`(`nombre`);



-- CARGA DE DATOS INICIAL
insert into `role`(`id`, `codigo`, `nombre`) values (1, 'ADMINISTRADOR', 'Perfil para administradores');
insert into `role`(`id`, `codigo`, `nombre`) values (2, '	USUARIO', 'Perfil para usuarios');

insert into `usuario`(`email`, `password`, `nombre`, `apellidos`, `role_id`, `fecha_creacion`, `usuario_creacion`) values ('root@localhost', 's3cret', 'administrador', 'usuario', 1, now(), 'root@localhost');


select * from usuario