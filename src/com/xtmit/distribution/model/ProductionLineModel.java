package com.xtmit.distribution.model;

import java.io.Serializable;

import com.xtmit.zxing.client.android.constans.Constant;

public class ProductionLineModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**��ϵ Id*/
	private int MPLRID;
	
	/**������ˮ��*/
	private String RunningNumber;
	
	/**����Id*/
	private int ProductionLineID;
	
	/**�ֿ� ��ά����*/
	private String STQRCode;
	
	/**���� ��ά����*/
	private String PLQRCode;
	
	/**��˾ Id*/
	private int CompanyID;
	
	/**��������*/
	private String ProductionLineName;
	
	/**���߱��*/
	private int ProductionLineNum;
	
	/**����״̬*/
	private int ProductionLineStatus;
	
	/**��ע*/
	private String Remark;
	
	/**Ѷ�Ʊ��*/
	private String MitNum;
	
	/**�������ϱ��*/
	private String MaterialName;
	

	public String getBomNum() {
		return MitNum;
	}

	public void setBomNum(String bomNum) {
		MitNum = bomNum;
	}

	public int getMPLRID() {
		return MPLRID;
	}

	public void setMPLRID(int mPLRID) {
		MPLRID = mPLRID;
	}

	public String getRunningNumber() {
		return RunningNumber;
	}

	public void setRunningNumber(String runningNumber) {
		RunningNumber = runningNumber;
	}

	public int getProductionLineID() {
		return ProductionLineID;
	}

	public void setProductionLineID(int productionLineID) {
		ProductionLineID = productionLineID;
	}

	public String getSTQRCode() {
		return STQRCode;
	}

	public void setSTQRCode(String sTQRCode) {
		STQRCode = sTQRCode;
	}

	public String getPLQRCode() {
		return PLQRCode;
	}

	public void setPLQRCode(String pLQRCode) {
		PLQRCode = pLQRCode;
	}

	public int getCompanyID() {
		return CompanyID;
	}

	public void setCompanyID(int companyID) {
		CompanyID = companyID;
	}

	public String getProductionLineName() {
		return ProductionLineName;
	}

	public void setProductionLineName(String productionLineName) {
		ProductionLineName = productionLineName;
	}

	public int getProductionLineNum() {
		return ProductionLineNum;
	}

	public void setProductionLineNum(int productionLineNum) {
		ProductionLineNum = productionLineNum;
	}

	public int getProductionLineStatus() {
		return ProductionLineStatus;
	}

	public void setProductionLineStatus(int productionLineStatus) {
		ProductionLineStatus = productionLineStatus;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	@Override
	public String toString() {
		// TODO  
		return Constant.gson.toJson(this);
	}

	public String getMaterialName() {
		return MaterialName;
	}

	public void setMaterialName(String materialName) {
		MaterialName = materialName;
	}
}
