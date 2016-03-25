CREATE TABLE "HIBERNATEMAP" (
"ID" NUMBER(8)  NOT NULL,
"HOST" VARCHAR2(30)  DEFAULT NULL,
"TITLE" VARCHAR2(60)  DEFAULT NULL,
"CLASSNAME" VARCHAR2(80)  DEFAULT NULL,
"DATASOURCE" VARCHAR2(20)  DEFAULT NULL,
"TABLENAME" VARCHAR2(20) DEFAULT NULL,
"CATALOGNAME" VARCHAR2(20) DEFAULT NULL,
"XMLMAP" CLOB,
"CREATETIME" TIMESTAMP  DEFAULT CURRENT_TIMESTAMP NOT NULL,
PRIMARY KEY ("ID")
);
--创建索引
CREATE UNIQUE INDEX "IX_HIBERNATEMAP_CLASSNAME" ON "HIBERNATEMAP" ("HOST", "CLASSNAME", "DATASOURCE");
CREATE INDEX "IX_HIBERNATEMAP_TITLE" ON "HIBERNATEMAP" ("TITLE");
CREATE INDEX "IX_HIBERNATEMAP_TABLENAME" ON "HIBERNATEMAP" ("TABLENAME");
CREATE INDEX "IX_HIBERNATEMAP_CREATETIME" ON "HIBERNATEMAP" ("CREATETIME");
CREATE INDEX "IX_HIBERNATEMAP_CATALOGNAME" ON "HIBERNATEMAP" ("CATALOGNAME");
--添加注释
COMMENT ON COLUMN "HIBERNATEMAP"."ID" IS '序号';
COMMENT ON COLUMN "HIBERNATEMAP"."HOST" IS '服务器机器名';
COMMENT ON COLUMN "HIBERNATEMAP"."TITLE" IS '标题';
COMMENT ON COLUMN "HIBERNATEMAP"."CLASSNAME" IS '映射类名';
COMMENT ON COLUMN "HIBERNATEMAP"."DATASOURCE" IS '数据库连接池名';
COMMENT ON COLUMN "HIBERNATEMAP"."TABLENAME" IS '映射的表名';
COMMENT ON COLUMN "HIBERNATEMAP"."CATALOGNAME" IS '数据库目录';
COMMENT ON COLUMN "HIBERNATEMAP"."XMLMAP" IS '映射配置';
COMMENT ON COLUMN "HIBERNATEMAP"."CREATETIME" IS '创建/最后修改时间';
--建立序列
CREATE SEQUENCE HIBERNATEMAP_SQ;
--建立触发器，以设置自动增量字段的值
CREATE OR REPLACE TRIGGER HIBERNATEMAP_TR
BEFORE INSERT ON HIBERNATEMAP
FOR EACH ROW
DECLARE
BEGIN
  IF :NEW.ID IS NULL THEN
    SELECT HIBERNATEMAP_SQ.NEXTVAL INTO :NEW.ID FROM DUAL;
  END IF;
END;