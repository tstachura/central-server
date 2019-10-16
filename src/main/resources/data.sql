TRUNCATE TABLE PRODUCT CASCADE;
TRUNCATE TABLE PRODUCT_TYPE CASCADE;
TRUNCATE TABLE WAREHOUSE CASCADE;
TRUNCATE TABLE USERDATA CASCADE;
TRUNCATE TABLE ADDRESS CASCADE;
TRUNCATE TABLE OAUTH_CLIENT_DETAILS CASCADE;
TRUNCATE TABLE AUTHORITY CASCADE;
TRUNCATE TABLE ADDRESS CASCADE;
TRUNCATE TABLE USER_ CASCADE;
TRUNCATE TABLE USER_ROLE CASCADE;
TRUNCATE TABLE USERS_ROLES_AUTHORITIES CASCADE;

--//---- ADDRESSES ----//--
INSERT INTO public.address (id, building_number, city, deleted, flat_number, street, version) VALUES (1, '60', 'Kraków', false, null, 'Podole', 0);
INSERT INTO public.address (id, building_number, city, deleted, flat_number, street, version) VALUES (2, '2', 'Warszawa', false, null, 'żelazna', 0);
INSERT INTO public.address (id, building_number, city, deleted, flat_number, street, version) VALUES (3, '3', 'Łódź', false, '2', 'Piotrkowaska', 0);
INSERT INTO public.address (id, building_number, city, deleted, flat_number, street, version) VALUES (4, '4', 'Łódź', false, null, 'Kościuszki', 0);
INSERT INTO public.address (id, building_number, city, deleted, flat_number, street, version) VALUES (5, '5', 'Lublin', false, null, 'Lipowa', 0);
INSERT INTO public.address (id, building_number, city, deleted, flat_number, street, version) VALUES (6, '6', 'Sopot', false, null, 'Grunwaldzka', 0);
INSERT INTO public.address (id, building_number, city, deleted, flat_number, street, version) VALUES (7, '7', 'Szczecin', false, null, 'Kolumba', 0);
INSERT INTO public.address (id, building_number, city, deleted, flat_number, street, version) VALUES (8, '1', 'Zakopane', false, null, 'Krupówki', 0);
INSERT INTO public.address (id, building_number, city, deleted, flat_number, street, version) VALUES (9, '1', 'Zakopane', false, null, 'Krupówki', 0);
INSERT INTO public.address (id, building_number, city, deleted, flat_number, street, version) VALUES (10, '1', 'Zakopane', false, null, 'Krupówki', 0);
INSERT INTO public.address (id, building_number, city, deleted, flat_number, street, version) VALUES (11, '1', 'Zakopane', false, null, 'Krupówki', 0);
INSERT INTO public.address (id, building_number, city, deleted, flat_number, street, version) VALUES (12, '5', 'Kraków', false, '3', 'Dworcowa', 0);

--//---- USERDATA ----//--
INSERT INTO public.userdata (id, join_date, email, name, position, surname, version, workplace, address_id) VALUES (4, '2019-01-13 02:28:56.848000', 'email4@email.com', 'John', null, 'William', 0, null, 8);
INSERT INTO public.userdata (id, join_date, email, name, position, surname, version, workplace, address_id) VALUES (1, '2011-03-12 12:00:00.000000', 'email1@email.com', 'Tomasz', null, 'Stachura', 0, null, 1);
INSERT INTO public.userdata (id, join_date, email, name, position, surname, version, workplace, address_id) VALUES (3, '2019-01-13 02:30:51.643000', 'email3@email.com', 'David', null, 'William', 0, null, 10);
INSERT INTO public.userdata (id, join_date, email, name, position, surname, version, workplace, address_id) VALUES (2, '2019-01-13 02:30:31.659000', 'email2@email.com', 'Robert', null, 'William', 0, null, 9);
INSERT INTO public.userdata (id, join_date, email, name, position, surname, version, workplace, address_id) VALUES (5, '2019-01-19 12:45:25.764000', 'email5@email.com', 'Kamil', 'Programista', 'Nowak', 0, null, 12);

--//---- AUTHORITIES ----//--

---- Products ----
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (1,TRUE, 'PRODUCT_READ');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (2,TRUE, 'PRODUCT_LIST_READ');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (3,TRUE, 'PRODUCT_LIST_FOR_WAREHOUSE_READ');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (4,TRUE, 'PRODUCT_CREATE');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (5,TRUE, 'PRODUCT_UPDATE');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (6,TRUE, 'PRODUCT_DELETE');

---- Product Types ----
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (7,TRUE, 'PRODUCT_TYPE_READ');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (8,TRUE, 'PRODUCT_TYPE_LIST_READ');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (9,TRUE, 'PRODUCT_TYPE_CREATE');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (10,TRUE, 'PRODUCT_TYPE_UPDATE');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (11,TRUE, 'PRODUCT_TYPE_DELETE');

---- Warehouse ----
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (12,TRUE, 'WAREHOUSE_READ');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (13,TRUE, 'WAREHOUSE_LIST_READ');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (14,TRUE, 'WAREHOUSE_CREATE');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (15,TRUE, 'WAREHOUSE_UPDATE');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (16,TRUE, 'WAREHOUSE_DELETE');

---- User ----
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (17,TRUE, 'USER_DELETE');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (18,TRUE, 'ACCOUNT_UPDATE_ADMIN');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (19,TRUE, 'USER_READ');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (20,TRUE, 'USER_ROLES_READ');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (21,TRUE, 'PASSWORD_UPDATE');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (22,TRUE, 'PASSWORD_READ');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (23,TRUE, 'PASSWORD_ADMIN_UPDATE');
INSERT INTO AUTHORITY(ID,ACTIVE, NAME) VALUES (24,TRUE, 'PASSWORD_ADMIN_READ');

