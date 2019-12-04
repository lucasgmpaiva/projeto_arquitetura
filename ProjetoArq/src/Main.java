import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
						JOptionPane.showMessageDialog(null, "Opcao Invalida!");
						break;
					}
				} while (opcao != 0);
				
				arquivo.close();				
			}
		} catch (FileNotFoundException e) {
			final JPanel panel = new JPanel();
		    JOptionPane.showMessageDialog(panel, "Arquivo nao encontrado!\nEncerrando!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public static void iniciarProcessamento(Processador[] processadores, SystemMemory memoriaPrincipal) {
		int posicao = Integer.parseInt(JOptionPane.showInputDialog("Informe a posicao de memoria:"));
		if(posicao > 200) {
			final JPanel panel = new JPanel();
		    JOptionPane.showMessageDialog(panel, "Posicao de memoria invalida!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			int core = Integer.parseInt(JOptionPane.showInputDialog("Qual core deseja utilizar? (Inicia em 0)"));
			int proce = core/2;
			Processador processador = processadores[proce];
			Core coreUtilizado;
			if(core%2 == 0) {
				coreUtilizado = processador.getCore1();
			} else {
				coreUtilizado = processador.getCore2();
			}
			int dado = memoriaPrincipal.getDado(posicao);
			//Adicionando na L2
			processador.getCache().adicionarDado(dado);
			//Adicionando na L1
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
			
			String memoriaProcessador = "";
			String memoriaCache = "";
			
			for (int i = 0; i < processador.getCache().getMemoria().length; i++) {
				if((i+1)%5==0 && i !=0) {
					memoriaProcessador += "[" + processador.getCache().getMemoria()[i] + "]" + "\n";
				} else {
					memoriaProcessador += "[" + processador.getCache().getMemoria()[i] + "]" + " ";
				}
			}
			
			for (int i = 0; i < coreUtilizado.getCache().getMemoria().length; i++) {
				if((i+1)%5==0 && i !=0) {
					memoriaCache += "[" + coreUtilizado.getCache().getMemoria()[i] + "]" + "\n";
				} else {
					memoriaCache += "[" + coreUtilizado.getCache().getMemoria()[i] + "]" + " ";
				}
			}
			JTextArea textArea = new JTextArea("CACHE L2\n" + memoriaProcessador);
			JScrollPane scrollPane = new JScrollPane(textArea);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			scrollPane.setPreferredSize( new Dimension( 300, 300 ) );
			JOptionPane.showMessageDialog(null, scrollPane, "Memoria Processador", JOptionPane.WARNING_MESSAGE);
			JTextArea textArea1 = new JTextArea("CACHE L1\n" + memoriaCache);
			JScrollPane scrollPane1 = new JScrollPane(textArea1);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
		    JOptionPane.showMessageDialog(null, scrollPane1, "Memoria do core", JOptionPane.WARNING_MESSAGE);
			
		}
	}
}