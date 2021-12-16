use sd_dev

#1 Role
INSERT INTO role (description, name)
VALUES ('ROLE_ADMIN', 'ADMIN'),('ROLE_TSOCIAL', 'TSOCIAL'), ('ROLE_USER', 'USER');

#2 City
INSERT INTO city (description, name)
VALUES  ('ENC', 'Encarnacion'), ('ASU','ASUNCION'), ('CDE', 'Ciudad del Este'),
        ('PIL', 'Pilar'), ('VLL', 'Villarrica'), ('CNC', 'Concepcion'),
        ('CMB', 'Cambyreta'), ('ÑMB', 'Ñemby'), ('AYL', 'Ayolas'),
        ('SPD', 'San Pedro');

#3 Neighborhood
INSERT INTO neighborhood(description, name, city_id)
VALUES  ('ENC01', 'Villa del Maestro', 1), ('ENC02', 'B. Caballero', 1), ('ENC03', 'Catedral', 1), ('ENC04', 'Inmaculada', 1), ('ENC05', 'Jardin', 1), ('ENC06', 'Ciudad Nueva', 1), ('ENC07', 'Kennedy', 1), ('ENC08', 'Barril Paso', 1), ('ENC09', 'San Pedro', 1), ('ENC10', 'San Isidro', 1), ('ENC11', 'Pacu Cua', 1), ('ENC12', 'Circuito', 1), ('ENC13', 'CENTRO', 1),
        ('ASU01', 'Sajonia', 2), ('ASU02', 'B. Obrero', 2), ('ASU03', 'Bella Vista', 2), ('ASU04', 'Herrera', 2), ('ASU05', 'Enramada',2),
        ('CDE01', 'Montelindo', 3), ('CDE02', 'San Ramon', 3), ('CDE03', 'Santa Lucia', 3), ('CDE04', 'El pinar', 3), ('CDE05', 'Yrendy', 3), ('CDE06', 'Santa Ana', 3),
        ('PIL01', 'San Jose', 4), ('PIL02', 'Guarani', 4), ('PIL03', 'Ytororo', 4),
        ('VLL01', 'Lomas Valentina', 5), ('VLL02', 'Urbano', 5), ('VLL03', 'Tuyutimi', 5), ('VLL04', 'Mbopi Kua', 5),
        ('CNC01', 'San Blas', 6), ('CNC02', 'Primavera', 6), ('CNC03', 'Villa Oliva', 6), ('CNC04', 'Saladillo', 6), ('CNC05', 'Fatima', 6), ('CNC06', 'Centro', 6), ('CNC07', 'Itacurubi', 6), ('CNC08', 'San Jorge', 6), ('CNC09', 'San Jose', 6), ('CNC10', 'Laguna Plato', 6), ('CNC11', 'Rincon I', 6),
        ('ÑMB01', 'Villa Anita', 8), ('ÑMB02', 'San Estanislao', 8),
        ('AYL01', 'Centro', 9), ('SPD01', 'Sajonia', 10), ('SPD02', 'B. Obrero', 10), ('SPD03', 'Bella Vista', 10);

