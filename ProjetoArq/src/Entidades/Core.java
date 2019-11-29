package Entidades;

import java.util.Scanner;

public class Core {
	
	private Cache cache;
	
	public Core() {
		this.cache = new Cache(50);
	}

	public Core(Cache cache) {
		super();
		this.cache = cache;
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}
	
	public boolean processar() {
		Scanner teclado = new Scanner(System.in);
		System.out.println("1 - Ler");
		System.out.println("2 - Escrever");
		int opcao = teclado.nextInt();
		switch (opcao) {
		case 1:
			System.out.println("Dado armazenado: " + cache.getLast());
			return false;
		case 2:
			System.out.println("Insira o novo valor");
			int novo = teclado.nextInt();
			cache.updateLast(novo);
			return true;
		default:
			System.out.println("Ta zuado, man");
			return false;
		}
	}

}
