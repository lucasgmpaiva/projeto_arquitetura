package utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Utilitario {
	
	public static void escreverNoArquivo(Integer[] array) { 
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("dados/arquivodados");
			PrintWriter printWriter = new PrintWriter(fileWriter);
		    for (int i = 0; i < array.length; i++) {
		    	printWriter.printf(array[i].toString() + "\n");
			}
		    printWriter.close();
		} catch (IOException e) {
			System.out.println("Problema ao escrever no arquivo!");
		}
	}

}
