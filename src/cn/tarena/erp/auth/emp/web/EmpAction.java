package cn.tarena.erp.auth.emp.web;



import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.tarena.erp.auth.emp.business.ebi.EmpEbi;
import cn.tarena.erp.auth.emp.vo.EmpModel;

public class EmpAction extends ActionSupport {
	
	public EmpModel em=new EmpModel();
	 
	//ע��ҵ���ӿ�
	private EmpEbi  empEbi;
	

	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}
	//��¼
	 public String  login(){//ҳ���õ���struts��ʽ
		 
		//1.ҳ���ռ����û�����������Ϣ
		//2.�����ݴ��ݵ�ҵ���
		//3.ҵ���ת�����ݲ�
		//4.����ҵ���ѯ����ѯ�������
		 EmpModel loginEm =empEbi.login(em.getUserName(),em.getPwd());
//			 System.out.println(em.getUserName()+",,"+em.getPwd());
		//5.�жϲ�ѯ����������ѯ��
		 if (loginEm==null) {
			  //6.��¼ʧ��
			 //��ӵ�¼ʧ�ܵ���Ϣ
			 this.addActionError("�Բ����û����������");
			 return  "loginFail";
		} else {
			//7.��¼�ɹ�
			//����½����Ϣ����Session����ת����ҳ��
			ActionContext.getContext().getSession().put(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, loginEm);
			return "loginSuccess";
			
		}
	
		 
	
		
		
	 }
	
	

}
