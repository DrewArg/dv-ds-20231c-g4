
DROP TABLE IF EXISTS ventas_tarjeta;

DROP TABLE IF EXISTS ventas_efectivo;

DROP TABLE IF EXISTS venta_items;

DROP TABLE IF EXISTS ventas;

DROP TABLE IF EXISTS prendas_nueva;

DROP TABLE IF EXISTS prendas_liquidacion;

DROP TABLE IF EXISTS prendas_promocion;

DROP TABLE IF EXISTS prendas;

DROP TABLE IF EXISTS clientes;



CREATE TABLE clientes (
                          cli_id bigint NOT NULL AUTO_INCREMENT,
                          cli_apellido varchar(255) DEFAULT NULL,
                          cli_nombre varchar(255) DEFAULT NULL,
                          PRIMARY KEY (cli_id)
);

CREATE TABLE prendas (
                         prd_id bigint NOT NULL AUTO_INCREMENT,
                         prd_descripcion varchar(255) DEFAULT NULL,
                         prd_precio_base decimal(19,2) DEFAULT NULL,
                         prd_tipo_prenda varchar(255) DEFAULT NULL,
                         prd_estado_prenda varchar(255) DEFAULT NULL,
                         PRIMARY KEY (prd_id)
);

CREATE TABLE ventas (
                        vta_id bigint NOT NULL AUTO_INCREMENT,
                        tipo_venta varchar(31) NOT NULL,
                        vta_fecha datetime(6) DEFAULT NULL,
                        vta_cli_id bigint DEFAULT NULL,
                        PRIMARY KEY (vta_id),
                        KEY vta_cli_fk (vta_cli_id),
                        CONSTRAINT vta_cli_fk FOREIGN KEY (vta_cli_id) REFERENCES clientes (cli_id)
);

CREATE TABLE venta_items (
                             itm_id bigint NOT NULL AUTO_INCREMENT,
                             itm_cantidad int DEFAULT NULL,
                             itm_prd_id bigint DEFAULT NULL,
                             itm_vta_id bigint NOT NULL,
                             PRIMARY KEY (itm_id),
                             KEY itm_vta_fk (itm_vta_id),
                             CONSTRAINT itm_vta_fk FOREIGN KEY (itm_vta_id) REFERENCES ventas (vta_id),
                             KEY itm_prd_fk (itm_prd_id),
                             CONSTRAINT itm_prd_fk FOREIGN KEY (itm_prd_id) REFERENCES prendas (prd_id)
);

CREATE TABLE ventas_efectivo (
                                 vta_id bigint NOT NULL,
                                 PRIMARY KEY (vta_id),
                                 CONSTRAINT vte_vta_fk FOREIGN KEY (vta_id) REFERENCES ventas (vta_id)
);

CREATE TABLE ventas_tarjeta (
                                vta_id bigint NOT NULL,
                                vtt_cantidad_cuotas int DEFAULT NULL,
                                vtt_coeficiente decimal(2,2) DEFAULT NULL,
                                PRIMARY KEY (vta_id),
                                CONSTRAINT vtt_vta_fk FOREIGN KEY (vta_id) REFERENCES ventas (vta_id)
);

CREATE TABLE prendas_nueva(
                              prd_id bigint NOT NULL,
                              PRIMARY KEY (prd_id),
                              CONSTRAINT prd_pnu_fk FOREIGN KEY (prd_id) REFERENCES prendas (prd_id)
);
CREATE TABLE prendas_liquidacion(
                              prd_id bigint NOT NULL,
                              prd_porcen_dto int DEFAULT NULl,
                              PRIMARY KEY (prd_id),
                              CONSTRAINT prd_pliq_fk FOREIGN KEY (prd_id) REFERENCES prendas (prd_id)
);
CREATE TABLE prendas_promocion(
                              prd_id bigint NOT NULL,
                              prd_val_dto int DEFAULT NULl,
                              PRIMARY KEY (prd_id),
                              CONSTRAINT prd_pro_fk FOREIGN KEY (prd_id) REFERENCES prendas (prd_id)
);