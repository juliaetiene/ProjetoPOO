package br.com.ufood.Contract;
public interface IBebidas{

    boolean setTeorAlcoolico(boolean teorAlcoolico);
    void verProdutosBebidasEstoqueDisponivel();
    void verProdutosBebidasEstoqueIndisponivel();
    void exibirInfo();
    void verificarValidade();
    void verBebidasComTeorAlcoolico();
    void calcularMediaPreco();

}
