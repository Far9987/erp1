package cn.tarena.erp.auth.emp.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.tarena.erp.auth.emp.vo.EmpModel;

//ebi Enterprice Business Interface ��ҵ�߼��ӿ�
@Transactional// Ĭ�ϼ���  ��д����
public interface EmpEbi {//��ҵ�߼��ӿ�
    //ҵ������ÿһ����������ĵ�ע��
	
	/**
	 * �����û����������¼
	 * @param userName �û���
	 * @param pwd  ����
	 * @return ��¼�û���Ϣ���������nul,��ʾ��¼ʧ��
	 */
	public EmpModel login(String userName, String pwd);

}
