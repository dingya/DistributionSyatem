package com.xtmit.distribution.model;

import java.io.Serializable;

import com.xtmit.zxing.client.android.util.StringUtils;

 

/**
 * 领料单的基本信息mysql模型
 * 
 * @author xumin
 * 
 */
public class BasicInformation implements Serializable {
	private static final long serialVersionUID = 1L;
	private int companyID;

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	private int companyUserID;

	public int getCompanyUserID() {
		return companyUserID;
	}

	public void setCompanyUserID(int companyUserID) {
		this.companyUserID = companyUserID;
	}

	private String companyUserTrueName = "";

	public void setCompanyUserTrueName(String companyUserTrueName) {
		this.companyUserTrueName = companyUserTrueName;
	}

	public String getCompanyUserTrueName() {
		return StringUtils.nullStrToEmpty(companyUserTrueName);
	}

	private String companyUserName = "";

	public void setCompanyUserName(String companyUserName) {
		this.companyUserName = companyUserName;
	}

	public String getCompanyUserName() {
		return StringUtils.nullStrToEmpty(companyUserName);
	}

	private String materialRequisitionNum = "";

	public void setMaterialRequisitionNum(String materialRequisitionNum) {
		this.materialRequisitionNum = materialRequisitionNum;
	}

	public String getMaterialRequisitionNum() {
		return StringUtils.nullStrToEmpty(materialRequisitionNum);
	}

	private String materialRequisitionDate = "";

	public void setMaterialRequisitionDate(String materialRequisitionDate) {
		this.materialRequisitionDate = materialRequisitionDate;
	}

	public String getMaterialRequisitionDate() {
		String date = StringUtils.nullStrToEmpty(materialRequisitionDate);
		if (date != null && date.length() >= 10) {
			return date.substring(0, 10);
		}
		return "";
	}

	private int status = 10;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	private String companyEnShortName;

	public void setCompanyEnShortName(String companyEnShortName) {
		this.companyEnShortName = companyEnShortName;
	}

	public String getCompanyEnShortName() {
		return StringUtils.nullStrToEmpty(companyEnShortName);
	}

	private DepartmentModel departmentModel;

	public DepartmentModel getDepartmentModel() {
		return departmentModel;
	}

	public void setDepartmentModel(DepartmentModel departmentModel) {
		this.departmentModel = departmentModel;
	}

	// 领用人所属部门
	private DepartmentModel departmentModelInCompany;

	public DepartmentModel getDepartmentModelInCompany() {
		return departmentModelInCompany;
	}

	public void setDepartmentModelInCompany(
			DepartmentModel departmentModelInCompany) {
		this.departmentModelInCompany = departmentModelInCompany;
	}

	private SettlementAccountModel settlementAccountModel;

	public SettlementAccountModel getSettlementAccountModel() {
		return settlementAccountModel;
	}

	public void setSettlementAccountModel(
			SettlementAccountModel settlementAccountModel) {
		this.settlementAccountModel = settlementAccountModel;
	}

	private BusinessUnitModel businessUnitModel;

	public BusinessUnitModel getBusinessUnitModel() {
		return businessUnitModel;
	}

	public void setBusinessUnitModel(BusinessUnitModel businessUnitModel) {
		this.businessUnitModel = businessUnitModel;
	}

	private String remark = "";

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return StringUtils.nullStrToEmpty(remark);
	}
	
	/************************************************/
	// 数据库新增字段
	/**
	 * 审核人id
	 */
	private Integer AuditorID = 0;
	public Integer getAuditorID() {
		return AuditorID;
	}
	public void setAuditorID(Integer auditorID) {
		AuditorID = auditorID;
	}
	
	/**
	 * 审核人用户名
	 */
	private String AuditorName;
	public String getAuditorName() {
		return AuditorName;
	}
	public void setAuditorName(String auditorName) {
		AuditorName = auditorName;
	}
	
	/**
	 * 审核人姓名
	 */
	private String  AuditorTrueName;
	public String getAuditorTrueName() {
		return AuditorTrueName;
	}
	public void setAuditorTrueName(String auditorTrueName) {
		AuditorTrueName = auditorTrueName;
	}
	
	/**
	 * 应急领用标识:0不是应急领用；1是应急领用
	 * 客户端提交时候都是0
	 */
	private Integer EmergencyIde;
	public Integer getEmergencyIde() {
		return EmergencyIde;
	}
	public void setEmergencyIde(Integer emergencyIde) {
		EmergencyIde = emergencyIde;
	}
	
	/**
	 * 总价
	 */
	private Double TotalPrice;
	public Double getTotalPrice() {
		return TotalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		TotalPrice = totalPrice;
	}
	
	/************************************************/

}