#4 User
INSERT INTO user (name,surname,email,phone,address,cn,city_id,username,password)
VALUES  ('Wylie','Fulton','f_wylie1479@google.com',9414393,'Ap #417-6548 Nisl Street',6098243,6,'FWillie','$2a$04$wwietxcXYYDhsva8irZ/.egR0a.vT2T5mtWGVyDRDGtKE1uzzflOi'),
        ('Silas','Guy','guy.silas@google.com',2373647,'Ap #108-8958 Dictum Street',4257183,2,'GSilas','$2a$04$wwietxcXYYDhsva8irZ/.egR0a.vT2T5mtWGVyDRDGtKE1uzzflOi'),
        ('Lionel','Merrill','m-lionel@google.com',6208197,'P.O. Box 161, 8314 Donec Ave',7264518,1,'MLionel','$2a$04$wwietxcXYYDhsva8irZ/.egR0a.vT2T5mtWGVyDRDGtKE1uzzflOi'),
        ('Levi','Barrett','l.barrett8556@google.com',3121436,'432-1968 Quis, Avenue',3349700,2,'BLevi','$2a$04$wwietxcXYYDhsva8irZ/.egR0a.vT2T5mtWGVyDRDGtKE1uzzflOi'),
        ('Malcolm','Guy','guy.malcolm5310@google.com',4454530,'239-2180 Ut Street',2709666,8,'GMalcom','$2a$04$wwietxcXYYDhsva8irZ/.egR0a.vT2T5mtWGVyDRDGtKE1uzzflOi'),
        ('Sheila','Fowler','fowler.sheila@google.com',5376382,'Ap #193-9046 Pede, Street',1322875,10,'FSheila','$2a$04$wwietxcXYYDhsva8irZ/.egR0a.vT2T5mtWGVyDRDGtKE1uzzflOi'),
        ('Regina','Valenzuela','r-valenzuela@google.com',4887675,'495-5714 In Av.',5968307,5,'VRegina','$2a$04$wwietxcXYYDhsva8irZ/.egR0a.vT2T5mtWGVyDRDGtKE1uzzflOi'),
        ('Fallon','Donaldson','fallondonaldson@google.com',7869448,'4751 Sapien, St.',1033412,4,'DFallon','$2a$04$wwietxcXYYDhsva8irZ/.egR0a.vT2T5mtWGVyDRDGtKE1uzzflOi'),
        ('Beck','Wooten','beck.wooten7114@google.com',3429008,'501-1717 Luctus Ave',7438435,3,'WBeck','$2a$04$wwietxcXYYDhsva8irZ/.egR0a.vT2T5mtWGVyDRDGtKE1uzzflOi'),
        ('Theodore','Jones','jtheodore2685@google.com',9361703,'179-8299 Velit St.',6135124,2,'JTheodore','$2a$04$wwietxcXYYDhsva8irZ/.egR0a.vT2T5mtWGVyDRDGtKE1uzzflOi');

INSERT INTO user (name,surname,email,phone,address,cn,city_id,username,password)
VALUES  ('Dalila','Castelnovo','dalila.castelnovo@fiuni.edu.py',9414393,'Ap #417-6548 Nisl Street',6098243,6,'Dali','$2a$04$wwietxcXYYDhsva8irZ/.egR0a.vT2T5mtWGVyDRDGtKE1uzzflOi'),
        ('Sebastian','Caballero','sebastian.caballero@fiuni.edu.py',2373647,'Ap #108-8958 Dictum Street',4257183,2,'Reni','$2a$04$wwietxcXYYDhsva8irZ/.egR0a.vT2T5mtWGVyDRDGtKE1uzzflOi'),
        ('Alexandra','Lezcano','alexandra.lezcano@fiuni.edu.py',6208197,'P.O. Box 161, 8314 Donec Ave',7264518,1,'Alex','$2a$04$wwietxcXYYDhsva8irZ/.egR0a.vT2T5mtWGVyDRDGtKE1uzzflOi');


#5 Tipo denuncia
INSERT INTO tipo_denuncia (descripcion, titulo)
VALUES  ('Denuncia de abuso', 'Abuso'), ('Denuncia de violencia intrafamiliar', 'Violencia intrafamiliar'),
        ('Denuncia de violencia', 'Violencia'), ('Denuncia de maltrato', 'Maltrato'), ('Denuncia de abandono', 'Abandono'),
        ('Denuncia de explotacion infantil', 'Explotacion Infantil'), ('Denuncia de trata de blancas', 'Trata de blancas'),
        ('Denuncia de pornografia infantil', 'Pornografia infantil'), ('Denuncia de abuso de sustancias', 'Abuso de sustancias');