--//---- USER ----//--
INSERT INTO public.user_ (id, account_expired, account_locked, credentials_expired, enabled, password, user_name, version, userdata_id) VALUES (1, false, false, false, true, '$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha', 'admin', 0, 1);
INSERT INTO public.user_ (id, account_expired, account_locked, credentials_expired, enabled, password, user_name, version, userdata_id) VALUES (2, false, false, false, true, '$2a$08$uSVsWY3W1VPF/fKGHlwYN.ntllnUW6eYOTsRrPeO4VZ5d8dYJymFC', 'warehouseman', 0, 4);
INSERT INTO public.user_ (id, account_expired, account_locked, credentials_expired, enabled, password, user_name, version, userdata_id) VALUES (3, false, false, false, true, '$2a$08$KjqKtpwHqXLJWo2XEP5u6uYJ3gtkKPNMgKxAnlG9pJnbzl4QQWIQ6', 'user', 0, 5);

--//---- USER ROLE ----//--
INSERT INTO USER_ROLE(ID,NAME,ACTIVE) VALUES (1,'ADMIN',TRUE );
INSERT INTO USER_ROLE(ID,NAME,ACTIVE) VALUES (2,'USER',TRUE );
INSERT INTO USER_ROLE(ID,NAME,ACTIVE) VALUES (3,'WAREHOUSEMAN',TRUE );

--//---- USERS ROLES ----//--
INSERT INTO public.users_roles (user_id, user_role_id) VALUES (1, 1);
INSERT INTO public.users_roles (user_id, user_role_id) VALUES (1, 2);
INSERT INTO public.users_roles (user_id, user_role_id) VALUES (1, 3);
INSERT INTO public.users_roles (user_id, user_role_id) VALUES (2, 2);
INSERT INTO public.users_roles (user_id, user_role_id) VALUES (2, 3);
INSERT INTO public.users_roles (user_id, user_role_id) VALUES (3, 2);

--//---- USER ROLES AUTHORITIES ----//--
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 17);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 18);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 23);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (1, 24);

---- USER ----
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (2, 19);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (2, 20);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (2, 21);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (2, 22);

---- WAREHOUSEMAN ----
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 1);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 2);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 3);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 4);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 5);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 6);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 7);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 8);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 9);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 10);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 11);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 12);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 13);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 14);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 15);
INSERT INTO USERS_ROLES_AUTHORITIES(USER_ROLE_ID, AUTHORITY_ID) VALUES (3, 16);

INSERT INTO public.warehouse (id, deleted, name, version) VALUES (1, false, 'Łódź | Office warehouse', 0);

INSERT INTO public.product_type (id, cost, deleted, manufacture, name, version) VALUES (2, 379, false, 'ASUS', 'Mysz Razer Naga Trinity (RZ01-02410100-R3M1)', 0);
INSERT INTO public.product_type (id, cost, deleted, manufacture, name, version) VALUES (1, 2099, false, 'Lenovo', 'Laptop Lenovo V110-15IKB (80TH003CPB)', 0);

INSERT INTO public.product (id, create_date, deleted, last_update, serial_number, status, version, warehouse_id, product_type_id) VALUES (2, '2011-03-12 12:00:00.000000', false, '2011-03-12 12:00:00.000000', 'XYZ123LPZ', 'available', 0, 1,1);
INSERT INTO public.product (id, create_date, deleted, last_update, serial_number, status, version, warehouse_id, product_type_id) VALUES (1, '2011-03-12 12:00:00.000000', false, '2018-03-12 12:00:00.000000', 'XYZ153LPZ', 'available', 0, 1,1);
INSERT INTO public.product (id, create_date, deleted, last_update, serial_number, status, version, warehouse_id, product_type_id) VALUES (3, '2011-03-12 12:00:00.000000', false, '2011-03-12 12:30:00.000000', 'XYZ123JPZ', 'available', 0, 1,1);
INSERT INTO public.product (id, create_date, deleted, last_update, serial_number, status, version, warehouse_id, product_type_id) VALUES (4, '2011-03-12 12:00:00.000000', false, '2011-03-12 12:00:00.000000', 'XYZ1H3LPZ', 'available', 0, 1,1);
INSERT INTO public.product (id, create_date, deleted, last_update, serial_number, status, version, warehouse_id, product_type_id) VALUES (5, '2011-03-12 12:00:00.000000', false, '2011-03-12 12:00:00.000000', 'XY345GLPZ', 'available', 0, 1,1);
INSERT INTO public.product (id, create_date, deleted, last_update, serial_number, status, version, warehouse_id, product_type_id) VALUES (6, '2011-03-12 12:00:00.000000', false, '2011-03-12 12:30:00.000000', 'XYZ12DDPZ', 'available', 0, 1,2);
INSERT INTO public.product (id, create_date, deleted, last_update, serial_number, status, version, warehouse_id, product_type_id) VALUES (7, '2011-03-12 12:00:00.000000', false, '2018-03-12 12:00:00.000000', 'XYZ189LPZ', 'available', 0, 1,2);
INSERT INTO public.product (id, create_date, deleted, last_update, serial_number, status, version, warehouse_id, product_type_id) VALUES (8, '2011-03-12 12:00:00.000000', false, '2018-03-12 12:00:00.000000', 'XYZ349LPZ', 'available', 0, 1,2);
INSERT INTO public.product (id, create_date, deleted, last_update, serial_number, status, version, warehouse_id, product_type_id) VALUES (9, '2011-03-12 12:00:00.000000', false, '2018-03-12 12:00:00.000000', 'XYZ189LRT', 'available', 0, 1,2);