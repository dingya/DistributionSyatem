package com.xtmit.distribution.model;

import java.util.List;


/**
 * 领料单mysql模型
 * @author xumin
 *
 */
public class MaterialRequisitionModel {
	private BasicInformation basicInformation;

	public void setBasicInformation(BasicInformation basicInformation) {
		this.basicInformation = basicInformation;
	}

	public BasicInformation getBasicInformation() {
		return basicInformation;
	}

	public List<BasicGoods> basicGoods;

	public void setBasicGoods(List<BasicGoods> basicGoods) {
		this.basicGoods = basicGoods;
	}

	public List<BasicGoods> getBasicGoods() {
		return basicGoods;
	}
}
