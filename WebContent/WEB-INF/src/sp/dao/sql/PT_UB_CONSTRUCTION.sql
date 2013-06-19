
create table PT_UB_CONSTRUCTION(
    ORPE_NM VARCHAR2(50)  , /* */
    ORPE_TEL VARCHAR2(24)  , /* */
    ORPE_POSTNUM VARCHAR2(16)  , /* */
    ORPE_ADDR VARCHAR2(64)  , /* */
    ORPE_DETAILADDR VARCHAR2(128)  , /* */
    PLANER_NAME VARCHAR2(100)  , /* */
    PLANER_NM VARCHAR2(50)  , /* */
    PLANER_TEL VARCHAR2(24)  , /* */
    PLANER_POSTNUM VARCHAR2(16)  , /* */
    PLANER_ADDR VARCHAR2(64)  , /* */
    PLANER_DETAILADDR VARCHAR2(128)  , /* */
    RECV_DT VARCHAR2(24)  , /* */
    STRU_COMMIT_NUM VARCHAR2(50)  , /* */
    STRU_ADDR_POSTNUM VARCHAR2(16)  , /* */
    STRU_ADDR_ADDR VARCHAR2(64)  , /* */
    STRU_ADDR_DETAILADDR VARCHAR2(128)  , /* */
    USE VARCHAR2(100)  , /* */
    AREA VARCHAR2(10)  , /* */
    NUM_FL VARCHAR2(200)  , /* */
    WORK_ITEM VARCHAR2(250)  , /* */
    STE VARCHAR2(5)  , /* */
    SUV_APPL VARCHAR2(25)  , /* */
    CONFIRMER_NM VARCHAR2(25)  , /* */
    CONFIRMER_POSI VARCHAR2(50)  , /* */
    CONFIRMER_TEL VARCHAR2(24)  , /* */
    INSP_OPIN VARCHAR2(200)  , /* */
    PRE_ITEM VARCHAR2(500)  , /* */
    SW_BEF_REPO_DELINUM VARCHAR2(40)  , /* */
    REPO_DT VARCHAR2(24)  , /* */
    WRT_ID VARCHAR2(16)  , /* */
    CIV_RECV_NUM VARCHAR2(20)  , /* */
    SIDO_CODE VARCHAR2(5) NOT NULL , /* */
    SIGUNGU_CODE VARCHAR2(6) NOT NULL , /* */
    INS_DT VARCHAR2(24)  , /* */
    UPD_DT VARCHAR2(24)  , /* */
    RECV_NUM VARCHAR2(12) NOT NULL , /* */
    INSP_BAS VARCHAR2(255)  , /* */
    CER_DELI_NUM VARCHAR2(26)  , /* */
    CER_DELI_CONFIRM VARCHAR2(15)  , /* */
    INSP_APPL_DT VARCHAR2(24)  , /* */
    PROC_STE VARCHAR2(4)  , /* */
    CER_DELI_YN VARCHAR2(4)  , /* */
    PROC_OFFI_NM VARCHAR2(20)  , /* */
    WORK_ITEM_ETC VARCHAR2(200)  , /* */
    ETC_INSP_BAS VARCHAR2(200)  , /* */
    UPD_DT_OLD VARCHAR2(24)  , /* */
    BACKUP_TRANS_DT VARCHAR2(24)  , /* */
    CONFIRMER_OFFI_ID VARCHAR2(20)  , /* */
    CHG_INSP VARCHAR2(4)  , /* */
    STRU_INFO_BIGO VARCHAR2(4)  , /* */
    USE_ETC VARCHAR2(256)  , /* */
    ORPE_NM VARCHAR2(50)  , /* */
    ORPE_TEL VARCHAR2(24)  , /* */
    ORPE_POSTNUM VARCHAR2(16)  , /* */
    ORPE_ADDR VARCHAR2(64)  , /* */
    ORPE_DETAILADDR VARCHAR2(128)  , /* */
    PLANER_NAME VARCHAR2(100)  , /* */
    PLANER_NM VARCHAR2(50)  , /* */
    PLANER_TEL VARCHAR2(24)  , /* */
    PLANER_POSTNUM VARCHAR2(16)  , /* */
    PLANER_ADDR VARCHAR2(64)  , /* */
    PLANER_DETAILADDR VARCHAR2(128)  , /* */
    RECV_DT VARCHAR2(24)  , /* */
    STRU_COMMIT_NUM VARCHAR2(50)  , /* */
    STRU_ADDR_POSTNUM VARCHAR2(16)  , /* */
    STRU_ADDR_ADDR VARCHAR2(64)  , /* */
    STRU_ADDR_DETAILADDR VARCHAR2(128)  , /* */
    USE VARCHAR2(100)  , /* */
    AREA VARCHAR2(10)  , /* */
    NUM_FL VARCHAR2(200)  , /* */
    WORK_ITEM VARCHAR2(250)  , /* */
    STE VARCHAR2(5)  , /* */
    SUV_APPL VARCHAR2(25)  , /* */
    CONFIRMER_NM VARCHAR2(25)  , /* */
    CONFIRMER_POSI VARCHAR2(50)  , /* */
    CONFIRMER_TEL VARCHAR2(24)  , /* */
    INSP_OPIN VARCHAR2(200)  , /* */
    PRE_ITEM VARCHAR2(500)  , /* */
    SW_BEF_REPO_DELINUM VARCHAR2(40)  , /* */
    REPO_DT VARCHAR2(24)  , /* */
    WRT_ID VARCHAR2(16)  , /* */
    CIV_RECV_NUM VARCHAR2(20)  , /* */
    SIDO_CODE VARCHAR2(5) NOT NULL , /* */
    SIGUNGU_CODE VARCHAR2(6) NOT NULL , /* */
    INS_DT VARCHAR2(24)  , /* */
    UPD_DT VARCHAR2(24)  , /* */
    RECV_NUM VARCHAR2(12) NOT NULL , /* */
    INSP_BAS VARCHAR2(255)  , /* */
    CER_DELI_NUM VARCHAR2(26)  , /* */
    CER_DELI_CONFIRM VARCHAR2(15)  , /* */
    INSP_APPL_DT VARCHAR2(24)  , /* */
    PROC_STE VARCHAR2(4)  , /* */
    CER_DELI_YN VARCHAR2(4)  , /* */
    PROC_OFFI_NM VARCHAR2(20)  , /* */
    WORK_ITEM_ETC VARCHAR2(200)  , /* */
    ETC_INSP_BAS VARCHAR2(200)  , /* */
    UPD_DT_OLD VARCHAR2(24)  , /* */
    BACKUP_TRANS_DT VARCHAR2(24)  , /* */
    CONFIRMER_OFFI_ID VARCHAR2(20)  , /* */
    CHG_INSP VARCHAR2(1)  , /* */
    STRU_INFO_BIGO VARCHAR2(1)  , /* */
    PRIMARY KEY(RECV_NUM,RECV_NUM,SIDO_CODE,SIDO_CODE,SIGUNGU_CODE,SIGUNGU_CODE)
   );
