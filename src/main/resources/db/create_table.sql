CREATE TABLE tarea
(
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `nombre` varchar(250) NOT NULL,
    `realizada` tinyint(1),
    `fecha_realizacion` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


ALTER TABLE `tarea` ADD INDEX `ix_tarea_nombre`(`nombre`);
