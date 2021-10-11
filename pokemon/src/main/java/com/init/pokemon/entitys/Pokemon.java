package com.init.pokemon.entitys;

public class Pokemon {
	
	private int codigo;
	private String nombre;
	private String type1;
	private String type2;
	private int total;
	private int hp;
	private int attack;
	private int defense;
	private int spattack;
	private int spdefense;
	private int speed;
	private int generation;
	private boolean legendary;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getSpattack() {
		return spattack;
	}
	public void setSpattack(int spattack) {
		this.spattack = spattack;
	}
	public int getSpdefense() {
		return spdefense;
	}
	public void setSpdefense(int spdefense) {
		this.spdefense = spdefense;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getGeneration() {
		return generation;
	}
	public void setGeneration(int generation) {
		this.generation = generation;
	}
	public boolean isLegendary() {
		return legendary;
	}
	public void setLegendary(boolean legendary) {
		this.legendary = legendary;
	}

}
