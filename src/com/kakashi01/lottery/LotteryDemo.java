package com.kakashi01.lottery;

import java.util.LinkedList;
import java.util.List;

import com.kakashi01.common.KeyAndValue;
import com.kakashi01.lottery.domain.ConfigLottery;
import com.kakashi01.lottery.domain.ConfigLotteryItem;
import com.kakashi01.player.domain.Player;

public class LotteryDemo {

	private static final int[]			lotteryTypes	= new int[] {
																		ConfigLottery.SLIVER,
																		ConfigLottery.GOLD,
																		ConfigLottery.DIAMOND };
	private static final int[]			timesTpyes		= new int[] {
																		1,
																		10 };																																																																																																		// 只能抽1次或10连抽
	private static final LotteryService	loggterService	= new LotteryService();
	static {
		// 这个static代码块中的代码实际上正式开发应该读取策划的配置表。为了演示方便在这里通过程序生成数据
		for (int lotteryType : lotteryTypes) {
			for (int timesType : timesTpyes) {
				ConfigLottery configLottery = new ConfigLottery();
				configLottery.setLotteryType(lotteryType);
				configLottery.setTimesType(timesType);
				configLottery.setCosts(new KeyAndValue[] {
															new KeyAndValue(Player.RESOURCE_GOLD, timesType * 1000),
															new KeyAndValue(Player.RESOURCE_DIAMOND, timesType * 1) });
				List<ConfigLotteryItem> items = new LinkedList<>();
				items.add(new ConfigLotteryItem(1000 * lotteryType, 1, 10));
				items.add(new ConfigLotteryItem(1001 * lotteryType, 2, 20));
				items.add(new ConfigLotteryItem(1002 * lotteryType, 3, 30));
				configLottery.setItems(items);
				loggterService.addConfigLottery(configLottery);
			}
		}
	}

	public static void main(String[] args) {
		Player player = null;
		for (int lotteryType : lotteryTypes) {
			for (int timesType : timesTpyes) {
				System.out.println("Lottery#" + lotteryType + "#" + timesType);
				for (int i = 0; i < 10; i++) {
					player = newPlayer();
					System.out.println("-- " + i);
					loggterService.lottery(player, lotteryType, timesType);
				}
			}
		}
	}

	private static Player newPlayer() {
		Player player = new Player();
		for (int resource : Player.ALL_RESOURCES) {
			player.setResource(resource, 100000);
		}
		return player;
	}
}
