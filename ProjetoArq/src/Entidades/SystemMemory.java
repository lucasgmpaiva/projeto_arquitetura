package Entidades;

public class SystemMemory {
	
private int[] memoria;
	
	public SystemMemory() {
		super();
		this.memoria = new int[200];
	}

	public SystemMemory (int tamanho) {
		this.memoria = new int[tamanho];
	}

	public SystemMemory (int[] memoria) {
		super();
		this.memoria = memoria;
	}

	public int[] getMemoria() {
		return memoria;
	}

	public void setMemoria(int[] memoria) {
		this.memoria = memoria;
	}
	
	public int getDado(int posicao) {
		return memoria[posicao];
	}

}
