package cn.tarena.erp.auth.emp.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.tarena.erp.auth.emp.vo.EmpModel;

//ebi Enterprice Business Interface 商业逻辑接口
@Transactional// 默认加上  读写事物
public interface EmpEbi {//商业逻辑接口
    //业务层必须每一个方法添加文档注释
	
	/**
	 * 根据用户名和密码登录
	 * @param userName 用户名
	 * @param pwd  密码
	 * @return 登录用户信息。如果返回nul,表示登录失败
	 */
	public EmpModel login(String userName, String pwd);

}
