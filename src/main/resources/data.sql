CREATE SCHEMA IF NOT EXISTS onskelampen;

SET SCHEMA onskelampen;

drop table if exists onsker;
CREATE TABLE onsker(
                       id integer auto_increment PRIMARY KEY,
                       oname varchar(100),
                       odescription varchar(100),
                       oprice interger(255),
                       oamount interger(255),
                       olink varchar(255)
);

insert into onsker (oname,odescription,oprice,oamount,olink) values ('computer', 'til at spille spil', 8000, 1, 'computersalg');
insert into onsker (oname,odescription,oprice,oamount,olink) values ('bil', 'til at køre', 48000, 1, 'børnevenlig');
commit;