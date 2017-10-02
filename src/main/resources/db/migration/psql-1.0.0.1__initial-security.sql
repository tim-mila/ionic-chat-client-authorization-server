
CREATE TABLE "users" (
    "id" bigint NOT NULL,
    "username" character varying(255) not null,
    "password" character varying(255) not null,
    "enabled" boolean,
    constraint "users_pkey" primary key("id"),
    constraint "uidx_users_username" UNIQUE ("username")
);

CREATE TABLE "authorities" (
    "username" character varying(255) not null,
    "authority" character varying(255) not null
);

CREATE INDEX idx_authorities_username on "authorities" ("username");

CREATE TABLE "oauth_client_details" (
    "id" bigint NOT NULL,
    "name" character varying(255) not null,
    "client_id" character varying(255) not null,
    "client_secret" character varying(255) not null,
    "resource_ids" character varying(255),
    "scope" character varying(255),
    "authorized_grant_types" character varying(255),
    "web_server_redirect_uri" character varying(255),
    "authorities" character varying(255),
    "access_token_validity" character varying(255),
    "refresh_token_validity" character varying(255),
    "additional_information" character varying(4096),
    "autoapprove" character varying(255),
    constraint "oauthclientdetails_pkey" primary key("id"),
    constraint "uidx_oauthclientdetails_name" unique("name"),
    constraint "uidx_oauthclientdetails_client_id" unique("client_id"),
    constraint "uidx_oauthclientdetails_client_secret" unique("client_secret")
);

create table "oauth_client_token" (
    "token_id" character varying(256),
    "token" bytea,
    "authentication_id" character varying,
    "user_name" character varying(256),
    "client_id" character varying(256),
    constraint "oauthclienttoken_pkey" primary key("authentication_id")
);

create table "oauth_access_token" (
    "token_id" character varying(256),
    "token" bytea,
    "authentication_id" character varying(256),
    "user_name" character varying(256),
    "client_id" character varying(256),
    "authentication" bytea,
    "refresh_token" character varying(256),
    constraint "oauthaccesstoken_pkey" primary key("authentication_id")
);

create table "oauth_refresh_token"(
    "token_id" character varying(256),
    "token" bytea,
    "authentication" bytea
);

create table "oauth_code" (
    "code" character varying(256),
    "authentication" bytea
);

create table "oauth_approvals" (
	"userId" character varying(256),
	"clientId" character varying(256),
	"scope" character varying(256),
	"status" character varying(10),
	"expiresAt" timestamp without time zone,
	"lastModifiedAt" timestamp without time zone
);
