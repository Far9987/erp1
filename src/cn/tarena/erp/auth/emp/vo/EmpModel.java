package cn.tarena.erp.auth.emp.vo;

public class EmpModel {//ģ��
	public static final String EMP_LOGIN_USER_OBJECT_NAME="loginEm";//���óɳ���
	//ע�Ṧ��
	private Long uuid;
	
	//�û���
	private String userName;
	
	//����
	private String pwd;
	
	//��ʵ����
	private  String name;

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
