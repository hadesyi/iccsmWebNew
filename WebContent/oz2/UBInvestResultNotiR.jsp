<%@ page contentType="text/html; charset=utf-8" %>
<html>
	<head>		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>사용전검사 결과 보고서</title>
	</head>
	<body>
	<OBJECT width = "0" height = "0" ID="ZTransferX" CLASSID="CLSID:C7C7225A-9476-47AC-B0B0-FF3B79D55E67" codebase="http://99.1.5.75/oz/ozviewer/ZTransferX.cab#version=2,2,0,8">
	<PARAM NAME="download.Server" VALUE="http://99.1.5.75/oz/ozviewer">
	<PARAM NAME="download.Port" VALUE="80">
	<PARAM NAME="download.Instruction" VALUE="ozrviewer.idf">
	<PARAM NAME="install.Base" VALUE="<PROGRAMS>/Forcs">
	<PARAM NAME="install.Namespace" VALUE="OzViewerSetUp">
	</OBJECT>
		
	<OBJECT id = "ozrviewer" CLASSID="CLSID:0DEF32F8-170F-46f8-B1FF-4BF7443F5F25" width="100%" height="100%">
		<PARAM NAME="connection.servlet" value="http://99.1.5.75/oz/server">
		<PARAM NAME="connection.reportname" value="/BeforeInvest/UBInvestResultNotiR.ozr">
		<PARAM NAME="odi.odinames" value="Q)UBInvestResultNotiR,Q)UBCertTable"> 
		<PARAM NAME="odi.Q)UBInvestResultNotiR.pcount" value="3">
		<PARAM NAME="odi.Q)UBInvestResultNotiR.args1" value="RECV_NUM=<%=request.getParameter("scRECV_NUM")%>">
		<PARAM NAME="odi.Q)UBInvestResultNotiR.args2" value="SIDO_CODE=<%=request.getParameter("SIDO_CODE")%>">
		<PARAM NAME="odi.Q)UBInvestResultNotiR.args3" value="SIGUNGU_CODE=<%=request.getParameter("SIGUNGU_CODE")%>">
		<PARAM NAME="odi.Q)UBCertTable.pcount" value="3">
		<PARAM NAME="odi.Q)UBCertTable.args1" value="RECV_NUM=<%=request.getParameter("scRECV_NUM")%>">
		<PARAM NAME="odi.Q)UBCertTable.args2" value="SIDO_CODE=<%=request.getParameter("SIDO_CODE")%>">
		<PARAM NAME="odi.Q)UBCertTable.args3" value="SIGUNGU_CODE=<%=request.getParameter("SIGUNGU_CODE")%>">
		<PARAM NAME="viewer.isframe" value="false">
	</OBJECT>
	</body>
</html>
