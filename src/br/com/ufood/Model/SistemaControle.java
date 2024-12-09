package br.com.ufood.Model;

import br.com.ufood.Enum.EnumCategoriaProdutos;

public class SistemaControle extends Sistema{
    String cnpj;
    String nomeEmpresa;


    public SistemaControle(int id, String codigoProduto, String nome, double preco, String descricao, int quantidadeEmEstoque, EnumCategoriaProdutos categoria, String cnpj, String nomeEmpresa) {
        super(id, codigoProduto, nome, preco, descricao, quantidadeEmEstoque, categoria);
        this.cnpj = cnpj;
        this.nomeEmpresa = nomeEmpresa;
    }
}
