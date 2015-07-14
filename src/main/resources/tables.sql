-- Create USER "Address"
psql -h localhost -U postgres --port=5432 -c "create user address with password 'password' CREATEDB CREATEUSER"
-- Create DATABASE "addressbook"
createdb -h localhost -U address --port=5432 addressbook
-- Connect to the DATABASE
psql -h localhost -U address --port=5432 -s addressbook
-- Create table CONTACT
CREATE TABLE CONTACT(name text,email text,profession text,school text,hospital text);