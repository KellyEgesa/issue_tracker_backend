CREATE DATABASE issue_tracker;
\c issue_tracker;
CREATE TABLE users (userId SERIAL PRIMARY KEY, fireBaseUserId VARCHAR,userName VARCHAR, email VARCHAR);
CREATE TABLE groups (groupId SERIAL PRIMARY KEY, groupName VARCHAR,userAdminId INTEGER);
CREATE TABLE project (projectId SERIAL PRIMARY KEY, projectName VARCHAR,groupId INTEGER, projectDescription VARCHAR,duration TIMESTAMP);
CREATE TABLE tickets (ticketId SERIAL PRIMARY KEY, ticketName VARCHAR, ticketDescription VARCHAR, ticketStatus INTEGER,ticketPriority INTEGER,ticketDueDate TIMESTAMP,ticketProjectId INTEGER);
CREATE TABLE ticketuser (userId INTEGER,ticketId INTEGER);
CREATE TABLE usergroup (userId INTEGER,groupId INTEGER);
CREATE DATABASE issue_tracker_test WITH TEMPLATE issue_tracker;