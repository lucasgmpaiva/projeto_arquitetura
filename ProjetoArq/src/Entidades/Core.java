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
	
	public void processar(int posicao) {
		Scanner teclado = new Scanner(System.in);
		System.out.println("1 - Ler");
		System.out.println("2 - Escrever");
		int opcao = teclado.nextInt();
		switch (opcao) {
		case 1:
			System.out.println(cache.getDado(posicao));
			break;
		case 2:
			System.out.println("");
			break;
		default:
			System.out.println("Ta zuado, man");
			break;
		}
	}

}
