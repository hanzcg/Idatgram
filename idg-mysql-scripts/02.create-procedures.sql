use `demo_db`;

DELIMITER $$
CREATE PROCEDURE `usp_users_add`(in p_email varchar(45), in p_name varchar(30), in p_last_name varchar(35))
BEGIN
	declare newId int default 0;
	select IFNULL(max(id), 0) + 1 into newId from users;

	insert 	
    into 	users (id, email, name, last_name) 
    values 	(newId, p_email, p_name, p_last_name);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `usp_users_del`(in p_id int)
BEGIN
	delete 
    from 	users 
    where 	id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `usp_users_get_all`()
BEGIN
	select 	id, email, name, last_name  
    from 	users;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `usp_users_get_by_email`(in p_email varchar(45))
BEGIN
	select 	id, email, name, last_name 
    from 	users 
    where	email = p_email;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `usp_users_get_by_id`(in p_id int)
BEGIN
	select 	id, email, name, last_name 
    from 	users 
    where	id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `usp_users_upd`(in p_id int, in p_email varchar(45), in p_name varchar(30), in p_last_name varchar(35))
BEGIN
	update  users 
	set 	email = p_email, 
            name = p_name, 
            last_name = p_last_name 
	where 	id = p_id;
END$$
DELIMITER ;
