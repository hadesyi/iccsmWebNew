--DROP TABLE TB_ICS_SAUPJA_MT CASCADE CONSTRAINTS;

CREATE TABLE TB_ICS_SAUPJA_MT
(
  REGIST_NO                     VARCHAR2(150 BYTE),
  ICS_SIDO                       VARCHAR2(150 BYTE),
  BUSS_YMD                     VARCHAR2(150 BYTE),
  JURIDICAL_PERSON_GB    VARCHAR2(150 BYTE),
  VAT_NO                         VARCHAR2(150 BYTE),
  JURIDICAL_NO                VARCHAR2(150 BYTE),
  FIRM_NM_KOR                VARCHAR2(150 BYTE),
  REP_CERTI_RESI_NO_1      VARCHAR2(150 BYTE),
  REP_CERTI_RESI_NO_2      VARCHAR2(150 BYTE),
  REP_NM_KOR                  VARCHAR2(150 BYTE),
  REP_NM_CHI                   VARCHAR2(150 BYTE),
  SITE_ZIP_CD                    VARCHAR2(150 BYTE),
  SITE_ADDR_1                   VARCHAR2(150 BYTE),
  SITE_ADDR_2                   VARCHAR2(150 BYTE),
  PHONE_NO_OFFICE          VARCHAR2(150 BYTE),
  FAX_NO_OFFICE               VARCHAR2(150 BYTE),
  OFFICE_SPACE                 VARCHAR2(150 BYTE),
  DIAGNOS_AGENCY_GB      VARCHAR2(150 BYTE),
  DIAGNOS_AGENCY           VARCHAR2(150 BYTE),
  PAY_IN_CAPITAL_AMT_K    VARCHAR2(150 BYTE),
  REAL_CAPITAL_AMT_K       VARCHAR2(150 BYTE),
  UNION_INVEST_AMT_K      VARCHAR2(150 BYTE)
);



--DROP TABLE TB_ICS_KYUMUP_DT CASCADE CONSTRAINTS;

CREATE TABLE TB_ICS_KYUMUP_DT
(
  REGIST_NO         VARCHAR2(150 BYTE),
  SIDE_REGIST_NO  VARCHAR2(150 BYTE),
  SIDE_JOB_GB       VARCHAR2(150 BYTE)
);


--DROP TABLE TB_ICS_SILJUK_DT CASCADE CONSTRAINTS;

CREATE TABLE TB_ICS_SILJUK_DT
(
  REGIST_NO    VARCHAR2(150 BYTE),
  SILJUK_YEAR  VARCHAR2(150 BYTE),
  SILJUK_AMT   VARCHAR2(150 BYTE)
);



--DROP TABLE TB_ICS_EVALMAIN_DT CASCADE CONSTRAINTS;

CREATE TABLE TB_ICS_EVALMAIN_DT
(
  REGIST_NO     VARCHAR2(150 BYTE),
  EVAL_YMD      VARCHAR2(150 BYTE),
  EVAL_AMT      VARCHAR2(150 BYTE),
  STRART_YMD  VARCHAR2(150 BYTE),
  END_YMD       VARCHAR2(150 BYTE)
);



--DROP TABLE TB_ICS_PUNISH_DT CASCADE CONSTRAINTS;

CREATE TABLE TB_ICS_PUNISH_DT
(
  REGIST_NO           VARCHAR2(150 BYTE),
  ICS_SIDO              VARCHAR2(150 BYTE),
  PUNISH_AGENCY   VARCHAR2(150 BYTE),
  PUNISH_LIST         VARCHAR2(150 BYTE),
  PUNISH_NM         VARCHAR2(150 BYTE),
  START_YMD          VARCHAR2(150 BYTE),
  STOP_YMD           VARCHAR2(150 BYTE),
  PUNISH_REASON   VARCHAR2(150 BYTE),
  PUNISH_YMD        VARCHAR2(150 BYTE)
);



