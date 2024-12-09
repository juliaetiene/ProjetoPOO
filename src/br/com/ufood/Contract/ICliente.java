package br.com.ufood.Contract;
import br.com.ufood.Enum.EnumTiposDePagamento;
public interface ICliente {

    void fazerCadastroCliente();
    void listarClientesCadastrados();
    void adicionarProdutoCarrinhoOnline();
    void consultarCarrinhoOnline();
    void realizarCompraOnline();
    void exibirHistoricoCompras();

    String getCpf();

    String getNome();

    String getEmail();

    String getTelefone();

    String getEndereco();

    EnumTiposDePagamento getCadastroPagamento();
}
