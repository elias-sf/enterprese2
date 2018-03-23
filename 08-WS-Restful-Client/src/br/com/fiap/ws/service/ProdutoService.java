package br.com.fiap.ws.service;



import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.fiap.ws.to.Produto;

public class ProdutoService {

	private Client client = Client.create();
	private static final String URL="http://localhost:8080/07-WS-Restful-Server/rest/produto";
	
	
	
	public void remover (int codigo) {
		
		WebResource w = client.resource(URL).path(String.valueOf(codigo));
		
		ClientResponse response = w.delete(ClientResponse.class);
		
		
		// 204 confirma se o delete funcionou 
		if (response.getStatus() != 204) {
			System.err.println("Erro: " + response.getStatus());
			}
	}
	
	
	public void atualizar (Produto produto) throws Exception {
		
		// so coloca o path quando precisa , quando precisar enviar algo especifico para o banco, dizendo quem vc quer achar
		WebResource w = client.resource(URL).path(String.valueOf(produto.getCodigo()));
		/// para o atualizar usar o metodo put
		ClientResponse response = w.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, produto);
		
		
		//200 Created. confirmacao esta ok
				if(response.getStatus()!=200) {

					throw new Exception("Erro" + response.getStatus());
				}
		
	}

	public void cadastrar(Produto produto) throws Exception {
		WebResource w = client.resource(URL);

		ClientResponse response = w.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, produto);
		
		//201 Created. confirmacao 
		if(response.getStatus()!=201) {

			throw new Exception("Erro" + response.getStatus());
		}
		
		
	}
	
	
	public Produto pesquisar (int codigo) throws Exception {
		// Cria a URL com o codigo no final, e se da o caminho pelo path
		WebResource w = client.resource(URL).path(String.valueOf(codigo));

		ClientResponse response = w.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		// Valida se nao foi ok 
		if(response.getStatus()!=200) {

			throw new Exception("Erro" + response.getStatus());
		}
		//Retorna o produto encontrado 
		return response.getEntity(Produto.class);
	}

	public List <Produto> listar() throws Exception{
		// para esse caso não usa o path para pegar o codigo, nao estamos pegando especifico 
		WebResource w = client.resource(URL);
		ClientResponse response = w.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		if(response.getStatus()!=200) {

			throw new Exception("Erro" + response.getStatus());
		}
		// nesse caso retorna um vetor 
		return  Arrays.asList(response.getEntity(Produto[].class));

	}

}
