
create table PT_UB_FILE(
    FILE_NM VARCHAR2(100)  , /* */
    FILE_SIZE VARCHAR2(10)  , /* */
    WRT_ID VARCHAR2(16)  , /* */
    INS_DT VARCHAR2(24)  , /* */
    UPD_DT VARCHAR2(24)  , /* */
    SEQ NUMBER(22) NOT NULL , /* */
    RECV_NUM VARCHAR2(30) NOT NULL , /* */
    SIDO_CODE VARCHAR2(5) NOT NULL , /* */
    SIGUNGU_CODE VARCHAR2(4) NOT NULL , /* */
    UPD_DT_OLD VARCHAR2(24)  , /* */
    BACKUP_TRANS_DT VARCHAR2(24)  , /* */
    SYS_FILE_NAME VARCHAR2(281)  , /* */
    PRIMARY KEY(RECV_NUM,SEQ,SIDO_CODE,SIGUNGU_CODE)
   );
