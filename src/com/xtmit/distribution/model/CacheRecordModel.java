package com.xtmit.distribution.model;

import java.io.Serializable;

import com.hrw.framework.ahibernate.annotation.Column;
import com.hrw.framework.ahibernate.annotation.Id;
import com.hrw.framework.ahibernate.annotation.Table;
import com.xtmit.zxing.client.android.constans.Constant;


@Table(name=Constant.tableName_OrderList)
public class CacheRecordModel implements Serializable {

	public CacheRecordModel() {
 	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
 	@Id(autoGenerate=true)
	private Long id;
	
	public Long getId() {
		return id;
	}
	

	public String getProductionLineName() {
		return ProductionLineName;
	}

	public void setProductionLineName(String productionLineName) {
		ProductionLineName = productionLineName;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name="MaterialResquitionNum")
	private  String MaterialResquitionNum = "";
	/**产线名称*/
	
	@Column(name="ProductionLineName")
	private   String ProductionLineName = "ProductionName";
	
	
	/**id*/
	@Column(name="ProductionId")
	private   Long ProductionId ;
	/***/
	@Column(name="MaterialName")
	private   String MaterialName = "MaterialName";
	
	
	@Column(name="RunningNumber")
	private   String RunningNumber;
	
	
	@Column(name="ProductionLineStatus")
	private   Long ProductionLineStatus ;
	
	@Column(name="userTrueName")
	private   String userTrueName;
	
	@Column(name="userID")
	private   Long userID ;
	
	@Column(name="userName")
	private   String userName;
	
	@Column(name="time")
	private   String time ;
	
	/** {@link Constant.orderRecord} 下料记录  */
	
	@Column(name="recordType")
	private Long recordType;
	
	
	//出库者 ：我 处理了 哪一个下料人 的下单
	//下单这 ：谁 出库了我的下料
	@Column(name="TrueNameHandler")
	private  String TrueNameHandler;

	public String getMaterialResquitionNum() {
		return MaterialResquitionNum;
	}

	public void setMaterialResquitionNum(String materialResquitionNum) {
		MaterialResquitionNum = materialResquitionNum;
	}

	public String getProductionName() {
		return ProductionLineName;
	}

	public void setProductionName(String productionName) {
		ProductionLineName = productionName;
	}

	public Long getProductionId() {
		return ProductionId;
	}

	public void setProductionId(Long productionId) {
		ProductionId = productionId;
	}

	public String getMaterialName() {
		return MaterialName;
	}

	public void setMaterialName(String materialName) {
		MaterialName = materialName;
	}

	public String getRunningNumber() {
		return RunningNumber;
	}

	public void setRunningNumber(String runningNumber) {
		RunningNumber = runningNumber;
	}

	public Long getProductionLineStatus() {
		return ProductionLineStatus;
	}

	public void setProductionLineStatus(Long productionLineStatus) {
		ProductionLineStatus = productionLineStatus;
	}

	public String getUserTrueName() {
		return userTrueName;
	}

	public void setUserTrueName(String userTrueName) {
		this.userTrueName = userTrueName;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public  String getTruenamehandler() {
		return getTrueNameHandler();
	}
	
	

	public CacheRecordModel(String materialResquitionNum,
			String productionName, 
			Long productionId, 
			String materialName,
			String runningNumber,
			Long productionLineStatus,
			String userTrueName, 
			Long userID, 
			String userName, 
			String time,
			Long type) {
		super();
		MaterialResquitionNum = materialResquitionNum;
		ProductionLineName = productionName;
		ProductionId = productionId;
		MaterialName = materialName;
		RunningNumber = runningNumber;
		ProductionLineStatus = productionLineStatus;
		this.userTrueName = userTrueName;
		this.userID = userID;
		this.userName = userName;
		this.time = time;
		recordType= type;
	}

	public Long getRecordType() {
		return recordType;
	}

	public void setRecordType(Long recordType) {
		this.recordType = recordType;
	}

	public  String getTrueNameHandler() {
		return TrueNameHandler;
	}

	public void setTrueNameHandler(String trueNameHandler) {
		TrueNameHandler = trueNameHandler;
	}
	
}
