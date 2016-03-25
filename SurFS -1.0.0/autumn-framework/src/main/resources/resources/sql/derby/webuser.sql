CREATE TABLE "WEBUSER" (
  "ID" INT NOT NULL GENERATED ALWAYS AS IDENTITY ,
  "DIRID" INT NOT NULL DEFAULT 0,
  "SOAPID" INT DEFAULT 0,
  "USERNAME" VARCHAR(30) NOT NULL DEFAULT '',
  "PASSWORD" VARCHAR(30) NOT NULL DEFAULT '',
  "REALNAME" VARCHAR(30) DEFAULT NULL,
  "USERGROUP" VARCHAR(30) DEFAULT NULL,
  "MOBILE" VARCHAR(30) DEFAULT NULL,
  "EMAIL" VARCHAR(60) DEFAULT NULL,
  "PERMISSION" VARCHAR(50) DEFAULT NULL,
  "STIMEOUT" INT NOT NULL DEFAULT 0,
  "ISACTIVE" SMALLINT NOT NULL DEFAULT 1,
  "IPLIST" VARCHAR(100) DEFAULT NULL,
  "EXTPARAMS" VARCHAR(60) DEFAULT NULL,
  "LOGINTIME" TIMESTAMP NOT NULL DEFAULT '1900-01-01 00:00:00',
  "MEMO" CLOB,
  "CREATETIME" TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY ("ID")
);
CREATE UNIQUE INDEX WEBUSER_IDX ON APP."WEBUSER"("USERNAME");
CREATE INDEX WEBUSER_REALNAME ON APP."WEBUSER"("REALNAME");
CREATE INDEX WEBUSER_USERGROUP ON APP."WEBUSER"("USERGROUP");
CREATE INDEX WEBUSER_MOBILE ON APP."WEBUSER"("MOBILE");
CREATE INDEX WEBUSER_EMAIL ON APP."WEBUSER"("EMAIL");
CREATE INDEX WEBUSER_ISACTIVE ON APP."WEBUSER"("ISACTIVE");
CREATE INDEX WEBUSER_LOGINTIME ON APP."WEBUSER"("LOGINTIME");
CREATE INDEX WEBUSER_DIRID ON APP."WEBUSER"("DIRID");
CREATE INDEX WEBUSER_SOAPID ON APP."WEBUSER"("SOAPID");
CREATE INDEX WEBUSER_EXTPARAMS ON APP."WEBUSER"("EXTPARAMS")