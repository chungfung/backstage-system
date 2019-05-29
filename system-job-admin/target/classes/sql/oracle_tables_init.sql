/*==============================================================*/
/* DBMS name:      SAP SQL Anywhere 16                          */
/* Created on:     2019/5/10 16:10:11                           */
/*==============================================================*/

/* 删除表结构 */
DROP TABLE TD_BLOB_TRIGGERS PURGE ;
DROP TABLE TD_CALENDARS PURGE ;
DROP TABLE TD_CRON_TRIGGERS PURGE ;
DROP TABLE TD_FIRED_TRIGGERS PURGE ;
DROP TABLE TD_JOB_DETAILS PURGE ;
DROP TABLE TD_LOCKS PURGE ;
DROP TABLE TD_PAUSED_TRIGGER_GRPS PURGE ;
DROP TABLE TD_SCHEDULER_STATE PURGE ;
DROP TABLE TD_SIMPLE_TRIGGERS PURGE ;
DROP TABLE TD_SIMPROP_TRIGGERS PURGE ;
DROP TABLE TD_TRIGGER_GROUP PURGE ;
DROP TABLE TD_TRIGGER_INFO PURGE ;
DROP TABLE TD_TRIGGER_LOG PURGE ;
DROP TABLE TD_TRIGGER_LOGGLUE PURGE ;
DROP TABLE TD_TRIGGER_REGISTRY PURGE ;
DROP TABLE TD_TRIGGERS PURGE ;
DROP TABLE TD_USER PURGE ;

/** 删除索引 */
DROP INDEX TD_TRIGGER_LOG.IDX_TRIGGER_LOG_HANDLE_CODE ;
DROP INDEX TD_TRIGGER_LOG.IDX_TRIGGER_LOG_TRIGGER_TIME ;
DROP INDEX TD_TRIGGER_REGISTRY.IDX_TRIGGER_REGISTRY_GROUP_KEY_VALUE ;
DROP INDEX TD_TRIGGERS.IDX_TRIGGERS_NAME_NAME_GROUP ;
DROP INDEX TD_USER.IDX_USER_USERNAME ;

/** 删除序列 */
DROP SEQUENCE SEQ_TD_TRIGGER_GROUP;
DROP SEQUENCE SEQ_TD_TRIGGER_INFO;
DROP SEQUENCE SEQ_TD_TRIGGER_LOG;
DROP SEQUENCE SEQ_TD_TRIGGER_LOGGLUE;
DROP SEQUENCE SEQ_TD_TRIGGER_REGISTRY;
DROP SEQUENCE SEQ_TD_USER;

/** 创建序列 */
CREATE SEQUENCE SEQ_TD_TRIGGER_GROUP START WITH 1 INCREMENT BY 1 NOMAXVALUE NOMINVALUE NOCYCLE NOCACHE;
CREATE SEQUENCE SEQ_TD_TRIGGER_INFO START WITH 1 INCREMENT BY 1 NOMAXVALUE NOMINVALUE NOCYCLE NOCACHE;
CREATE SEQUENCE SEQ_TD_TRIGGER_LOG START WITH 1 INCREMENT BY 1 NOMAXVALUE NOMINVALUE NOCYCLE NOCACHE;
CREATE SEQUENCE SEQ_TD_TRIGGER_LOGGLUE START WITH 1 INCREMENT BY 1 NOMAXVALUE NOMINVALUE NOCYCLE NOCACHE;
CREATE SEQUENCE SEQ_TD_TRIGGER_REGISTRY START WITH 1 INCREMENT BY 1 NOMAXVALUE NOMINVALUE NOCYCLE NOCACHE;
CREATE SEQUENCE SEQ_TD_USER START WITH 1 INCREMENT BY 1 NOMAXVALUE NOMINVALUE NOCYCLE NOCACHE;

