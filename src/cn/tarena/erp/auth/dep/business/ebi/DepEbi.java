package cn.tarena.erp.auth.dep.business.ebi;



import org.springframework.transaction.annotation.Transactional;

import cn.tarena.erp.auth.dep.vo.DepModel;

import cn.tarena.erp.util.base.BaseEbi;

@Transactional//������ؽӿ�������Ϊ�Ժ�дʵ���಻Ҫ��д�����ˣ�Ҳ����Ϊ�Ժ�Ĵ���ά��

//����:spring����
//�����:�����˸�ע�����ڵ����ӿ��е����з���
//execution(cn.tarena.erp.auth.dep.business.ebi.DepEbi.*(..))
public interface DepEbi extends BaseEbi<DepModel>  {
   

}
