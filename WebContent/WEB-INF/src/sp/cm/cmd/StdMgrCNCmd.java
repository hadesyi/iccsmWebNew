/**
 * 파일명   : ComCodeMgrCmd.java
 * 설명     : 공통코드관리 cmd
 * 이력사항
 * CH00     : 2006/09/21 양석환 최초작성
 */

package sp.cm.cmd;

import javax.servlet.http.HttpServletRequest;

import kjf.action.KJFCommand;
import kjf.cfg.Config;
import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
import kjf.util.KJFMi;
import kjf.util.KJFUtil;

import org.apache.struts.action.ActionForm;

import sp.util.keng.KengWorker;
import sp.util.keng.Kengineer;

import com.tobesoft.platform.PlatformRequest;
import com.tobesoft.platform.data.ColumnInfo;
import com.tobesoft.platform.data.Dataset;
import com.tobesoft.platform.data.DatasetList;
import com.tobesoft.platform.data.VariableList;

public class StdMgrCNCmd<PT_R_DOC_NUM> implements KJFCommand {
	
    private String next;
    
    private DatasetList  dl = new DatasetList();
    private VariableList vl = new VariableList();

    public StdMgrCNCmd() {
    }

    public StdMgrCNCmd(String next_url) {
    	next = next_url;
    }



