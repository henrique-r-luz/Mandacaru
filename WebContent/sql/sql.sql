-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.5.25a - MySQL Community Server (GPL)
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura para tabela compras.ccategoria
DROP TABLE IF EXISTS `ccategoria`;
CREATE TABLE IF NOT EXISTS `ccategoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.ccategoria: ~3 rows (aproximadamente)
DELETE FROM `ccategoria`;
/*!40000 ALTER TABLE `ccategoria` DISABLE KEYS */;
INSERT INTO `ccategoria` (`id`, `nome`) VALUES
	(3, 'eletrico'),
	(1, 'informática'),
	(2, 'livros');
/*!40000 ALTER TABLE `ccategoria` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.ccatmat
DROP TABLE IF EXISTS `ccatmat`;
CREATE TABLE IF NOT EXISTS `ccatmat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(255) DEFAULT NULL,
  `material_id` int(11) DEFAULT NULL,
  `tipocatmat_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero` (`numero`),
  KEY `FK86BE8FCD7671F4E6` (`material_id`),
  KEY `FK86BE8FCD6732C238` (`tipocatmat_id`),
  CONSTRAINT `FK86BE8FCD6732C238` FOREIGN KEY (`tipocatmat_id`) REFERENCES `ctipocatmat` (`id`),
  CONSTRAINT `FK86BE8FCD7671F4E6` FOREIGN KEY (`material_id`) REFERENCES `cinfomaterial` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.ccatmat: ~4 rows (aproximadamente)
DELETE FROM `ccatmat`;
/*!40000 ALTER TABLE `ccatmat` DISABLE KEYS */;
INSERT INTO `ccatmat` (`id`, `numero`, `material_id`, `tipocatmat_id`) VALUES
	(1, '214522', 1, 2),
	(2, '873773', 2, 2),
	(3, '251418', 3, 2),
	(4, '259654', 4, 1);
/*!40000 ALTER TABLE `ccatmat` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.ccotacao
DROP TABLE IF EXISTS `ccotacao`;
CREATE TABLE IF NOT EXISTS `ccotacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `empresa` varchar(255) DEFAULT NULL,
  `site` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `orcamento_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `url` (`url`),
  KEY `FK68F1D3B517BA46BC` (`orcamento_id`),
  CONSTRAINT `FK68F1D3B517BA46BC` FOREIGN KEY (`orcamento_id`) REFERENCES `corcamento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.ccotacao: ~12 rows (aproximadamente)
DELETE FROM `ccotacao`;
/*!40000 ALTER TABLE `ccotacao` DISABLE KEYS */;
INSERT INTO `ccotacao` (`id`, `data`, `email`, `empresa`, `site`, `url`, `valor`, `orcamento_id`) VALUES
	(1, '2014-02-11', 'fi@ifnmg.com', 'fornecedor 1', 'f1.com.be', 'f68d5776416624dd0bac61cc46fc0fb31392157468593.jpg', 12.00, 1),
	(2, '2014-02-11', 'emial@com.br', 'f2', 'fe.com.br', '419c5ac3b316eef1afa8e6cb6953fe9f1392157499693.jpg', 36.00, 1),
	(3, '2014-02-11', 'emial@f3.com', 'f3', 'f3.com.br', '2abcb5a00124503a0ebb0ec474314b181392157535165.jpg', 32.00, 1),
	(4, '2014-02-11', 'ee1', 't1', 'ss1', '546327ab1d41c4a8c5b8d18f3f18bb5e1392157648051.jpg', 12.00, 2),
	(5, '2014-02-11', 'ee2', 't2', 'sitet2', 'd48653b63cee6302b3e57532ac063f171392157677241.jpg', 23.00, 2),
	(6, '2014-02-11', 'email3', 't3', 'site3', '10755437cea939454108ebed98e1fdc71392157707363.jpg', 24.00, 2),
	(7, '2014-02-12', 'email1', 'com1', 'site1', '2849b36598b1d263bab7f9d16fc7d4231392224407503.jpg', 1000.00, 3),
	(8, '2014-02-13', 'email2', 'com2', 'site2', '6bcc37db0c65c67333c486564f92a9b61392224434753.jpg', 2000.00, 3),
	(9, '2014-02-13', 'email3', 'com3', 'site3', '68e650f7e44a27d0bea96bea040b882e1392224469049.jpg', 3000.00, 3),
	(10, '2014-02-17', 'p1e', 'p1', 'p1s', '18bf62e980dad1eb5d32293209acbe641392657826954.jpg', 58.00, 4),
	(11, '2014-02-17', 'p2e', 'p2', 'pes', '1945a07446c9847e9c76d256b3c667221392657851276.jpg', 56.00, 4),
	(12, '2014-02-17', 'p3e', 'p3', 'p3s', '8c0410bfe7e2192f5827fd7b8d4137851392657872360.jpg', 82.00, 4);
