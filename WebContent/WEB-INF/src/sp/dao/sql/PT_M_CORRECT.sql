
create table PT_M_CORRECT(
    TMP_WRT_NUM VARCHAR2(12) NOT NULL , /* */
    WRT_NUM VARCHAR2(12) NOT NULL , /* */
    CORRECT_ORDER_CONT VARCHAR2(1024)  , /* */
    CORRECT_ORDER_START_DT VARCHAR2(8)  , /* */
    CORRECT_ORDER_END_DT VARCHAR2(8)  , /* */
    INS_DT VARCHAR2(24)  , /* */
    UPD_DT VARCHAR2(24)  , /* */
    WRT_ID VARCHAR2(16)  , /* */
    DECISION_DATE VARCHAR2(8)  , /* */
    LIS_END_DATE VARCHAR2(8)  , /* */
    BEFORE_DISPO VARCHAR2(1)  , /* */
    OPINI_SUBMIT VARCHAR2(1)  , /* */
    OPINI_SUBMIT_DATE VARCHAR2(8)  , /* */
    PRIMARY KEY(TMP_WRT_NUM,WRT_NUM)
   );
