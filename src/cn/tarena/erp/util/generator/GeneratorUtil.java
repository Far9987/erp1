package cn.tarena.erp.util.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import cn.tarena.erp.auth.dep.business.ebi.DepEbi;
import cn.tarena.erp.auth.dep.dao.dao.DepDao;
import cn.tarena.erp.auth.dep.vo.DepModel;
import cn.tarena.erp.auth.dep.vo.DepQueryModel;
import cn.tarena.erp.auth.emp.vo.EmpModel;
import cn.tarena.erp.util.base.BaseAction;
import cn.tarena.erp.util.base.BaseDao;
import cn.tarena.erp.util.base.BaseEbi;
import cn.tarena.erp.util.base.BaseImpl;
import cn.tarena.erp.util.base.BaseQueryModel;

public class GeneratorUtil {//代码生成器 核心工作原理：IO+反射
	private static Class class1;
	private	String className;//EmpModel
	private	String big;//Emp
	private	String first;//获取Emp第一个字母，然后转换成小写
	private	String small;
	private	String rootpkg;//获取当前类的包名对象的包名（cn.tarena.erp.auth.emp.vo）
		                                        //传什么数据模型过来就获取当前数据模型的包名
	private	String pkg;//cn.tarena.erp.auth.emp
	    //再把当前获取的包名cn.tarena.erp.auth.emp把转换为c/taren/erp/auth/emp
	private    String dir;
		
	public GeneratorUtil(Class class1 ) throws Exception{
		this.class1=class1;
		//生成所有的内容
		//-1.数据初始化
		dataInit();
		//0.创建目录
//		generatorDirectory();
		//1.QueryModel
//		generatorQueryModel();
		//2.hbm.xml
//		generatorHbmXml();
		//3.Dao
//		generatorDao();
		//4.Impl
//		generatorImpl();
		//5.Ebi
//		generatorEbi();
		//6.Ebo
//		generatorEbo();
		//7.Action
//		generatorAction();
		//8.applictionContext.xml
//		genratorApplication();
		//9.struts.xml(选作)
//		modifyStrutsXml();
		
	}
	//9.struts.xml(选作)
	private void modifyStrutsXml() throws Exception {
		//读取原始的内容
		//读取到特定位置（/package）添加到指定内容
		
		//我们要读取的文件与写的文件是同一个文件
		
		
	}

