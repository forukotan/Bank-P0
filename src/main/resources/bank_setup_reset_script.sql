DROP TABLE IF exists "user";

CREATE TABLE "user"(
username Varchar(30),
password text(30)
);

INSERT INTO "user" values  ('admin', 1234);

DROP TABLE IF exists "account";


create table "account"(
account_Id integer primary key autoincrement,
balance integer default 0,
account_Type text default 'checking',
account_holder text,
foreign key (account_holder ) references user(username)
);

insert into account(account_holder, account_Type) values('user', 'saving');