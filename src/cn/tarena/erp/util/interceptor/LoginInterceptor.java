package cn.tarena.erp.util.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


import cn.tarena.erp.auth.emp.vo.EmpModel;

public class LoginInterceptor extends AbstractInterceptor {//��¼������

	
	public String intercept(ActionInvocation invocation) throws Exception {
		//ִ�г��˵�¼����֮ǰ�����в�������¼У��
		//��ȡ���β�������Ϣ
		/*
		System.out.println(invocation.getProxy().getAction());
		System.out.println(invocation.getProxy().getActionName());		emp_login
		System.out.println(invocation.getProxy().getMethod());
		*/
		System.out.println("-----");
		String actionName = invocation.getProxy().getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName+"."+methodName;
		
		if("cn.tarena.erp.auth.emp.web.EmpAction.login".equals(allName)){
			return invocation.invoke();
		}
		
		//��ȡ��ǰ��¼����Ϣ
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		//�����ǰû�е�¼����ת����¼ҳ��
		if(loginEm == null){
			//��ת����¼
			return "noLogin";
		}
		
		//ִ��ԭʼ����
		return invocation.invoke();
	}


}
