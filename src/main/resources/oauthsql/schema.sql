drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove tinyint
)DEFAULT CHARACTER SET = utf8;

INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types) VALUES ('mobile', 'e4624f06-9cda-4f25-8c88-2936617c0480', 'read,write,trust','password,authorization_code,refresh_token,implicit');

create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BLOB,
  refresh_token VARCHAR(255)
)DEFAULT CHARACTER SET = utf8;

create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication BLOB
)DEFAULT CHARACTER SET = utf8;
