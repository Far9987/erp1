package cn.tarena.erp.auth.dep.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import cn.tarena.erp.auth.dep.dao.dao.DepDao;
import cn.tarena.erp.auth.dep.vo.DepModel;
import cn.tarena.erp.auth.dep.vo.DepQueryModel;
import cn.tarena.erp.util.base.BaseImpl;
import cn.tarena.erp.util.base.BaseQueryModel;

public class DepImpl extends BaseImpl<DepModel> implements DepDao{

      
	public void doQbc(BaseQueryModel qm, DetachedCriteria dc) {
		DepQueryModel dqm=(DepQueryModel)qm;
		//
		if(dqm.getName()!=null && dqm.getName().trim().length()>0){
			dc.add(Restrictions.like("name","%"+dqm.getName().trim()+"%" ));
		}
		if(dqm.getTele()!=null && dqm.getTele().trim().length()>0){
			dc.add(Restrictions.like("tele", "%"+dqm.getTele().trim()+"%"));
		}
		
	}

	
	
	

	


}
