ALTER TABLE pessoa
ADD COLUMN `login` varchar(50) NOT NULL AFTER `ativo`;

ALTER TABLE pessoa
ADD COLUMN `senha` varchar(20) NOT NULL AFTER `login`;


UPDATE pessoa SET login='ggd@gmail' WHERE nome='Gabriel';
UPDATE pessoa SET senha=123 WHERE nome='Gabriel';
UPDATE pessoa SET login='rafa@gmail' WHERE nome='Rafael';
UPDATE pessoa SET login=123 WHERE nome='Rafael';
UPDATE pessoa SET login='babis@gmail' WHERE nome='Bárbara';
UPDATE pessoa SET login=123 WHERE nome='Bárbara';