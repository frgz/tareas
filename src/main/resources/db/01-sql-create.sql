DROP TABLE if exists tarea;
DROP TABLE if exists lista;
DROP TABLE if exists usuario;
DROP TABLE if exists role;
DROP TABLE if exists permiso;

CREATE TABLE permiso (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    codigo char(30) NOT NULL,
    nombre varchar(250) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
ALTER TABLE permiso ADD INDEX IX_permiso_codigo (codigo);

CREATE TABLE role (
    id int(11) unsigned NOT NULL,
    codigo char(15) NOT NULL,
    nombre varchar(250) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE role ADD INDEX IX_role_codigo (codigo);

CREATE TABLE role_permiso (
    role_id int(11) unsigned NOT NULL,
    permiso_id int(11) unsigned NOT NULL,
    PRIMARY KEY (role_id, permiso_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE role_permiso ADD CONSTRAINT FK_role_permiso_role FOREIGN KEY (role_id) REFERENCES role(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE role_permiso ADD CONSTRAINT FK_role_permiso_permiso FOREIGN KEY (permiso_id) REFERENCES permiso(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE role_permiso ADD INDEX IX_role_permiso_role(role_id);
ALTER TABLE role_permiso ADD INDEX IX_role_permiso_permiso(permiso_id);

CREATE TABLE usuario (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    email varchar(250) NOT NULL,
    password varchar(60) NOT NULL,
    nombre varchar(100) NOT NULL,
    apellidos varchar(100) NOT NULL,
    role_id int(11) unsigned NOT NULL,
    activo tinyint(1) unsigned NOT NULL,
    fecha_ultimo_acceso datetime,    
    fecha_creacion datetime NOT NULL,
    usuario_creacion varchar(250) NOT NULL,
    fecha_ultima_modificacion datetime,
    usuario_ultima_modificacion varchar(250),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
ALTER TABLE usuario ADD CONSTRAINT FK_usuario_role FOREIGN KEY (role_id) REFERENCES role(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE usuario ADD INDEX IX_usuario_role(role_id);
ALTER TABLE usuario ADD UNIQUE INDEX UQ_usuario_email (email);

CREATE TABLE lista (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    propietario_id int(11) unsigned NOT NULL,
    nombre varchar(100) NOT NULL,
    activa tinyint(1),
    fecha_creacion datetime NOT NULL,
    usuario_creacion varchar(250) NOT NULL,
    fecha_ultima_modificacion datetime,
    usuario_ultima_modificacion varchar(250),	
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
ALTER TABLE lista ADD CONSTRAINT FK_lista_propietario FOREIGN KEY (propietario_id) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE lista ADD INDEX IX_lista_nombre(nombre);
ALTER TABLE lista ADD INDEX IX_lista_propietario(propietario_id);
ALTER TABLE lista ADD INDEX IX_lista_propietario_nombre(propietario_id, nombre);
ALTER TABLE lista ADD UNIQUE UQ_lista_propietario_nombre(propietario_id, nombre);

CREATE TABLE tarea (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    lista_id int(11) unsigned NOT NULL,
    propietario_id int(11) unsigned NOT NULL,
    nombre varchar(100) NOT NULL,
    descripcion varchar(4000),
    completada tinyint(1),
    fecha_realizacion datetime,
    fecha_creacion datetime NOT NULL,
    usuario_creacion varchar(250) NOT NULL,
    fecha_ultima_modificacion datetime,
    usuario_ultima_modificacion varchar(250),    
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
ALTER TABLE tarea ADD CONSTRAINT FK_tarea_lista FOREIGN KEY (lista_id) REFERENCES lista(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE tarea ADD CONSTRAINT FK_tarea_propietario FOREIGN KEY (propietario_id) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE tarea ADD INDEX IX_tarea_lista(lista_id);
ALTER TABLE tarea ADD INDEX IX_tarea_propietario(propietario_id);
ALTER TABLE tarea ADD INDEX IX_tarea_nombre(nombre);
ALTER TABLE tarea ADD INDEX IX_tarea_propietario_nombre(propietario_id, nombre);
ALTER TABLE tarea ADD UNIQUE UQ_tarea_propietario_nombre(propietario_id, nombre);

-- CARGA DE DATOS INICIAL
insert into role(id, codigo, nombre) values (1, 'ROLE_ADMIN', 'Perfil para administradores');
insert into role(id, codigo, nombre) values (2, 'ROLE_USER', 'Perfil para usuarios');

insert into usuario(email, password, nombre, apellidos, role_id, activo, fecha_creacion, usuario_creacion) values ('root@localhost', '$2a$10$n.SoHnLHvahPU4xbxozOiOocqFLnCaR4EXLN.koLkl4DSpWuRBlGm', 'administrador', 'usuario', 1, 1, now(), 'root@localhost');
insert into usuario(email, password, nombre, apellidos, role_id, activo, fecha_creacion, usuario_creacion) values ('fabio@localhost', '$2a$10$n.SoHnLHvahPU4xbxozOiOocqFLnCaR4EXLN.koLkl4DSpWuRBlGm', 'fabio', 'dieguez', 2, 1, now(), 'root@localhost');


