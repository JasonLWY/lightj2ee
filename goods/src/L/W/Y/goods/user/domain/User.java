package L.W.Y.goods.user.domain;
/*
 * 
 * �û�ģ��ʵ����
 */
/*
 * ����λ�� 
 * 1��t_user���װ��user������
 * 2����ģ������б�
 * 
 * 
 * 
 */

public class User {
	//��Ӧ���ݿ��
	private String uid;//����
	private String loginname;//��¼��
	private String email;//��¼����
	private String phone;//��¼�񻨺���
	private String loginpass;//��¼����
	private boolean status;//״̬ true ��ʾ�Ѿ��������δ����
	private String activationCode;//������ ������Ψһֵ ��
	
	//ע���
	private String reloginpass;//ȷ������
	private String verifycode;//��֤��
	//�޸�����
	private String newloginpass;//������
	public String getReloginpass() {
		return reloginpass;
	}
	public void setReloginpass(String reloginpass) {
		this.reloginpass = reloginpass;
	}
	public String getVerifycode() {
		return verifycode;
	}
	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	public String getNewloginpass() {
		return newloginpass;
	}
	public void setNewloginpass(String newloginpass) {
		this.newloginpass = newloginpass;
	}
	
	
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getLoginpass() {
		return loginpass;
	}
	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", loginname=" + loginname + ", email="
				+ email + ", phone=" + phone + " , loginpass="
				+ loginpass + ", status=" + status + ", activationCode="
				+ activationCode + ", reloginpass=" + reloginpass
				+ ", verifycode=" + verifycode + ", newloginpass="
				+ newloginpass + "]";
	}
	
}
