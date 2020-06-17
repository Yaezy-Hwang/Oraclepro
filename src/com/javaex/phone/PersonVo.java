package com.javaex.phone;

public class PersonVo {

	//필드
	private int personId;
	private String name;
	private String hp;
	private String company;

	//생성자
	public PersonVo() {}

	public PersonVo(int personId, String name, String hp, String company) {
		this.personId = personId;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	//getter
	public int getPersonId() {
		return personId;
	}

	public String getName() {
		return name;
	}

	public String getHp() {
		return hp;
	}

	public String getCompany() {
		return company;
	}

	
}
