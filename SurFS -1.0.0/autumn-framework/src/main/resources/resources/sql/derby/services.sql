CREATE TABLE "SERVICES" (
  "ID" INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
  "HOST" VARCHAR(30) NOT NULL DEFAULT '',
  "TITLE" VARCHAR(60) DEFAULT NULL,
  "CLASSNAME" VARCHAR(80) DEFAULT NULL,
  "PARAMS" CLOB,
  "LOGNAME" VARCHAR(20) NOT NULL DEFAULT 'system',
  "STATUS" SMALLINT NOT NULL DEFAULT 0,
  "MEMO" CLOB,
  "CREATETIME" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY ("ID")
);
CREATE UNIQUE INDEX SERVICES_IDX ON APP."SERVICES"("HOST","TITLE");
CREATE INDEX SERVICES_CLASSNAME ON APP."SERVICES"("CLASSNAME");
CREATE INDEX SERVICES_STATUS ON APP."SERVICES"("STATUS")