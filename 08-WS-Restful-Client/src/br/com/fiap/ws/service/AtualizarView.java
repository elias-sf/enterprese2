package br.com.fiap.ws.service;

import java.util.Scanner;

import br.com.fiap.ws.to.Produto;

public class AtualizarView {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Produto produto = new Produto();


		System.out.println("Digite o codigo para atualizar");
		produto.setCodigo(sc.nextInt());

		System.out.println("Nome do produto");
		produto.setNome(sc.next()+sc.nextLine());

		System.out.println("Preco");
		produto.setPreco(sc.nextDouble());


		System.out.println("Disponivel");
		produto.setDisponivel(sc.nextBoolean());

		ProdutoService service = new ProdutoService();

		try {
			service.atualizar(produto);
			System.out.println("Atualizado.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		sc.close();
	}

}
