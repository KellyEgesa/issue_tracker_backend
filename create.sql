CREATE DATABASE issue_tracker;
\c issue_tracker;
CREATE TABLE users (id SERIAL PRIMARY KEY, userName VARCHAR,positionInCompany VARCHAR, userRole VARCHAR,departmentId INTEGER);
CREATE TABLE news (id SERIAL PRIMARY KEY, newsInfo VARCHAR, type VARCHAR,departmentId INTEGER);
CREATE TABLE department (id SERIAL PRIMARY KEY, departmentName VARCHAR, departmentDescription VARCHAR,departmentEmployees INTEGER);
CREATE DATABASE organisation_news_test WITH TEMPLATE organisation_news;