package br.com.ufood.Model;

import br.com.ufood.Contract.*;
import br.com.ufood.Enum.*;

import java.util.*;

public abstract class Sistema  implements ISistema{
    private int id = 0;
    private String codigoProduto;
    private String nome;
    private double preco;
    private String descricao;
    private static int quantidadeEmEstoque;
    private EnumCategoriaProdutos categoria;
    private static List<Bebida> bebidasCadastradas = new ArrayList<>();
    private static List<Alimento> alimentosCadastrados = new ArrayList<>();
    private static Map<String, Integer> historicoDeCompras;

    public Sistema(int id,
                   String codigoProduto,
                   String nome,
                   double preco,
                   String descricao,
                   int quantidadeEmEstoque,
                   EnumCategoriaProdutos categoria) {
        this.id = id;
        this.codigoProduto = codigoProduto;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.categoria = categoria;

    }


    public void reporEstoque() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o nome do produto desejado: ");
        String nomeDoProduto = entrada.nextLine();

        System.out.println("Digite a quantidade para alocar ao estoque: ");
        int atribuirAoEstoque = entrada.nextInt();


        entrada.nextLine();

        if (atribuirAoEstoque <= 0) {
            System.out.println("A quantidade a ser reposta deve ser maior que zero.");
            return;
        }

        boolean produtoEncontrado = false;
        for (Bebida bebida : bebidasCadastradas) {
            if (bebida.getNome().equals(nomeDoProduto)) {
                bebida.setQuantidadeEmEstoque(bebida.getQuantidadeEmEstoque() + atribuirAoEstoque);
                produtoEncontrado = true;
                System.out.println("Estoque reposto para a bebida: " + bebida.getNome() + ". Novo estoque: " + bebida.getQuantidadeEmEstoque());
                break;
            }
        }

        if (!produtoEncontrado) {
            for (Alimento alimento : alimentosCadastrados) {
                if (alimento.getNome().equals(nomeDoProduto)) {
                    alimento.setQuantidadeEmEstoque(alimento.getQuantidadeEmEstoque() + atribuirAoEstoque);
                    produtoEncontrado = true;
                    System.out.println("Estoque reposto para o alimento: " + alimento.getNome() + ". Novo estoque: " + alimento.getQuantidadeEmEstoque());
                    break;
                }
            }
        }

