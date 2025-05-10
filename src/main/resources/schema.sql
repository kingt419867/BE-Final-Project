DROP TABLE IF EXISTS transaction_types;
DROP TABLE IF EXISTS type;
DROP TABLE IF EXISTS trax_type;
DROP TABLE IF EXISTS transaction_register;
DROP TABLE IF EXISTS account_list;


CREATE TABLE account_list (
	account_id INT NOT NULL AUTO_INCREMENT,
	account_name VARCHAR(128) NOT NULL,
	account_number VARCHAR(60),
	account_contact VARCHAR(128),
	PRIMARY KEY(account_id)
);
CREATE TABLE transaction_register (
	transaction_id INT NOT NULL AUTO_INCREMENT,
	transaction_date VARCHAR(10) NOT NULL,
	account_id INT NOT NULL,
	verified BOOLEAN,
	payment_amount DECIMAL(7,2),
	deposit_amount DECIMAL(7,2),
	check_number INT,
	PRIMARY KEY(transaction_id),
	FOREIGN KEY(account_id) REFERENCES account_list (account_id) ON DELETE NO ACTION
);
CREATE TABLE trax_type (
	type_id INT NOT NULL AUTO_INCREMENT,
	type_code VARCHAR(10),
	PRIMARY KEY(type_id)
);
CREATE TABLE transaction_types (
	transaction_id INT NOT NULL,
	type_id INT NOT NULL,
	FOREIGN KEY(transaction_id) REFERENCES transaction_register (transaction_id) ON DELETE NO ACTION,
	FOREIGN KEY(type_id) REFERENCES trax_type (type_id) ON DELETE NO ACTION
);
