package br.com.ufood.Model;

import br.com.ufood.Contract.IAlimentos;
import br.com.ufood.Enum.EnumCategoriaProdutos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Alimento extends Sistema implements IAlimentos {
    private String dataValidade;
    private String marca;
    private double valor;
    private String status;
    private int peso;
    private String tipo;

    public Alimento(int id,
                    String codigoProduto,
                    String nome,
                    double preco,
                    String descricao,
                    int quantidadeEmEstoque,
                    EnumCategoriaProdutos categoria,
                    String dataValidade,
                    String marca,
                    double valor,
                    String status,
                    int peso,
                    String tipo) {
        super(id, codigoProduto, nome, preco, descricao, quantidadeEmEstoque, categoria);
        this.dataValidade = dataValidade;
        this.marca = marca;
        this.valor = valor;
        this.status = status;
        this.peso = peso;
        this.tipo = tipo;
    }

    public void verProdutosEstoqueAlimentoDisponivel() {
        boolean encontrouDisponivel = false;
        for (Alimento alimento : getAlimentosCadastrados()) {
            if (alimento.getQuantidadeEmEstoque() > 0) {
                alimento.exibirInfo();
                encontrouDisponivel = true;
            }
        }

        if (!encontrouDisponivel) {
            System.out.println("Não há alimentos disponíveis em estoque.");
        }
    }

    public void verProdutosEstoqueAlimentoIndisponivel() {
        boolean encontrouIndisponivel = false;
        for (Alimento alimento : getAlimentosCadastrados()) {
            if (alimento.getQuantidadeEmEstoque() == 0) {
                alimento.exibirInfo();
                encontrouIndisponivel = true;
            }
        }

        if (!encontrouIndisponivel) {
            System.out.println("Todos os alimentos estão disponíveis em estoque.");
        }
    }


    @Override
    public void verProdutosEstoqueAlimentosDisponivel() {

    }

    @Override
    public void verProdutosEstoqueAlimentosIndisponivel() {

    }

    public void verificarValidadeAlimentos() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate hoje = LocalDate.now();

        for (Alimento alimento : getAlimentosCadastrados()) {
            LocalDate validade = LocalDate.parse(alimento.getDataValidade(), formatter);

            String statusValidade = hoje.isBefore(validade) || hoje.isEqual(validade)
                    ? "está dentro da validade."
                    : "está vencido.";

            System.out.println(alimento.getNome() + " " + statusValidade);
        }
    }

    public void exibirInfo() {
        for (Alimento alimento : getAlimentosCadastrados()) {
            System.out.println("Nome: " + alimento.getNome());
            System.out.println("Código: " + alimento.getCodigoProduto());
            System.out.println("Preço: R$ " + alimento.getPreco());
            System.out.println("Descrição: " + alimento.getDescricao());
            System.out.println("Quantidade em Estoque: " + alimento.getQuantidadeEmEstoque());
            System.out.println("Categoria: " + alimento.getCategoria());
            System.out.println("Data de Validade: " + alimento.getDataValidade());
            System.out.println("Marca: " + alimento.getMarca());
            System.out.println("Valor: R$ " + alimento.getValor());
            System.out.println("----------------------------------");
        }
    }

    private String isStatus() {
        return isStatus();
    }


    public void calcularValorTotalEmEstoque() {
        double valorTotal = 0.0;

        for (Alimento alimento : getAlimentosCadastrados()) {
            valorTotal += alimento.getPreco() * alimento.getQuantidadeEmEstoque();
        }

        System.out.printf("O valor total dos alimentos em estoque é: R$ %.2f%n", valorTotal);
    }

    public void listarPorTipo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o tipo de alimento que deseja listar: ");
        String tipoEscolhido = scanner.nextLine();
        boolean encontrouTipo = false;

        for (Alimento alimento : getAlimentosCadastrados()) {
            if (alimento.getTipo().equalsIgnoreCase(tipoEscolhido)) {
                alimento.exibirInfo();
                encontrouTipo = true;
            }
        }

        if (!encontrouTipo) {
            System.out.println("Nenhum alimento do tipo '" + tipoEscolhido + "' encontrado.");
        }
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }




}
