package cn.tarena.erp.auth.dep.business.ebi;



import org.springframework.transaction.annotation.Transactional;

import cn.tarena.erp.auth.dep.vo.DepModel;

import cn.tarena.erp.util.base.BaseEbi;

@Transactional//事务加载接口上是因为以后写实现类不要在写事务了，也是因为以后的代码维护

//切面:spring内置
//切入点:定义了该注解所在的类或接口中的所有方法
//execution(cn.tarena.erp.auth.dep.business.ebi.DepEbi.*(..))
public interface DepEbi extends BaseEbi<DepModel>  {
   

}