#6 Tipo sujeto
INSERT INTO tipo_sujeto (nombre) VALUES ('Denunciante'), ('Victima'), ('Victimario');

#7 Dependencias del estado
INSERT INTO dependencias_del_estado (description, name)
VALUES  ('DEP01', 'Fiscalia'), ('DEP02', 'Codeni'), ('DEP03', 'Ministerio de la niñez y la adolescencia'),
        ('DEP04', 'Ministerio de la mujer'), ('DEP05', 'Kuña Aty'), ('DEP07', 'Comisaria'), ('DEP08', 'Policia Nacional'),
        ('DEP09', 'Ministerio del interior'), ('DEP10', 'Defensoria'), ('DEP11', 'Ministerio de justicia'),
        ('DEP12', 'Secretaria Nacional por los Derechos Humanos de las Personas con Discapacidad '),
        ('DEP13', 'CONTRAFOR'), ('DEP14', 'Consejo Nacional de la niñez y la Adolexcencia'), ('DEP15', 'SENADE');

#8 Denuncias estado
INSERT INTO denuncia_estado (nombre) VALUES ('Pendiente'), ('Atendido'), ('Cerrado');

#Test User
INSERT INTO user (name,surname,email,phone,address,cn,city_id,username,password)
VALUES ('Test','User','test5@google.com',9361703,'179-8299 Velit St.',6135124,2,'User','$2a$04$wwietxcXYYDhsva8irZ/.egR0a.vT2T5mtWGVyDRDGtKE1uzzflOi');

#9 Asignar Admins
INSERT INTO user_role (user_id, role_id)
VALUES (1, 2), (11, 2);

#9 Asignar T Sociales
INSERT INTO user_role (user_id, role_id)
VALUES (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1);

INSERT INTO user_role (user_id, role_id)
VALUES (12, 1), (13, 2), (14, 2);

#10 Denuncias
INSERT INTO denuncias (codigo, descripcion, fecha, city_id, estado_id, neighborhood_id, caso_derivado_id)
VALUES   ('QVE872','commodo at, libero. Morbi accumsan laoreet ipsum. Curabitur consequat, lectus sit amet luctus vulputate','21-08-19',9,3,45, null),
         ('FPF310','fringilla. Donec feugiat metus sit amet ante. Vivamus','21-05-11',5,3,29, null),
         ('ETY136','erat, in consectetuer ipsum nunc id','20-10-24',10,2,45, null),
         ('QXD484','mattis semper, dui lectus rutrum urna, nec luctus felis','21-06-11',10,2,47, null),
         ('XWI167','Phasellus elit pede, malesuada vel, venenatis','21-12-16',6,2,42, null),
         ('WFZ707','egestas rhoncus. Proin nisl sem, consequat nec, mollis vitae, posuere','21-11-15',6,2,38, null),
         ('JUR382','est. Nunc laoreet lectus quis massa. Mauris vestibulum, neque sed dictum eleifend','21-01-30',6,2,37, null),
         ('QUN789','venenatis vel, faucibus id, libero. Donec consectetuer mauris id sapien. Cras','21-11-21',10,2,15, null),
         ('RUK181','metus. Vivamus euismod urna. Nullam lobortis quam a felis','21-05-15',4,2,26, null),
         ('RWQ356','Sed pharetra, felis eget varius ultrices, mauris ipsum porta elit, a feugiat tellus','21-02-06',6,3,32, null),
         ('CGS861','ut dolor dapibus gravida. Aliquam tincidunt, nunc ac mattis ornare, lectus ante dictum','21-03-16',10,2,48, null),
         ('LQM489','at, libero. Morbi accumsan laoreet ipsum. Curabitur consequat','21-06-30',1,2,1, null),
         ('NIS146','Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet','21-12-16',4,3,26, null),
         ('IDN938','ipsum primis in faucibus orci luctus et ultrices','21-06-13',3,2,20, null),
         ('NKA604','sodales at, velit. Pellentesque ultricies dignissim lacus. Aliquam rutrum lorem ac risus. Morbi','21-08-30',1,1,3, null),
         ('GIC426','purus sapien, gravida non, sollicitudin a, malesuada id, erat.','21-01-24',2,2,15, null),
         ('YBB385','in consectetuer ipsum nunc id enim. Curabitur massa. Vestibulum accumsan neque et nunc. Quisque ornare','21-06-25',5,3,30, null),
         ('SEH245','lacus. Mauris non dui nec urna','21-11-11',6,2,32, null),
         ('FLR368','ac mattis ornare, lectus ante dictum mi, ac mattis velit justo','21-09-03',4,2,26, null),
         ('CGP861','penatibus et magnis dis parturient montes, nascetur ridiculus mus.','21-08-21',3,1,21, null);

