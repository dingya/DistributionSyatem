package com.xtmit.distribution.model;

import com.xtmit.zxing.client.android.util.StringUtils;

 
public class DepartmentModel {
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
	
	private String departmentNumber;
	public void setDepartmentNumber(String departmentNumber){
		this.departmentNumber = departmentNumber;
	}
	public String getDepartmentNumber(){
		return StringUtils.nullStrToEmpty(departmentNumber);
	}
	
	private String departmentName;
	public void setDepartmentName(String departmentName){
		this.departmentName = departmentName;
	}
	public String getDepartmentName(){
		return StringUtils.nullStrToEmpty(departmentName);
	}
	
	
	private String departmentEnName;
	public void setDepartmentEnName(String departmentEnName){
		this.departmentEnName = departmentEnName;
	}
	public String getDepartmentEnName(){
		return StringUtils.nullStrToEmpty(departmentEnName);
	}
}