    public String execute(HttpServletRequest request, ActionForm form) throws Exception {
    	
    	/****** Service API 초기화 ******/
    	PlatformRequest pReq = new PlatformRequest(request, Config.props.get("ENCODING"));
    	/** Web Server에서 XML수신 및 Parsing **/
    	pReq.receiveData();
    	/** 변수 획득 **/
    	vl = pReq.getVariableList();    	     
        
        ReportDAO    rDAO        = new ReportDAO();
        ReportEntity rMasterEntity     = null;

        StringBuilder sQuery = new StringBuilder();
        StringBuilder sWhere = new StringBuilder();

        String sworkMODE = vl.getValueAsString("workMODE");
        
      // 아래 if 블록은 호출이 아예 안되는거 같은데.. 확인해서 삭제처리할 것
        if (sworkMODE.equalsIgnoreCase("RECV_NUM"))
        {
        	String scRECV_NUM = vl.getValueAsString("scRECV_NUM");
        	String scSIDO_CODE = vl.getValueAsString("scSIDO_CODE");
        	
        	sQuery.append(" SELECT * FROM PT_R_REGIST_STATEMENT ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND RECV_NUM = '" + scRECV_NUM + "'");
	        sWhere.append(" AND SIDO_CODE = '" + scSIDO_CODE + "'");
		
	       	rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	       	KJFMi.ReEnt2Ds(dl,rMasterEntity,"output");
	       	sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	       	
	       	sQuery.append(" SELECT AA.SEQ, AA.OTOB_COM_NUM, AA.RECV_NUM, AA.OTOB_COM_CODE, BB.CODE_NAME AS  OTOB_COM_NAME ");
	       	sQuery.append(" FROM PT_R_SUBSIDIARY_COMPANY AA LEFT JOIN ");
	       	sQuery.append(" (SELECT CODE, CODE_NAME FROM PT_COM_CODE ");
	       	sQuery.append(" WHERE P_CODE = 'SUBSIDIARY') BB ON AA.OTOB_COM_CODE = BB.CODE ");
	       	sWhere.append(" WHERE AA.RECV_NUM = '" + scRECV_NUM + "'");
	       	sWhere.append(" AND AA.SIDO_CODE = '" + scSIDO_CODE + "'");
	       	
	       	rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	       	KJFMi.ReEnt2Ds(dl,rMasterEntity,"output2");
        }
        else if (sworkMODE.equalsIgnoreCase("REFER_CHECK"))
        {
        	//String scCOM_NUM = vl.getValueAsString("scCOM_NUM");
        	String scSSN1 = vl.getValueAsString("scSSN1");
        	String scSSN2 = vl.getValueAsString("scSSN2");
        	
        	sQuery.append(" SELECT BB.NM_KOR, BB.ENT_DT, BB.RET_DT, AA.TMP_WRT_NUM "); 
        	sQuery.append(" FROM PT_R_COMPANY_MASTER AA LEFT JOIN PT_R_REFFER BB ON AA.TMP_WRT_NUM = BB.TMP_WRT_NUM ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND ROWNUM = 1 ");
	        //sWhere.append(" AND RTRIM(AA.COM_NUM) != '" + scCOM_NUM + "'");
	        sWhere.append(" AND BB.SSN1 = '" + scSSN1 + "'");
	        sWhere.append(" AND BB.SSN2 = '" + scSSN2 + "'");
	        sWhere.append(" ORDER BY BB.ENT_DT DESC ");
		
	       	rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	       	sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output");
        }
        else if (sworkMODE.equalsIgnoreCase("GET_NEXT_SEQ_REFFER"))
        {
        	String scTMP_WRT_NUM = vl.getValueAsString("scTMP_WRT_NUM");
        	
        	//sQuery.append(" SELECT MAX(NVL(SEQ, 0)) + 1 AS SEQ "); 
        	sQuery.append(" SELECT NVL(MAX(to_number(SEQ, 0)), 0) + 1 AS SEQ ");
        	sQuery.append(" FROM PT_R_REFFER ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND TMP_WRT_NUM = '" + scTMP_WRT_NUM + "'");
		
	       	rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	       	sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	       	System.out.println( rMasterEntity.getRowCnt() ) ;
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output");

        }
        else if (sworkMODE.equalsIgnoreCase("ENGINEER_CHECK"))
        {
        	String scSSN = vl.getValueAsString("scSSN");
        	String scNM_KOR = vl.getValueAsString("scNM_KOR");

			// 2010.01.14. PT_R_ENGINEER_MASTER 사용안함.
        	sQuery.append(" SELECT  ");
			sQuery.append(" ENGINEER_NM, ENGINEER_SSN1, ENGINEER_SSN2,  ");
			sQuery.append(" EMPL_YMD, RET_YMD, REMARK, WRT_ID, UPD_DT, INS_DT,  ");
			sQuery.append(" SEQ, TMP_WRT_NUM, TMP_FIELD,  ");
			sQuery.append(" CARE_BOOK_VAL_START_DT, CARE_BOOK_VAL_END_DT, ISSUE_DT, CARE_BOOK_ISSUE_NUM, ");
			sQuery.append(" ENGINEER_NM AS NM_KOR, ENGINEER_SSN1 || ENGINEER_SSN2 AS ENGINEER_SSN, TMP_FIELD AS TMP_GRADE ");
			sQuery.append(" FROM PT_R_ENGINEER_CHANGE ");
        	sWhere.append(" WHERE 1 = 1 ");
        	if (!KJFUtil.isEmpty(scSSN))
        		sWhere.append(" AND ENGINEER_SSN1 || ENGINEER_SSN2 = '" + scSSN + "' ");
        	if (!KJFUtil.isEmpty(scNM_KOR))
        		sWhere.append(" AND	  ENGINEER_NM = '" + scNM_KOR + "' ");
		
	       	rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	       	sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output");
        }
        // 대표자가 타업체 재직기술자 등록되면 안됨
        else if (sworkMODE.equalsIgnoreCase("REFER_DUPLICATE_CHECK"))
        {
        	String scTMP_WRT_NUM = vl.getValueAsString("scTMP_WRT_NUM");
        	//String scSSN = vl.getValueAsString("scSSN");
        	Dataset dsPT_R_REFFER = pReq.getDatasetList().getDataset("dsPT_R_REFFER");
        	String scSSN_IN = "";
        	for(int i = 0; i < dsPT_R_REFFER.getRowCount(); i++ ) {
        		String posClass = dsPT_R_REFFER.getColumn( i, "POS_CLASS" ) + "";
        		if( !posClass.equals( "P1" ) && !posClass.equals( "P4" ) )
        			continue;
        		String scSSN = dsPT_R_REFFER.getColumn( i, "SSN1" ) + "" + dsPT_R_REFFER.getColumn( i, "SSN2" );
        		scSSN_IN += "'" + scSSN + "', ";
        	}
        	if( scSSN_IN.length() > 0 ) {
        		scSSN_IN = scSSN_IN.substring( 0, scSSN_IN.length() - 2 );
        	
	        	sQuery.append(" SELECT R.TMP_WRT_NUM, R.ENGINEER_NM NM_KOR, C.SIDO_CODE, C.NAME  "); 
	        	sQuery.append(" FROM PT_R_ENGINEER_CHANGE R ");
	        	sQuery.append(" LEFT OUTER JOIN PT_R_COMPANY_MASTER C ");
	        	sQuery.append(" ON R.TMP_WRT_NUM = C.TMP_WRT_NUM ");
		        sWhere.append(" WHERE 1 = 1 ");
		        sWhere.append(" AND R.RET_YMD IS NULL ");
		        sWhere.append(" AND (R.ENGINEER_SSN1 || R.ENGINEER_SSN2) IN (" + scSSN_IN + ")");
	        	sWhere.append(" and c.trans_union_proc is null and c.c_com_dt is null AND C.TMP_WRT_NUM <> '" + scTMP_WRT_NUM + "'");
			
		       	rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
		       	sQuery.delete(0, sQuery.length());
		       	sWhere.delete(0, sWhere.length());
		        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output");
	        } // if( scSSN_IN.length() > 0 )
        }
        // 재직기술자가 타업체 대표자로 등록되면 안됨
        else if (sworkMODE.equalsIgnoreCase("ENGINEER_DUPLICATE_CHECK"))
        {
        	String scTMP_WRT_NUM = vl.getValueAsString("scTMP_WRT_NUM");
        	//String scSSN = vl.getValueAsString("scSSN");
        	Dataset dsPT_R_ENGINEER_CHANGE = pReq.getDatasetList().getDataset("dsPT_R_ENGINEER_CHANGE");
        	String scSSN_IN = "";
        	for(int i = 0; i < dsPT_R_ENGINEER_CHANGE.getRowCount(); i++ ) {
        		String scSSN = dsPT_R_ENGINEER_CHANGE.getColumn( i, "ENGINEER_SSN1" ) + "" + dsPT_R_ENGINEER_CHANGE.getColumn( i, "ENGINEER_SSN2" );
        		scSSN_IN += "'" + scSSN + "', ";
        	}
        	if( scSSN_IN.length() > 0 ) {
        		scSSN_IN = scSSN_IN.substring( 0, scSSN_IN.length() - 2 );
        	
	        	sQuery.append(" SELECT R.TMP_WRT_NUM, R.NM_KOR, C.SIDO_CODE, C.NAME  "); 
	        	sQuery.append(" FROM PT_R_REFFER R ");
	        	sQuery.append(" LEFT OUTER JOIN PT_R_COMPANY_MASTER C ");
	        	sQuery.append(" ON R.TMP_WRT_NUM = C.TMP_WRT_NUM ");
		        sWhere.append(" WHERE 1 = 1 ");
		        sWhere.append(" AND R.RET_DT IS NULL AND POS_CLASS != 'P2' AND POS_CLASS != 'P3' ");
		        sWhere.append(" AND (R.SSN1 || R.SSN2) IN (" + scSSN_IN + ")");
		        sWhere.append(" and c.trans_union_proc is null and c.c_com_dt is null AND C.TMP_WRT_NUM <> '" + scTMP_WRT_NUM + "'");
			
		       	rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
		       	sQuery.delete(0, sQuery.length());
		       	sWhere.delete(0, sWhere.length());
		       	
		        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output");
	        } // if( scSSN_IN.length() > 0 )
        }
        else if (sworkMODE.equalsIgnoreCase("GET_NEXT_SEQ_ENGINEER"))
        {
        	String scTMP_WRT_NUM = vl.getValueAsString("scTMP_WRT_NUM");
        	sQuery.append(" SELECT MAX(NVL(SEQ, 0)) + 1 AS SEQ "); 
        	sQuery.append(" FROM PT_R_ENGINEER_CHANGE ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND TMP_WRT_NUM = '" + scTMP_WRT_NUM + "'");
		
	       	rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	       	sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	       	System.out.println( rMasterEntity.getRowCnt() ) ;
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output");

        }
        else if (sworkMODE.equalsIgnoreCase("GET_CORP"))
        {
        	//String scTMP_WRT_NUM = vl.getValueAsString("scTMP_WRT_NUM2");
        	String scCOI_WRT_NUM = vl.getValueAsString("scCOI_WRT_NUM");
        	  //System.out.println("KEY1 ============= " + scTMP_WRT_NUM);
        	  System.out.println("KEY2 ============= " + scCOI_WRT_NUM);
	       	//Company Master Table
	        //sQuery.append(" SELECT PT_C.*, PT_CD.CODE_NAME AS OFFICE_USE_NAME ");
       
        	sQuery.append(" SELECT PT_C.* ");
	        sQuery.append(" FROM PT_R_COMPANY_MASTER PT_C LEFT JOIN ");
	        sQuery.append(" (SELECT CODE, CODE_NAME FROM PT_COM_CODE ");
	        sQuery.append(" WHERE P_CODE = 'BLDDIV')PT_CD ON PT_C.OFFICE_USE_CODE = PT_CD.CODE ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND PT_C.COI_WRT_NUM = '" + scCOI_WRT_NUM + "'");
		
	       	rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	       	sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output");
	        
	        String sTMP_WRT_NUM = rMasterEntity.getValue(0, "TMP_WRT_NUM");
	        
	       	//Company Change History
	        //sQuery.append(" SELECT MAX(CHGBRE_SEQ) + 1 AS CHGBRE_SEQ, MAX(CHGBRE_SEQ) AS TMP_SEQ FROM PT_R_BASIC_CHANGE_HISTORY ");
	        sQuery.append(" SELECT NVL(MAX(to_number(CHGBRE_SEQ)), 0) + 1 CHGBRE_SEQ, MAX(CHGBRE_SEQ) AS TMP_SEQ FROM PT_R_BASIC_CHANGE_HISTORY ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND TMP_WRT_NUM = '" + sTMP_WRT_NUM + "'");
		
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output6");
	        sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        
	        //Change Seq
	        //String sSeq = rMasterEntity.getValue(0, "TMP_SEQ");
	        
	        //PT_R_ENGINEER_CHANGE
			// 2010.01.14. PT_R_ENGINEER_MASTER 사용안함.
	        sQuery.append(" SELECT  ");
			sQuery.append(" ENGINEER_NM, ENGINEER_SSN1, ENGINEER_SSN2,  ");
			sQuery.append(" EMPL_YMD, RET_YMD, REMARK, WRT_ID, UPD_DT, INS_DT,  ");
			sQuery.append(" SEQ, TMP_WRT_NUM, TMP_FIELD,  ");
			sQuery.append(" CARE_BOOK_VAL_START_DT, CARE_BOOK_VAL_END_DT, ISSUE_DT, CARE_BOOK_ISSUE_NUM, ");
			sQuery.append(" ENGINEER_NM AS NM_KOR, ENGINEER_SSN1 || ENGINEER_SSN2 AS ENGINEER_SSN, TMP_FIELD AS TMP_GRADE ");
			sQuery.append(" FROM PT_R_ENGINEER_CHANGE ");
			sWhere.append(" WHERE 1 = 1 ");
			sWhere.append(" AND TMP_WRT_NUM = '"+ sTMP_WRT_NUM +"' ");
			sWhere.append(" AND RET_YMD IS NULL ");
			sWhere.append(" ORDER BY EMPL_YMD ");
		
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	        sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output2");
	        
	        //PT_R_REFER
	        sQuery.append(" SELECT '0' AS CHECKER, CONCAT(AA.SSN1, AA.SSN2) AS TMPSSN, AA.*, ");
	        sQuery.append(" DECODE(AA.POS_CLASS, 'P1', '대표이사', 'P2', '감사', 'P3', '이사', '대표자') AS POS_NAME ");
       		sQuery.append(" FROM PT_R_REFFER AA ");
	        		
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND AA.TMP_WRT_NUM = '" + sTMP_WRT_NUM + "'");
		
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	        sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output3");
	        
	        //dsPT_R_SUBSIDIARY
	        sQuery.append(" SELECT '0' AS CHECKER, AA.*, BB.CODE_NAME AS SUB_NAME FROM PT_R_SUBSIDIARY AA ");
	        sQuery.append(" LEFT JOIN (SELECT CODE, CODE_NAME FROM PT_COM_CODE ");
	        sQuery.append(" WHERE P_CODE = 'SUBSIDIARY')BB ON AA.SUB_CODE = BB.CODE ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND AA.TMP_WRT_NUM = '" + sTMP_WRT_NUM + "'");
		
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	        sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output4");
	        
	        sQuery.append(" SELECT * FROM PT_R_DEFICIT_CODE ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND DEFI_GROUP = 'DRBC' ");
		
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	        sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output5");
	        
	        // 접수시 등록한 메모 가져오기
	        // output6은 서블릿에서 리턴되지 않지만 PID에서 바인딩하고 있어서 메모를 output7 으로 리턴.
	        String scSIDO_CODE = vl.getValueAsString("scSIDO_CODE");
        	String scRECV_NUM = vl.getValueAsString("scRECV_NUM");
        	
	       	sQuery.append("SELECT * " );
	       	sQuery.append("FROM PT_R_MEMO " );
	       	sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND RECV_NUM = '" + scRECV_NUM + "'");
	        sWhere.append(" AND SIDO_CODE = '" + scSIDO_CODE + "'");
        	
        	rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	       	KJFMi.ReEnt2Ds(dl,rMasterEntity,"output7");
	       	sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
        }
        else if (sworkMODE.equalsIgnoreCase("Eng_Test"))
        {
        	String scTMP_WRT_NUM = vl.getValueAsString("scTMP_WRT_NUM");
        	
        	// 2010.01.14. PT_R_ENGINEER_MASTER 사용안함.
			sQuery.append(" SELECT  ");
			sQuery.append(" ENGINEER_NM, ENGINEER_SSN1, ENGINEER_SSN2,  ");
			sQuery.append(" EMPL_YMD, RET_YMD, REMARK, WRT_ID, UPD_DT, INS_DT,  ");
			sQuery.append(" SEQ, TMP_WRT_NUM, TMP_FIELD,  ");
			sQuery.append(" CARE_BOOK_VAL_START_DT, CARE_BOOK_VAL_END_DT, ISSUE_DT, CARE_BOOK_ISSUE_NUM, ");
			sQuery.append(" ENGINEER_NM AS NM_KOR, ENGINEER_SSN1 || ENGINEER_SSN2 AS ENGINEER_SSN, TMP_FIELD AS TMP_GRADE ");
			sQuery.append(" FROM PT_R_ENGINEER_CHANGE ");
        	sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND TMP_WRT_NUM = '5492' ");
	        //sWhere.append(" AND AA.RET_YMD IS NULL ");
		
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	        sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());

	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output");
        }
        else
        {
	        String scTMP_WRT_NUM = vl.getValueAsString("scTMP_WRT_NUM");
	        String scREC_NUM = vl.getValueAsString("scRECV_NUM");
	        String sSeq = vl.getValueAsString("scCHGBRE_SEQ");
	        System.out.println("KEY ============= " + sSeq);

	       	//Company Master Table
	        //sQuery.append(" SELECT PT_C.*, PT_CD.CODE_NAME AS OFFICE_USE_NAME ");
	     	/*sQuery.append(" SELECT pt_c.tmp_wrt_num,pt_c.sido_code,pt_c.commana_class,pt_c.mana_num,pt_c.name,pt_c.rep_ssn2,pt_c.rep_nm_kor,decode(pt_c.gubun,null,1) GUBUN   ");
	        sQuery.append(" FROM PT_R_COMPANY_MASTER PT_C LEFT JOIN ");
	        sQuery.append(" (SELECT CODE, CODE_NAME FROM PT_COM_CODE ");
	        sQuery.append(" WHERE P_CODE = 'BLDDIV')PT_CD ON PT_C.OFFICE_USE_CODE = PT_CD.CODE ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND PT_C.TMP_WRT_NUM = '" + scTMP_WRT_NUM + "'");
        	*/
        	
	     
	        sQuery.append(" SELECT PT_C.* ");
	        sQuery.append(" FROM PT_R_COMPANY_MASTER PT_C LEFT JOIN ");
	        sQuery.append(" (SELECT CODE, CODE_NAME FROM PT_COM_CODE ");
	        sQuery.append(" WHERE P_CODE = 'BLDDIV')PT_CD ON PT_C.OFFICE_USE_CODE = PT_CD.CODE ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND PT_C.TMP_WRT_NUM = '" + scTMP_WRT_NUM + "'");
		   
	       	rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	       	sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output");
	        
	       	//PT_R_BASIC_STATEMENT Table
	       /*
	        sQuery.append(" SELECT RECV_NUM,SIDO_CODE,RECV_DT,RECV_TIME,PROC_LIM,OFFI_PART,OFFI_PART_REBO_WRT_NUM,COI_WRT_NUM,DEFI_STE,WRT_ID,UPD_DT,");
	        sQuery.append(" INS_DT,NAME,REP_NM_KOR,ADDR_ADDR,WRT_DT,PROC_TIME,ADDR_DETAIL_ADDR,ADDR_POST_NUM,TMP_WRT_NUM,CHGBRE_SEQ,");
	        sQuery.append(" MEMO_CONT,decode(COMMANA_CLASS,null,1,1,2,2,3,3) COMMANA_CLASS,MANA_NUM,REP_SSN1,REP_SSN2,REP_NM_HAN,REP_TEL_NUM,ADDR_TEL_NUM,ADDR_FAX_NUM,EMAIL_ADDR,OFFICE_AREA,");
	        sQuery.append(" OFFICE_USE_CODE,COMPANY_DIAG_CLASS_CODE,COMPANY_DIAG_BAS_DT,COMPANY_DIAG_ISSUE_DT,DIAG_NM_NM,DIAG_NM_WRT_NUM,");
	        sQuery.append(" DIAG_ORG_CODE,PAY_CAP,REA_CAP,TUP_CLASS_CODE,TUP_AOM,C_COM_DT,C_COM_CAUSE_CODE,");
	        sQuery.append(" COM_COV_DT,BAS_STA_DT,OFFICE_OWN_CLASS,STA_CHG_CLASS_CODE,CHG_DT,MOT_STE,REP_MOBILE,HOSCRE,");
	        sQuery.append(" LICTAX,SND_DT,SND_STE,COM_NUM,COMPANY_DIAG_BAS_DT2,decode(FOREIGN_YN,null,'N','N','N','Y','Y') FOREIGN_YN,EXT_CHG_CLASS_CODE,DOC_CODE,decode(GUBUN,null,1,1,1,2,2) GUBUN,OFFICE_USE_NAME, ");
	        sQuery.append(" BUSS_YMD,REGIST_YMD,UNION_INVEST_NUM,MIDAL_YN "); 
	        sQuery.append(" FROM PT_R_BASIC_STATEMENT ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND TMP_WRT_NUM = '" + scTMP_WRT_NUM + "'");
	        sWhere.append(" AND RECV_NUM = '" + scREC_NUM + "'");
	        */
	        
	        sQuery.append(" SELECT * ");
	        sQuery.append(" FROM PT_R_BASIC_STATEMENT ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND TMP_WRT_NUM = '" + scTMP_WRT_NUM + "'");
	        sWhere.append(" AND RECV_NUM = '" + scREC_NUM + "'");
		    
	       	rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	       	sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output1");
	        
	       	//Company Change History
	        //sQuery.append(" SELECT * FROM PT_R_BASIC_CHANGE_HISTORY ");
	        //sWhere.append(" WHERE 1 = 1 ");
	        //sWhere.append(" AND TMP_WRT_NUM = '" + scTMP_WRT_NUM + "'");
	        //sWhere.append(" AND RECV_NUM = '" + scREC_NUM + "'");
	        
	        sQuery.append(" SELECT PT_C.*, PT_CD.CODE_NAME AS OFFICE_USE_NAME ");
	        sQuery.append(" FROM PT_R_BASIC_CHANGE_HISTORY PT_C LEFT JOIN ");
	        sQuery.append(" (SELECT CODE, CODE_NAME FROM PT_COM_CODE ");
	        sQuery.append(" WHERE P_CODE = 'BLDDIV')PT_CD ON PT_C.OFFICE_USE_CODE = PT_CD.CODE ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND PT_C.TMP_WRT_NUM = '" + scTMP_WRT_NUM + "'");
	        sWhere.append(" AND PT_C.RECV_NUM = '" + scREC_NUM + "'");
		
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	        sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output2");
	        
	        //Change Seq
	        //String sSeq = rMasterEntity.getValue(0, "scCHGBRE_SEQ");
	        
	        // 테스트용으로 시도가 tete 예외 처리를 위해 wbjeon 2010-08-25
	        String rscSIDO_CODE = rMasterEntity.getValue(0, "SIDO_CODE");
	        
	      //PT_R_REFER
	        sQuery.append(" SELECT '0' AS CHECKER, CONCAT(AA.SSN1, AA.SSN2) AS TMPSSN, AA.*, ");
	        sQuery.append(" DECODE(AA.POS_CLASS, 'P1', '대표이사', 'P2', '감사', 'P3', '이사', '대표자') AS POS_NAME ");
       		sQuery.append(" FROM PT_R_REFFER AA ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND AA.TMP_WRT_NUM = '" + scTMP_WRT_NUM + "'");
		
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	        sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output8");
	        
	        
	        
	        
	        
	        
	        //PT_R_REG_MEMO
	        sQuery.append(" SELECT '0' AS CHECKER, AA.* FROM PT_R_REG_MEMO AA ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND AA.TMP_WRT_NUM = '" + scTMP_WRT_NUM + "'");
	        sWhere.append(" AND AA.CHGBRE_SEQ = '" + sSeq + "'");
		
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	        sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output9");
	    
	        //PT_R_REFUSAL_REASON
	        sQuery.append(" SELECT '0' AS CHECKER, AA.*, BB.CODE_NAME AS REFUSAL_DIV_NAME ");
	        sQuery.append(" FROM PT_R_REFUSAL_REASON AA LEFT JOIN (SELECT CODE, CODE_NAME FROM PT_COM_CODE  ");
	        sQuery.append(" WHERE P_CODE = 'REGCANCEL') BB ON AA.REFS_ITEM = BB.CODE ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND AA.TMP_WRT_NUM = '" + scTMP_WRT_NUM + "'");
	        sWhere.append(" AND AA.CHGBRE_SEQ = '" + sSeq + "'");
		
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	        sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output10");
	        
	        //dsPT_R_SUBSIDIARY
	        sQuery.append(" SELECT '0' AS CHECKER, AA.*, BB.CODE_NAME AS SUB_NAME FROM PT_R_SUBSIDIARY AA ");
	        sQuery.append(" LEFT JOIN (SELECT CODE, CODE_NAME FROM PT_COM_CODE ");
	        sQuery.append(" WHERE P_CODE = 'SUBSIDIARY')BB ON AA.SUB_CODE = BB.CODE ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND AA.TMP_WRT_NUM = '" + scTMP_WRT_NUM + "'");
		
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	        sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output11");
	        
	        //dsPT_R_COM_DEFICIT
	        sQuery.append(" SELECT AA.*, BB.CONT AS DEFI_CONT FROM PT_R_COM_DEFICIT AA LEFT JOIN ");
	        sQuery.append(" (SELECT DEFI_CODE, CONT FROM PT_R_DEFICIT_CODE ");
	        sQuery.append(" WHERE DEFI_GROUP = 'CRRS') BB ON AA.DEFI_CODE = BB.DEFI_CODE ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND AA.TMP_WRT_NUM = '" + scTMP_WRT_NUM + "'");
	        sWhere.append(" AND AA.CHGBRE_SEQ = '" + sSeq + "'");
		
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	        sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output12");
	        
	      //dsPT_R_COM_DEFICIT
	        sQuery.append(" SELECT * FROM PT_R_MOT_STE ");
	        sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND TMP_WRT_NUM = '" + scTMP_WRT_NUM + "'");
	        sWhere.append(" AND CHGBRE_SEQ = '" + sSeq + "'");
		
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	        sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output13");
	        
	        
	        //PT_R_ENGINEER_CHANGE
			sQuery.append(" SELECT '0' AS CHECKER,");
			sQuery.append(" ENGINEER_NM, ENGINEER_SSN1, ENGINEER_SSN2,  ");
			sQuery.append(" EMPL_YMD, RET_YMD, REMARK, WRT_ID, UPD_DT, INS_DT,  ");
			sQuery.append(" SEQ, TMP_WRT_NUM, TMP_FIELD,  ");
			sQuery.append(" CARE_BOOK_VAL_START_DT, CARE_BOOK_VAL_END_DT, ISSUE_DT, CARE_BOOK_ISSUE_NUM, ");
			sQuery.append(" ENGINEER_NM AS NM_KOR, ENGINEER_SSN1 || ENGINEER_SSN2 AS ENGINEER_SSN, TMP_FIELD AS TMP_GRADE ");
			sQuery.append(" FROM PT_R_ENGINEER_CHANGE ");
        	sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND TMP_WRT_NUM = '" + scTMP_WRT_NUM + "' ");
	        sWhere.append(" AND RET_YMD IS NULL ");
	        sWhere.append(" ORDER BY EMPL_YMD, ENGINEER_SSN1 ASC ");
		
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());
	        sQuery.delete(0, sQuery.length());
	       	sWhere.delete(0, sWhere.length());
	        KJFMi.ReEnt2Ds(dl,rMasterEntity,"output14");
	        
	        
	        //PT_R_ENGINEER_CHANGE
			// 2010.01.14. PT_R_ENGINEER_MASTER 사용안함.
	  		sQuery.append(" SELECT '0' AS CHECKER,");
			sQuery.append(" ENGINEER_NM, ENGINEER_SSN1, ENGINEER_SSN2,  ");
			sQuery.append(" EMPL_YMD, RET_YMD, REMARK, WRT_ID, UPD_DT, INS_DT,  ");
			sQuery.append(" SEQ, TMP_WRT_NUM, TMP_FIELD,  ");
			sQuery.append(" CARE_BOOK_VAL_START_DT, CARE_BOOK_VAL_END_DT, ISSUE_DT, CARE_BOOK_ISSUE_NUM, ");
			sQuery.append(" ENGINEER_NM AS NM_KOR, ENGINEER_SSN1 || ENGINEER_SSN2 AS ENGINEER_SSN, TMP_FIELD AS TMP_GRADE ");
			sQuery.append(" FROM PT_R_ENGINEER_CHANGE ");
        	sWhere.append(" WHERE 1 = 1 ");
	        sWhere.append(" AND TMP_WRT_NUM = '" + scTMP_WRT_NUM + "' ");
	        sWhere.append(" AND RET_YMD IS NULL ");
	        sWhere.append(" ORDER BY EMPL_YMD ");
	        
