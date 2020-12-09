DROP TABLE IF EXISTS UserCredential;
DROP TABLE IF EXISTS UserProfile;
DROP TABLE IF EXISTS User_Credential;
DROP TABLE IF EXISTS User_Credentials;
DROP TABLE IF EXISTS User_Profile;




--User Credential Data--
CREATE TABLE User_Credential (
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  id INTEGER NOT NULL
);

--User Profile Data Data--
CREATE TABLE User_Profile (
  id INTEGER NOT NULL,
  firstname VARCHAR(250) NOT NULL,
  lastname VARCHAR(250) NOT NULL,
  address VARCHAR(250) DEFAULT NULL,
  phone INTEGER NOT NULL
);
