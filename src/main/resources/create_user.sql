CREATE USER 'drugstore_db_user'@'localhost' IDENTIFIED BY 'spring';

GRANT ALL PRIVILEGES ON drugstore.* TO 'drugstore_db_user'@'localhost' WITH GRANT OPTION;

SHOW GRANTS FOR 'drugstore_db_user'@'localhost';
