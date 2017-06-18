package br.unincor.control;

import java.util.List;
import java.util.ArrayList;
import br.unincor.model.PackEthernet;
import br.unincor.view.ViewGUI;

public class Executar {
	public static boolean erroPacote(long taxaErro){
		long num = (long)(Math.random()*100+1);
		
		if(num < taxaErro)
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		ViewGUI gui = new ViewGUI();
		
		double bandaNominal = 100.0;
		double bandaReal = 100.0;
		long transmissao = 10000l;
		long taxaErro = 10l;
		
		double bytesInfo = 0.0;
		double bytesTotal = 0.0;
		long pacotesRecebidos = 0l;
		long erroEnvio = 0l;
		
		List<PackEthernet> listaPacotes = new ArrayList<PackEthernet>();
		for(int i = 0; i < transmissao; i++){
			PackEthernet pe = new PackEthernet();
			listaPacotes.add(pe);
		}
		
		for(int i = 0; i < transmissao; i++){
			boolean erro = erroPacote(taxaErro);
			
			while(erro == true){
				bytesTotal += listaPacotes.get(i).tamanhoTotal();
				erro = erroPacote(taxaErro);
				
				if(!erro){
					erroEnvio++;
				}
			}
			
			bytesTotal += listaPacotes.get(i).tamanhoTotal();
			bytesInfo += listaPacotes.get(i).getDados();
		}
		
		pacotesRecebidos = transmissao - erroEnvio;
		
		double vazao = ((bandaReal/bandaNominal) * ((double)(pacotesRecebidos)/(double)(transmissao)) * (bytesInfo/bytesTotal));
		vazao *= 100;
		
		gui.exibeMsg(
					"Banda Real: " + bandaReal +
					"\nBanda Nominal: " + bandaNominal +
					"\nPacotes Recebidos: " + pacotesRecebidos +
					"\nPacotes Enviados: " + transmissao +
					"\nBytes de Informação: " + bytesInfo + 
					"\nBytes Totais: " + bytesTotal +
					"\n\nVazão: " + String.format("%.2f", vazao) + "%"
				);		
	}


}
