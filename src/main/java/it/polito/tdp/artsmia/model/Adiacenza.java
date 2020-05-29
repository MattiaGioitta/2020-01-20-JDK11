package it.polito.tdp.artsmia.model;

public class Adiacenza implements Comparable<Adiacenza> {
	private Integer a1;
	private Integer a2;
	private Integer peso;
	/**
	 * @param a1
	 * @param a2
	 * @param peso
	 */
	public Adiacenza(Integer a1, Integer a2, Integer peso) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.peso = peso;
	}
	/**
	 * @return the a1
	 */
	public Integer getA1() {
		return a1;
	}
	/**
	 * @param a1 the a1 to set
	 */
	public void setA1(Integer a1) {
		this.a1 = a1;
	}
	/**
	 * @return the a2
	 */
	public Integer getA2() {
		return a2;
	}
	/**
	 * @param a2 the a2 to set
	 */
	public void setA2(Integer a2) {
		this.a2 = a2;
	}
	/**
	 * @return the peso
	 */
	public Integer getPeso() {
		return peso;
	}
	/**
	 * @param peso the peso to set
	 */
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(Adiacenza other) {
		return -(this.peso-other.getPeso());
	}
	
	

}
