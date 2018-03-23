package br.com.fiap.ws.service;

import java.util.Scanner;

import br.com.fiap.ws.to.Produto;

public class RemoverView {

	public static void main(String[] args) {

		Produto produto = new Produto();
		Scanner sc = new Scanner(System.in);

		System.out.println("Digite o codigo para atualizar");
		produto.setCodigo(sc.nextInt());
		
		ProdutoService service = new ProdutoService();

		try {
			service.remover(produto.getCodigo());
			System.out.println("Removido.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		sc.close();
	}
}
