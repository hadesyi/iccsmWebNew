
create table PT_UB_DETAIL(
    DETAIL_CONT VARCHAR2(100)  , /* */
    ITEM_OUT VARCHAR2(4)  , /* */
    WRT_ID VARCHAR2(16)  , /* */
    INS_DT VARCHAR2(24)  , /* */
    UPD_DT VARCHAR2(24)  , /* */
    SRL NUMBER(4) NOT NULL , /* */
    SEQ NUMBER(22) NOT NULL , /* */
    SIDO_CODE VARCHAR2(5) NOT NULL , /* */
    RECV_NUM VARCHAR2(12) NOT NULL , /* */
    SIGUNGU_CODE VARCHAR2(4) NOT NULL , /* */
    PRIMARY KEY(RECV_NUM,SEQ,SIDO_CODE,SIGUNGU_CODE,SRL)
   );
