package com.xtmit.distribution.model;

import com.xtmit.zxing.client.android.util.StringUtils;

 
/**
 * 企业用户信息mysql模型
 * @author xumin
 *
 */
public class CompanyUserModel {
	private String companyUserName;
	public void setCompanyUserName(String companyUserName){
		this.companyUserName = companyUserName;
	}
	public String getCompanyUserName(){
		return StringUtils.nullStrToEmpty(companyUserName);
	}
	
	private String companyUserTrueName;
	public void setCompanyUserTrueName(String companyUserTrueName){
		this.companyUserTrueName = companyUserTrueName;
	}
	public String getCompanyUserTrueName(){
		return StringUtils.nullStrToEmpty(companyUserTrueName);
	}
	
	private int companyUserID = 0;
	public void setCompanyUserID(int companyUserID){
		this.companyUserID = companyUserID;
	}
	public int getCompanyUserID(){
		return companyUserID;
	}
	
	private String companyUserPassword;
	public void setCompanyUserPassword(String companyUserPassword){
		this.companyUserPassword = companyUserPassword;
	}
	public String getCompanyUserPassword(){
		return StringUtils.nullStrToEmpty(companyUserPassword);
	}
	
	
	// 5是企业领料人
	private int companyUserType = 0;
	public void setCompanyUserType(int companyUserType){
		this.companyUserType = companyUserType;
	}
	public int getCompanyUserType(){
		return companyUserType;
	}
	
	private int companyID = 0;
	public void setCompanyID(int companyID){
		this.companyID = companyID;
	}
	public int getCompanyID(){
		return companyID;
	}
	
	private int departmentID = 0;
	public void setDepartmentID(int departmentID){
		this.departmentID = departmentID;
	}
	public int getDepartmentID(){
		return departmentID;
	}
	private String CompanyUserDCPassword;
	public String getCompanyUserDCPassword() {
		return CompanyUserDCPassword;
	}
	public void setCompanyUserDCPassword(String companyUserDCPassword) {
		CompanyUserDCPassword = companyUserDCPassword;
	}
}
