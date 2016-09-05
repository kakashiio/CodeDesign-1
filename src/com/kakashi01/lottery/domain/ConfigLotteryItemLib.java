package com.kakashi01.lottery.domain;

import java.util.List;
import java.util.Random;

public class ConfigLotteryItemLib {

	private int						modelID;
	private List<ConfigLotteryItem>	items;																				// 掉落的物品
	private int						totalWeight;

	public ConfigLotteryItemLib(int modelID, List<ConfigLotteryItem> items) {
		super();
		this.modelID = modelID;
		this.items = items;
		for (ConfigLotteryItem item : items) {
			totalWeight += item.getWeight();
		}
	}

	public int getModelID() {
		return modelID;
	}

	public ConfigLotteryItem getRandomItem(Random rand) {
		int randNum = rand.nextInt(totalWeight);
		for (ConfigLotteryItem item : items) {
			if (randNum < item.getWeight()) {
				System.out.println("Drop item " + item.getModelID() + ", " + item.getNum());
				return item;
			}
			randNum -= item.getWeight();
		}
		return null;
	}

}