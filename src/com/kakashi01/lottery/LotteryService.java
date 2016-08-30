package com.kakashi01.lottery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.kakashi01.lottery.domain.ConfigLottery;
import com.kakashi01.lottery.domain.ConfigLotteryItem;
import com.kakashi01.player.domain.Player;

public class LotteryService {

	private final Map<Integer, Map<Integer, ConfigLottery>> lotteryMap = new HashMap<>();

	public void addConfigLottery(ConfigLottery configLottery) {
		Map<Integer, ConfigLottery> map = lotteryMap.get(configLottery.getLotteryType());
		if (map == null) {
			map = new HashMap<>();
			lotteryMap.put(configLottery.getLotteryType(), map);
		}
		map.put(configLottery.getTimesType(), configLottery);
	}

	public ConfigLottery getConfigLottery(int lotteryType, int timesType) {
		Map<Integer, ConfigLottery> map = lotteryMap.get(lotteryType);
		if (map == null) {
			return null;
		}
		return map.get(timesType);
	}

	/**
	 * @param player
	 *            进行抽奖的玩家
	 * @param lotteryType
	 *            宝箱类型
	 * @param timesType
	 *            次数类型
	 */
	public void lottery(Player player, int lotteryType, int timesType) {
		ConfigLottery configLottery = getConfigLottery(lotteryType, timesType);
		if (configLottery == null) {
			System.err.println("Can not found such ConfigLottery : " + lotteryType + ", " + timesType);
			return;
		}
		if (tryCostResource(player, configLottery)) {
			dropItem(configLottery);
		} else {
			System.err.println("Not enough resource for lottery : " + lotteryType + ", " + timesType);
			return;
		}
	}

	private void dropItem(ConfigLottery configLottery) {
		Random rand = new Random();
		for (int i = 0; i < configLottery.getTimesType(); i++) {
			List<ConfigLotteryItem> items = configLottery.getItems();
			int totalWeight = 0;
			for (ConfigLotteryItem item : items) {
				totalWeight += item.getWeight();
			}

			int randNum = rand.nextInt(totalWeight);
			for (ConfigLotteryItem item : items) {
				if (randNum < item.getWeight()) {
					System.out.println("Drop item " + item.getModelID() + ", " + item.getNum());
					break;
				}
				randNum -= item.getWeight();
			}
		}
	}

	private boolean tryCostResource(Player player, ConfigLottery configLottery) {
		return player.alterResource(configLottery.getCostType(), -configLottery.getCost());
	}
}