package cn.tarena.erp.auth.emp.dao.impl;

import java.sql.DriverManager;
import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mysql.jdbc.Connection;

import cn.tarena.erp.auth.emp.dao.dao.EmpDao;
import cn.tarena.erp.auth.emp.vo.EmpModel;
public class EmpImpl  extends HibernateDaoSupport implements EmpDao{

	@SuppressWarnings("unchecked")
	@Override
	public EmpModel getByUserNameAndPwd(String userName, String pwd) {
		String sql="from EmpModel where userName = ? and pwd = ?";	
		List<EmpModel> temp=this.getHibernateTemplate().find(sql,userName,pwd);
		//temp中有对象，返回第一个，否则返回null
	
		return	temp.size()>0 ? temp.get(0):null; 
	}
	
	public static void main(String[] args) {
		EmpImpl emi=new EmpImpl();
		EmpModel em=emi.getByUserNameAndPwd("admin", "admin");
		System.out.println(em);
		
	
	  
	     
	}
	

}
