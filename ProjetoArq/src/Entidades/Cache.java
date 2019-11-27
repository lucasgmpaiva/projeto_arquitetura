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
		return memoria[posicao];
	}
	
	public void adicionarDado(int dado) {
		if(ultimoAtt == memoria.length) {
			ultimoAtt = 0;
		}
		memoria[ultimoAtt] = dado;
		ultimoAtt++;
	}
	
}
