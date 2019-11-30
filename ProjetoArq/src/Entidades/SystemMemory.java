package Entidades;

public class SystemMemory {
	
	private Integer[] memoria;
	private int ultimoAtt = 0;
	
	public SystemMemory() {
		super();
		this.memoria = new Integer[200];
	}

	public SystemMemory (int tamanho) {
		this.memoria = new Integer[tamanho];
	}

	public SystemMemory (Integer[] memoria) {
		super();
		this.memoria = memoria;
	}

	public Integer[] getMemoria() {
		return memoria;
	}

	public void setMemoria(Integer[] memoria) {
		this.memoria = memoria;
	}
	
	public int getUltimoAtt() {
		return ultimoAtt;
	}

	public void setUltimoAtt(int ultimoAtt) {
		this.ultimoAtt = ultimoAtt;
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
