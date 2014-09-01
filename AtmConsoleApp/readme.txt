!!!!!!!!!!
To Save you the trouble of setting this up on your own machine, I have included
a project in the same repository that uses a static datasource. Other than the Dao Class,
it is exactly the same 
!!!!!!!!!!


This app runs on a postgres database to host the data. 
The postgresql JDBC driver found here:

http://jdbc.postgresql.org/download.html

is required to work this database.

As well as going into the Dao.java class and editing the constant "PASSWORD" to the password 
you have chosen for your instance.

The database has the following tables:

-- Table: "ACCOUNT"


-- DROP TABLE "ACCOUNT";


CREATE TABLE "ACCOUNT"

(
  "ACCOUNT_ID" serial NOT NULL,
  
"PIN" character(4) NOT NULL,
  
CONSTRAINT "ACCOUNT_ID" 
PRIMARY KEY ("ACCOUNT_ID")
)

WITH (
  OIDS=FALSE
);

ALTER TABLE "ACCOUNT"
  
OWNER TO postgres;


-- Table: "TRANSACTION"


-- DROP TABLE "TRANSACTION";


CREATE TABLE "TRANSACTION"

(
  "TRANSACTION_ID" serial NOT NULL,
  
"CREDIT_AMOUNT" money NOT NULL DEFAULT 0,
  
"DEBIT_AMOUNT" money NOT NULL DEFAULT 0,
  
"ACCOUNT_ID" integer NOT NULL,
  
CONSTRAINT "TRANSACTION_ID" 
PRIMARY KEY ("TRANSACTION_ID"),
  
CONSTRAINT "TRANSACTION_ACCOUNT_ID" 
FOREIGN KEY ("ACCOUNT_ID")
      
REFERENCES "ACCOUNT" ("ACCOUNT_ID") 
MATCH SIMPLE
      
ON UPDATE NO ACTION ON DELETE NO ACTION
)

WITH (
  OIDS=FALSE
);

ALTER TABLE "TRANSACTION"
  
OWNER TO postgres;


-- Index: "fki_TRANSACTION_ACCOUNT_ID"


-- DROP INDEX "fki_TRANSACTION_ACCOUNT_ID";


CREATE INDEX "fki_TRANSACTION_ACCOUNT_ID"
  ON "TRANSACTION"
  USING btree
  ("ACCOUNT_ID");








