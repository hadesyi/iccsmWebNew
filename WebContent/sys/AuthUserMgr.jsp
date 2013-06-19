<%--
*************************************************************************************************
* 파  일  명 : AuthUserMgr.jsp
* 설      명 : 권한그룹별 프로그램 관리화면
* 
* 이력사항 :
* 2006/09/14  양석환  최초작성
* 2006/11/03  김경덕 수정
*************************************************************************************************
--%>

<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="kjf.util.*" %>
<%@ page import="kjf.ops.*" %>
<%@ page import="sp.uent.*" %>
<%@ page import="sp.sys.*" %>
<%@ taglib uri="/KJFTags" prefix="KTags" %>

<%
	//초기 변수 들 선언
	SysParam pm=(SysParam)request.getAttribute("pm");
	ReportEntity rEntity =(ReportEntity)request.getAttribute("rEntity");

	Vector v_menuEntList= (Vector)request.getAttribute("v_menuEntList");	
	

	//필수 변수 (검색창 상태)
	String isSearch   = KJFUtil.print(pm.getIsSearch());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>프로그램 관리</TITLE>
<link href="../com/css/style.css" rel="stylesheet"   type="text/css">
<link href="../com/css/skin/blue.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript" src="../com/js/kjs.js"></script>
<script language="javascript" type="text/javascript" src="../com/js/tree.js"></script>
<script>

<%


 	boolean b_flag = false;
 	boolean isTree = false;


	if (v_menuEntList != null  ){
		int cnt = v_menuEntList.size();

		out.println("foldersTree = gFld('프로그램', '');");
		for (int i = cnt-2 ; i+1 >  0  ; i--) {
			MenuEnt menuEnt = (MenuEnt)v_menuEntList.get(i);

			isTree = true;

			rEntity = (ReportEntity)menuEnt.getPT_PGEnt();
			if("0".equals(menuEnt.getP_PG_GROUP_ID())) {
				if(!KJFUtil.isEmpty(menuEnt.getGR_AND_PG_LOW_IDS())){
				out.println("sub_" + menuEnt.getPG_GROUP_ID() + "=insFld(foldersTree, gFld('" + menuEnt.getGROUP_NAME() + "','javascript:f_gr_ins(\"" + menuEnt.getGR_AND_PG_LOW_IDS() + "\")'));");
				}
			}else{
				if(!KJFUtil.isEmpty(menuEnt.getGR_AND_PG_LOW_IDS())){
					out.println("sub_" + menuEnt.getPG_GROUP_ID() + "=insFld(sub_" + menuEnt.getP_PG_GROUP_ID() + ", gFld('" + menuEnt.getGROUP_NAME() + "','javascript:f_gr_ins(\"" + menuEnt.getGR_AND_PG_LOW_IDS() + "\")'));");
				}
			}
			if(rEntity!=null){
				for(int j=0; j < rEntity.getRowCnt(); j++){
						out.println("insDoc(sub_" + menuEnt.getPG_GROUP_ID() + ", gLnk(1, '" + rEntity.getValue(j,"PG_NAME") + "', 'javascript:f_ins(\"" + rEntity.getValue(j,"PG_ID") + "\",\"" + rEntity.getValue(j,"PG_GROUP_ID") + "\")'));");
				}
			}
		}
	}

%>

function f_ins(PG_ID, PG_GROUP_ID){
	var fm = document.fmParam;
	
    fm.scUSER_ID.value = document.fmSearch.scUSER_ID.value;
    fm.USER_ID.value = document.fmSearch.scUSER_ID.value;
    fm.PG_ID.value = PG_ID;
    fm.PG_GROUP_ID.value = PG_GROUP_ID;

	fm.cmd.value = "AuthUserMgrCUD";
    fm.mode.value= "C";

	fm.submit();
}

function f_gr_ins(GR_ADN_PG_IDS){

	if(!confirm("선택한 메뉴 하위 프로그램들을 모두 옮기겠습니까?")){
		return;
	}
	var fm = document.fmParam;
	
    fm.scUSER_ID.value = document.fmSearch.scUSER_ID.value;
    fm.USER_ID.value = document.fmSearch.scUSER_ID.value;
    fm.GR_ADN_PG_IDS.value = GR_ADN_PG_IDS;
    fm.cmd.value = "AuthUserMgrCUD";
    fm.mode.value= "GC";
    
    fm.submit();
  
}

function fn_new() {
}

// 내용 저장
function fn_save(){
	var fm = ListFrame.document.fmParam;

	if(confirm("설정 내용을 저장하시겠습니까?")){
		fm.USER_ID.value = document.fmSearch.scUSER_ID.value;
		fm.scUSER_ID.value = document.fmSearch.scUSER_ID.value;
		fm.cmd.value = "AuthUserMgrCUD";
		fm.mode.value = "U";
		fm.submit();
	}
}

