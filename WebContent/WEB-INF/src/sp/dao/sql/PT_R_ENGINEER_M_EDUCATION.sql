
create table PT_R_ENGINEER_M_EDUCATION(
    SEQ VARCHAR2(12) NOT NULL , /* */
    CARE_BOOK_ISSUE_NUM VARCHAR2(15) NOT NULL , /* */
    TERM VARCHAR2(3)  , /* */
    EDU_PROCNM VARCHAR2(30)  , /* */
    COMPLETION_NUM VARCHAR2(10)  , /* */
    EDU_ORGNM VARCHAR2(30)  , /* */
    WRT_ID VARCHAR2(16)  , /* */
    UPD_DT VARCHAR2(24)  , /* */
    WRT_DT VARCHAR2(24)  , /* */
    ETC1 VARCHAR2(20)  , /* */
    PRIMARY KEY(CARE_BOOK_ISSUE_NUM,SEQ)
   );
