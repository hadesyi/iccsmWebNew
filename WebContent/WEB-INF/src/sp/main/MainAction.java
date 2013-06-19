/**
 * ���ϸ�    : MainAction.java 
 * ����      : Main ���ΰ��� Action 
 * �̷»���
 *
 */

package sp.main; 

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sp.action.KJFAction;
import kjf.action.KJFCommand;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sp.main.cmd.*;



public class MainAction extends KJFAction{

    private HashMap commands; //�������� class��
        	
    /**
     * ����Command���� �����Ѵ�.
     * @param ActionMapping mapping,
     *         ActionForm form,
     *         HttpServletRequest request,
     *         HttpServletResponse response
     * @return  ActionForward    
     */

	public String executeAction(ActionMapping mapping,
							            ActionForm form,
							            HttpServletRequest request,
							            HttpServletResponse response) throws Exception{
		
			initCommands();		
            KJFCommand cmd = lookupCommand(request.getParameter("cmd"));         
	
	        return cmd.execute(request, form) ; 
	} 
	
	
    /**
     * Command���� ����Ѵ�.
     * @param 
     * @return       
     */
    private void initCommands(){

        commands = new HashMap();
      
        //���� cmd�� ���⿡ ����Ѵ�.

        commands.put("Schedule",	new MainCmd()); // ���� ȭ�� ������


    }




    /**
     * ��û���� ������ Command instance�� ���Ѵ�.
     * @param cmd ���ɸ�
     * @return Command class instance
     */
    private KJFCommand lookupCommand(String cmd) throws Exception{
        if (commands.containsKey(cmd)) {
            return (KJFCommand)commands.get(cmd);
        }else{
            throw new Exception("Invalid Command Identifier");
        }
    }//end lookupCommand

		
}