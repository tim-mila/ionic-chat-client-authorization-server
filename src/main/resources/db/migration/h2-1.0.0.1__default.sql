CREATE TABLE oauth_client_details (
    "NAME" VARCHAR(255),
    "CLIENT_ID" VARCHAR(255),
    "CLIENT_SECRET" VARCHAR(255),
    "RESOURCE_IDS" VARCHAR(255),
    "SCOPE" VARCHAR(255),
    "AUTHORIZED_GRANT_TYPES" VARCHAR(255),
    "WEB_SERVER_REDIRECT_URI" VARCHAR(255),
    "AUTHORITIES" VARCHAR(255),
    "ACCESS_TOKEN_VALIDITY" VARCHAR(255),
    "REFRESH_TOKEN_VALIDITY" VARCHAR(255),
    "ADDITIONAL_INFORMATION" VARCHAR(4096),
    "AUTOAPPROVE" VARCHAR(255),
    constraint "uidx_oauthclientdetails_name" unique("NAME"),
    constraint "uidx_oauthclientdetails_client_id" unique("CLIENT_ID"),
    constraint "uidx_oauthclientdetails_client_secret" unique("CLIENT_SECRET")
);

--create table oauth_client_token (
--    "token_id" VARCHAR(256),
--    "token" bytea,
--    "authentication_id" VARCHAR,
--    "user_name" VARCHAR(256),
--    "client_id" VARCHAR(256),
--    constraint "oauthclienttoken_pkey" primary key("authentication_id")
--);
--
--create table "oauth_access_token" (
--    "token_id" VARCHAR(256),
--    "token" bytea,
--    "authentication_id" VARCHAR(256),
--    "user_name" VARCHAR(256),
--    "client_id" VARCHAR(256),
--    "authentication" bytea,
--    "refresh_token" VARCHAR(256),
--    constraint "oauthaccesstoken_pkey" primary key("authentication_id")
--);
--
--create table "oauth_refresh_token"(
--    "token_id" VARCHAR(256),
--    "token" bytea,
--    "authentication" bytea
--);
--
--create table "oauth_code" (
--    "code" VARCHAR(256),
--    "authentication" bytea
--);
--
--create table "oauth_approvals" (
--	"userId" VARCHAR(256),
--	"clientId" VARCHAR(256),
--	"scope" VARCHAR(256),
--	"status" VARCHAR(10),
--	"expiresAt" timestamp without time zone,
--	"lastModifiedAt" timestamp without time zone
--);