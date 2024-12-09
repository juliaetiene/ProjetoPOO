package br.com.ufood.Contract;

public interface ISistema {
    void cadastrarProduto();
    void reporEstoque();
    void atualizarPreco();
    void verificarDisponibilidadeProduto();
    void compraRealizada();
    void removerItemDoEstoque();
}
