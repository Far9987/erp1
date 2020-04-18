package cn.tarena.erp.util.base;

import java.io.Serializable;
import java.util.List;

import cn.tarena.erp.auth.dep.vo.DepQueryModel;

public interface BaseDao<T> {
	public void save(T t);

	public void update(T t);

	public void delete(T t);
	
	public List<T> getAll();

	public T getUuid(Serializable uuid);

	public List<T> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount);

	public Integer getCount(BaseQueryModel dqm);

}
