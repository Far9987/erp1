package cn.tarena.erp.auth.emp.business.ebo;

import cn.tarena.erp.auth.emp.business.ebi.EmpEbi;
import cn.tarena.erp.auth.emp.dao.dao.EmpDao;
import cn.tarena.erp.auth.emp.vo.EmpModel;
import cn.tarena.erp.util.format.MD5Utils;

//Enterprice Business object ��ҵ�߼�����
public class EmpEbo implements EmpEbi{//��ҵ�߼�����
	//ע�����ݲ�ӿ�
	
	private EmpDao empDao;

	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	
	}

	@Override
	public EmpModel login(String userName, String pwd) {
		//MD5����
//		pwd=MD5Utils.md5(pwd);
		//�������ݲ�
		 return empDao.getByUserNameAndPwd(userName,pwd);
		 		
	}

	
	
	
	
	

}