        if (!produtoEncontrado) {
            System.out.println("Produto não encontrado.");
        }
    }



    public void atualizarPreco() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o nome do produto desejado: ");
        String nomeDoProduto = entrada.nextLine();

        System.out.println("Digite o novo valor ofertado: ");
        double precoNovo = entrada.nextDouble();

        if (precoNovo <= 0) {
            System.out.println("O preço não pode ser menor ou igual a zero.");
            return;
        }

        boolean produtoEncontrado = false;
        for (Bebida bebida : bebidasCadastradas) {
            if (bebida.getNome().equals(nomeDoProduto)) {
                bebida.setPreco(precoNovo);
                produtoEncontrado = true;
                System.out.println("Preço atualizado para a bebida: " + bebida.getNome() + ". Novo preço: R$" + bebida.getPreco());
                break;
            }
        }

        if (!produtoEncontrado) {
            for (Alimento alimento : alimentosCadastrados) {
                if (alimento.getNome().equals(nomeDoProduto)) {
                    alimento.setPreco(precoNovo);
                    produtoEncontrado = true;
                    System.out.println("Preço atualizado para o alimento: " + alimento.getNome() + ". Novo preço: R$" + alimento.getPreco());
                    break;
                }
            }
        }

        if (!produtoEncontrado) {
            System.out.println("Produto não encontrado.");
        }
    }

    public void cadastrarProduto() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Código do produto:");
        String codigoProdutoInput = entrada.nextLine();
        for (Bebida bebida : bebidasCadastradas) {
            if (bebida.getCodigoProduto().equalsIgnoreCase(codigoProdutoInput)) {
                System.out.println("Produto já cadastrado.");
                return;
            }
        }
        for (Alimento alimento : alimentosCadastrados) {
            if (alimento.getCodigoProduto().equalsIgnoreCase(codigoProdutoInput)) {
                System.out.println("Produto já cadastrado.");
                return;
            }
        }

        System.out.println("Nome do produto:");
        String nomeInput = entrada.nextLine();
        System.out.println("Preço do produto:");
        double precoInput = entrada.nextDouble();
        entrada.nextLine();
        System.out.println("Descrição do produto:");
        String descricaoInput = entrada.nextLine();
        System.out.println("Digite a quantidade em estoque:");
        int quantidadeEmEstoqueInput = entrada.nextInt();
        entrada.nextLine();
        System.out.println("----------TIPO DO PRODUTO----------");
        System.out.println("[1.] ALIMENTO");
        System.out.println("[2.] BEBIDAS");
        System.out.println("------------------------------------");
        System.out.println("Digite o valor desejado:");
        int valorDesejado = entrada.nextInt();
        entrada.nextLine();
        EnumCategoriaProdutos categoriaSelecionada;
        switch (valorDesejado) {
            case 1 -> {
                categoriaSelecionada = EnumCategoriaProdutos.ALIMENTO;

                System.out.println("Digite a data de validade:");
                String dataDeValidadeAlimentoInput = entrada.nextLine();

                System.out.println("Digite a marca do produto:");
                String marcaAlimentoInput = entrada.nextLine();

                System.out.println("Digite o tipo do produto (LIMPEZA, CONSUMO e etc.):");
                String tipoAlimentoInput = entrada.nextLine();

                System.out.println("Digite o peso do alimento (em gramas):");
                int pesoAlimentoInput = entrada.nextInt();

                entrada.nextLine();

                Alimento novoAlimento = new Alimento(
                        ++id,
                        codigoProdutoInput,
                        nomeInput,
                        precoInput,
                        descricaoInput,
                        quantidadeEmEstoqueInput,
                        categoriaSelecionada,
                        dataDeValidadeAlimentoInput,
                        marcaAlimentoInput,
                        precoInput,
                        null,
                        pesoAlimentoInput,
                        tipoAlimentoInput
                );

                alimentosCadastrados.add(novoAlimento);
            }
            case 2 -> {
                categoriaSelecionada = EnumCategoriaProdutos.BEBIDAS;

                System.out.println("Digite a data de validade:");
                String dataDeValidadeInput = entrada.nextLine();

                System.out.println("Digite a marca do produto:");
                String marcaInput = entrada.nextLine();

                System.out.println("Digite o volume da bebida (em ml):");
                int volumeInput = entrada.nextInt();

                entrada.nextLine();

                System.out.println("Digite o tipo de bebida (Ex: Gelado):");
                String tipoInput = entrada.nextLine();

                System.out.println("A bebida tem teor alcoólico?:");
                System.out.println("[1.] SIM");
                System.out.println("[2.] NÃO");
                int opcaoInput = entrada.nextInt();
                boolean variavel = (opcaoInput == 1);

                entrada.nextLine();

                Bebida novaBebida = new Bebida(
                        ++id,
                        codigoProdutoInput,
                        nomeInput,
                        precoInput,
                        descricaoInput,
                        quantidadeEmEstoqueInput,
                        categoriaSelecionada,
                        volumeInput,
                        marcaInput,
                        variavel,
                        dataDeValidadeInput,
                        tipoInput
                );

                bebidasCadastradas.add(novaBebida);
            }
            default -> {
                System.out.println("Categoria inválida! Por favor, escolha entre 1 (Alimento) e 2 (Bebidas).");
                return;
            }
        }
        System.out.println("Produto cadastrado com sucesso!");
        System.out.println("\n--- Lista de Bebidas Cadastradas ---");
        for (Bebida bebida : bebidasCadastradas) {
            System.out.println(bebida);
        }
        System.out.println("\n--- Lista de Alimentos Cadastrados ---");
        for (Alimento alimento : alimentosCadastrados) {
            System.out.println(alimento);
        }
    }


    public void verificarDisponibilidadeProduto() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o nome do produto desejado: ");
        String nomeDoProduto = entrada.nextLine().trim();
        boolean produtoEncontrado = false;
        for (Bebida bebida : bebidasCadastradas) {
            System.out.println("Verificando bebida: " + bebida.getNome());
            if (bebida.getNome().equalsIgnoreCase(nomeDoProduto)) {
                if (bebida.getQuantidadeEmEstoque() == 0) {
                    System.out.println("Produto " + bebida.getNome() + " " + EnumDisponibilidade.INDISPONIVEL);
                    return;
                }
                System.out.println("Produto " + bebida.getNome() + " " + EnumDisponibilidade.DISPONIVEL);
                produtoEncontrado = true;
                break;
            }
        }


        if (!produtoEncontrado) {
            for (Alimento alimento : alimentosCadastrados) {
                System.out.println("Verificando alimento: " + alimento.getNome());
                if (alimento.getNome().equalsIgnoreCase(nomeDoProduto)) {
                    if (alimento.getQuantidadeEmEstoque() == 0) {
                        System.out.println("Produto " + alimento.getNome() + " " + EnumDisponibilidade.INDISPONIVEL);
                        return;
                    }
                    System.out.println("Produto " + alimento.getNome() + " " + EnumDisponibilidade.DISPONIVEL);
                    produtoEncontrado = true;
                    break;
                }
            }
        }


        if (!produtoEncontrado) {
            System.out.println("Produto não encontrado.");
        }
    }

    public void compraRealizada() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o nome do produto desejado para compra: ");
        String nomeDoProduto = entrada.nextLine();

        boolean produtoEncontrado = false;

        for (Bebida bebida : bebidasCadastradas) {
            if (bebida.getNome().equals(nomeDoProduto)) {
                if (bebida.getQuantidadeEmEstoque() == 0) {
                    System.out.println("Produto " + bebida.getNome() + " Situação: " + EnumDisponibilidade.INDISPONIVEL);
                    return;
                }
                bebida.setQuantidadeEmEstoque(bebida.getQuantidadeEmEstoque() - 1);
                System.out.println("Compra realizada com sucesso! Produto: " + bebida.getNome());
                historicoDeCompras.put(bebida.getNome(), historicoDeCompras.getOrDefault(bebida.getNome(), 0) + 1);
                produtoEncontrado = true;
                break;
            }
        }

        if (!produtoEncontrado) {
            for (Alimento alimento : alimentosCadastrados) {
                if (alimento.getNome().equals(nomeDoProduto)) {
                    if (alimento.getQuantidadeEmEstoque() == 0) {
                        System.out.println("Produto " + alimento.getNome() + " Situação: " + EnumDisponibilidade.INDISPONIVEL);
                        return;
                    }
                    alimento.setQuantidadeEmEstoque(alimento.getQuantidadeEmEstoque() - 1);
                    System.out.println("Compra realizada com sucesso! Produto: " + alimento.getNome());
                    historicoDeCompras.put(alimento.getNome(), historicoDeCompras.getOrDefault(alimento.getNome(), 0) + 1);
                    produtoEncontrado = true;
                    break;
                }
            }
        }

        if (!produtoEncontrado) {
            System.out.println("Produto não encontrado.");
        }
    }


    public void removerItemDoEstoque() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o nome do produto a ser removido do estoque: ");
        String nomeDoProduto = entrada.nextLine();

        boolean produtoEncontrado = false;
        for (Bebida bebida : bebidasCadastradas) {
            if (bebida.getNome().equals(nomeDoProduto)) {
                if (bebida.getQuantidadeEmEstoque() == 0) {
                    System.out.println("Produto " + bebida.getNome() + " " + EnumDisponibilidade.INDISPONIVEL);
                    return;
                }
                bebidasCadastradas.remove(bebida);
                System.out.println("Produto " + bebida.getNome() + " removido do estoque.");
                produtoEncontrado = true;
                break;
            }
        }

        if (!produtoEncontrado) {
            for (Alimento alimento : alimentosCadastrados) {
                if (alimento.getNome().equals(nomeDoProduto)) {
                    if (alimento.getQuantidadeEmEstoque() == 0) {
                        System.out.println("Produto " + alimento.getNome() + " " + EnumDisponibilidade.INDISPONIVEL);
                        return;
                    }
                    alimentosCadastrados.remove(alimento);
                    System.out.println("Produto " + alimento.getNome() + " removido do estoque.");
                    produtoEncontrado = true;
                    break;
                }
            }
        }

        if (!produtoEncontrado) {
            System.out.println("Produto não encontrado.");
        }

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public EnumCategoriaProdutos getCategoria() {
        return categoria;
    }

    public void setCategoria(EnumCategoriaProdutos categoria) {
        this.categoria = categoria;
    }

    public static List<Bebida> getBebidasCadastradas() {
        return bebidasCadastradas;
    }

    public void setBebidasCadastradas(List<Bebida> bebidasCadastradas) {
        this.bebidasCadastradas = bebidasCadastradas;
    }


    public List<Alimento> getAlimentosCadastrados() {
        return alimentosCadastrados;
    }

    public void setAlimentosCadastrados(List<Alimento> alimentosCadastrados) {
        this.alimentosCadastrados = alimentosCadastrados;
    }

    public static Map<String, Integer> getHistoricoDeCompras() {
        return historicoDeCompras;
    }

    public static void setHistoricoDeCompras(Map<String, Integer> historicoDeCompras) {
        Sistema.historicoDeCompras = historicoDeCompras;
    }
}
