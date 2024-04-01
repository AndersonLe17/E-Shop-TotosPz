alter table personas
    alter column usu_reg drop not null,
    alter column usu_mod drop not null;

alter table perfiles
    alter column usu_reg drop not null,
    alter column usu_mod drop not null;

alter table sedes
    alter column usu_reg drop not null,
    alter column usu_mod drop not null;

alter table usuarios
    alter column usu_reg drop not null,
    alter column usu_mod drop not null;

INSERT INTO personas (per_cod, per_nom, per_ape_pat, per_ape_mat, per_tip_doc, per_num_doc, per_cor_ele, per_est, usu_reg, usu_mod, fec_hor_reg, fec_hor_mod)
    VALUES (1, 'ANDERSON LEONARDO', 'ORELLANA', 'ESPIRITU', 'DNI', '77531657', 'anderleorellana@gmail.com', 'ACTIVO', null, null, NOW(), NOW()),
           (2, 'CARMEN', 'ESPIRITU', 'DE LA CRUZ', 'DNI', '09288112', 'carmen.edc30@gmail.com', 'ACTIVO', null, null, NOW(), NOW()),
           (3, 'JUAN JOSE', 'ORELLANA', 'ESPIRITU', 'DNI', '46238250', 'juan.dunk26@gmail.com', 'ACTIVO', null, null, NOW(), NOW());

INSERT INTO perfiles (perf_cod, perf_nom, perf_des, perf_est, usu_reg, usu_mod, fec_hor_reg, fec_hor_mod)
    VALUES (1, 'ROLE_ADMINISTRADOR', 'Usuario con acceso total al sistema', 'ACTIVO', null, null, NOW(), NOW()),
           (2, 'ROLE_CAJERO', 'Usuario responsable de tomar pedidos y recibir pagos.', 'ACTIVO', null, null, NOW(), NOW()),
           (3, 'ROLE_ COCINERO', 'Usuario responsable de procesar los pedidos.', 'ACTIVO', null, null, NOW(), NOW());

INSERT INTO sedes (sed_cod, sed_nom, sed_ubi_cod, sed_dir, sed_ruc, sed_raz_soc, sed_est, usu_reg, usu_mod, fec_hor_reg, fec_hor_mod)
    VALUES (1, 'Toto''s Pizza Huancayo', '110101', 'Av. Leandra Torres Nro. 112', '20568283385', 'TOTO''S PIZZA S.A.C.', 'ACTIVO', null, null, NOW(), NOW());

INSERT INTO usuarios (usu_cod, usu_per_cod, usu_nom, usu_cor_ele, usu_con, usu_perf_cod, usu_sed_cod, usu_est, usu_reg, usu_mod, fec_hor_reg, fec_hor_mod)
    VALUES (1, 1, 'AOrellana', 'aorellana@totospz.com', '$2a$10$znNTSHMmDJJ4bqiFi7OMHeZ/JNzVAFEdi14lQNe17leLCGsQdYIsK', 1, 1, 'ACTIVO', null, null, NOW(), NOW()),
           (2, 2, 'CEspiritu', 'cespiritu@totospz.com', '$2a$10$S6yJANR1FPgv5r6tqaFv6OvV8zL5I0az/WHrWe9nRiQuhft2vFry.', 1, 1, 'ACTIVO', null, null, NOW(), NOW()),
           (3, 3, 'JOrellana', 'jorellana@totospz.com', '$2a$10$ejvA5Oe57lQP1OCa9NDMP.5YC6vNxZx800o38hq8bdMloa5rYiUja', 1, 1, 'ACTIVO', null, null, NOW(), NOW());

UPDATE personas SET usu_reg = 1, usu_mod = 1 WHERE per_cod = 1;
UPDATE personas SET usu_reg = 1, usu_mod = 1 WHERE per_cod = 2;
UPDATE personas SET usu_reg = 1, usu_mod = 1 WHERE per_cod = 3;

UPDATE perfiles SET usu_reg = 1, usu_mod = 1 WHERE perf_cod = 1;
UPDATE perfiles SET usu_reg = 1, usu_mod = 1 WHERE perf_cod = 2;
UPDATE perfiles SET usu_reg = 1, usu_mod = 1 WHERE perf_cod = 3;

UPDATE sedes SET usu_reg = 1, usu_mod = 1 WHERE sed_cod = 1;

UPDATE usuarios SET usu_reg = 1, usu_mod = 1 WHERE usu_cod = 1;
UPDATE usuarios SET usu_reg = 1, usu_mod = 1 WHERE usu_cod = 2;
UPDATE usuarios SET usu_reg = 1, usu_mod = 1 WHERE usu_cod = 3;

alter table personas
    alter column usu_reg set not null,
    alter column usu_mod set not null;

alter table perfiles
    alter column usu_reg set not null,
    alter column usu_mod set not null;

alter table sedes
    alter column usu_reg set not null,
    alter column usu_mod set not null;

alter table usuarios
    alter column usu_reg set not null,
    alter column usu_mod set not null;