/*!40000 ALTER TABLE `ccotacao` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.cfornecedor
DROP TABLE IF EXISTS `cfornecedor`;
CREATE TABLE IF NOT EXISTS `cfornecedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `site` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.cfornecedor: ~0 rows (aproximadamente)
DELETE FROM `cfornecedor`;
/*!40000 ALTER TABLE `cfornecedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `cfornecedor` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.cfracassados
DROP TABLE IF EXISTS `cfracassados`;
CREATE TABLE IF NOT EXISTS `cfracassados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `justificativa` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.cfracassados: ~2 rows (aproximadamente)
DELETE FROM `cfracassados`;
/*!40000 ALTER TABLE `cfracassados` DISABLE KEYS */;
INSERT INTO `cfracassados` (`id`, `justificativa`, `titulo`) VALUES
	(1, 'o lance foi maior que o valor medio<br>', 'lance incorreto'),
	(2, 'as&nbsp; empresas foram desclassificadas', 'empresas desclassificadas');
/*!40000 ALTER TABLE `cfracassados` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.cfracassados_citem
DROP TABLE IF EXISTS `cfracassados_citem`;
CREATE TABLE IF NOT EXISTS `cfracassados_citem` (
  `Cfracassados_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  KEY `FK1385090EB4FA8D1F` (`Cfracassados_id`),
  KEY `FK1385090E596E8098` (`item_id`),
  CONSTRAINT `FK1385090E596E8098` FOREIGN KEY (`item_id`) REFERENCES `citem` (`id`),
  CONSTRAINT `FK1385090EB4FA8D1F` FOREIGN KEY (`Cfracassados_id`) REFERENCES `cfracassados` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.cfracassados_citem: ~0 rows (aproximadamente)
DELETE FROM `cfracassados_citem`;
/*!40000 ALTER TABLE `cfracassados_citem` DISABLE KEYS */;
/*!40000 ALTER TABLE `cfracassados_citem` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.cgrupo
DROP TABLE IF EXISTS `cgrupo`;
CREATE TABLE IF NOT EXISTS `cgrupo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.cgrupo: ~1 rows (aproximadamente)
DELETE FROM `cgrupo`;
/*!40000 ALTER TABLE `cgrupo` DISABLE KEYS */;
INSERT INTO `cgrupo` (`id`, `nome`) VALUES
	(1, 'ROLE_ADMIN');
/*!40000 ALTER TABLE `cgrupo` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.cinfomaterial
DROP TABLE IF EXISTS `cinfomaterial`;
CREATE TABLE IF NOT EXISTS `cinfomaterial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `descricao` (`descricao`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.cinfomaterial: ~4 rows (aproximadamente)
DELETE FROM `cinfomaterial`;
/*!40000 ALTER TABLE `cinfomaterial` DISABLE KEYS */;
INSERT INTO `cinfomaterial` (`id`, `descricao`, `nome`) VALUES
	(1, 'mouse optico usb<br>', 'mouse'),
	(2, 'teclado Brasil usb<br>', 'teclado '),
	(3, 'placa de rede 1000Mb<br>', 'placa de rede'),
	(4, 'computador 3 geração<br>', 'computador');
/*!40000 ALTER TABLE `cinfomaterial` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.citem
DROP TABLE IF EXISTS `citem`;
CREATE TABLE IF NOT EXISTS `citem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoria_id` int(11) DEFAULT NULL,
  `catmat_id` int(11) DEFAULT NULL,
  `material_id` int(11) DEFAULT NULL,
  `tipo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3E1A1D68D2E80F8` (`tipo_id`),
  KEY `FK3E1A1D696DC79B8` (`catmat_id`),
  KEY `FK3E1A1D6BAB65BDC` (`categoria_id`),
  KEY `FK3E1A1D67671F4E6` (`material_id`),
  CONSTRAINT `FK3E1A1D67671F4E6` FOREIGN KEY (`material_id`) REFERENCES `cinfomaterial` (`id`),
  CONSTRAINT `FK3E1A1D68D2E80F8` FOREIGN KEY (`tipo_id`) REFERENCES `ctipo` (`id`),
  CONSTRAINT `FK3E1A1D696DC79B8` FOREIGN KEY (`catmat_id`) REFERENCES `ccatmat` (`id`),
  CONSTRAINT `FK3E1A1D6BAB65BDC` FOREIGN KEY (`categoria_id`) REFERENCES `ccategoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.citem: ~4 rows (aproximadamente)
DELETE FROM `citem`;
/*!40000 ALTER TABLE `citem` DISABLE KEYS */;
INSERT INTO `citem` (`id`, `categoria_id`, `catmat_id`, `material_id`, `tipo_id`) VALUES
	(1, 1, 1, 1, 1),
	(2, 1, 2, 2, 1),
	(3, 1, 3, 3, 1),
	(4, 1, 4, 4, 2);