/*==============================================================*/
/* Table: TD_BLOB_TRIGGERS                                      */
/*==============================================================*/
CREATE TABLE TD_BLOB_TRIGGERS
(
   SCHED_NAME           VARCHAR2(120)                  NOT NULL,
   TRIGGER_NAME         VARCHAR2(200)                  NOT NULL,
   TRIGGER_GROUP        VARCHAR2(200)                  NOT NULL,
   BLOB_DATA            BLOB                           NULL,
   CONSTRAINT PK_TD_BLOB_TRIGGERS PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

/*==============================================================*/
/* Table: TD_CALENDARS                                          */
/*==============================================================*/
CREATE TABLE TD_CALENDARS
(
   SCHED_NAME           VARCHAR2(120)                  NOT NULL,
   CALENDAR_NAME        VARCHAR2(200)                  NOT NULL,
   CALENDAR             BLOB                           NULL,
   CONSTRAINT PK_TD_CALENDARS PRIMARY KEY (SCHED_NAME, CALENDAR_NAME)
);

/*==============================================================*/
/* Table: TD_CRON_TRIGGERS                                      */
/*==============================================================*/
CREATE TABLE TD_CRON_TRIGGERS
(
   SCHED_NAME           VARCHAR2(120)                  NOT NULL,
   TRIGGER_NAME         VARCHAR2(200)                  NOT NULL,
   TRIGGER_GROUP        VARCHAR2(200)                  NOT NULL,
   CRON_EXPRESSION      VARCHAR2(200)                  NULL,
   TIME_ZONE_ID         VARCHAR2(80)                   NULL,
   CONSTRAINT PK_TD_CRON_TRIGGERS PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

/*==============================================================*/
/* Table: TD_FIRED_TRIGGERS                                     */
/*==============================================================*/
CREATE TABLE TD_FIRED_TRIGGERS
(
   SCHED_NAME           VARCHAR2(120)                  NOT NULL,
   ENTRY_ID             VARCHAR2(95)                   NOT NULL,
   TRIGGER_NAME         VARCHAR2(200)                  NULL,
   TRIGGER_GROUP        VARCHAR2(200)                  NULL,
   INSTANCE_NAME        VARCHAR2(200)                  NULL,
   FIRED_TIME           NUMBER(20)                     NULL,
   SCHED_TIME           NUMBER(20)                     NULL,
   PRIORITY             NUMBER(11)                     NULL,
   STATE                VARCHAR2(16)                   NULL,
   JOB_NAME             VARCHAR2(200)                  NULL,
   JOB_GROUP            VARCHAR2(200)                  NULL,
   IS_NONCONCURRENT     VARCHAR2(1)                    NULL,
   REQUESTS_RECOVERY    VARCHAR2(1)                    NULL,
   CONSTRAINT PK_TD_FIRED_TRIGGERS PRIMARY KEY (SCHED_NAME, ENTRY_ID)
);

/*==============================================================*/
/* Table: TD_JOB_DETAILS                                        */
/*==============================================================*/
CREATE TABLE TD_JOB_DETAILS
(
   SCHED_NAME           VARCHAR2(120)                  NOT NULL,
   JOB_NAME             VARCHAR2(200)                  NOT NULL,
   JOB_GROUP            VARCHAR2(200)                  NOT NULL,
   DESCRIPTION          VARCHAR2(250)                  NULL,
   JOB_CLASS_NAME       VARCHAR2(250)                  NULL,
   IS_DURABLE           VARCHAR2(1)                    NULL,
   IS_NONCONCURRENT     VARCHAR2(1)                    NULL,
   IS_UPDATE_DATA       VARCHAR2(1)                    NULL,
   REQUESTS_RECOVERY    VARCHAR2(1)                    NULL,
   JOB_DATA             BLOB                           NULL,
   CONSTRAINT PK_TD_JOB_DETAILS PRIMARY KEY (SCHED_NAME, JOB_NAME, JOB_GROUP)
);

/*==============================================================*/
/* Table: TD_LOCKS                                              */
/*==============================================================*/
CREATE TABLE TD_LOCKS
(
   SCHED_NAME           VARCHAR2(120)                  NOT NULL,
   LOCK_NAME            VARCHAR2(40)                   NOT NULL,
   CONSTRAINT PK_TD_LOCKS PRIMARY KEY (SCHED_NAME, LOCK_NAME)
);

/*==============================================================*/
/* Table: TD_PAUSED_TRIGGER_GRPS                                */
/*==============================================================*/
CREATE TABLE TD_PAUSED_TRIGGER_GRPS
(
   SCHED_NAME           VARCHAR2(120)                  NOT NULL,
   TRIGGER_GROUP        VARCHAR2(200)                  NOT NULL,
   CONSTRAINT PK_TD_PAUSED_TRIGGER_GRPS PRIMARY KEY (SCHED_NAME, TRIGGER_GROUP)
);

/*==============================================================*/
/* Table: TD_SCHEDULER_STATE                                    */
/*==============================================================*/
CREATE TABLE TD_SCHEDULER_STATE
(
   SCHED_NAME           VARCHAR2(120)                  NOT NULL,
   INSTANCE_NAME        VARCHAR2(200)                  NOT NULL,
   LAST_CHECKIN_TIME    NUMBER(20)                     NULL,
   CHECKIN_INTERVAL     NUMBER(20)                     NULL,
   CONSTRAINT PK_TD_SCHEDULER_STATE PRIMARY KEY (SCHED_NAME, INSTANCE_NAME)
);

/*==============================================================*/
/* Table: TD_SIMPLE_TRIGGERS                                    */
/*==============================================================*/
CREATE TABLE TD_SIMPLE_TRIGGERS
(
   SCHED_NAME           VARCHAR2(120)                  NOT NULL,
   TRIGGER_NAME         VARCHAR2(200)                  NOT NULL,
   TRIGGER_GROUP        VARCHAR2(200)                  NOT NULL,
   REPEAT_COUNT         NUMBER(20)                     NULL,
   REPEAT_INTERVAL      NUMBER(20)                     NULL,
   TIMES_TRIGGERED      NUMBER(20)                     NULL,
   CONSTRAINT PK_TD_SIMPLE_TRIGGERS PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

/*==============================================================*/
/* Table: TD_SIMPROP_TRIGGERS                                   */
/*==============================================================*/
CREATE TABLE TD_SIMPROP_TRIGGERS
(
   SCHED_NAME           VARCHAR2(120)                  NOT NULL,
   TRIGGER_NAME         VARCHAR2(200)                  NOT NULL,
   TRIGGER_GROUP        VARCHAR2(200)                  NOT NULL,
   STR_PROP_1           VARCHAR2(512)                  NULL,
   STR_PROP_2           VARCHAR2(512)                  NULL,
   STR_PROP_3           VARCHAR2(512)                  NULL,
   INT_PROP_1           NUMBER(11)                     NULL,
   INT_PROP_2           NUMBER(11)                     NULL,
   LONG_PROP_1          NUMBER(20)                     NULL,
   LONG_PROP_2          NUMBER(20)                     NULL,
   DEC_PROP_1           NUMBER(13,4)                   NULL,
   DEC_PROP_2           NUMBER(13,4)                   NULL,
   BOOL_PROP_1          VARCHAR2(1)                    NULL,
   BOOL_PROP_2          VARCHAR2(1)                    NULL,
   CONSTRAINT PK_TD_SIMPROP_TRIGGERS PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

/*==============================================================*/
/* Table: TD_TRIGGER_GROUP                                      */
/*==============================================================*/
CREATE TABLE TD_TRIGGER_GROUP
(
   ID                   NUMBER(11)                     NOT NULL,
   APP_NAME             VARCHAR2(64)                   NULL,
   TITLE                VARCHAR2(15)                   NULL,
   "ORDER"              NUMBER(4)                      NULL,
   ADDRESS_TYPE         NUMBER(4)                      NULL,
   ADDRESS_LIST         VARCHAR2(512)                  NULL,
   CONSTRAINT PK_TD_TRIGGER_GROUP PRIMARY KEY (ID)
);

/*==============================================================*/
/* Table: TD_TRIGGER_INFO                                       */
/*==============================================================*/
CREATE TABLE TD_TRIGGER_INFO
(
   ID                   NUMBER(11)                     NOT NULL,
   JOB_GROUP            NUMBER(11)                     NULL,
   JOB_CRON             VARCHAR2(128)                  NULL,
   JOB_DESC             VARCHAR2(255)                  NULL,
   ADD_TIME             TIMESTAMP                       NULL,
   UPDATE_TIME          TIMESTAMP                       NULL,
   AUTHOR               VARCHAR2(64)                   NULL,
   ALARM_EMAIL          VARCHAR2(255)                  NULL,
   EXECUTOR_ROUTE_STRATEGY VARCHAR2(50)                   NULL,
   EXECUTOR_HANDLER     VARCHAR2(255)                  NULL,
   EXECUTOR_PARAM       VARCHAR2(512)                  NULL,
   EXECUTOR_BLOCK_STRATEGY VARCHAR2(50)                   NULL,
   EXECUTOR_TIMEOUT     NUMBER(11)                     NULL,
   EXECUTOR_FAIL_RETRY_COUNT NUMBER(11)                     NULL,
   GLUE_TYPE            VARCHAR(50)                    NULL,
   GLUE_SOURCE          VARCHAR2(3000)                 NULL,
   GLUE_REMARK          VARCHAR(128)                   NULL,
   GLUE_UPDATETIME      TIMESTAMP                       NULL,
   CHILD_JOBID          VARCHAR(255)                   NULL,
   CONSTRAINT PK_TD_TRIGGER_INFO PRIMARY KEY (ID)
);

/*==============================================================*/
/* Table: TD_TRIGGER_LOG                                        */
/*==============================================================*/
CREATE TABLE TD_TRIGGER_LOG
(
   ID                   NUMBER(20)                     NOT NULL,
   JOB_GROUP            NUMBER(11)                     NULL,
   JOB_ID               NUMBER(11)                     NULL,
   EXECUTOR_ADDRESS     VARCHAR2(255)                  NULL,
   EXECUTOR_HANDLER     VARCHAR2(255)                  NULL,
   EXECUTOR_PARAM       VARCHAR2(512)                  NULL,
   EXECUTOR_SHARDING_PARAM VARCHAR2(20)                NULL,
   EXECUTOR_FAIL_RETRY_COUNT NUMBER(11)                DEFAULT 0,
   TRIGGER_TIME         TIMESTAMP                       NULL,
   TRIGGER_CODE         NUMBER(11)                     NULL,
   TRIGGER_MSG          VARCHAR2(2048)                 NULL,
   HANDLE_TIME          TIMESTAMP                       NULL,
   HANDLE_CODE          NUMBER(11)                     NULL,
   HANDLE_MSG           VARCHAR2(2048)                 NULL,
   ALARM_STATUS         NUMBER(1)                      DEFAULT 0,
   CONSTRAINT PK_TD_TRIGGER_LOG PRIMARY KEY (ID)
);

COMMENT ON COLUMN TD_TRIGGER_LOG.ALARM_STATUS IS
'告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败';

/*==============================================================*/
/* Index: IDX_TRIGGER_LOG_TRIGGER_TIME                          */
/*==============================================================*/
CREATE INDEX IDX_TRIGGER_LOG_TRIGGER_TIME ON TD_TRIGGER_LOG (
TRIGGER_TIME ASC
);

/*==============================================================*/
/* Index: IDX_TRIGGER_LOG_HANDLE_CODE                           */
/*==============================================================*/
CREATE INDEX IDX_TRIGGER_LOG_HANDLE_CODE ON TD_TRIGGER_LOG (
HANDLE_CODE ASC
);

/*==============================================================*/
/* Table: TD_TRIGGER_LOGGLUE                                    */
/*==============================================================*/
CREATE TABLE TD_TRIGGER_LOGGLUE
(
   ID                   NUMBER(11)                     NOT NULL,
   JOB_ID               NUMBER(11)                     NULL,
   GLUE_TYPE            VARCHAR(50)                    NULL,
   GLUE_SOURCE          VARCHAR(4000)                  NULL,
   GLUE_REMARK          VARCHAR(128)                   NULL,
   ADD_TIME             TIMESTAMP                       NULL,
   UPDATE_TIME          TIMESTAMP                       NULL,
   CONSTRAINT PK_TD_TRIGGER_LOGGLUE PRIMARY KEY (ID)
);

/*==============================================================*/
/* Table: TD_TRIGGER_REGISTRY                                   */
/*==============================================================*/
CREATE TABLE TD_TRIGGER_REGISTRY
(
   ID                   NUMBER(11)                     NOT NULL,
   REGISTRY_GROUP       VARCHAR2(255)                  NULL,
   REGISTRY_KEY         VARCHAR2(255)                  NULL,
   REGISTRY_VALUE       VARCHAR2(255)                  NULL,
   UPDATE_TIME          TIMESTAMP                       NULL,
   CONSTRAINT PK_TD_TRIGGER_REGISTRY PRIMARY KEY (ID)
);

/*==============================================================*/
/* Index: IDX_TRIGGER_REGISTRY_GROUP_KEY_VALUE                  */
/*==============================================================*/
CREATE INDEX IDX_TRIGGER_REGISTRY_GROUP_KEY_VALUE ON TD_TRIGGER_REGISTRY (
REGISTRY_GROUP ASC,
REGISTRY_KEY ASC,
REGISTRY_VALUE ASC
);

/*==============================================================*/
/* Table: TD_TRIGGERS                                           */
/*==============================================================*/
CREATE TABLE TD_TRIGGERS
(
   SCHED_NAME           VARCHAR2(120)                  NOT NULL,
   TRIGGER_NAME         VARCHAR2(200)                  NOT NULL,
   TRIGGER_GROUP        VARCHAR2(200)                  NOT NULL,
   JOB_NAME             VARCHAR2(200)                  NULL,
   JOB_GROUP            VARCHAR2(200)                  NULL,
   DESCRIPTION          VARCHAR2(250)                  NULL,
   NEXT_FIRE_TIME       NUMBER(20)                     NULL,
   PREV_FIRE_TIME       NUMBER(20)                     NULL,
   PRIORITY             NUMBER(11)                     NULL,
   TRIGGER_STATE        VARCHAR2(16)                   NULL,
   TRIGGER_TYPE         VARCHAR2(8)                    NULL,
   START_TIME           NUMBER(20)                     NULL,
   END_TIME             NUMBER(20)                     NULL,
   CALENDAR_NAME        VARCHAR2(200)                  NULL,
   MISFIRE_INSTR        NUMBER(5)                      NULL,
   JOB_DATA             BLOB                           NULL,
   CONSTRAINT PK_TD_TRIGGERS PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

/*==============================================================*/
/* Index: IDX_TRIGGERS_NAME_NAME_GROUP                          */
/*==============================================================*/
CREATE INDEX IDX_TRIGGERS_NAME_NAME_GROUP ON TD_TRIGGERS (
SCHED_NAME ASC,
JOB_NAME ASC,
JOB_GROUP ASC
);

/*==============================================================*/
/* Table: TD_USER                                               */
/*==============================================================*/
CREATE TABLE TD_USER
(
   ID                   NUMBER(11)                     NOT NULL,
   USERNAME             VARCHAR2(50)                   NULL,
   PASSWORD             VARCHAR2(50)                   NULL,
   ROLE                 NUMBER(4)                      NULL,
   PERMISSION           VARCHAR2(255)                  NULL,
   CONSTRAINT PK_TD_USER PRIMARY KEY (ID)
);

/*==============================================================*/
/* Index: IDX_USER_USERNAME                                     */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_USER_USERNAME ON TD_USER (
USERNAME ASC
);

INSERT INTO TD_TRIGGER_GROUP(id,app_name, title, "ORDER", address_type, address_list) VALUES (SEQ_TD_TRIGGER_GROUP.nextval,'xxl-job-executor-sample', '示例执行器', 1, 0, NULL);
INSERT INTO TD_USER(id,username, password, role, permission) VALUES (SEQ_TD_USER.nextval,'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);
INSERT INTO TD_TRIGGER_INFO   (id,    job_group,    job_cron,    job_desc,    add_time,    update_time,    author,    alarm_email,    executor_route_strategy,    executor_handler,    executor_param,    executor_block_strategy,    executor_timeout,    executor_fail_retry_count,    glue_type,    glue_source,    glue_remark,    glue_updatetime,    child_jobid) VALUES   (seq_TD_TRIGGER_INFO.nextval,    1,    '0 0 0 * * ? *',    '测试任务1',    to_date('2018-11-03 22:21:31', 'yyyy-mm-dd hh24:mi:ss'),    to_date('2018-11-03 22:21:31', 'yyyy-mm-dd hh24:mi:ss'),    'XXL',    '',    'FIRST',    'demoJobHandler',    '',    'SERIAL_EXECUTION',    0,    0,    'BEAN',    '',    'GLUE代码初始化',    to_date('2018-11-03 22:21:31', 'yyyy-mm-dd hh24:mi:ss'),    '');