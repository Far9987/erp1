package cn.tarena.erp.auth.emp.dao.dao;

import cn.tarena.erp.auth.emp.vo.EmpModel;

public interface EmpDao {

     public	EmpModel getByUserNameAndPwd(String userName, String pwd);

}
