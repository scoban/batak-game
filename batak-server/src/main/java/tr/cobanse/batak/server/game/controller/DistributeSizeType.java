package tr.cobanse.batak.server.game.controller;

public enum DistributeSizeType {
	ONE(1), TWO(2), THREE(3), FOUR(4);
	private int length;
	private DistributeSizeType(int length) {
		this.length = length;
	}
	
	public int getLength() {
		return length;
	}
}
