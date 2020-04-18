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
	
	private Class<T> entityClass;//entityClass�õ���ֵ��TȻ��T�ڴ���DepModel
	
	//��entityClass��ʼ��
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
//		String hql="from"+entityClass.getSimpleName();//SimpleName��ȡ�򵥵�����
//		return this.getHibernateTemplate().find(hql);
		//���������һ����˼
		DetachedCriteria dc= DetachedCriteria.forClass(entityClass);
		return this.getHibernateTemplate().findByCriteria(dc);
		
	}

	
//	public List<DepModel> getAll(DepQueryModel dqm) {
//		//ִ�в�ѯ����̬����dqm�е�������Ϊ��ѯ��������ɲ�ѯ
//		//HQL Query
//		//QBC Criteria //H3�ĳ���
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
		//�޸�QBC��ѯ�Ĳ�ѯ����
		//ԭʼ��selelct * from ....
		//�޸ģ�selelct count(uuid) from......
		dc.setProjection(Projections.rowCount());
		doQbc(qm,dc);
		List<Long> count=this.getHibernateTemplate().findByCriteria(dc);
		return count.get(0).intValue();
	}
    
	//������ķ���
	public abstract void doQbc(BaseQueryModel qm,DetachedCriteria dc);
}
