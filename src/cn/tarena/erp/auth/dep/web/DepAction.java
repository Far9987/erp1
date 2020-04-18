package cn.tarena.erp.auth.dep.web;

import java.util.List;
import cn.tarena.erp.auth.dep.business.ebi.DepEbi;
import cn.tarena.erp.auth.dep.vo.DepModel;
import cn.tarena.erp.auth.dep.vo.DepQueryModel;
import cn.tarena.erp.util.base.BaseAction;

public class DepAction extends BaseAction {
	
//	public  DepModel dm=new DepModel();//对象封装数据的  模型驱动
//	
//	//定义一个收集查询条件的数据对象
//	public DepQueryModel dqm=new DepQueryModel();
//	
//	
//	//注入业务层
//	public DepEbi depEbi;
//	public void setDepEbi(DepEbi depEbi) {
//		this.depEbi = depEbi;
//	}
//	
//	public Integer pageNum=1;
//	public Integer pageCount=2;//收集数据总量
//	public Integer maxPageNum;
//	public Integer dataTotal;
//	
//	
//	
//	//跳转到列表页面
//	public String list(){
//		//查询数据总条目数
//		dataTotal=depEbi.getCount(dqm);
//		//需要最大页码值
//		maxPageNum=(dataTotal+pageCount-1)/pageCount;
//		
//		
//		
//		//加载所以部门信息，放入制定范围，页面从指定范围取出，进行展示
//		List<DepModel> depList=depEbi.getAll(dqm,pageNum,pageCount);
//		//指定范围：request（选定），session(客户端，整个浏览器进行共享)，
//		//application（整个应用的共享，所有的人只要访问这个服务器都会共享）
//		ActionContext.getContext().put("depList", depList);
//		return "list";
//		
//	}
////	//查询列表功能 与上面的功能合并了
////	public  String queryList(){
////		
////		//根据查询条件获取数据（查询条件封装在dqm对象中）
////		List<DepModel> depList=depEbi.getAll(dqm);
////		//放入指定范围
////		ActionContext.getContext().put("deList", depList);
////		//跳转页面
////		return "list";
////		
////	}
//	
//   //跳转到增加页面
//	public  String input(){
//		if (dm.getUuid()!=null) {
//			dm=depEbi.get(dm.getUuid());
//		}
//		return "input";
//	}
//	//跳转到修改页面
////	public String input2(){
////		//查询数据，并进行展示
////		//获取指定被修改的数据信息，放入指定的范围
////		dm=depEbi.get(dm.getUuid());
////		
////		//跳转页面
////		return  "input";
////	}
//	//添加功能
//	public String save(){
//		//根据页面传递的参数判断操作时添加还是修改，依据dm.uuid
//		if (dm.getUuid()==null) {
//			//添加功能
//			depEbi.save(dm);
//		} else {
//             //修改页面
//			depEbi.update(dm);
//		}
//		//将收集值传递到业务层，完后保存功能
//		
//		return "toList";
//	}
//	
//	//删除部门
//	public String delete(){
//		System.out.println(dm);
//		 depEbi.delete(dm);
//		return "toList";
//	}
	public DepModel dm = new DepModel();
	public DepQueryModel dqm = new DepQueryModel();
	
	private DepEbi depEbi;
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	
	
	
	//跳转到列表页面
	public String list(){
		setDataTotal(depEbi.getCount(dqm));
		List<DepModel> depList = depEbi.getAll(dqm,pageNum,pageCount);
		put("depList", depList);
		return LIST;
	}
	
	//跳转到添加页面
	public String input(){
		if(dm.getUuid()!=null){
			dm = depEbi.get(dm.getUuid());
		}
		return INPUT;
	}
	
	//添加功能
	public String save(){
		if(dm.getUuid() == null){
			depEbi.save(dm);
		}else{
			depEbi.update(dm);
		}
		return TO_LIST;
	}
	
	//删除部门
	public String delete(){
		depEbi.delete(dm);
		return TO_LIST;
	}
	

}
