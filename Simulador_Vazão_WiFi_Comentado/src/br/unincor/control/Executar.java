package br.unincor.control;

import java.util.List;
import java.util.ArrayList;
import br.unincor.model.PackWifi;
import br.unincor.view.ViewGUI;

public class Executar {
	//essa função retorna true ou false baseado na taxa de erro
	public static boolean erroPacote(long taxaErro){
		long num = (long)(Math.random()*100+1); //gera um numero aleatorio entre 1 e 100
		
		if(num < taxaErro) //caso o numero seja menor que a taxa de erro(definida na linha 26)
			return true; //retorna true - deu erro na transmissao do pacote
		else //caso contrario
			return false; //retorna false - sem erro na transmissao
	}
	
	public static void main(String[] args) {
		ViewGUI gui = new ViewGUI(); //chama a view
		
		//declara as variáveis		
		double bandaNominal = 100.0;
		double bandaReal = 100.0; //banda real de wifi é 54.0
		long transmissao = 10000l;
		long taxaErro = 10l;
		
		double bytesInfo = 0.0;
		double bytesTotal = 0.0;
		long pacotesRecebidos = 0l;
		long erroEnvio = 0l;
		
		List<PackWifi> listaPacotes = new ArrayList<PackWifi>(); //cria a lista de pacotes wifi
		for(int i = 0; i < transmissao; i++){ //for de 0 até a quantidade de pacotes totais(10000)
			PackWifi pe = new PackWifi(); //cria um pack wifi
			listaPacotes.add(pe); //adiciona ele à lista
		}
		
		for(int i = 0; i < transmissao; i++){ //mesmo for de antes
			boolean erro = erroPacote(taxaErro); //erro recebe a função erroPacote(retorna true ou false)
			
			while(erro == true){ // enquanto true - enquanto der erro na transmissao
				//transmite outra vez...
				bytesTotal += listaPacotes.get(i).tamanhoTotal(); //somando o tamanho total do pacote...
				erro = erroPacote(taxaErro); //e tenta enviar outra vez
				
				if(!erro){ //se não tiver erro
					erroEnvio++; //confirma o envio, somando um pacote aos pacotes enviados
				}
			}
			
			bytesTotal += listaPacotes.get(i).tamanhoTotal(); //soma o total aos bytes totais transmitidos
			bytesInfo += listaPacotes.get(i).getDados(); //soma os dados do pacote aos bytes de informação
		}
		
		pacotesRecebidos = transmissao - erroEnvio; //pacotes recebidos são os pactoes totais menos os que deram erro na transmissao
		
		double vazao = ((bandaReal/bandaNominal) * ((double)(pacotesRecebidos)/(double)(transmissao)) * (bytesInfo/bytesTotal)); //formula da vazao
		vazao *= 100; // multiplicar por 100 pra chegar ao valor em porcentagem
		
		gui.exibeMsg( //exibe msg
					"Banda Real: " + bandaReal +
					"\nBanda Nominal: " + bandaNominal +
					"\nPacotes Recebidos: " + pacotesRecebidos +
					"\nPacotes Enviados: " + transmissao +
					"\nBytes de Informação: " + bytesInfo + 
					"\nBytes Totais: " + bytesTotal +
					"\n\nVazão: " + String.format("%.2f", vazao) + "%" //exibe o valor da vazão com duas casas decimais
				);		
	}


}