	        rMasterEntity    = rDAO.select(sQuery.toString() + sWhere.toString());

	        /********************************************************/
	        /*	      TEST후 원복해야 함      wbjeon 2010-08-25  		    */
	        /********************************************************/	   
	        	        
//	        	        // 테스트용
//	        	        //KJFMi.ReEnt2Ds(dl,rMasterEntity,"output7");
//	        	        
//	        	        ///*
//	        	        Dataset ds = new Dataset("output7");
//	        	        ds.addColumn("CHECKER",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("CARE_BOOK_ISSUE_NUM",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("ENGINEER_SSN",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("ENGINEER_SSN1",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("ENGINEER_SSN2",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("ENGINEER_NM",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("CARE_BOOK_VAL_START_DT",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("CARE_BOOK_VAL_END_DT",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("ISSUE_DT",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("TMP_FIELD",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("ENGINEER_GRADE",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("NAME",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("EMPL_YMD",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("RET_YMD",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("TMP_WRT_NUM",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("SEQ",ColumnInfo.CY_COLINFO_STRING, 20);
//	        					ds.addColumn("REMARK",ColumnInfo.CY_COLINFO_STRING, 100);
//	        					
//	        	        if (rMasterEntity.getRowCnt() != 0 && rMasterEntity != null){
//	        	        
//	        		        String[] sSSN = new String[rMasterEntity.getRowCnt()];
//	        		        for(int nLoop = 0; nLoop < rMasterEntity.getRowCnt(); nLoop++)
//	        		        {
//	        		        	sSSN[nLoop] = rMasterEntity.getValue(nLoop, "ENGINEER_SSN");
//	        		        }
//	        	        
//	        						iccsSelectBySSN(dl, ds, sSSN, rMasterEntity);
//	        	        }
//	        	        
//	        	        dl.addDataset(ds);
//	        	        //*/

