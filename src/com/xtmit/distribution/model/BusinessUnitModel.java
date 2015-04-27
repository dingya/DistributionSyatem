package com.xtmit.distribution.model;

import com.xtmit.zxing.client.android.util.StringUtils;

 
public class BusinessUnitModel {
	private int businessUnitID = 0;
	public void setBusinessUnitID(int businessUnitID){
		this.businessUnitID = businessUnitID;
	}
	public int getBusinessUnitID(){
		return businessUnitID;
	}
	
	private int companyID = 0;
	public void setCompanyID(int companyID){
		this.companyID = companyID;
	}
	public int getCompanyID(){
		return companyID;
	}
	
	private String businessUnitNumber;
	public void setBusinessUnitNumber(String businessUnitNumber){
		this.businessUnitNumber = businessUnitNumber;
	}
	public String getBusinessUnitNumber(){
		return StringUtils.nullStrToEmpty(businessUnitNumber);
	}
	
	private String businessUnitShortName;
	public void setBusinessUnitShortName(String businessUnitShortName){
		this.businessUnitShortName = businessUnitShortName;
	}
	public String getBusinessUnitShortName(){
		return StringUtils.nullStrToEmpty(businessUnitShortName);
	}
	
	
	private String businessUnitName;
	public void setBusinessUnitName(String businessUnitName){
		this.businessUnitName = businessUnitName;
	}
	public String getBusinessUnitName(){
		return StringUtils.nullStrToEmpty(businessUnitName);
	}
	
	private String remark;
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return StringUtils.nullStrToEmpty(remark);
	}
	
	public Boolean isHasUpLevel = false;// �Ƿ���ʾ���ϰ�ť
	public Boolean isHasNext = false;// �Ƿ�����һ��
	public Boolean isLastLevel = false;// �Ƿ������һ��
}
