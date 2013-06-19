<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="sp.reg.RegParam" %>
<%@ page import="kjf.util.*" %>
<%
	ReportEntity masterEntity = (ReportEntity)request.getAttribute("masterEntity");  //공사업자 기본사항
	
	String coi_wrt_num = masterEntity.getValue(0,"COI_WRT_NUM"); //등록번호(사업자번호)
	String name = masterEntity.getValue(0,"NAME"); //상호
	String ins_dt = masterEntity.getValue(0,"INS_DT"); //등록일자
	ins_dt = KJFDate.getChangDatePattern(ins_dt,"yyyyMMdd", "yyyy-MM-dd"); //등록일자 포멧 수정
	String upd_dt = masterEntity.getValue(0,"UPD_DT"); //수정일자
	String rep_nm_kor = masterEntity.getValue(0,"REP_NM_KOR"); //대표자
	String rep_ssn1 = masterEntity.getValue(0,"REP_SSN1"); //주민번호1
	String rep_ssn2 = masterEntity.getValue(0,"REP_SSN2"); //주민번호2
	String office_area = masterEntity.getValue(0,"OFFICE_AREA"); //사무실 면적
	String mana_num = KJFUtil.print("("+masterEntity.getValue(0,"MANA_NUM")+")",""); //사업자 번호
	String cum_num = KJFUtil.print("("+masterEntity.getValue(0,"COM_NUM")+")",""); //법인 번호
	
	
	String addr_post_num = KJFUtil.print("("+masterEntity.getValue(0,"ADDR_POST_NUM")+")",""); //우편번호
	String addr_addr = masterEntity.getValue(0,"ADDR_ADDR"); //주소
	String addr_detail_addr = masterEntity.getValue(0,"ADDR_DETAIL_ADDR"); //상세주소
	
	String addr_tel_num = masterEntity.getValue(0,"ADDR_TEL_NUM"); //전화번호
	String addr_fax_num = masterEntity.getValue(0,"ADDR_FAX_NUM"); //팩스번호
	String pay_cap = masterEntity.getValue(0,"PAY_CAP"); //납입자본금
	String rea_cap = masterEntity.getValue(0,"REA_CAP"); //실질자본금

	ReportEntity resultEntity = (ReportEntity)request.getAttribute("resultEntity");  //공사실적
	int resultCnt = resultEntity.getRowCnt();
	
	String year1="";
	String year2="";
	String year3="";
	String work_rom1="";
	String work_rom2="";
	String work_rom3="";
	
	if(resultCnt > 0 ) {
		year1 = resultEntity.getValue(resultCnt-1,"YEAR"); //공사실적  년도 1
		year2 = resultEntity.getValue(resultCnt-2,"YEAR"); //공사실적  년도 2
		year3 = resultEntity.getValue(resultCnt-3,"YEAR"); //공사실적  년도 3
		work_rom1 = resultEntity.getValue(resultCnt-1,"WORK_ROM"); //공사실적  금액1
		work_rom2 = resultEntity.getValue(resultCnt-2,"WORK_ROM"); //공사실적  금액2
		work_rom3 = resultEntity.getValue(resultCnt-3,"WORK_ROM"); //공사실적  금액3
	}
	
	ReportEntity appraisalEntity = (ReportEntity)request.getAttribute("appraisalEntity");  //시공능력평가액
	String appraisal = appraisalEntity.getValue(0,"ASSE_AOM"); //시공능력평가액
	
	ReportEntity disposeEntity = (ReportEntity)request.getAttribute("disposeEntity");  //행정처분사항
	ReportEntity changeEntity = (ReportEntity)request.getAttribute("changeEntity");  //등록기준신고
	ReportEntity sidelineEntity = (ReportEntity)request.getAttribute("sidelineEntity");  //겸업사항
	ReportEntity technicianEntity = (ReportEntity)request.getAttribute("technicianEntity");  //보유기술자
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">

