package Entidades;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
		int opcao = Integer.parseInt(JOptionPane.showInputDialog("1 - Ler" + "\n" + "2 - Escrever"));
		switch (opcao) {
		case 1:
			JOptionPane.showMessageDialog(null, "Dado armazenado: " + cache.getLast());
			return false;
		case 2:
			int novo = Integer.parseInt(JOptionPane.showInputDialog("Informe o novo valor!"));
			cache.updateLast(novo);
			return true;
		default:
			final JPanel panel = new JPanel();
		    JOptionPane.showMessageDialog(panel, "Opcao invalida!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

}