--DROP TABLE TB_ICS_DAEPYO_DT CASCADE CONSTRAINTS;

CREATE TABLE TB_ICS_DAEPYO_DT
(
  REGIST_NO       VARCHAR2(150 BYTE),
  ACCEPT_NO      VARCHAR2(150 BYTE),
  JUMIN_NO1      VARCHAR2(150 BYTE),
  JUMIN_NO2      VARCHAR2(150 BYTE),
  DAEPYO_NM     VARCHAR2(150 BYTE),
  CHANGE_YMD   VARCHAR2(150 BYTE)
);


--DROP TABLE TB_ICS_SANGHO_DT CASCADE CONSTRAINTS;

CREATE TABLE TB_ICS_SANGHO_DT
(
  REGIST_NO      VARCHAR2(150 BYTE),
  ACCEPT_NO     VARCHAR2(150 BYTE),
  SANGHO_NM   VARCHAR2(150 BYTE),
  CHANGE_YMD  VARCHAR2(150 BYTE)
);


--DROP TABLE TB_ICS_SOJAEJI_DT CASCADE CONSTRAINTS;

CREATE TABLE TB_ICS_SOJAEJI_DT
(
  REGIST_NO       VARCHAR2(150 BYTE),
  ACCEPT_NO      VARCHAR2(150 BYTE),
  ZIP_CD             VARCHAR2(150 BYTE),
  ADDR_1            VARCHAR2(150 BYTE),
  ADDR_2            VARCHAR2(150 BYTE),
  OFFICE_AREA    VARCHAR2(150 BYTE),
  PHONE_NO       VARCHAR2(150 BYTE),
  FAX_NO            VARCHAR2(150 BYTE),
  CHANGE_YMD   VARCHAR2(150 BYTE)
);


--DROP TABLE TB_ICS_JABON_DT CASCADE CONSTRAINTS;

CREATE TABLE TB_ICS_JABON_DT
(
  REGIST_NO       VARCHAR2(150 BYTE),
  ACCEPT_NO      VARCHAR2(150 BYTE),
  AGENCY_GB      VARCHAR2(150 BYTE),
  AGENCY_NM     VARCHAR2(150 BYTE),
  AGENCY_YMD   VARCHAR2(150 BYTE),
  PAY_AMT          VARCHAR2(150 BYTE),
  REAL_AMT        VARCHAR2(150 BYTE),
  INVEST_AMT     VARCHAR2(150 BYTE),
  CHANGE_YMD   VARCHAR2(150 BYTE)
);


--DROP TABLE TB_ICS_HISTORY_DT CASCADE CONSTRAINTS;

CREATE TABLE TB_ICS_HISTORY_DT
(
  REGIST_NO               VARCHAR2(150 BYTE),
  ACCEPT_NO              VARCHAR2(150 BYTE),
  JOB_GB                    VARCHAR2(150 BYTE),
  REPORT_NO              VARCHAR2(150 BYTE),
  REGIST_NO_BEFO       VARCHAR2(150 BYTE),
  REGIST_NO_AFTER      VARCHAR2(150 BYTE),
  CHANGE_YMD           VARCHAR2(150 BYTE)
);


--DROP TABLE TB_ICS_REP_DT CASCADE CONSTRAINTS;

CREATE TABLE TB_ICS_REP_DT
(
  REGIST_NO         VARCHAR2(150 BYTE),
  ACCEPT_NO        VARCHAR2(150 BYTE),
  ACCEPT_OFFICE   VARCHAR2(150 BYTE),
  JUMIN_NO          VARCHAR2(150 BYTE),
  NM_KOR            VARCHAR2(150 BYTE),
  NM_CHI             VARCHAR2(150 BYTE),
  FOREINER_YN      VARCHAR2(150 BYTE),
  CHANGE_YMD     VARCHAR2(150 BYTE)
);









