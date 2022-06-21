-- # close autocommit 23s
DROP PROCEDURE IF EXISTS users_init;
DELIMITER //
CREATE PROCEDURE users_init()
BEGIN
    DECLARE i INT DEFAULT 1;
    set autocommit = 0;
    WHILE i <= 1000000 DO
        insert into test.users(id, username, password, balance)
        VALUES (i, 'lzm', '888', 123);
        SET i = i + 1;
        end while;
    commit;
end //
DELIMITER ;
CALL users_init();
