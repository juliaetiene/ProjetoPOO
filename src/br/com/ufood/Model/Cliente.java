package br.com.ufood.Model;

import br.com.ufood.Contract.ICliente;
import br.com.ufood.Enum.EnumTiposDePagamento;

import java.util.*;

public class Cliente extends Pessoa implements ICliente {
    private String nome;
    private int diaNascimento;
    private int mesNascimento;
    private int anoNascimento;
    private EnumTiposDePagamento cadastroPagamento;
    private Set<String> historicoCompras;
    private String endereco;
    private Set<ICliente> clientesCadastrados;
    private List<Map<String, Integer>> carrinhoCompras;

    public Cliente(String cpf, String nomeDaMae, String email, String telefone, int id,
                   String nome, int diaNascimento, int mesNascimento, int anoNascimento,
                   EnumTiposDePagamento cadastroPagamento, String endereco) {
        super(cpf, nomeDaMae, email, telefone, id);
        this.nome = nome;
        this.diaNascimento = diaNascimento;
        this.mesNascimento = mesNascimento;
        this.anoNascimento = anoNascimento;
        this.cadastroPagamento = cadastroPagamento;
        this.endereco = endereco;
        this.clientesCadastrados = new HashSet<>();
        this.carrinhoCompras = new ArrayList<>();
        this.historicoCompras = new HashSet<>();
    }

    public void fazerCadastroCliente() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Digite seu CPF (apenas números): ");
            String cpf = scanner.nextLine();
            if (cpf.length() != 11 || !cpf.matches("[0-9]+")) {
                throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos numéricos.");
            }

            System.out.print("Digite o nome da mãe: ");
            String nomeDaMae = scanner.nextLine();

            System.out.print("Digite seu nome completo: ");
            String nome = scanner.nextLine();

            System.out.print("Digite seu email: ");
            String email = scanner.nextLine();
            if (!email.contains("@") || !email.contains(".")) {
                throw new IllegalArgumentException("Email inválido.");
            }

            System.out.print("Digite seu telefone: ");
            String telefone = scanner.nextLine();

            System.out.print("Digite seu endereço: ");
            String endereco = scanner.nextLine();

            System.out.print("Digite sua forma de pagamento (CREDITO/DEBITO/PIX): ");
            String pagamento = scanner.nextLine().toUpperCase();
            EnumTiposDePagamento tipoPagamento;
            try {
                tipoPagamento = EnumTiposDePagamento.valueOf(pagamento);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Forma de pagamento inválida.");
            }

            System.out.print("Digite sua data de nascimento (DD/MM/AAAA): ");
            String[] dataNascimento = scanner.nextLine().split("/");
            if (dataNascimento.length != 3) {
                throw new IllegalArgumentException("Data de nascimento inválida.");
            }

            int diaNascimento = Integer.parseInt(dataNascimento[0]);
            int mesNascimento = Integer.parseInt(dataNascimento[1]);
            int anoNascimento = Integer.parseInt(dataNascimento[2]);

            Cliente novoCliente = new Cliente(
                    cpf,
                    nomeDaMae,
                    email,
                    telefone,
                    clientesCadastrados.size() + 1,
                    nome,
                    diaNascimento,
                    mesNascimento,
                    anoNascimento,
                    tipoPagamento,
                    endereco);