	        //if ("tete".equals(rscSIDO_CODE)){
		           KJFMi.ReEnt2Ds(dl,rMasterEntity,"output7");
		           
		           
				/*     
		        } else {
					        Dataset ds = new Dataset("output7");
					        ds.addColumn("CHECKER",ColumnInfo.CY_COLINFO_STRING, 20);
					        ds.addColumn("CARE_BOOK_ISSUE_NUM",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("ENGINEER_SSN",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("ENGINEER_SSN1",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("ENGINEER_SSN2",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("ENGINEER_NM",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("CARE_BOOK_VAL_START_DT",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("CARE_BOOK_VAL_END_DT",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("ISSUE_DT",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("TMP_FIELD",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("ENGINEER_GRADE",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("NAME",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("EMPL_YMD",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("RET_YMD",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("TMP_WRT_NUM",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("SEQ",ColumnInfo.CY_COLINFO_STRING, 20);
							ds.addColumn("REMARK",ColumnInfo.CY_COLINFO_STRING, 100);
							
					        if (rMasterEntity.getRowCnt() != 0 && rMasterEntity != null){
						        
						        String[] sSSN = new String[rMasterEntity.getRowCnt()];
						        for(int nLoop = 0; nLoop < rMasterEntity.getRowCnt(); nLoop++)
						        {
						        	sSSN[nLoop] = rMasterEntity.getValue(nLoop, "ENGINEER_SSN");
						        }
										iccsSelectBySSN(dl, ds, sSSN, rMasterEntity);
					        }
							
							
					        dl.addDataset(ds);
				        }	
				 */   
		           
        }


        request.setAttribute("dl", dl);
        
        return next;
    }
    
	private void iccsSelectBySSN( DatasetList dl, Dataset ds, String[] sSSN, ReportEntity ent ) throws Exception {
		ReportDAO rDAO = new ReportDAO();
		ReportEntity rEntity = null;

		StringBuilder sQuery = new StringBuilder();
		StringBuilder sWhere = new StringBuilder();

		KengWorker kWorker = new KengWorker();
		Kengineer kengs[] = kWorker.getKengs( sSSN );
	
		if( kengs == null )
			return;

		for( int i = 0; i < kengs.length; i++ ) {
			Kengineer keng = kengs[i];
			String NM_KOR = keng.getNM_KOR();
			String SSN = keng.getSSN();
			String GRADE = keng.getGRADE();
			String TMP_GRADE = keng.getTMP_GRADE();
			String BOOK_NUM = keng.getBOOK_NUM();
			String EDU_DT = keng.getEDU_DT();
			String S_DT = keng.getS_DT();
			String E_DT = keng.getE_DT();
			String CORP = keng.getCORP();
			System.out.println("*****"+i);
			// 협회 쿼리 후, 인덱스가 동일하게 온다는 보장이 없다.
			int myIdx = -1;
			for( myIdx = 0; myIdx < ent.getRowCnt(); myIdx++ ) {
				if( ent.getValue(myIdx, "ENGINEER_SSN").equals( SSN ) )
					break;
			}
		
		
      if( myIdx < 0 )
      	continue;
      String EMPL_YMD = ent.getValue(myIdx, "EMPL_YMD");
      String RET_YMD = ent.getValue(myIdx, "RET_YMD");
      String TMP_WRT_NUM = ent.getValue(i, "TMP_WRT_NUM");
      String SEQ = ent.getValue(myIdx, "SEQ");
      String REMARK = ent.getValue( myIdx, "REMARK" );
      
			if( S_DT != null && S_DT.length() > 10 )
				S_DT = S_DT.substring( 0, 10 ).replaceAll( "-", "" );

			if( E_DT != null && E_DT.length() > 10 )
				E_DT = E_DT.substring( 0, 10 ).replaceAll( "-", "" );

			sQuery.delete( 0, sQuery.length() );
			sWhere.delete( 0, sWhere.length() );

			int row = ds.appendRow();
			ds.setColumn( row, "CARE_BOOK_ISSUE_NUM", BOOK_NUM );
			ds.setColumn( row, "CHECKER", "0" );
			ds.setColumn( row, "ENGINEER_SSN", SSN );
			ds.setColumn( row, "ENGINEER_SSN1", SSN.substring( 0, 6 ) );
			ds.setColumn( row, "ENGINEER_SSN2", SSN.substring( 6, 13 ) );
			ds.setColumn( row, "ENGINEER_NM", NM_KOR.trim() );
			ds.setColumn( row, "CARE_BOOK_VAL_START_DT", S_DT );
			ds.setColumn( row, "CARE_BOOK_VAL_END_DT", E_DT );
			ds.setColumn( row, "ISSUE_DT", "" );
			ds.setColumn( row, "TMP_FIELD", TMP_GRADE );
			ds.setColumn( row, "ENGINEER_GRADE", GRADE );
			ds.setColumn( row, "NAME", "" );
			ds.setColumn( row, "EMPL_YMD", EMPL_YMD );
			ds.setColumn( row, "RET_YMD", RET_YMD );
			ds.setColumn( row, "TMP_WRT_NUM", TMP_WRT_NUM );
			ds.setColumn( row, "SEQ", SEQ );
			ds.setColumn( row, "REMARK", REMARK );
		} // for
	}
}