	//8.applictionContext.xml
	private void genratorApplication() throws Exception {
		File file=new File("resources/applicationContext-"+small+".xml");
		  if(file.exists()){
	       	  return;
	         }
		file.createNewFile();
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(file));
		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
		bw.write("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
		
		bw.write("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
			
		bw.write("	xsi:schemaLocation=\"");
		
		bw.write("		http://www.springframework.org/schema/beans ");
		
		bw.write("		http://www.springframework.org/schema/beans/spring-beans.xsd");
		
		bw.write("		\">  ");
		
		bw.write("	<!-- Action -->");
		
		bw.write("	<!-- action类对应的bean应该配置scope  非单例     -->");
		
		bw.write("	<bean id=\""+small+"Action\" class=\""+pkg+".web."+big+"Action\" scope=\"prototype\">");
		
		bw.write("	<property name=\""+small+"Ebi\" ref=\""+small+"Ebi\"></property>");
		
		bw.write("	</bean>");
		
		bw.write("	<!-- Ebi -->");
		
		bw.write("	<bean id=\""+small+"Ebi\" class=\""+pkg+".business.ebo."+big+"Ebo\">");
		
		bw.write("	<property name=\""+small+"Dao\" ref=\""+small+"Dao\"></property>");

		bw.write("	</bean>");
		
		bw.write("    <!-- Dao -->");
		
		bw.write("	<bean id=\""+small+"Dao\" class=\""+pkg+".dao.impl."+big+"Impl\">");
		
		bw.write("	<!-- 注入sessionFactory -->");
		
		bw.write("	<property name=\"sessionFactory\" ref=\"sessionFactory\"></property><!-- 主配置文件里面的sessionFactory一样 -->");
		
		bw.write("	</bean>");
		
		bw.write("</beans>");
		
		bw.flush();
		bw.close();
		
	}

	//7.Action
	private void generatorAction() throws Exception {
		File file=new File("src/"+dir+"/web/"+big+"Action.java");
		  if(file.exists()){
	       	  return;
	         }
		file.createNewFile();
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(file));
		bw.write("package "+pkg+".web;");
		bw.newLine();
		
		bw.write("import java.util.List;");
		
		bw.write("import "+pkg+".business.ebi."+big+"Ebi;");
		
		bw.write("import "+pkg+".vo."+big+"Model;");
		
		bw.write("import "+pkg+".vo."+big+"QueryModel;");
		
		bw.write("import cn.tarena.erp.util.base.BaseAction;");
		bw.newLine();
		
		bw.write("public class "+big+"Action extends BaseAction {");
		bw.newLine();
		
		bw.write("	public DepModel dm = new DepModel();");
		
		bw.write("	public DepQueryModel dqm = new DepQueryModel();");
		bw.newLine();
		
		bw.write("	private "+big+"Ebi "+small+"Ebi;");
		bw.newLine();
		
		bw.write("	public void set"+big+"Ebi("+big+"Ebi "+small+"Ebi) {");
		bw.newLine();
		
		bw.write("		this."+small+"Ebi = "+small+"Ebi;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.write("	//跳转到列表页面");
		bw.newLine();
		
		bw.write("	public String list(){");
		bw.newLine();
		
		bw.write("		setDataTotal("+small+"Ebi.getCount(dqm));");
		bw.newLine();
		
		bw.write("		List<"+big+"Model> "+small+"List = "+small+"Ebi.getAll(dqm,pageNum,pageCount);");
		bw.newLine();
		
		bw.write("		put(\""+small+"List\", "+small+"List);");
		bw.newLine();
		
		bw.write("		return LIST;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.write("	//跳转到添加页面");
		bw.newLine();
		
		bw.write("	public String input(){");
		bw.newLine();
		
		bw.write("		if("+first+"m.getUuid()!=null){");
		bw.newLine();
		
		bw.write("			"+first+"m = "+small+"Ebi.get("+first+"m.getUuid());");
		bw.newLine();
		
		bw.write("		}");
		bw.newLine();
		
		bw.write("		return INPUT;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.write("	//添加功能");
		bw.newLine();
		
		bw.write("	public String save(){");
		bw.newLine();
		
		bw.write("		if("+first+"m.getUuid() == null){");
		bw.newLine();
		
		bw.write("			"+small+"Ebi.save(dm);");
		bw.newLine();
		
		bw.write("		}else{");
		bw.newLine();
		
		bw.write("			"+small+"Ebi.update(dm);");
		bw.newLine();
		
		bw.write("		}");
		bw.newLine();
		
		bw.write("		return TO_LIST;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.write("	//删除部门");
		bw.newLine();
		
		bw.write("	public String delete(){");
		bw.newLine();
		
		bw.write("		"+small+"Ebi.delete(dm);");
		bw.newLine();
		
		bw.write("		return TO_LIST;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.write("}");
		bw.newLine();
		
		bw.flush();
		bw.close();
		
	}

	//6.Ebo
	private void generatorEbo()  throws Exception{
		 File f=new File("src/"+dir+"/business/ebo/"+big+"Ebo.java");
         if(f.exists()){
       	  return;
         }
         f.createNewFile();
         
         BufferedWriter bw=new BufferedWriter(new FileWriter(f));
         
         
         bw.write("import java.io.Serializable;");
         
         bw.write("import java.util.List;");
         
         bw.write("import "+pkg+".business.ebi."+big+"Ebi;");
       
         bw.write("import "+pkg+".dao.dao."+big+"Dao;");
         
         bw.write("import "+pkg+".vo."+big+"Model;");
         
         bw.write("import cn.tarena.erp.util.base.BaseQueryModel;");
         bw.newLine();
         
         bw.write("public class "+big+"Ebo  implements "+big+"Ebi{");
         bw.newLine();
         
         bw.write("	private "+big+"Dao "+small+"Dao;");
         bw.newLine();
         
         bw.write("	public void set"+big+"Dao("+big+"Dao "+small+"Dao) {");
         bw.newLine();
         
         bw.write("		this."+small+"Dao = "+small+"Dao;");
         bw.newLine();
         
         bw.write("	}");
         bw.newLine();
         
         bw.write("	public void save("+big+"Model "+first+"m) {");
         bw.newLine();
         
         bw.write("		"+small+"Dao.save("+first+"m);");
         bw.newLine();
         
         bw.write("	}");
         bw.newLine();
         
         bw.write("	public List<"+big+"Model> getAll() {");
         bw.newLine();
         
         bw.write("		return "+small+"Dao.getAll();");
         bw.newLine();
         
         bw.write("	}");
         bw.newLine();
         
         bw.write("	public "+big+"Model get(Serializable uuid) {");
         bw.newLine();
         
         bw.write("return "+small+"Dao.getUuid(uuid);");
         bw.newLine();
         
         bw.write("	}");
         bw.newLine();
         
         bw.write("	public void update("+big+"Model dm) {");
         bw.newLine();
         
         bw.write("		"+small+"Dao.update(dm);");
         bw.newLine();
         
         bw.write("	}");
         bw.newLine();
         
         bw.write("	public void delete("+big+"Model dm) {");
         bw.newLine();
         
         bw.write("		"+small+"Dao.delete(dm);");
         bw.newLine();
         
         bw.write("	}");
         bw.newLine();
         
         bw.write("	public List<"+big+"Model> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {");
         bw.newLine();
         
         bw.write("		return "+small+"Dao.getAll(qm,pageNum,pageCount);");
         bw.newLine();
         
         bw.write("	}");
         bw.newLine();
         
         bw.write("	public Integer getCount(BaseQueryModel qm) {");
         bw.newLine();
         
         bw.write("		return "+small+"Dao.getCount(qm);");
         bw.newLine();
         
         bw.write("	  }");
         bw.newLine();
         
         bw.write("}");
         bw.newLine();
         
         bw.flush();
         bw.close();
		
	}


	//5.Ebi
	private void generatorEbi() throws Exception{
		
		 File f=new File("src/"+dir+"/business/ebi/"+big+"Ebi.java");
         if(f.exists()){
       	  return;
         }
         f.createNewFile();
         
         BufferedWriter bw=new BufferedWriter(new FileWriter(f));
         
         
         bw.write("package "+pkg+".business.ebi;");
         bw.newLine();
         
         bw.write("import org.springframework.transaction.annotation.Transactional;");
         
         bw.write("import "+pkg+".vo."+big+"Model;");
         
         bw.write("import cn.tarena.erp.util.base.BaseEbi;");
         bw.newLine();
         
         bw.write("@Transactional");
         bw.newLine();
         
         bw.write("public interface "+big+"Ebi extends BaseEbi<"+big+"Model>  {");
         bw.newLine();
         
         bw.newLine();
         
         bw.write("}");
         bw.newLine();
         
         bw.flush();
         bw.close();
	}
	//4.Impl
	private void generatorImpl() throws Exception{
		 File f=new File("src/"+dir+"/dao/impl/"+big+"Impl.java");
         if(f.exists()){
       	  return;
         }
         f.createNewFile();
         
         BufferedWriter bw=new BufferedWriter(new FileWriter(f));
                  
         bw.write("package "+pkg+".dao.impl;");
         bw.newLine();
         
         bw.write("import org.hibernate.criterion.DetachedCriteria;"); 

         bw.write("import org.hibernate.criterion.Restrictions;");
       

         bw.write("import "+pkg+".dao.dao.+"+big+"+Dao;");
      

         bw.write("import "+pkg+".vo.+"+big+"+Model;");
     

         bw.write("import "+pkg+".vo."+big+"QueryModel;");
        

         bw.write("import cn.tarena.erp.util.base.BaseImpl;");
     

         bw.write("import cn.tarena.erp.util.base.BaseQueryModel;");
         bw.newLine();
         
         bw.write("public class "+big+"Impl extends BaseImpl<"+big+"Model> implements "+big+"Dao{");
         bw.newLine();
         
         
         bw.write("public void doQbc(BaseQueryModel qm, DetachedCriteria dc) {");
         bw.newLine();
         
         
         bw.write("	"+big+"QueryModel "+first+"qm=("+big+"QueryModel)qm;");
         bw.newLine();
         
         bw.write("    //TODO 添加自定义查询条件");
         bw.newLine();
         
         bw.write("    }");
         bw.newLine();
         bw.write("}");
         bw.newLine();
         
    
         bw.flush();
         bw.close();
         
		
	}

	//3.Dao
	private void generatorDao() throws Exception {
          File f=new File("src/"+dir+"/dao/dao/"+big+"Dao.java");
          if(f.exists()){
        	  return;
          }
          f.createNewFile();
          
          BufferedWriter bw=new BufferedWriter(new FileWriter(f));
          
          
          bw.write("package "+pkg+".dao.dao;");
          bw.newLine();
          
          bw.write("import "+pkg+".vo."+big+"Model;");
        
          
          bw.write("import cn.tarena.erp.util.base.BaseDao;");
          bw.newLine();
          
          
          bw.write("public interface "+big+"Dao extends BaseDao<"+big+"Model>{");
          bw.newLine();
          
          
          bw.newLine();
          
          
          bw.write("}");
          bw.newLine();
          
          bw.flush();
          bw.close();
          
	}

	//2.hbm.xml
		private void generatorHbmXml() throws Exception {
			
			//1.创建文件
			File file=new File("src/"+dir+"/vo/"+big+"Model.hbm.xml");
			
			if (file.exists()) {
				return;
			}
			
		   
			file.createNewFile();
			//2.IO写入内容
			BufferedWriter bw=new BufferedWriter(new FileWriter(file));
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			bw.newLine();
			bw.write("<!DOCTYPE hibernate-mapping PUBLIC");
			bw.newLine();
			bw.write("        '-//Hibernate/Hibernate Mapping DTD 3.0//EN'");
			bw.newLine();
			bw.write("        'http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd'>");
			bw.newLine();
			bw.write("<hibernate-mapping>");
			bw.newLine();
			bw.write("    <class name=\"cn.tarena.erp.auth.emp.vo.EmpModel\" table=\"tbl_emp\">");
			bw.newLine();
			bw.write("        <id name=\"uuid\">");
			bw.newLine();
			bw.write("            <generator class=\"native\" />");
			bw.newLine();
			bw.write("        </id>");
			bw.newLine();
			//hibernate的映射配置文件中要对原始模型类中的属性进行配置，反射获取所有字段
			      Field[] field=class1.getDeclaredFields();
			      for (Field fd:field) {
			    	  //如果修饰符是private，就生成
			    	  if (fd.getModifiers()==Modifier.PUBLIC && !fd.getName().equals("uuid")) {
			    		  //如果是关联关系就不生成，不是关联关系（Long,Integer,Double,String）
			    		  if(     fd.getType().equals(String.class)||
			    				  fd.getType().equals(Double.class)||
			    				  fd.getType().equals(Integer.class)||
			    				  fd.getType().equals(Long.class)
			    				  
			    				  ){
			    			  bw.write("        <property name=\""+fd.getName()+"\"/>");
							  bw.newLine();
			    			  
			    		  }
			    		
					}
			    	
				}
			bw.write("    </class>");
			bw.newLine();
			bw.write("</hibernate-mapping>");
			bw.newLine();
			bw.flush();
			bw.close();
			
			
			
		}

		//1.QueryModel
		private void generatorQueryModel() throws IOException {
			//1.创建文件
			File f=new File("src/"+dir+"/vo/"+big+"QueryModel.java");
			//判断：如果该文件存在，终止操作
			
			if (f.exists()) {
				return;
			}
			
			f.createNewFile();
			//2.IO写入内容
			BufferedWriter bw=new BufferedWriter(new FileWriter(f));
			bw.write("package"+pkg+".vo;");
			bw.newLine();
			bw.newLine();
			bw.write("import cn.tarena.erp.util.base.BaseQueryModel;");
			bw.newLine();
			bw.write("public class "+big+"QueryModel extends "+big+"Model implements BaseQueryModel{");
			bw.newLine();
			bw.write("    //TODO 添加自定义查询条件");
			bw.newLine();
			bw.write("}");
			bw.newLine();
			
			bw.flush();
			bw.close();
		
		
	}

		//0.创建目录
		private void generatorDirectory() {
			//business/ebi
			File file =new File("scr/"+dir+"/business/ebi");
			file.mkdirs();
			//business/ebo
			file =new File("scr/"+dir+"/business/ebo");
			file.mkdirs();
			//dao/dao
			file =new File("scr/"+dir+"/dao/dao");
			file.mkdirs();
			//dao/impl
			file =new File("scr/"+dir+"/dao/impl");
			file.mkdirs();
			//web
			file =new File("scr/"+dir+"/web");
			file.mkdirs();
			
		
	}

		//-1.数据初始化
		private void dataInit() {
		//5.5个东西需要初始化
			 className=class1.getSimpleName();//EmpModel
			 big=className.substring(0, className.length()-5);//Emp
			 first=big.substring(0,1).toLowerCase();//获取Emp第一个字母，然后转换成小写
			 small=first+big.substring(1);
			 rootpkg=class1.getPackage().getName();//获取当前类的包名对象的包名（cn.tarena.erp.auth.emp.vo）
			                                        //传什么数据模型过来就获取当前数据模型的包名
			pkg=rootpkg.substring(0, rootpkg.length()-3);//cn.tarena.erp.auth.emp
	        //再把当前获取的包名cn.tarena.erp.auth.emp把转换为c/taren/erp/auth/emp
	        dir=pkg.replace(".", "/");
	     
			
			System.out.println(dir);
			
			
		}
		


	public static void main(String[] args) throws Exception {
		
		new GeneratorUtil(EmpModel.class);
		
		
		
	}
	
	
	
//	public static void main(String[] args) throws Exception {
//		//创建一个新文件
//		File f=new File("src/EmpAction.java");
//		f.createNewFile();
//		
//		BufferedWriter bw=new BufferedWriter(new FileWriter(f));
//		bw.write("public class EmpAction{}");
//		bw.flush();
//		bw.close();
//		
//	}

}