/*!40000 ALTER TABLE `citem` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.citemprocesso
DROP TABLE IF EXISTS `citemprocesso`;
CREATE TABLE IF NOT EXISTS `citemprocesso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `processo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK55D67FD6596E8098` (`item_id`),
  KEY `FK55D67FD6B9A72278` (`processo_id`),
  CONSTRAINT `FK55D67FD6596E8098` FOREIGN KEY (`item_id`) REFERENCES `citem` (`id`),
  CONSTRAINT `FK55D67FD6B9A72278` FOREIGN KEY (`processo_id`) REFERENCES `cprocesso` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.citemprocesso: ~4 rows (aproximadamente)
DELETE FROM `citemprocesso`;
/*!40000 ALTER TABLE `citemprocesso` DISABLE KEYS */;
INSERT INTO `citemprocesso` (`id`, `data`, `item_id`, `processo_id`) VALUES
	(1, NULL, 1, 1),
	(2, NULL, 2, 1),
	(3, NULL, 4, 3),
	(5, NULL, 4, 5);
/*!40000 ALTER TABLE `citemprocesso` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.citem_cfracassados
DROP TABLE IF EXISTS `citem_cfracassados`;
CREATE TABLE IF NOT EXISTS `citem_cfracassados` (
  `Citem_id` int(11) NOT NULL,
  `fracassados_id` int(11) NOT NULL,
  KEY `FK2EE22D408582DAD5` (`Citem_id`),
  KEY `FK2EE22D40F52075FC` (`fracassados_id`),
  CONSTRAINT `FK2EE22D408582DAD5` FOREIGN KEY (`Citem_id`) REFERENCES `citem` (`id`),
  CONSTRAINT `FK2EE22D40F52075FC` FOREIGN KEY (`fracassados_id`) REFERENCES `cfracassados` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.citem_cfracassados: ~1 rows (aproximadamente)
DELETE FROM `citem_cfracassados`;
/*!40000 ALTER TABLE `citem_cfracassados` DISABLE KEYS */;
INSERT INTO `citem_cfracassados` (`Citem_id`, `fracassados_id`) VALUES
	(2, 2);
/*!40000 ALTER TABLE `citem_cfracassados` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.corcamento
DROP TABLE IF EXISTS `corcamento`;
CREATE TABLE IF NOT EXISTS `corcamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) NOT NULL,
  `item_id` int(11) DEFAULT NULL,
  `pedidos_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9BE94D0D596E8098` (`item_id`),
  KEY `FK9BE94D0D6655AA7C` (`pedidos_id`),
  CONSTRAINT `FK9BE94D0D596E8098` FOREIGN KEY (`item_id`) REFERENCES `citem` (`id`),
  CONSTRAINT `FK9BE94D0D6655AA7C` FOREIGN KEY (`pedidos_id`) REFERENCES `cpedidos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.corcamento: ~6 rows (aproximadamente)
DELETE FROM `corcamento`;
/*!40000 ALTER TABLE `corcamento` DISABLE KEYS */;
INSERT INTO `corcamento` (`id`, `quantidade`, `item_id`, `pedidos_id`) VALUES
	(1, 100, 1, 1),
	(2, 20, 2, 1),
	(3, 15, 4, 2),
	(4, 5, 3, 3),
	(7, 0, 2, 4),
	(8, 0, 4, 4);
/*!40000 ALTER TABLE `corcamento` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.cpedidos
DROP TABLE IF EXISTS `cpedidos`;
CREATE TABLE IF NOT EXISTS `cpedidos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `statusPedidos_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`),
  KEY `FK6B4902BBF21AE7C` (`usuario_id`),
  KEY `FK6B4902BAB45DE1C` (`statusPedidos_id`),
  CONSTRAINT `FK6B4902BAB45DE1C` FOREIGN KEY (`statusPedidos_id`) REFERENCES `cstatuspedidos` (`id`),
  CONSTRAINT `FK6B4902BBF21AE7C` FOREIGN KEY (`usuario_id`) REFERENCES `cusuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.cpedidos: ~4 rows (aproximadamente)
DELETE FROM `cpedidos`;
/*!40000 ALTER TABLE `cpedidos` DISABLE KEYS */;
INSERT INTO `cpedidos` (`id`, `nome`, `statusPedidos_id`, `usuario_id`) VALUES
	(1, 'informatica 2014', 1, 1),
	(2, 'pedido 2', 1, 1),
	(3, 'pedidos 3', 1, 1),
	(4, 'informatica pedido 2', 0, 1);
