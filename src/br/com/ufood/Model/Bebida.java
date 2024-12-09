package br.com.ufood.Model;

import br.com.ufood.Contract.IBebidas;
import br.com.ufood.Enum.EnumCategoriaProdutos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Bebida extends Sistema implements IBebidas {
    private int volume;
    private String marca;
    protected boolean teorAlcoolico;
    private String dataFabricacao;
    private String status;


    public Bebida(int id,
                  String codigoProduto,
                  String nome,
                  double preco,
                  String descricao,
                  int quantidadeEmEstoque,
                  EnumCategoriaProdutos categoria,
                  int volume,
                  String marca,
                  boolean teorAlcoolico,
                  String dataFabricacao,
                  String status) {
        super(id, codigoProduto, nome, preco, descricao, quantidadeEmEstoque, categoria);
        this.volume = volume;
        this.marca = marca;
        this.teorAlcoolico = teorAlcoolico;
        this.dataFabricacao = dataFabricacao;
        this.status = status;
    }

    public void verProdutosBebidasEstoqueDisponivel() {
        boolean encontrouDisponivel = false;
        for (Bebida bebida : getBebidasCadastradas()) {
            if (bebida.getQuantidadeEmEstoque() > 0) {
                bebida.exibirInfo();
                encontrouDisponivel = true;
            }
        }

        if (!encontrouDisponivel) {
            System.out.println("Não há produtos disponíveis em estoque.");
        }
    }



    public void verProdutosBebidasEstoqueIndisponivel() {
        boolean encontrouIndisponivel = false;
        for (Bebida bebida : getBebidasCadastradas()) {
            if (bebida.getQuantidadeEmEstoque() == 0) {
                bebida.exibirInfo();
                encontrouIndisponivel = true;
                break;
            }
        }

        if (!encontrouIndisponivel) {
            System.out.println("Não há produtos indisponíveis em estoque.");
        }
    }



    public void exibirInfo() {
        for (Bebida bebida : getBebidasCadastradas()) {
            System.out.println("Nome: " + bebida.getNome());
            System.out.println("Código: " + bebida.getCodigoProduto());
            System.out.println("Preço: " + bebida.getPreco());
            System.out.println("Descrição: " + bebida.getDescricao());
            System.out.println("Quantidade em Estoque: " + bebida.getQuantidadeEmEstoque());
            System.out.println("Categoria: " + bebida.getCategoria());
            System.out.println("Volume: " + bebida.getVolume() + " ml");
            System.out.println("Marca: " + bebida.getMarca());
            System.out.println("Teor Alcoólico: " + (bebida.isTeorAlcoolico() ? "Sim" : "Não"));
            System.out.println("Data de Fabricação: " + bebida.getDataFabricacao());
            System.out.println("----------------------------------");
        }
    }


    public void verificarValidade() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate hoje = LocalDate.now();

        for (Bebida bebida : getBebidasCadastradas()) {
            LocalDate dataFabricacao = LocalDate.parse(bebida.getDataFabricacao(), formatter);
            LocalDate validade = dataFabricacao.plusYears(1);

            String statusValidade = hoje.isBefore(validade) || hoje.isEqual(validade)
                    ? "está dentro da validade."
                    : "está vencida.";

            System.out.println(bebida.getNome() + " " + statusValidade);
        }
    }

    public void verBebidasComTeorAlcoolico() {
        boolean encontrouAlcoolicas = false;
        for (Bebida bebida : getBebidasCadastradas()) {
            if (bebida.isTeorAlcoolico()) {
                bebida.exibirInfo();
                encontrouAlcoolicas = true;
            }
        }

        if (!encontrouAlcoolicas) {
            System.out.println("Não há bebidas alcoólicas cadastradas.");
        }
    }

    public void calcularMediaPreco() {
        double somaPreco = 0;
        int totalBebidas = 0;

        for (Bebida bebida : getBebidasCadastradas()) {
            somaPreco += bebida.getPreco();
            totalBebidas++;
        }

        if (totalBebidas > 0) {
            double media = somaPreco / totalBebidas;
            System.out.println("A média de preço das bebidas é: " + media);
        } else {
            System.out.println("Não há bebidas cadastradas.");
        }
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isTeorAlcoolico() {
        return teorAlcoolico;
    }

    public boolean setTeorAlcoolico(boolean teorAlcoolico) {
        this.teorAlcoolico = teorAlcoolico;
        return teorAlcoolico;
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(String dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
