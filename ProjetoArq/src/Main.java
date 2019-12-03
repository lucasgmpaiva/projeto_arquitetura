import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Entidades.Cache;
import Entidades.Core;
import Entidades.Processador;
import Entidades.SystemMemory;
import utilities.Utilitario;

public class Main {
	
	public static void main(String[] args) {
			
		try {
			Scanner arquivo = new Scanner (new File("dados/arquivodados"));
			int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de cores:"));
			
			if(quantidade%2 != 0 || quantidade == 0 || quantidade < 0) {
				final JPanel panel = new JPanel();
			    JOptionPane.showMessageDialog(panel, "Quantidade de cores inválida!\nEncerrando!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				quantidade = quantidade/2;
				Processador processadores[] = new Processador[quantidade];
				for(int i = 0; i < quantidade; i++) {
					processadores[i] = new Processador();
				}
				SystemMemory memoriaPrincipal = new SystemMemory();
				
				for (int i = 0; i < memoriaPrincipal.getMemoria().length; i++) {
					if(arquivo.hasNext()) {
						memoriaPrincipal.getMemoria()[i] = arquivo.nextInt();
					} else if (!arquivo.hasNext() && i == 0) {
						Integer[] arrayInicial = new Integer[200];
						for(int j = 0; j < memoriaPrincipal.getMemoria().length; j++) {
							arrayInicial[j] = j;
						}
						Utilitario.escreverNoArquivo(arrayInicial);
					}
				}
				int opcao = 0;
				do {
					opcao = Integer.parseInt(JOptionPane.showInputDialog("0 - Sair do programa" + "\n" + "1 - Carregar dado"));
					switch (opcao) {
					case 0:
						JOptionPane.showMessageDialog(null, "Bye, bye! :D");
						break;
					case 1:
						iniciarProcessamento(processadores, memoriaPrincipal);
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opção Inválida!");
						break;
					}
				} while (opcao != 0);
				
			}
		} catch (FileNotFoundException e) {
			final JPanel panel = new JPanel();
		    JOptionPane.showMessageDialog(panel, "Arquivo não encontrado!\nEncerrando!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public static void iniciarProcessamento(Processador[] processadores, SystemMemory memoriaPrincipal) {
		int posicao = Integer.parseInt(JOptionPane.showInputDialog("Informe a posição de memória:"));
		if(posicao > 200) {
			final JPanel panel = new JPanel();
		    JOptionPane.showMessageDialog(panel, "Posição de memória inválida!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			int core = Integer.parseInt(JOptionPane.showInputDialog("Qual core deseja utilizar?"));
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
			Utilitario.escreverNoArquivo(memoriaPrincipal.getMemoria());
			if(core%2 == 0) {
				processador.setCore1(coreUtilizado);
			} else {
				processador.setCore2(coreUtilizado);
			}
			processadores[proce] = processador;
			
		}
	}
}