            if (clientesCadastrados.add(novoCliente)) {
                System.out.println("Cadastro realizado com sucesso!");
            } else {
                System.out.println("Cliente já está cadastrado.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    public void listarClientesCadastrados() {
        if (clientesCadastrados.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("\n--- Lista de Clientes Cadastrados ---");
        for (ICliente cliente : clientesCadastrados) {
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("Endereço: " + cliente.getEndereco());
            System.out.println("Forma de Pagamento: " + cliente.getCadastroPagamento());
            System.out.println("----------------------------------");
        }
    }


    public void adicionarProdutoCarrinhoOnline() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do produto:");
        String nomeProduto = scanner.nextLine();
        System.out.println("Digite a quantidade do produto:");
        int quantidade;
        try {
            quantidade = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida. Tente novamente.");
            return;
        }
        if (nomeProduto.isEmpty() || quantidade <= 0) {
            System.out.println("Nome do produto ou quantidade inválidos.");
            return;
        }
        Map<String, Integer> produto = new HashMap<>();
        produto.put(nomeProduto, quantidade);
        carrinhoCompras.add(produto);
        System.out.println("Produto adicionado ao carrinho com sucesso!");
    }


    public void consultarCarrinhoOnline() {
        try {
            if (carrinhoCompras.isEmpty()) {
                throw new Exception("Carrinho de compras está vazio.");
            }

            System.out.println("Produtos no carrinho:");
            for (Map<String, Integer> produto : carrinhoCompras) {
                System.out.println(produto);
            }
        } catch (Exception e) {
            System.out.println("Erro!");
        }
    }

    public void realizarCompraOnline() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Digite o nome do produto que deseja comprar:");
            String nomeProduto = scanner.nextLine();
            Bebida bebida = buscarBebidaNoEstoque(nomeProduto);
            Alimento alimento = buscarAlimentoNoEstoque(nomeProduto);

            if (bebida == null && alimento == null) {
                throw new Exception("Produto não encontrado no estoque.");
            }
            System.out.println("Digite a quantidade desejada:");
            int quantidadeDesejada;
            try {
                quantidadeDesejada = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Quantidade inválida. Tente novamente.");
                return;
            }
            if (quantidadeDesejada <= 0) {
                System.out.println("Quantidade inválida. Deve ser maior que zero.");
                return;
            }

            if (bebida != null) {
                if (quantidadeDesejada > bebida.getQuantidadeEmEstoque()) {
                    throw new Exception("Estoque insuficiente. Apenas " + bebida.getQuantidadeEmEstoque() + " unidade(s) disponíveis.");
                }
                bebida.setQuantidadeEmEstoque(bebida.getQuantidadeEmEstoque() - quantidadeDesejada);
                historicoCompras.add("Compra realizada: " + quantidadeDesejada + " unidade(s) de " + nomeProduto + " (Bebida)");
                System.out.println("Compra realizada com sucesso! " + quantidadeDesejada + " unidade(s) de " + nomeProduto + " (Bebida) foram compradas.");

            } else if (alimento != null) {
                if (quantidadeDesejada > alimento.getQuantidadeEmEstoque()) {
                    throw new Exception("Estoque insuficiente. Apenas " + alimento.getQuantidadeEmEstoque() + " unidade(s) disponíveis.");
                }
                alimento.setQuantidadeEmEstoque(alimento.getQuantidadeEmEstoque() - quantidadeDesejada);
                historicoCompras.add("Compra realizada: " + quantidadeDesejada + " unidade(s) de " + nomeProduto + " (Alimento)");

                System.out.println("Compra realizada com sucesso! " + quantidadeDesejada + " unidade(s) de " + nomeProduto + " (Alimento) foram compradas.");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }


public Bebida buscarBebidaNoEstoque(String nomeProduto) {
        for (Bebida bebida : Sistema.getBebidasCadastradas()) {
            if (bebida.getNome().equalsIgnoreCase(nomeProduto)) {
                return bebida;
            }
        }
        return null;
    }

    public Alimento buscarAlimentoNoEstoque(String nomeProduto) {
        Sistema sistema = null;
        for (Alimento alimento : sistema.getAlimentosCadastrados()) {
            if (alimento.getNome().equalsIgnoreCase(nomeProduto)) {
                return alimento;
            }
        }
        return null;
    }

    public void exibirHistoricoCompras() {
        if (historicoCompras.isEmpty()) {
            System.out.println("Nenhuma compra foi realizada ainda.");
            return;
        }

        System.out.println("\n--- Histórico de Compras ---");
        for (String item : historicoCompras) {
            System.out.println("Item: " + item);
        }
        System.out.println("-----------------------------");
    }

    public void setClientesCadastrados(Set<ICliente> clientesCadastrados) {
        this.clientesCadastrados = clientesCadastrados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDiaNascimento() {
        return diaNascimento;
    }

    public void setDiaNascimento(int diaNascimento) {
        this.diaNascimento = diaNascimento;
    }

    public int getMesNascimento() {
        return mesNascimento;
    }

    public void setMesNascimento(int mesNascimento) {
        this.mesNascimento = mesNascimento;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public EnumTiposDePagamento getCadastroPagamento() {
        return cadastroPagamento;
    }

    public void setCadastroPagamento(EnumTiposDePagamento cadastroPagamento) {
        this.cadastroPagamento = cadastroPagamento;
    }

    public Set<String> getHistoricoCompras() {
        return historicoCompras;
    }

    public void setHistoricoCompras(Set<String> historicoCompras) {
        this.historicoCompras = historicoCompras;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Set<ICliente> getClientesCadastrados() {
        return clientesCadastrados;
    }


    public List<Map<String, Integer>> getCarrinhoCompras() {
        return carrinhoCompras;
    }

    public void setCarrinhoCompras(List<Map<String, Integer>> carrinhoCompras) {
        this.carrinhoCompras = carrinhoCompras;
    }
}