// 선택 삭제
function fn_delete() {
}

// 검색 창 처리
function fn_search(){
}

// 츨력 처리
function fn_print(){
}

// 엑셀 츨력 처리
function fn_excel(){
}

</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0"  marginheight="0">

<form name="fmParam" method="post" action="SysAction.do" target="ListFrame">
	<!-- CUD 모드-->
	<input type="hidden" name="cmd"  value="">
	<input type="hidden" name="mode" value="">
	<input type="hidden" name="menu" value="">
	<!-- 검색창 상태 처리(필수)-->   
	<input type="hidden" name="isSearch" value="<%=isSearch%>">
	<!-- 실 데이터를 위한 인수-->   
	<input type="hidden" name="scUSER_ID" value="">
	<input type="hidden" name="USER_ID" value="">
	<input type="hidden" name="PG_ID"           value="">
	<input type="hidden" name="PG_GROUP_ID"     value="">
	<input type="hidden" name="READ_FLAG"       value="">
	<input type="hidden" name="WRITE_FLAG"      value="">
	<input type="hidden" name="COM_FLAG"        value="">
	
	<input type="hidden" name="GR_ADN_PG_IDS"   value="">


<!-- 타이틀 시작-->
<jsp:include page="../com/inc/title_inc.jsp" flush="true">
	<jsp:param name="repTitle" value=""/>
        <jsp:param name="ButtonType" value="N,S,D"/>
</jsp:include>
</form>
<!-- 타이틀 끝 -->

<!-- 테두리 시작 -->
<table width="100%" height="87%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td width="100%" height="60" align="center" valign="top"> 
      <!-- 검색부 디자인 부분 시작 -->
	  <jsp:include page="../com/inc/bgBox_top_inc.jsp" flush="true"/>
      <table width="100%" border="0" cellspacing="0" cellpadding="1" >
			<form name="fmSearch" action="SysAction.do?cmd=AuthUserMgrL" method="post" target="ListFrame">
			<tr>
				<td class="table_header_right"  width="100">사용자 선택&nbsp;&nbsp;&nbsp;</td>
				<td class="table_header_bright" width="250">
					<KTags:KJFSelect	item='<%=(Vector)request.getAttribute("v_user_id")%>'
										name="scUSER_ID"
										value="<%=KJFUtil.print(pm.getUSER_ID()) %>"
										script="class='input_combobox' onchange='document.fmSearch.submit()' "/>
				</td>
				<td>&nbsp;</td>
			</tr>
			</form>
       </table>  
		<jsp:include page="../com/inc/bgBox_bottom_inc.jsp" flush="true"/>
      <!-- 검색부 디자인 부분 끝 -->
    </td>
  </tr>
  <tr> 
    <td>
    	 <table width="100%" height="95%" border="0" cellspacing="0" cellpadding="0">
        	<tr> 
          		<td width="220" valign="top" align="center" > 
				<jsp:include page="../com/inc/bgBox_top_inc.jsp" flush="true"/>
		            <table width="100%" border="0" cellspacing="0" cellpadding="5">
        		    	<tr>
			            	<td height="20" class="table_header_center"><strong>::전체프로그램::</strong></td>
						</tr>
						<tr> 
			                <td valign="top" height="100%"> 
								<%if (isTree){		%>
								<script language="JavaScript">initializeDocument(foldersTree);</script>
								<%}						%>
							</td>
			            </tr>
		            </table>
				<jsp:include page="../com/inc/bgBox_bottom_inc.jsp" flush="true"/>
				</td>
          		<td width="25" align="center"> &nbsp;&nbsp;▶&nbsp;&nbsp; </td>
		       	<td valign="top" width="100%" > 
		   		<jsp:include page="../com/inc/bgBox_top_inc.jsp" flush="true"/>
				  <table width="100%" height="450" align="center"  cellspacing="0" cellpadding="5">
		              <tr>
							<td height="20" class="table_header_center"><strong>::허용된 프로그램::</strong></td>
		              </tr>
		              <tr> 
							<td valign="top" height="100%"> 
							<!-- 리스트 프레임 시작 -->
							<iframe width="100%" height="100%" name="ListFrame" src="SysAction.do?cmd=AuthUserMgrL&scUSER_ID=<%=pm.getScUSER_ID() %>" marginheight="0" marginwidth="0" frameborder="0" valign="top" scrolling="YES"></iframe>
							<!-- 리스트 프레임 끝 -->
			                </td>
		              </tr>
		            </table>
		          <jsp:include page="../com/inc/bgBox_bottom_inc.jsp" flush="true"/></td>
	        </tr>
    	  </table>
    </td>
  </tr>
</table>

<!-- copyright End -->
<jsp:include page="../com/inc/foot_inc.jsp" flush="true"/>

</body>
</html>
