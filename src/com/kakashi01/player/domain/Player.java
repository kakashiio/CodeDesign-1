package com.kakashi01.player.domain;

import java.util.HashMap;
import java.util.Map;

public class Player {

	public static final int						RESOURCE_SLIVER		= 1;
	public static final int						RESOURCE_GOLD		= 2;
	public static final int						RESOURCE_DIAMOND	= 3;
	public static final int						RESOURCE_POWER		= 4;

	public static final int[]					ALL_RESOURCES		= {
																		RESOURCE_SLIVER,
																		RESOURCE_GOLD,
																		RESOURCE_DIAMOND,
																		RESOURCE_POWER };

	private static final Map<Integer, Integer>	RESOURCE_MAX		= new HashMap<>();
	static {
		for (int resource : ALL_RESOURCES) {
			RESOURCE_MAX.put(resource, Integer.MAX_VALUE);
		}
	}

	private int						id;
	private Map<Integer, Integer>	resources	= new HashMap<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getResource(int resource) {
		Integer r = resources.get(resource);
		if (r == null) {
			return 0;
		}
		return r.intValue();
	}

	public void setResource(int resource, int value) {
		resources.put(resource, value);
	}

	public boolean alterResource(int resource, int alter) {
		int current = getResource(resource);
		int old = current;
		int min = 0;
		int max = getMaxResource(resource);
		if (alter > 0) {
			current += alter;
			if (current < min || current > max) {
				current = max;
			}
		} else if (alter < 0) {
			if (current >= -alter) {
				current += alter;
			}
			if (current < min) {
				current = min;
			}
		}
		setResource(resource, current);
		return old != current;
	}

	public static int getMaxResource(int resource) {
		Integer r = RESOURCE_MAX.get(resource);
		if (r == null) {
			return 0;
		}
		return r.intValue();
	}
}