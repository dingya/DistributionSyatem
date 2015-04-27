package com.xtmit.distribution.model;

import com.xtmit.zxing.client.android.util.StringUtils;

 
/**
 * 结算账户模型
 * @author xumin
 *
 */
public class SettlementAccountModel {
	private String settlementNumber = ""; // 结算编号
	public String getSettlementNumber() {
		return StringUtils.nullStrToEmpty(settlementNumber);
	}
	public void setSettlementNumber(String settlementNumber) {
		this.settlementNumber = settlementNumber;
	}

	private String settlementAccountName = ""; // 结算账户中文名
	public String getSettlementAccountName() {
		return StringUtils.nullStrToEmpty(settlementAccountName);
	}
	public void setSettlementAccountName(String settlementAccountName) {
		this.settlementAccountName = settlementAccountName;
	}
	
	private String settlementAccountEnName = ""; // 结算账户英文名
	public String getSettlementAccountEnName() {
		return StringUtils.nullStrToEmpty(settlementAccountEnName);
	}
	public void setSettlementAccountEnName(String settlementAccountEnName) {
		this.settlementAccountEnName = settlementAccountEnName;
	}
	
	public Boolean isHasUpLevel = false;// 是否显示向上按钮
	public Boolean isHasNext = false;// 是否有下一级
	public Boolean isLastLevel = false;// 是否是最后一级
}
