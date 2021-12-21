package com.passgym.gymequip.vo;

public class GymEquip {
	private int ownerNo;
	private int equipNo;
	private int equipCount;
	
	public GymEquip() {}

	public GymEquip(int ownerNo, int equipNo, int equipCount) {
		super();
		this.ownerNo = ownerNo;
		this.equipNo = equipNo;
		this.equipCount = equipCount;
	}

	public int getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(int ownerNo) {
		this.ownerNo = ownerNo;
	}

	public int getEquipNo() {
		return equipNo;
	}

	public void setEquipNo(int equipNo) {
		this.equipNo = equipNo;
	}

	public int getEquipCount() {
		return equipCount;
	}

	public void setEquipCount(int equipCount) {
		this.equipCount = equipCount;
	}

	@Override
	public String toString() {
		return "GymEquip [ownerNo=" + ownerNo + ", equipNo=" + equipNo + ", equipCount=" + equipCount + "]";
	}
}
