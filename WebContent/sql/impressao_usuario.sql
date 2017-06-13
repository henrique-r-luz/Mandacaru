
INSERT INTO `Cgrupo` (`id`, `nome`) VALUES
	(1, 'ROLE_ADMIN');

INSERT INTO Cusuario (id, ldap, login, senha, grupo_id) VALUES
	(1, 0, 'admin', '21232f297a57a5a743894a0e4a801fc3', 1);