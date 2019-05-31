DROP INDEX "IDX_SYS_DICT_TYPE";

DROP TABLE "TB_SYS_DICT_TYPE" CASCADE CONSTRAINTS;
DROP TABLE "TB_SYS_DICT_DATA" CASCADE CONSTRAINTS;
DROP TABLE "TB_SYS_OPER_LOG" CASCADE CONSTRAINTS;

/*==============================================================*/
/* Table: "TB_SYS_DICT_TYPE"                                    */
/*==============================================================*/
CREATE TABLE "TB_SYS_DICT_TYPE"
(
   "ID"                 NUMBER(19)           NOT NULL,
   "DICT_NAME"          VARCHAR2(100),
   "DICT_TYPE"          VARCHAR2(100),
   "STATUS"             VARCHAR2(1),
   "CREATE_TIME"        TIMESTAMP,
   "CREATE_USER"        VARCHAR2(30),
   "UPDATE_TIME"        TIMESTAMP,
   "UPDATE_USER"        VARCHAR2(30),
   "REMARK"             VARCHAR2(500),
   CONSTRAINT PK_TB_SYS_DICT_TYPE PRIMARY KEY ("ID")
);

COMMENT ON TABLE "TB_SYS_DICT_TYPE" IS
'数据字典类型表';

COMMENT ON COLUMN "TB_SYS_DICT_TYPE"."STATUS" IS
'状态（0停用,1正常 ）';

COMMENT ON COLUMN "TB_SYS_DICT_TYPE"."CREATE_TIME" IS
'创建时间';

COMMENT ON COLUMN "TB_SYS_DICT_TYPE"."CREATE_USER" IS
'创建人';

COMMENT ON COLUMN "TB_SYS_DICT_TYPE"."UPDATE_TIME" IS
'修改时间';

COMMENT ON COLUMN "TB_SYS_DICT_TYPE"."UPDATE_USER" IS
'修改人';

/*==============================================================*/
/* Index: "IDX_SYS_DICT_TYPE"                                   */
/*==============================================================*/
CREATE UNIQUE INDEX "IDX_SYS_DICT_TYPE" ON "TB_SYS_DICT_TYPE" (
   "DICT_TYPE" ASC
);

/*==============================================================*/
/* Table: "TB_SYS_DICT_DATA"                                    */
/*==============================================================*/
CREATE TABLE "TB_SYS_DICT_DATA"
(
   "ID"                 NUMBER(19)           NOT NULL,
   "DICT_SORT"          NUMBER(3),
   "DICT_LABEL"         VARCHAR2(100),
   "DICT_VALUE"         VARCHAR2(100),
   "DICT_TYPE"          VARCHAR2(100),
   "IS_DEFAULT"         VARCHAR2(1),
   "STATUS"             VARCHAR2(1),
   "CREATE_TIME"        TIMESTAMP,
   "CREATE_USER"        VARCHAR2(30),
   "UPDATE_TIME"        TIMESTAMP,
   "UPDATE_USER"        VARCHAR2(30),
   "REMARK"             VARCHAR2(500),
   CONSTRAINT PK_TB_SYS_DICT_DATA PRIMARY KEY ("ID")
);

COMMENT ON TABLE "TB_SYS_DICT_DATA" IS
'数据字典值表';

COMMENT ON COLUMN "TB_SYS_DICT_DATA"."STATUS" IS
'状态（0停用,1正常 ）';

COMMENT ON COLUMN "TB_SYS_DICT_DATA"."CREATE_TIME" IS
'创建时间';

COMMENT ON COLUMN "TB_SYS_DICT_DATA"."CREATE_USER" IS
'创建人';

COMMENT ON COLUMN "TB_SYS_DICT_DATA"."UPDATE_TIME" IS
'修改时间';

COMMENT ON COLUMN "TB_SYS_DICT_DATA"."UPDATE_USER" IS
'修改人';

/*==============================================================*/
/* Table: "TB_SYS_OPER_LOG"                                     */
/*==============================================================*/
CREATE TABLE "TB_SYS_OPER_LOG"
(
   "ID"                 NUMBER(19)           NOT NULL,
   "TITLE"              VARCHAR2(50),
   "BUSINESS_TYPE"      NUMBER(2),
   "METHOD"             VARCHAR2(100),
   "OPERATOR_TYPE"      NUMBER(1),
   "OPER_NAME"          VARCHAR2(50),
   "OPER_URL"           VARCHAR2(255),
   "OPER_IP"            VARCHAR2(50),
   "OPER_LOCATION"      VARCHAR2(255),
   "OPER_PARAM"         VARCHAR2(2000),
   "STATUS"             NUMBER(1),
   "ERROR_MSG"          VARCHAR2(2000),
   "OPER_TIME"          TIMESTAMP,
   CONSTRAINT PK_TB_SYS_OPER_LOG PRIMARY KEY ("ID")
);

COMMENT ON TABLE "TB_SYS_OPER_LOG" IS
'操作日志表';

COMMENT ON COLUMN "TB_SYS_OPER_LOG"."BUSINESS_TYPE" IS
'业务类型（0其它 1新增 2修改 3删除）';

COMMENT ON COLUMN "TB_SYS_OPER_LOG"."OPERATOR_TYPE" IS
'操作类别（0其它 1后台用户 2手机端用户）';

COMMENT ON COLUMN "TB_SYS_OPER_LOG"."STATUS" IS
'操作状态（0正常 1异常）';
