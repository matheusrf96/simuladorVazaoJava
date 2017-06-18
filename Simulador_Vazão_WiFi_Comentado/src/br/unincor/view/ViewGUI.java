package br.unincor.view;

import javax.swing.JOptionPane;

public class ViewGUI {
	
	public void exibeMsg(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}
	
	public String recebeString(String texto) {
		String resposta = JOptionPane.showInputDialog(texto);
		return resposta;
	}

	public Double recebeDouble(String texto) {
		String resposta = JOptionPane.showInputDialog(texto);
		Double respostaConvertida = Double.parseDouble(resposta);
		return respostaConvertida;
	}
	
	public Long recebeLong(String texto) {
		String resposta = JOptionPane.showInputDialog(texto);
		Long respostaConvertida = Long.parseLong(resposta);
		return respostaConvertida;
	}
}