INSERT INTO sujetos (ci, correo, direccion, nombre, telefono, denuncia_id, tipo_id)
VALUES  (123456, '', '', 'Marta Rodriguez', '0975163156', 1, 1),(123456, '', '', 'Pedro Perez', '0975163156', 1, 2), (123456, '', '', 'Alberto Suarez', '0975163156', 1, 3),
        (123456, '', '', 'Sol Garcia', '0975163156', 2, 1),(123456, '', '', 'Marcos Rodriguez', '0975163156', 2, 2), (123456, '', '', 'Alberto Rodriguez', '0975163156', 2, 3),
        (123456, '', '', 'Silvia Rodriguez', '0975163156', 3, 1),(123456, '', '', 'Sara Rojas', '0975163156', 3, 2), (123456, '', '', 'Carmelo Alvarez', '0975163156', 3, 3),
        (123456, '', '', 'Sergio Carmesi', '0975163156', 4, 1),(123456, '', '', 'Tito Carmesi', '0975163156', 4, 2), (123456, '', '', 'Carlota Carmesi', '0975163156', 4, 3),
        (123456, '', '', 'Pedro Rodriguez', '0975163156', 5, 1),(123456, '', '', 'Saul', '0975163156', 5, 2), (123456, '', '', 'Roberto Estevez', '0975163156', 5, 3),
        (123456, '', '', 'Ana Martinez', '0975163156', 6, 1),(123456, '', '', 'Marcos Juarez', '0975163156', 6, 2), (123456, '', '', 'Roque Juarez', '0975163156', 6, 3),
        (123456, '', '', 'Alberto Solis', '0975163156', 7, 1),(123456, '', '', 'Matias Solis', '0975163156', 7, 2), (123456, '', '', 'Rodrigo Solis', '0975163156', 7, 3),
        (123456, '', '', 'Mirna Saucedo', '0975163156', 8, 1),(123456, '', '', 'Marcos Saucedo', '0975163156', 8, 2), (123456, '', '', 'Jorge Rodriguez', '0975163156', 8, 3),
        (123456, '', '', 'Monica Suarez', '0975163156', 9, 1),(123456, '', '', 'David Garcia', '0975163156', 9, 2), (123456, '', '', 'Coco Ramirez', '0975163156', 9, 3),
        (123456, '', '', 'Rebeca Lopez', '0975163156', 10, 1),(123456, '', '', 'Camila Lopez', '0975163156', 10, 2), (123456, '', '', 'Camilo Lopez', '0975163156', 10, 3),
        (123456, '', '', 'Jorge Rodriguez', '0975163156', 11, 1),(123456, '', '', 'Solange Perez', '0975163156', 11, 2), (123456, '', '', 'Rita Suarez', '0975163156', 11, 3),
        (123456, '', '', 'Pedro Garcia', '0975163156', 12, 1),(123456, '', '', 'Arturo Rodriguez', '0975163156', 12, 2), (123456, '', '', 'Celeste Rodriguez', '0975163156', 12, 3),
        (123456, '', '', 'Josue Rodriguez', '0975163156', 13, 1),(123456, '', '', 'Mirta Rojas', '0975163156', 13, 2), (123456, '', '', 'Andres Alvarez', '0975163156', 13, 3),
        (123456, '', '', 'Silvio Carmesi', '0975163156', 14, 1),(123456, '', '', 'Sara Carmesi', '0975163156', 14, 2), (123456, '', '', 'Pablo Carmesi', '0975163156', 14, 3),
        (123456, '', '', 'Pancho Rodriguez', '0975163156', 15, 1),(123456, '', '', 'Rodrigo Amarilla', '0975163156', 15, 2), (123456, '', '', 'Ricardo Estevez', '0975163156', 15, 3),
        (123456, '', '', 'Andrea Martinez', '0975163156', 16, 1),(123456, '', '', 'Marta Juarez', '0975163156', 16, 2), (123456, '', '', 'Jose Juarez', '0975163156', 16, 3),
        (123456, '', '', 'Carlos Solis', '0975163156', 17, 1),(123456, '', '', 'Pablo Solis', '0975163156', 17, 2), (123456, '', '', 'Jesus Solis', '0975163156', 17, 3),
        (123456, '', '', 'Silvia Saucedo', '0975163156', 18, 1),(123456, '', '', 'Lisa Saucedo', '0975163156', 18, 2), (123456, '', '', 'Santiago Rodriguez', '0975163156', 18, 3),
        (123456, '', '', 'Sonia Suarez', '0975163156', 19, 1),(123456, '', '', 'Pepe Garcia', '0975163156', 19, 2), (123456, '', '', 'Marcos Ramirez', '0975163156', 19, 3),
        (123456, '', '', 'Luis Lopez', '0975163156', 20, 1),(123456, '', '', 'Matias Lopez', '0975163156', 20, 2), (123456, '', '', 'Juan Lopez', '0975163156', 20, 3);

