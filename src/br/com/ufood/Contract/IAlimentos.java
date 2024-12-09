package br.com.ufood.Contract;
import br.com.ufood.Model.Alimento;
import java.util.List;

public interface IAlimentos {

    void verProdutosEstoqueAlimentoDisponivel();
    void verProdutosEstoqueAlimentoIndisponivel();
    void verProdutosEstoqueAlimentosDisponivel();
    void verProdutosEstoqueAlimentosIndisponivel();
    void verificarValidadeAlimentos();
    void exibirInfo();
    void calcularValorTotalEmEstoque();
    void listarPorTipo();
    List<Alimento> getAlimentosCadastrados();

}
