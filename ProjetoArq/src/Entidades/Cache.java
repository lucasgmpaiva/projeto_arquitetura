package Entidades;

public class Cache {
	
	private int[] memoria;
	private int ultimoAtt = 0;
	
	public Cache(int tamanho) {
		this.memoria = new int[tamanho];
	}

	public Cache(int[] memoria) {
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
		ultimoAtt = posicao+1;
		return memoria[posicao];
	}
	public int getLast() {
		return memoria[ultimoAtt-1];
	}
	public void updateLast(int novo) {
		memoria[ultimoAtt-1] = novo;
	}
	public void adicionarDado(int dado) {
		if(ultimoAtt == memoria.length) {
			ultimoAtt = 0;
		}
		memoria[ultimoAtt] = dado;
		ultimoAtt++;
	}
	
}