INSERT INTO casos_derivados (date, description, denuncia_id, user_id)
VALUES ('2021-12-16', 'Este caso tiene esta descripcion', 1, 12),
       ('2021-12-16', 'Este caso tiene otra desscripcion', 2, 12),
       ('2021-12-16', 'Y este caso tiene esta otra', 3, 12),
       ('2021-12-16', 'Lorem Ipsum dolor sit amet', 4, 12),
       ('2021-12-16', 'Consecueter adipiscing elit', 5, 12),
       ('2021-12-16', 'Voluptatem quia voluptas sit aspernatur', 6, 12),
       ('2021-12-16', 'Sed quia non numquam eius modi', 7, 12),
       ('2021-12-16', 'Quis autem vel eum iure reprehenderit ', 8, 12),
       ('2021-12-16', 'Lorem Ipsum ', 9, 12),
       ('2021-12-16', 'velit esse quam nihil molestiae consequatut', 10, 12),
       ('2021-12-16', 'Consecueter adipiscing elit', 11, 12),
       ('2021-12-16', 'Illum qui dolorem eum fugiat quo voluptas nulla pariatur?"', 12, 14),
       ('2021-12-16', 'Excepteur sint occaecat cupidatat non proident', 13, 14),
       ('2021-12-16', 'Sunt in culpa qui officia deserunt mollit anim id est laborum', 14, 14);
INSERT INTO casos_derivados (date, description, denuncia_id, user_id)
VALUES ('2021-12-16', 'Sunt in culpa qui officia deserunt mollit anim id est laborum', 15, 12);

INSERT INTO dependencias_del_estado_casos_derivados (dep_estado_domain_id, casos_derivados_id)
VALUES  (1,1), (2,1), (3,1), (1,2), (4,3), (5,3), (5,4), (5,1), (6,1), (6,6), (6,7),
        (7,8), (7,2), (7,4), (7,6), (8,8), (8,9), (8,5), (9,2), (9,3), (9,9), (9,1),
        (10, 10), (10,2), (11, 11), (12,12), (12,1), (13,13), (14,14), (14, 3), (14, 5);