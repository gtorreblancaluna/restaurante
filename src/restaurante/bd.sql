DROP DATABASE IF EXISTS restaurante;

CREATE DATABASE IF NOT EXISTS restaurante;
USE restaurante;

CREATE TABLE empleado (
  id_empleado int(11) NOT NULL auto_increment,
  nombre varchar(45) default NULL,
  apellidos varchar(45) default NULL,
  tel_cel varchar(45) default NULL,
  tel_casa varchar(45) default NULL,
  puesto varchar(45) default NULL,
  contraseña varchar(45) default NULL,
  administrador varchar(45) default NULL,
  ventas varchar(2) default NULL,
  caja varchar(2) default NULL,
  insumos varchar(2) default NULL,
  compras varchar(2) default NULL,
  utilerias varchar(2) default NULL,
  PRIMARY KEY  (id_empleado)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE clientes (
  id_clientes int(11) NOT NULL auto_increment,
  id_empleado int(11) default NULL,
  nombre varchar(45) default NULL,
  apellidos varchar(45) default NULL,
  tel_movil varchar(45) default NULL,
  tel_fijo varchar(45) default NULL,
  rfc varchar(45) default NULL,
  razon_social varchar(45) default NULL,
  calle varchar(45) default NULL,
  noExt varchar(45) default NULL,
  noInt varchar(45) default NULL,
  colonia varchar(45) default NULL,
  cp varchar(45) default NULL,
  localidad varchar(45) default NULL,
  delegacion varchar(45) default NULL,
  estado varchar(45) default NULL,
  pais varchar(45) default NULL,
  email varchar(45) default NULL,
  PRIMARY KEY  (id_clientes),
  KEY fk_clientes_empleado1_idx (id_empleado),
  CONSTRAINT fk_clientes_empleado1 FOREIGN KEY (id_empleado) REFERENCES empleado (id_empleado) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE caja (
  id_caja int(11) NOT NULL auto_increment,
  id_empleado int(11) default NULL,
  fecha_apertura varchar(45) default NULL,
  fecha_cierre varchar(45) default NULL,
  abierta int(11) default NULL,
  nota varchar(45) default NULL,
  PRIMARY KEY  (id_caja),
  KEY fk_caja_empleado1_idx (id_empleado),
  CONSTRAINT fk_caja_empleado1 FOREIGN KEY (id_empleado) REFERENCES empleado (id_empleado) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8;

CREATE TABLE categorias (
  id_categorias int(11) NOT NULL auto_increment,
  nombre_categoria varchar(45) default NULL,
  fecha_alta varchar(45) default NULL,
  empleado_id int(11) default NULL,
  PRIMARY KEY  (id_categorias),
  KEY fk_productos_empleado_idx (empleado_id),
  CONSTRAINT fk_productos_empleado FOREIGN KEY (empleado_id) REFERENCES empleado (id_empleado) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

CREATE TABLE compras (
  id_compras int(11) NOT NULL auto_increment,
  id_empleado int(11) default NULL,
  fecha varchar(45) default NULL,
  proveedor varchar(45) default NULL,
  nota1 varchar(45) default NULL,
  PRIMARY KEY  (id_compras),
  KEY fk_compras_empleado1_idx (id_empleado),
  CONSTRAINT fk_compras_empleado1 FOREIGN KEY (id_empleado) REFERENCES empleado (id_empleado) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE detalle_caja (
  id_detalle_caja int(11) NOT NULL auto_increment,
  id_caja int(11) default NULL,
  ingreso int(11) default NULL,
  monto float default NULL,
  descripcion varchar(45) default NULL,
  PRIMARY KEY  (id_detalle_caja),
  KEY fk_detalle_caja_caja1_idx (id_caja),
  CONSTRAINT fk_detalle_caja_caja1 FOREIGN KEY (id_caja) REFERENCES caja (id_caja) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=971 DEFAULT CHARSET=utf8;

CREATE TABLE detalle_categorias (
  id_detalle_categorias int(11) NOT NULL auto_increment,
  id_categorias int(11) default NULL,
  descripcion varchar(45) default NULL,
  precio_publico float default NULL,
  activo varchar(2) default NULL,
  PRIMARY KEY  (id_detalle_categorias),
  KEY fk_detalle_productos_productos1_idx (id_categorias),
  CONSTRAINT fk_detalle_productos_productos1 FOREIGN KEY (id_categorias) REFERENCES categorias (id_categorias) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8;

CREATE TABLE consumibles_platillos (
  id_consumibles_platillos int(11) NOT NULL auto_increment,
  id_insumos int(11) default NULL,
  id_detalle_cat int(11) default NULL,
  cantidad float(4,2) NOT NULL,
  PRIMARY KEY  (id_consumibles_platillos),
  KEY fk_consumibles_platillos_insumos1_idx (id_insumos),
  KEY fk_consumibles_platillos_detalle_categorias1_idx (id_detalle_cat),
  CONSTRAINT fk_consumibles_platillos_detalle_categorias1 FOREIGN KEY (id_detalle_cat) REFERENCES detalle_categorias (id_detalle_categorias) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_consumibles_platillos_insumos1 FOREIGN KEY (id_insumos) REFERENCES insumos (id_insumos) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8;

CREATE TABLE insumos (
  id_insumos int(11) NOT NULL auto_increment,
  nombre_insumo varchar(45) default NULL,
  unidad_medida varchar(45) default NULL,
  ultimo_costo float default NULL,
  stock float default NULL,
  activo varchar(2) default NULL,
  PRIMARY KEY  (id_insumos)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;


CREATE TABLE detalle_compras (
  id_detalle_compras int(11) NOT NULL auto_increment,
  id_compras int(11) default NULL,
  id_insumos int(11) default NULL,
  cantidad float default NULL,
  ultimo_costo float default NULL,
  notas varchar(45) default NULL,
  PRIMARY KEY  (id_detalle_compras),
  KEY fk_detalle_compras_compras1_idx (id_compras),
  KEY fk_detalle_compras_insumos1_idx (id_insumos),
  CONSTRAINT fk_detalle_compras_compras1 FOREIGN KEY (id_compras) REFERENCES compras (id_compras) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_detalle_compras_insumos1 FOREIGN KEY (id_insumos) REFERENCES insumos (id_insumos) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE detalle_venta (
  id_detalle_venta int(11) NOT NULL auto_increment,
  id_venta int(11) default NULL,
  cantidad float default NULL,
  id_detalle_categorias int(11) default NULL,
  nota1 varchar(45) default NULL,
  nota2 varchar(45) default NULL,
  PRIMARY KEY  (id_detalle_venta),
  KEY fk_detalle_venta_venta1_idx (id_venta),
  KEY fk_detalle_venta_detalle_categorias1_idx (id_detalle_categorias),
  CONSTRAINT fk_detalle_venta_detalle_categorias1 FOREIGN KEY (id_detalle_categorias) REFERENCES detalle_categorias (id_detalle_categorias) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_detalle_venta_venta1 FOREIGN KEY (id_venta) REFERENCES venta (id_venta) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12471 DEFAULT CHARSET=utf8;

CREATE TABLE email (
  id_email int(11) NOT NULL auto_increment,
  cuenta_correo varchar(45) default NULL,
  contraseña varchar(45) default NULL,
  servidor varchar(45) default NULL,
  puerto varchar(45) default NULL,
  utiliza_conexion_TLS int(11) default NULL,
  utiliza_autenticacion int(11) default NULL,
  gmail int(11) default NULL,
  hotmail int(11) default NULL,
  personalizada int(11) default NULL,
  PRIMARY KEY  (id_email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mesa (
  id_mesa int(11) NOT NULL auto_increment,
  descripcion varchar(45) default NULL,
  estado varchar(45) default NULL,
  activo varchar(2) default NULL,
  orden int(11) default NULL,
  PRIMARY KEY  (id_mesa)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;


CREATE TABLE datos_generales (
  id_datos_generales int(11) NOT NULL auto_increment,
  razon_social varchar(45) default NULL,
  calle varchar(45) default NULL,
  no_ext varchar(10) default NULL,
  no_int varchar(10) default NULL,
  colonia varchar(45) default NULL,
  ciudad varchar(45) default NULL,
  estado varchar(45) default NULL,
  RFC varchar(45) default NULL,
  pais varchar(45) default NULL,
  cod_postal varchar(45) default NULL,
  referencia varchar(65) default NULL,
  tel_1 varchar(45) default NULL,
  tel_2 varchar(45) default NULL,
  tel_3 varchar(45) default NULL,
  leyenda1 varchar(45) default NULL,
  leyenda2 varchar(45) default NULL,
  leyenda3 varchar(45) default NULL,
  leyenda4 varchar(45) default NULL,
  leyenda5 varchar(45) default NULL,
  logo longblob,
  serie varchar(10) default NULL,
  folio_inicial int(11) default NULL,
  folio_final int(11) default NULL,
  folio_actual int(11) default NULL,
  impuesto varchar(45) default NULL,
  tasa_impuesto float(7,2) default NULL,
  lugar_expedicion varchar(45) default NULL,
  regimen_fiscal varchar(45) default NULL,
  ruta_pdf varchar(200) default NULL,
  ruta_xml varchar(200) default NULL,
  ruta_entrada varchar(200) default NULL,
  PRIMARY KEY  (id_datos_generales)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/* 2018.01.03 alteraciones a la bd */

CREATE TABLE estado_venta (
  id_estado_venta int(11) NOT NULL auto_increment,
  descripcion varchar(45) DEFAULT NULL,
  PRIMARY KEY  (id_estado_venta)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE venta (
  id_venta int(11) NOT NULL auto_increment,
  id_empleado int(11) default NULL,
  id_mesa int(11) default NULL,
  comensales varchar(6) default NULL,
  fecha varchar(45) default NULL,
  nota1 varchar(150) default NULL,
  nota2 varchar(150) default NULL,
  preventa varchar(45) default NULL,
  id_caja int(11) default NULL,
  facturado int(11) default NULL,
  id_clientes int(11) default NULL,
  cargo float default NULL,
  descuento float default NULL,
  propina float default NULL,
  -- se agrega el estado de la venta
  id_estado_venta INT(11) DEFAULT NULL,
  PRIMARY KEY  (id_venta),
  KEY fk_venta_empleado1_idx (id_empleado),
  KEY fk_venta_caja1_idx (id_caja),
  KEY fk_venta_clientes1_idx (id_clientes),
  KEY FK_venta_mesa1 (id_mesa),
  KEY FK_estado_venta_mesa1_idx (id_estado_venta), 
  CONSTRAINT fk_venta_caja1 FOREIGN KEY (id_caja) REFERENCES caja (id_caja) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_venta_clientes1 FOREIGN KEY (id_clientes) REFERENCES clientes (id_clientes) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_venta_empleado1 FOREIGN KEY (id_empleado) REFERENCES empleado (id_empleado) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_venta_mesa1 FOREIGN KEY (id_mesa) REFERENCES mesa (id_mesa) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_estado_venta1 FOREIGN KEY (id_estado_venta) REFERENCES estado_venta (id_estado_venta) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1807 DEFAULT CHARSET=utf8;

INSERT INTO estado_venta (descripcion) VALUES ('Activo');
INSERT INTO estado_venta (descripcion) VALUES ('Pendiente');
INSERT INTO estado_venta (descripcion) VALUES ('Cancelado');

-- debemos aplicar este comando en caso de que la bd ya tenga datos y/o este en funcionamiento

ALTER TABLE venta
  ADD COLUMN id_estado_venta INT (11),
  ADD FOREIGN KEY FK_estado_venta1(id_estado_venta) REFERENCES estado_venta(id_estado_venta) ON DELETE CASCADE;