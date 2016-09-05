package com.kakashi01.lottery.domain;

import java.util.List;

import com.kakashi01.common.KeyAndValue;

public class ConfigLottery {

	public static final int			SLIVER	= 1;
	public static final int			GOLD	= 2;
	public static final int			DIAMOND	= 3;

	private int						lotteryType;
	private int						timesType;
	private KeyAndValue[]			costs;								// 抽奖需要消耗的资源

	private List<ConfigLotteryItem>	items;								// 掉落的物品

	public int getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(int lotteryType) {
		this.lotteryType = lotteryType;
	}

	public int getTimesType() {
		return timesType;
	}

	public void setTimesType(int timesType) {
		this.timesType = timesType;
	}

	public List<ConfigLotteryItem> getItems() {
		return items;
	}

	public void setItems(List<ConfigLotteryItem> items) {
		this.items = items;
	}

	public KeyAndValue[] getCosts() {
		return costs;
	}

	public void setCosts(KeyAndValue[] costs) {
		this.costs = costs;
	}
}