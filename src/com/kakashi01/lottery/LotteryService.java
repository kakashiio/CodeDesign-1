package com.kakashi01.lottery;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.kakashi01.lottery.domain.ConfigLottery;
import com.kakashi01.lottery.domain.ConfigLotteryItemLib;
import com.kakashi01.player.domain.Player;

public class LotteryService {

	private final Map<Integer, Map<Integer, ConfigLottery>>	lotteryMap			= new HashMap<>();
	private final Map<Integer, ConfigLotteryItemLib>		lotteryItemLibMap	= new HashMap<>();

	public void addConfigLotteryItemLib(ConfigLotteryItemLib configLotteryItemLib) {
		lotteryItemLibMap.put(configLotteryItemLib.getModelID(), configLotteryItemLib);
	}

	private ConfigLotteryItemLib getConfigLotteryItemLib(int modelID) {
		return lotteryItemLibMap.get(modelID);
	}

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
		int[] configLotteryItemLibModelIDs = configLottery.getConfigLotteryItemLibModelIDs();
		for (int configLotteryItemLibModelID : configLotteryItemLibModelIDs) {
			ConfigLotteryItemLib configLotteryItemLib = getConfigLotteryItemLib(configLotteryItemLibModelID);
			configLotteryItemLib.getRandomItem(rand);
		}
	}

	private boolean tryCostResource(Player player, ConfigLottery configLottery) {
		return player.costResources(configLottery.getCosts());
	}
}