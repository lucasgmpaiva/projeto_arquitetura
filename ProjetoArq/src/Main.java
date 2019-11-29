import java.util.Scanner;

import Entidades.Cache;
import Entidades.Core;
import Entidades.Processador;
import Entidades.SystemMemory;

public class Main {
	
	public static void main(String[] args) {
			
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Insira a quantidade de cores (múltiplo de dois)");
		int quantidade  = teclado.nextInt();
		
		if(quantidade%2 != 0) {
			System.out.println("You cego, fela da puta?");
		} else {
			quantidade = quantidade/2;
			Processador processadores[] = new Processador[quantidade];
			for(int i = 0; i < quantidade; i++) {
				processadores[i] = new Processador();
				System.out.println(processadores[i].getCache().getDado(0));
			}
			Cache memoriaPrincipal = new Cache(200);
			
			for (int i = 0; i < memoriaPrincipal.getMemoria().length; i++) {
				memoriaPrincipal.getMemoria()[i] = i;
			}
			int opcao = 0;
			do {
				System.out.println("0 - Sair do programa");
				System.out.println("1 - Carregar dado");
				opcao = teclado.nextInt();
				switch (opcao) {
				case 0:
					System.out.println("Bye, bye :D");
					break;
				case 1:
					iniciarProcessamento(processadores, memoriaPrincipal);
					break;
				default:
					System.out.println("Opção inválida!");
					break;
				}
			} while (opcao != 0);
			
		}
	}
	
	public static void iniciarProcessamento(Processador[] processadores, Cache memoriaPrincipal) {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Informe a posição de memória: ");
		int posicao = teclado.nextInt();
		System.out.println("Qual core deseja utilizar?");
		int core = teclado.nextInt();
		if(posicao > 200) {
			System.out.println("Posição de memória inválida!");
		} else {
			int proce = core/2;
			Processador processador = processadores[proce];
			Core coreUtilizado;
			if(core%2 == 0) {
				coreUtilizado = processador.getCore1();
			} else {
				coreUtilizado = processador.getCore2();
			}
			int dado = memoriaPrincipal.getDado(posicao);
			processador.getCache().adicionarDado(dado);
			coreUtilizado.getCache().adicionarDado(dado);
			coreUtilizado.processar();
			processador.atualizar(coreUtilizado.getCache().getLast());
			memoriaPrincipal.updateLast(coreUtilizado.getCache().getLast());
			if(core%2 == 0) {
				processador.setCore1(coreUtilizado);
			} else {
				processador.setCore2(coreUtilizado);
			}
			processadores[proce] = processador;
			
		}
	}
}