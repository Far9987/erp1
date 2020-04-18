package cn.tarena.erp.auth.emp.web;



import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.tarena.erp.auth.emp.business.ebi.EmpEbi;
import cn.tarena.erp.auth.emp.vo.EmpModel;

public class EmpAction extends ActionSupport {
	
	public EmpModel em=new EmpModel();
	 
	//注入业务层接口
	private EmpEbi  empEbi;
	

	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}
	//登录
	 public String  login(){//页面用的是struts格式
		 
		//1.页面收集了用户名，密码信息
		//2.将数据传递到业务层
		//3.业务层转调数据层
		//4.进行业务查询，查询结果返回
		 EmpModel loginEm =empEbi.login(em.getUserName(),em.getPwd());
//			 System.out.println(em.getUserName()+",,"+em.getPwd());
		//5.判断查询结果，如果查询到
		 if (loginEm==null) {
			  //6.登录失败
			 //添加登录失败的信息
			 this.addActionError("对不起，用户名密码错误！");
			 return  "loginFail";
		} else {
			//7.登录成功
			//将登陆入信息放入Session，跳转到主页面
			ActionContext.getContext().getSession().put(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, loginEm);
			return "loginSuccess";
			
		}
	
		 
	
		
		
	 }
	
	

}