/*!40000 ALTER TABLE `cpedidos` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.cpregao
DROP TABLE IF EXISTS `cpregao`;
CREATE TABLE IF NOT EXISTS `cpregao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `statusPedidos_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9DD63755BF21AE7C` (`usuario_id`),
  KEY `FK9DD63755AB45DE1C` (`statusPedidos_id`),
  CONSTRAINT `FK9DD63755AB45DE1C` FOREIGN KEY (`statusPedidos_id`) REFERENCES `cstatuspedidos` (`id`),
  CONSTRAINT `FK9DD63755BF21AE7C` FOREIGN KEY (`usuario_id`) REFERENCES `cusuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.cpregao: ~1 rows (aproximadamente)
DELETE FROM `cpregao`;
/*!40000 ALTER TABLE `cpregao` DISABLE KEYS */;
INSERT INTO `cpregao` (`id`, `data`, `descricao`, `numero`, `statusPedidos_id`, `usuario_id`) VALUES
	(2, '2014-02-13', 'processo 1<br>', '01/2014', 0, 1);
/*!40000 ALTER TABLE `cpregao` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.cpregao_cprocesso
DROP TABLE IF EXISTS `cpregao_cprocesso`;
CREATE TABLE IF NOT EXISTS `cpregao_cprocesso` (
  `Cpregao_id` int(11) NOT NULL,
  `processoList_id` int(11) NOT NULL,
  UNIQUE KEY `processoList_id` (`processoList_id`),
  KEY `FK95ED88B9223F351A` (`processoList_id`),
  KEY `FK95ED88B9700BABB5` (`Cpregao_id`),
  CONSTRAINT `FK95ED88B9223F351A` FOREIGN KEY (`processoList_id`) REFERENCES `cprocesso` (`id`),
  CONSTRAINT `FK95ED88B9700BABB5` FOREIGN KEY (`Cpregao_id`) REFERENCES `cpregao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.cpregao_cprocesso: ~1 rows (aproximadamente)
DELETE FROM `cpregao_cprocesso`;
/*!40000 ALTER TABLE `cpregao_cprocesso` DISABLE KEYS */;
INSERT INTO `cpregao_cprocesso` (`Cpregao_id`, `processoList_id`) VALUES
	(2, 1);
/*!40000 ALTER TABLE `cpregao_cprocesso` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.cprocesso
DROP TABLE IF EXISTS `cprocesso`;
CREATE TABLE IF NOT EXISTS `cprocesso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `statusPedidos_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `pregao_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero` (`numero`),
  KEY `FK91FFBD23BF21AE7C` (`usuario_id`),
  KEY `FK91FFBD23AB45DE1C` (`statusPedidos_id`),
  KEY `FK91FFBD23F7A4ECB8` (`pregao_id`),
  CONSTRAINT `FK91FFBD23AB45DE1C` FOREIGN KEY (`statusPedidos_id`) REFERENCES `cstatuspedidos` (`id`),
  CONSTRAINT `FK91FFBD23BF21AE7C` FOREIGN KEY (`usuario_id`) REFERENCES `cusuario` (`id`),
  CONSTRAINT `FK91FFBD23F7A4ECB8` FOREIGN KEY (`pregao_id`) REFERENCES `cpregao` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.cprocesso: ~4 rows (aproximadamente)
DELETE FROM `cprocesso`;
/*!40000 ALTER TABLE `cprocesso` DISABLE KEYS */;
INSERT INTO `cprocesso` (`id`, `descricao`, `numero`, `statusPedidos_id`, `usuario_id`, `pregao_id`) VALUES
	(1, 'processo de informática 2014 <br>', '1312155445645456456454', 1, 1, NULL),
	(3, 'processo 2<br>', '3355565689889898888', 1, 1, NULL),
	(4, 'processo 3<br>', '445644g5sd4g44', 0, 1, NULL),
	(5, 'processo 4<br>', '94829348923849', 0, 1, NULL);
