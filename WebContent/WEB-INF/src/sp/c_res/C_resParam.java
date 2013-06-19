/**
 * ���ϸ�   : C_resParam.java
 * ����      : Parameter Bean 
 * �̷»���
 * 
 */
package sp.c_res;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import kjf.util.*;


public class C_resParam extends ActionForm{    


    /*����¡ ����*/
    private String nowPage;
    private String totalCount;
    private String rowPerPage; 

    	
    /*�˻� ����-���λ� sc (serch condition) */







    public C_resParam() {
    }


 

    /**
    *	request�� ���� parameter�� �ش� value�� ���
    *	paramName : value
    *	�������� ���
    */
    public String toString(HttpServletRequest request){
        return KJFUtil.reportParameters(request) ;
    }


    public String getNowPage() {
        return nowPage;
    }
    
    public void setNowPage(String nowPage) {
        this.nowPage = nowPage;
    }
    
    public String getRowPerPage() {
        return rowPerPage;
    }
    
    public void setRowPerPage(String rowPerPage) {
        this.rowPerPage = rowPerPage;
    }
        
    public String getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }




}//end class