<link href="../css/web_layout.css" rel="stylesheet" type="text/css" />
<link href="../css/web_style.css" rel="stylesheet" type="text/css" />
<link href="../css/web_form.css" rel="stylesheet" type="text/css" />


<title>정보통신 행정시스템 - 공사업체 상세조회</title>
<meta http-equiv="imagetoolbar" content="no" />
<meta name="author" content="정보통신 행정시스템" />
<head>
<script language="javascript">
function fn_print(){
	window.print();
}
</script>

</head>

<body>
<form name="searchfrm" action="../reg/RegAction.do?cmd=RegMgrViewClose" method="post">

			<!-- 중간컨텐츠 : S -->
			<a name="contents"></a>
	   			<div id="contents">
	   			
	   				<table width="100%" border="0" height="70">
	   					<tr>
	   						<td align="right" width="80%">&nbsp;</td>
	   						<!-- <td align="right"><a href="javascript:window.close();"><img src="../images/btn_close.gif"></a></td>  -->
	   						<td align="right"><a href="javascript:fn_print();"><img src="../images/bt_print.gif"></a></td>
	   					</tr>
	   				</table>
	   				
	   				<h4><font size="4">공사업자 세부정보 (폐업업자 정보)</font></h4>
	   				<br/>
	   				
					<h4>기본사항</h4>
					
					<table  class="t5">
						<tr>
							<th>등록번호<br/>(사업자번호)</th>
							<td class="letter0"><%=coi_wrt_num %><br/><%=mana_num %></td>
							<th>상호<br/>(법인번호)</th>
							<td class="letter0"><%=name %></td>
							<th>등록일자</th>
							<td class="letter0"><%=ins_dt %></td>
						</tr>
						<tr>
							<th>대표자</th>
							<td class="letter0"><%=rep_nm_kor %></td>
							<th>주민번호</th>
							<td class="letter0"><%=rep_ssn1 %>-<%=rep_ssn2 %></td>
							<th>사무실면적</th> 
							<td class="letter0"><%=office_area %> ㎡</td>
						</tr>
						<tr>
							<th rowspan="2">영업소재지</th>
							<td rowspan="2" colspan="3"><%=addr_post_num %>&nbsp;<%=addr_addr %>&nbsp;<%=addr_detail_addr %></td>
							<th>전화번호</th>
							<td><%=addr_tel_num %></td>
						</tr>
						<tr>
							<th>팩스번호</th>
							<td><%=addr_fax_num %></td> 
						</tr>
							
						<tr>
							<th rowspan="3">공사실적(천원)</th>
							<td><%=year3 %>년</td>
							<td><%=KJFUtil.money(KJFUtil.str2long(work_rom3)) %></td>
							<th>시공능력평가액(천원)</th> 
							<td colspan="2"><%=KJFUtil.money(KJFUtil.str2long(appraisal)) %></td>
						</tr>
						<tr>
							<td><%=year2 %>년</td>
							<td><%=KJFUtil.money(KJFUtil.str2long(work_rom2)) %></td>
							<th>납입자본금(천원)</th> 
							<td colspan="2"><%=KJFUtil.money(KJFUtil.str2long(pay_cap)) %></td>
						</tr>
						<tr>
							<td><%=year1 %>년</td>
							<td><%=KJFUtil.money(KJFUtil.str2long(work_rom1)) %></td>
							<th>실질자본금(천원)</th> 
							<td colspan="2"><%=KJFUtil.money(KJFUtil.str2long(rea_cap)) %></td> 
						</tr>	
					</table>
					
					
					
					<h4>상호 및 행정처분 사항</h4>
					<table class="t5">
						<tr>
							<th>년월일</th>
							<th>내용</th>
							<th>사유</th>
							<th>시행기관</th>
						</tr>
					<%
						if(disposeEntity.getRowCnt() > 0){ 
							for(int i=0; i<disposeEntity.getRowCnt(); i++) {
					%>	
						<tr>
							<td class="letter0" align="center"><%=KJFDate.getDateForm(disposeEntity.getValue(i, "WRT_NUM")) %></td>
							<td class="letter0" align="center"><%=disposeEntity.getValue(i, "DISPO_CONT_NM") %></td>
							<td class="letter0" align="center"><%=disposeEntity.getValue(i, "DISPO_CAUSE") %></td>
							<td class="letter0" align="center"><%=disposeEntity.getValue(i, "PUNISHMENT_AGENCY") %></td>
						</tr>
					<%
							}
						} else {
					%>
						<tr>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
						</tr>
					<%	
						}
					%>		
					</table>
					
					<h4>등록기준신고</h4>
					<table class="t5">
						<tr>
							<th>등록기준신고일</td>
							<th>내용</td>
							<th>신고예정일(신고일기준)</td>
						</tr>
					<%
						if(changeEntity.getRowCnt() > 0){ 
							for(int i=0; i<changeEntity.getRowCnt(); i++) {
					%>			
						<tr>
							<td class="letter0" align="center"><%=KJFDate.getDateForm(changeEntity.getValue(i, "BAS_STA_DT")) %></td>  
							<td class="letter0" align="center"><%=changeEntity.getValue(i, "DOC_CODE") %>&nbsp;&nbsp;(<%=changeEntity.getValue(i, "COI_WRT_NUM") %>-<%=changeEntity.getValue(i, "NAME") %>)</td>
							<td class="letter0" align="center"><%=changeEntity.getValue(i, "DUE_DATE") %></td>
						</tr>	
					<%
							}
						} else {
					%>
						<tr>
							<td class="letter0" align="center">&nbsp;</td>
							<td class="letter0" align="center">&nbsp;</td>
							<td class="letter0" align="center">&nbsp;</td>
						</tr>
					<%	
						}
					%>			
					</table>
					
					<h4>겸업사항</h4>
					<table class="t5">
						<tr>
							<th>등록년월일</th>
							<th>업종</th>
							<th>업종등록번호</th>
						</tr>
					<%
						if(sidelineEntity.getRowCnt() > 0){ 
							for(int i=0; i<sidelineEntity.getRowCnt(); i++) {
					%>			
						<tr>
							<td class="letter0" align="center"><%=sidelineEntity.getValue(i, "INS_DT") %></td>
							<td class="letter0" align="center"><%=sidelineEntity.getValue(i, "SUB_NM") %></td>
							<td class="letter0" align="center"><%=sidelineEntity.getValue(i, "SUB_WRT_NUM") %></td>
						</tr>
					<%
							}
						} else {
					%>
						<tr>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
						</tr>
					<%	
						}
					%>			
					</table>
					
					<h4>보유기술자</h4>
					<table class="t5">
						<tr>
							<th>성명</th> 
							<th>주민등록번호</th>
							<th>기술자등급</th>
							<th>입사일자</th>
						</tr>
					<%
						if(technicianEntity.getRowCnt() > 0){ 
							for(int i=0; i<technicianEntity.getRowCnt(); i++) {
					%>	
						<tr>
							<td class="letter0" align="center"><%=technicianEntity.getValue(i, "ENGINEER_NM") %></td>
							<td class="letter0" align="center"><%=technicianEntity.getValue(i, "ENGINEER_SSN1") %>-<%=technicianEntity.getValue(i, "ENGINEER_SSN2") %></td>
							<td class="letter0" align="center"><%=technicianEntity.getValue(i, "TMP_FIELD") %></td>
							<td class="letter0" align="center"><%=KJFDate.getDateForm(technicianEntity.getValue(i, "EMPL_YMD")) %></td>
						</tr>	
					<%
							}
						} else {
					%>
						<tr>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
							<td class="letter0">&nbsp;</td>
						</tr>
					<%	
						}
					%>		
					</table>
					
				</div>
</form>

</body>
</html>
