package cn.tarena.erp.auth.emp.business.ebo;

import cn.tarena.erp.auth.emp.business.ebi.EmpEbi;
import cn.tarena.erp.auth.emp.dao.dao.EmpDao;
import cn.tarena.erp.auth.emp.vo.EmpModel;
import cn.tarena.erp.util.format.MD5Utils;

//Enterprice Business object 商业逻辑对象
public class EmpEbo implements EmpEbi{//商业逻辑对象
	//注入数据层接口
	
	private EmpDao empDao;

	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	
	}

	@Override
	public EmpModel login(String userName, String pwd) {
		//MD5加密
//		pwd=MD5Utils.md5(pwd);
		//调用数据层
		 return empDao.getByUserNameAndPwd(userName,pwd);
		 		
	}

	
	
	
	
	

}
