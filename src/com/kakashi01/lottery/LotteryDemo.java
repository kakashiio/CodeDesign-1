package com.kakashi01.lottery;

import java.util.Arrays;

import com.kakashi01.common.KeyAndValue;
import com.kakashi01.lottery.domain.ConfigLottery;
import com.kakashi01.lottery.domain.ConfigLotteryItem;
import com.kakashi01.lottery.domain.ConfigLotteryItemLib;
import com.kakashi01.player.domain.Player;

public class LotteryDemo {

	private static final int[]			lotteryTypes	= new int[] {
																		ConfigLottery.SLIVER,
																		ConfigLottery.GOLD,
																		ConfigLottery.DIAMOND };
	private static final int[]			timesTpyes		= new int[] {
																		1,
																		10 };																																																																																																																																																																																																																															// 只能抽1次或10连抽
	private static final LotteryService	loggterService	= new LotteryService();
	static {
		// 这个static代码块中的代码实际上正式开发应该读取策划的配置表。为了演示方便在这里通过程序生成数据

		loggterService
				.addConfigLotteryItemLib(new ConfigLotteryItemLib(1, Arrays.asList(new ConfigLotteryItem(101, 2, 10),
						new ConfigLotteryItem(102, 2, 10), new ConfigLotteryItem(103, 2, 10))));

		loggterService
				.addConfigLotteryItemLib(new ConfigLotteryItemLib(2, Arrays.asList(new ConfigLotteryItem(201, 2, 10),
						new ConfigLotteryItem(202, 2, 10), new ConfigLotteryItem(203, 2, 10))));

		ConfigLottery configLottery = null;

		// ---------- 以下黃金抽1次 ----------
		configLottery = new ConfigLottery();
		configLottery.setLotteryType(ConfigLottery.GOLD);
		configLottery.setTimesType(1);
		configLottery.setCosts(new KeyAndValue[] {
													new KeyAndValue(Player.RESOURCE_GOLD, 120) });
		configLottery.setConfigLotteryItemLibModelIDs(new int[] {
																	1 });
		loggterService.addConfigLottery(configLottery);

		// ---------- 以上黃金抽1次 ----------

		// ---------- 以下黃金抽10次 ----------
		configLottery = new ConfigLottery();
		configLottery.setLotteryType(ConfigLottery.GOLD);
		configLottery.setTimesType(10);
		configLottery.setCosts(new KeyAndValue[] {
													new KeyAndValue(Player.RESOURCE_GOLD, 1000),
													new KeyAndValue(Player.RESOURCE_DIAMOND, 1) });
		configLottery.setConfigLotteryItemLibModelIDs(new int[] {
																	1,
																	1,
																	1,
																	1,
																	1,
																	1,
																	1,
																	1,
																	1,
																	2 });
		loggterService.addConfigLottery(configLottery);
		// ---------- 以上黃金抽10次 ----------

	}

	public static void main(String[] args) {
		Player player = newPlayer();
		System.out.println("Lottery once");
		loggterService.lottery(player, ConfigLottery.GOLD, 1);
		System.out.println();
		System.out.println("Lottery 10 times");
		loggterService.lottery(player, ConfigLottery.GOLD, 10);
	}

	private static Player newPlayer() {
		Player player = new Player();
		for (int resource : Player.ALL_RESOURCES) {
			player.setResource(resource, 100000);
		}
		return player;
	}
}
