package cn.tarena.erp.auth.dep.business.ebo;


import java.io.Serializable;
import java.util.List;
import cn.tarena.erp.auth.dep.business.ebi.DepEbi;
import cn.tarena.erp.auth.dep.dao.dao.DepDao;
import cn.tarena.erp.auth.dep.vo.DepModel;
import cn.tarena.erp.util.base.BaseQueryModel;

public class DepEbo  implements DepEbi{
	
	//注入数据层
	private DepDao depDao;
	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
	}
	@Override
	public void save(DepModel dm) {
		//调用数据层持久化方法
		depDao.save(dm);
	}
	@Override
	public List<DepModel> getAll() {
		
		return depDao.getAll();
	}
	@Override
	public DepModel get(Serializable uuid) {
	      
		return depDao.getUuid(uuid);
	}
	@Override
	public void update(DepModel dm) {

		depDao.update(dm);
		
	}
	@Override
	public void delete(DepModel dm) {
		depDao.delete(dm);
	
		
	}
//	@Override
//	public List<DepModel> getAll(DepQueryModel dqm) {
//	
//		return depDao.getAll(dqm);
//	}


	public List<DepModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return depDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return depDao.getCount(qm);
	}


}
