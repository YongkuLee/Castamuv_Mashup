package gr.brid.castamuv.core;

import java.util.UUID;

public class IdGenerator {
	public static long id() {
		return UUID.randomUUID().getMostSignificantBits();
	}
}
