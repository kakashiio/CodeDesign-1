package com.kakashi01.lottery.domain;

public class ConfigLotteryItem {

	private int	modelID;	// 物品模型ID
	private int	num;				// 物品数量
	private int	weight;				// 权重

	public ConfigLotteryItem(int modelID, int num, int weight) {
		super();
		this.modelID = modelID;
		this.num = num;
		this.weight = weight;
	}

	public int getModelID() {
		return modelID;
	}

	public void setModelID(int modelID) {
		this.modelID = modelID;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
