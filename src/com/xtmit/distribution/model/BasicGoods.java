package com.xtmit.distribution.model;

import java.util.List;

import com.xtmit.zxing.client.android.util.StringUtils;

 

/**
 * ���ϵ�������mysqlģ��
 * @author xumin
 *
 */
public class BasicGoods {
	private int companyMateriaID;

	public int getCompanyMateriaID() {
		return companyMateriaID;
	}

	public void setCompanyMateriaID(int companyMateriaID) {
		this.companyMateriaID = companyMateriaID;
	}

	private String materialRequisitionNum = "";

	public void setMaterialRequisitionNum(String materialRequisitionNum) {
		this.materialRequisitionNum = materialRequisitionNum;
	}

	public String getMaterialRequisitionNum() {
		return StringUtils.nullStrToEmpty(materialRequisitionNum);
	}

	private int mRQuantity;

	public int getMRQuantity() {
		return mRQuantity;
	}

	public void setMRQuantity(int mRQuantity) {
		this.mRQuantity = mRQuantity;
	}

	private String remark = "";

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return StringUtils.nullStrToEmpty(remark);
	}
	
	private String companyMateriaName;
	public void setCompanyMateriaName(String companyMateriaName){
		this.companyMateriaName = companyMateriaName;
	}
	public String getCompanyMateriaName(){
		return StringUtils.nullStrToEmpty(companyMateriaName);
	}
	
	private String brand;
	public void setBrand(String brand){
		this.brand = brand;
	}
	public String getBrand(){
		return StringUtils.nullStrToEmpty(brand);
	}
	
	
	private String model;
	public void setModel(String model){
		this.model = model;
	}
	public String getModel(){
		return StringUtils.nullStrToEmpty(model);
	}
	
	private String unit;
	public void setUnit(String unit){
		this.unit = unit;
	}
	public String getUnit(){
		return StringUtils.nullStrToEmpty(unit);
	}
	
	private String companyMateriaNameEn = "";
	public void setCompanyMateriaNameEn(String companyMateriaNameEn) {
		this.companyMateriaNameEn = companyMateriaNameEn;
	}
	public String getCompanyMateriaNameEn() {
		return StringUtils.nullStrToEmpty(companyMateriaNameEn);
	}
	
	// Ʒ��Ӣ��
		private String brandEn = "";

		public void setBrandEn(String brandEN) {
			this.brandEn = brandEN;
		}

		public String getBrandEn() {
			return StringUtils.nullStrToEmpty(brandEn);
		}

		// �ͺ�Ӣ��
		private String modelEn = "";

		public void setModelEn(String modelEN) {
			this.modelEn = modelEN;
		}

		public String getModelEn() {
			return StringUtils.nullStrToEmpty(modelEn);
		}

		// ��λӢ��
		private String unitEn = "";

		public void setUnitEn(String unitEN) {
			this.unitEn = unitEN;
		}

		public String getUnitEn() {
			return StringUtils.nullStrToEmpty(unitEn);
		}

		private String specificationsEn;

		public void setSpecificationsEn(String specificationsEN) {
			this.specificationsEn = specificationsEN;
		}

		public String getSpecificationsEn() {
			return StringUtils.nullStrToEmpty(specificationsEn);
		}
	
	private int logicCompanyStock = 0;
	public void setLogicCompanyStock(int logicCompanyStock){
		this.logicCompanyStock = logicCompanyStock;
	}
	public int getLogicCompanyStock(){
		return logicCompanyStock;
	}
	
	private Double unitPrice = 0.00;
	public void setUnitPrice(Double unitPrice){
		this.unitPrice = unitPrice;
	}
	public Double getUnitPrice(){
		return unitPrice;
	}
	
	private String companyMateriaNum;
	public void setCompanyMateriaNum(String companyMateriaNum){
		this.companyMateriaNum = companyMateriaNum;
	}
	public String getCompanyMateriaNum(){
		return StringUtils.nullStrToEmpty(companyMateriaNum);
	}
	
	private String specifications;
	public void setSpecifications(String specifications){
		this.specifications = specifications;
	}
	public String getSpecifications(){
		return StringUtils.nullStrToEmpty(specifications);
	}
	
	/**
	 * ������ˮ��
	 */
	private String runningNumber;
	public void setRunningNumber(String runningNumber) {
		this.runningNumber = runningNumber;
	}
	public String getRunningNumber() {
		return StringUtils.nullStrToEmpty(runningNumber);
	}

	
	/**
	 * ��������:0 ����ͨ�������ͣ�1 ������������ͣ�
	 */
	private int materialType;
	public void setMaterialType(int materialType) {
		this.materialType = materialType;
	}
	public int getMaterialType() {
		return materialType;
	}
	
	/**
	 * �������������ˮ��
	 */
	private String MGRunningNumber;
	public void setMGRunningNumber(String MGRunningNumber) {
		this.MGRunningNumber = MGRunningNumber;
	}
	public String getMGRunningNumber() {
		return MGRunningNumber;
	}
	
	/**
	 * ��������
	 */
	private float quantity;
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public float getQuantity() {
		return quantity;
	}
	
	/**
	 * ������ϵ���������
	 */
	private List<GoodsModel> childGoodsmodels;
	public void setChildGoodsmodels(List<GoodsModel> childGoodsmodels) {
		this.childGoodsmodels = childGoodsmodels;
	}
	public List<GoodsModel> getChildGoodsmodels() {
		return childGoodsmodels;
	}
}