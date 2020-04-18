package cn.tarena.erp.util.base;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.tarena.erp.auth.dep.vo.DepModel;
import java.lang.reflect.ParameterizedType;

public abstract class BaseImpl<T> extends HibernateDaoSupport {
	
	private Class<T> entityClass;//entityClass得到的值给T然后T在传给DepModel
	
	//将entityClass初始化
	public BaseImpl(){
		Type genType=getClass().getGenericSuperclass();
		Type[] params=((ParameterizedType) genType).getActualTypeArguments();
		entityClass=(Class)params[0];
		
	}

	public void save(T t) {
	this.getHibernateTemplate().save(t);
		
	}
	public void update(T t) {
		this.getHibernateTemplate().update(t);
		
	}
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
		
	}
	
	public T getUuid(Serializable uuid) {
		
		return this.getHibernateTemplate().get(entityClass, uuid);
	}
	
	public List<DepModel> getAll() {
		
//		String hql="from DepModel";
//		String hql="from"+entityClass.getSimpleName();//SimpleName获取简单的类名
//		return this.getHibernateTemplate().find(hql);
		//这个两个是一个意思
		DetachedCriteria dc= DetachedCriteria.forClass(entityClass);
		return this.getHibernateTemplate().findByCriteria(dc);
		
	}

	
//	public List<DepModel> getAll(DepQueryModel dqm) {
//		//执行查询，动态链接dqm中的条件作为查询条件，完成查询
//		//HQL Query
//		//QBC Criteria //H3的初衷
//		//SQL SQLQuery
//		
//		
//		DetachedCriteria dc= DetachedCriteria.forClass(DepModel.class);
//		if(dqm.getName()!=null && dqm.getName().trim().length()>0){
//			dc.add(Restrictions.like("name","%"+dqm.getN,amuwehddjiwos,uwbchgeuyciksmnhj  sudd sbdyxuasdlksiw yaiosleioxnegcxc sssliwnxgdyaoqmxn zalaosd cncdud ame().trim()+"%" ));
//		}
//		if(dqm.getTele()!=null && dqm.getTele().trim().length()>0){
//			dc.add(Restrictions.like("tele", "%"+dqm.getTele().trim()+"%"));
//		}
//		
//		return this.getHibernateTemplate().findByCriteria(dc);
//	}

	public List<DepModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount) {
		
		DetachedCriteria dc= DetachedCriteria.forClass(DepModel.class);
		
		
		doQbc(qm,dc);
		return this.getHibernateTemplate().findByCriteria(dc, (pageNum-1)*pageCount, pageCount);
	}

	
	public Integer getCount(BaseQueryModel qm) {
		DetachedCriteria dc= DetachedCriteria.forClass(DepModel.class);
		//修改QBC查询的查询内容
		//原始：selelct * from ....
		//修改：selelct count(uuid) from......
		dc.setProjection(Projections.rowCount());
		doQbc(qm,dc);
		List<Long> count=this.getHibernateTemplate().findByCriteria(dc);
		return count.get(0).intValue();
	}
    
	//抽出来的方法
	public abstract void doQbc(BaseQueryModel qm,DetachedCriteria dc);
}
