CREATE TABLE if NOT EXISTS
VerificationTokens (
  id int,
  email varchar(255),
  token varchar(255),
  expiryDate Date
);