/*!40000 ALTER TABLE `cprocesso` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.cstatus
DROP TABLE IF EXISTS `cstatus`;
CREATE TABLE IF NOT EXISTS `cstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.cstatus: ~3 rows (aproximadamente)
DELETE FROM `cstatus`;
/*!40000 ALTER TABLE `cstatus` DISABLE KEYS */;
INSERT INTO `cstatus` (`id`, `nome`) VALUES
	(1, 0),
	(2, 2),
	(3, 6);
/*!40000 ALTER TABLE `cstatus` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.cstatusitem
DROP TABLE IF EXISTS `cstatusitem`;
CREATE TABLE IF NOT EXISTS `cstatusitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tempo` bigint(20) NOT NULL,
  `item_id` int(11) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK69E3EA86499887E` (`id`),
  KEY `FK69E3EA8596E8098` (`item_id`),
  KEY `FK69E3EA8CD1F68B8` (`status_id`),
  CONSTRAINT `FK69E3EA8596E8098` FOREIGN KEY (`item_id`) REFERENCES `citem` (`id`),
  CONSTRAINT `FK69E3EA86499887E` FOREIGN KEY (`id`) REFERENCES `cstatusitem` (`id`),
  CONSTRAINT `FK69E3EA8CD1F68B8` FOREIGN KEY (`status_id`) REFERENCES `cstatus` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.cstatusitem: ~11 rows (aproximadamente)
DELETE FROM `cstatusitem`;
/*!40000 ALTER TABLE `cstatusitem` DISABLE KEYS */;
INSERT INTO `cstatusitem` (`id`, `tempo`, `item_id`, `status_id`) VALUES
	(1, 1392157378349, 1, 1),
	(2, 1392157392435, 2, 1),
	(3, 1392157716327, 1, 2),
	(4, 1392157716328, 2, 2),
	(5, 1392158641649, 1, 3),
	(6, 1392158641655, 2, 3),
	(7, 1392224294422, 3, 1),
	(8, 1392224358017, 4, 1),
	(9, 1392224491587, 4, 2),
	(10, 1392225408787, 4, 3),
	(11, 1392657876953, 3, 2);
/*!40000 ALTER TABLE `cstatusitem` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.cstatuspedidos
DROP TABLE IF EXISTS `cstatuspedidos`;
CREATE TABLE IF NOT EXISTS `cstatuspedidos` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.cstatuspedidos: ~2 rows (aproximadamente)
DELETE FROM `cstatuspedidos`;
/*!40000 ALTER TABLE `cstatuspedidos` DISABLE KEYS */;
INSERT INTO `cstatuspedidos` (`id`, `nome`) VALUES
	(0, 'Aberto'),
	(1, 'Fechado');
/*!40000 ALTER TABLE `cstatuspedidos` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.ctipo
DROP TABLE IF EXISTS `ctipo`;
CREATE TABLE IF NOT EXISTS `ctipo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.ctipo: ~2 rows (aproximadamente)
DELETE FROM `ctipo`;
/*!40000 ALTER TABLE `ctipo` DISABLE KEYS */;
INSERT INTO `ctipo` (`id`, `nome`) VALUES
	(1, 'consumo'),
	(2, 'investimento');
/*!40000 ALTER TABLE `ctipo` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.ctipocatmat
DROP TABLE IF EXISTS `ctipocatmat`;
CREATE TABLE IF NOT EXISTS `ctipocatmat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `sigla` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.ctipocatmat: ~2 rows (aproximadamente)
DELETE FROM `ctipocatmat`;
/*!40000 ALTER TABLE `ctipocatmat` DISABLE KEYS */;
INSERT INTO `ctipocatmat` (`id`, `nome`, `sigla`) VALUES
	(1, 'Genérico', 'false'),
	(2, 'Específico', 'true');
/*!40000 ALTER TABLE `ctipocatmat` ENABLE KEYS */;


-- Copiando estrutura para tabela compras.cusuario
DROP TABLE IF EXISTS `cusuario`;
CREATE TABLE IF NOT EXISTS `cusuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `grupo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  KEY `FK2803B18BB634D91C` (`grupo_id`),
  CONSTRAINT `FK2803B18BB634D91C` FOREIGN KEY (`grupo_id`) REFERENCES `cgrupo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela compras.cusuario: ~1 rows (aproximadamente)
DELETE FROM `cusuario`;
/*!40000 ALTER TABLE `cusuario` DISABLE KEYS */;
INSERT INTO `cusuario` (`id`, `Nome`, `login`, `senha`, `grupo_id`) VALUES
	(1, 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', 1);
/*!40000 ALTER TABLE `cusuario` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
