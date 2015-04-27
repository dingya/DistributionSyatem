package com.xtmit.distribution.model;

import com.xtmit.zxing.client.android.util.StringUtils;

 
/**
 * �����˻�ģ��
 * @author xumin
 *
 */
public class SettlementAccountModel {
	private String settlementNumber = ""; // ������
	public String getSettlementNumber() {
		return StringUtils.nullStrToEmpty(settlementNumber);
	}
	public void setSettlementNumber(String settlementNumber) {
		this.settlementNumber = settlementNumber;
	}

	private String settlementAccountName = ""; // �����˻�������
	public String getSettlementAccountName() {
		return StringUtils.nullStrToEmpty(settlementAccountName);
	}
	public void setSettlementAccountName(String settlementAccountName) {
		this.settlementAccountName = settlementAccountName;
	}
	
	private String settlementAccountEnName = ""; // �����˻�Ӣ����
	public String getSettlementAccountEnName() {
		return StringUtils.nullStrToEmpty(settlementAccountEnName);
	}
	public void setSettlementAccountEnName(String settlementAccountEnName) {
		this.settlementAccountEnName = settlementAccountEnName;
	}
	
	public Boolean isHasUpLevel = false;// �Ƿ���ʾ���ϰ�ť
	public Boolean isHasNext = false;// �Ƿ�����һ��
	public Boolean isLastLevel = false;// �Ƿ������һ��
}
