CREATE SCHEMA IF NOT EXISTS onskelampen;

use onskelampen;

drop table if exists onsker;
CREATE TABLE onsker(
                       id integer auto_increment PRIMARY KEY,
                       oname varchar(100),
                       odescription varchar(100),
                       oprice integer(255),
                       oamount integer(255),
                       olink varchar(100)
);

drop table if exists bruger;
CREATE TABLE bruger(
                       userid integer auto_increment PRIMARY KEY,
                       username varchar(100),
                       userpassword varchar(100)
);


insert into onsker (oname,odescription,oprice,oamount,olink) values ('computer', 'til at spille spil', 8000, 1, 'computersalg');
insert into onsker (oname,odescription,oprice,oamount,olink) values ('bil', 'til at køre', 48000, 1, 'børnevenlig');
insert into bruger (username, userpassword) VALUES ('Oskar', '1234');
insert into bruger (username, userpassword) VALUES ('Mikkel', '1234');
insert into bruger (username, userpassword) VALUES ('Jesper', '1234');
ALTER TABLE onsker ADD COLUMN userid INTEGER,
    ADD CONSTRAINT fk_userid FOREIGN KEY (userid) REFERENCES bruger(userid);
commit;