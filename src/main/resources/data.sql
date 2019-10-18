INSERT INTO User (id,username, password) values (1,'Olle','123');
insert into User (id,username, password) values (2,'Jerry','123');
insert into Role (userId, role) values (1, 'Admin');
insert into Role (userId, role) values (2,'Developer');
insert into Privilege (userId, privilege) values (2, 'SystemXUser');
insert into Privilege (userId, privilege) values (1, 'SystemXUser');

