package cn.tarena.erp.auth.dep.web;

import java.util.List;
import cn.tarena.erp.auth.dep.business.ebi.DepEbi;
import cn.tarena.erp.auth.dep.vo.DepModel;
import cn.tarena.erp.auth.dep.vo.DepQueryModel;
import cn.tarena.erp.util.base.BaseAction;

public class DepAction extends BaseAction {
	
//	public  DepModel dm=new DepModel();//�����װ���ݵ�  ģ������
//	
//	//����һ���ռ���ѯ���������ݶ���
//	public DepQueryModel dqm=new DepQueryModel();
//	
//	
//	//ע��ҵ���
//	public DepEbi depEbi;
//	public void setDepEbi(DepEbi depEbi) {
//		this.depEbi = depEbi;
//	}
//	
//	public Integer pageNum=1;
//	public Integer pageCount=2;//�ռ���������
//	public Integer maxPageNum;
//	public Integer dataTotal;
//	
//	
//	
//	//��ת���б�ҳ��
//	public String list(){
//		//��ѯ��������Ŀ��
//		dataTotal=depEbi.getCount(dqm);
//		//��Ҫ���ҳ��ֵ
//		maxPageNum=(dataTotal+pageCount-1)/pageCount;
//		
//		
//		
//		//�������Բ�����Ϣ�������ƶ���Χ��ҳ���ָ����Χȡ��������չʾ
//		List<DepModel> depList=depEbi.getAll(dqm,pageNum,pageCount);
//		//ָ����Χ��request��ѡ������session(�ͻ��ˣ�������������й���)��
//		//application������Ӧ�õĹ������е���ֻҪ����������������Ṳ��
//		ActionContext.getContext().put("depList", depList);
//		return "list";
//		
//	}
////	//��ѯ�б��� ������Ĺ��ܺϲ���
////	public  String queryList(){
////		
////		//���ݲ�ѯ������ȡ���ݣ���ѯ������װ��dqm�����У�
////		List<DepModel> depList=depEbi.getAll(dqm);
////		//����ָ����Χ
////		ActionContext.getContext().put("deList", depList);
////		//��תҳ��
////		return "list";
////		
////	}
//	
//   //��ת������ҳ��
//	public  String input(){
//		if (dm.getUuid()!=null) {
//			dm=depEbi.get(dm.getUuid());
//		}
//		return "input";
//	}
//	//��ת���޸�ҳ��
////	public String input2(){
////		//��ѯ���ݣ�������չʾ
////		//��ȡָ�����޸ĵ�������Ϣ������ָ���ķ�Χ
////		dm=depEbi.get(dm.getUuid());
////		
////		//��תҳ��
////		return  "input";
////	}
//	//��ӹ���
//	public String save(){
//		//����ҳ�洫�ݵĲ����жϲ���ʱ��ӻ����޸ģ�����dm.uuid
//		if (dm.getUuid()==null) {
//			//��ӹ���
//			depEbi.save(dm);
//		} else {
//             //�޸�ҳ��
//			depEbi.update(dm);
//		}
//		//���ռ�ֵ���ݵ�ҵ��㣬��󱣴湦��
//		
//		return "toList";
//	}
//	
//	//ɾ������
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
	
	
	
	//��ת���б�ҳ��
	public String list(){
		setDataTotal(depEbi.getCount(dqm));
		List<DepModel> depList = depEbi.getAll(dqm,pageNum,pageCount);
		put("depList", depList);
		return LIST;
	}
	
	//��ת�����ҳ��
	public String input(){
		if(dm.getUuid()!=null){
			dm = depEbi.get(dm.getUuid());
		}
		return INPUT;
	}
	
	//��ӹ���
	public String save(){
		if(dm.getUuid() == null){
			depEbi.save(dm);
		}else{
			depEbi.update(dm);
		}
		return TO_LIST;
	}
	
	//ɾ������
	public String delete(){
		depEbi.delete(dm);
		return TO_LIST;
	}
	

}
