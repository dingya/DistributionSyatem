package com.xtmit.distribution.model;

public enum CommandCode{
	Login_IncorrectedPsw("�Բ���,���벻��ȷ!"),
	Login_MaterialUser("�Բ���,��������ҵ������!"),
	Login_OccupiedUser("�Բ���,���û����������û���ͻ!"),
	Login_NonexistentUser("�Բ���,�����ڸ��û�!"),
	Login_Logined("�û��ѵ�¼�����豸���������¼!"),
	Login_ValidationFailed("�û���,������֤ʧ�ܣ�"),
	Login_NonwhiteList("���ڰ������ڣ��޷���¼��"),
	Submit_FAILED_NUM("�ύʧ���ύ����ʵ�ʿ����������"),
	Submit_FAILED_DELETE("�ύ�Ļ����Ѿ���ɾ����");
	private String name;
	CommandCode(String name){
		this.name = name;
	}
	public String getMsg(){
		return this.name;
	}
}
