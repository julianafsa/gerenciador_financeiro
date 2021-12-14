INSERT INTO categoria (nome, limite_mensal) VALUES
('Alimentação', '0'),       --1
('Educação', '0'),          --2
('Lazer', '200'),           --3
('Turismo', '0'),           --4
('Vestuário', '0'),         --5

('Salário', '0'),           --6
('Aluguel', '0'),           --7 
('Restituição de IR', '0'), --8 
('Lucro', '0'),             --9
('Jogos de azar', '0');     --10

INSERT INTO movimentacao (tipo, descricao, valor, categoria_fk, data_criacao) VALUES
('0', 'Cinema', '80.00', '3', '2021-12-01'),
('0', 'Supermercado', '1000.00', '1', '2021-12-05'),
('0', 'Frigorífico', '250.00', '1', '2021-12-05'),
('0', 'Bebidas', '950.00', '1', '2021-12-05'),
('0', 'Colégio Santo Agostinho', '2500.00', '2', '2021-12-06'),
('0', 'Visita Cristo Redentor', '100.00', '4', '2021-12-10'),
('0', 'Roupas para Natal', '300.00', '5', '2021-12-11'),
('0', 'Sapatos para Natal', '120.00', '5', '2021-12-16'),

('1', 'Salário', '10000.00', '6', '2021-12-01'),
('1', 'Aluguéis casas', '3500.00', '7', '2021-12-10'),
('1', 'Restituição Imposto de Renda', '2000.00', '8', '2021-12-01'),
('1', 'Lucro lojas', '100.00', '9', '2021-12-05'),
('1', 'Jogo do bicho', '300.00', '10', '2021-12-02');