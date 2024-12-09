package br.com.ufood.Model;

import br.com.ufood.Contract.IPessoa;

import java.util.*;

public abstract class Pessoa implements IPessoa {
    private String cpf;
    private String nomeDaMae;
    private String email;
    private String telefone;
    private int id;
    private static List<Map<String, String>> suporteAtendimentos = new ArrayList<>();
    protected static List<Map<String, String>> listaPromocoes = new ArrayList<>();



    public Pessoa(String cpf, String nomeDaMae, String email, String telefone, int id) {
        this.cpf = cpf;
        this.nomeDaMae = nomeDaMae;
        this.email = email;
        this.telefone = telefone;
        this.id = id;

    }

    public void verPromocoes() {
        try {
            if (listaPromocoes.isEmpty()) {
                System.out.println("Nenhuma promoção disponível no momento.");
            } else {
                System.out.println("\n--- Promoções do Dia ---");
                for (Map<String, String> promocao : listaPromocoes) {
                    System.out.println("Produto: " + promocao.get("Produto"));
                    System.out.println("Descrição: " + promocao.get("Descricao"));
                    System.out.println("-----------------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao exibir promoções.");
        }
    }


    public void listarSolicitacoesAtendimentoOnline() {
        try {
            if (suporteAtendimentos.isEmpty()) {
                System.out.println("Nenhuma solicitação de atendimento registrada.");
            } else {
                System.out.println("\n--- Solicitações de Atendimento ---");
                for (Map<String, String> atendimento : suporteAtendimentos) {
                    System.out.println("CPF: " + atendimento.get("CPF"));
                    System.out.println("Solicitação: " + atendimento.get("Solicitacao"));
                    System.out.println("-----------------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar solicitações de atendimento.");
        }
    }

    public void solicitarAtendimentoOnline() {
        Scanner entrada = new Scanner(System.in);
        try {
            System.out.println("Digite seu CPF: ");
            String cpfCliente = entrada.nextLine();
            System.out.println("Por favor, descreva sua solicitação: ");
            String solicitacao = entrada.nextLine();
            Map<String, String> atendimento = Map.of("CPF", cpfCliente, "Solicitacao", solicitacao);
            suporteAtendimentos.add(atendimento);
            System.out.println("Sua solicitação foi registrada. Em breve nossa equipe entrará em contato.");
        } catch (Exception e) {
            System.out.println("Erro ao registrar a solicitação.");
        }
    }

    public void visualizarProdutosMaisComprados() {
        try {
            Map<String, Integer> historico = Sistema.getHistoricoDeCompras();
            for (Map.Entry<String, Integer> entry : historico.entrySet()) {
                System.out.println("Produto: " + entry.getKey() + " - Quantidade comprada: " + entry.getValue());
            }
        } catch (Exception e) {
            System.out.println("Erro ao visualizar produtos mais comprados.");
        }
    }

    public void visualizarInformacoesPessoais() {
        try {
            System.out.println("CPF: " + this.cpf);
            System.out.println("Nome da Mãe: " + this.nomeDaMae);
            System.out.println("Email: " + this.email);
            System.out.println("Telefone: " + this.telefone);
            System.out.println("ID: " + this.id);
        } catch (Exception e) {
            System.out.println("Erro ao visualizar informações pessoais.");
        }
    }

    public void gerarSegundaViaNotaFiscal() {
        Scanner entrada = new Scanner(System.in);
        try {
            System.out.println("Digite o CPF do cliente: ");
            String cpfClienteNotaFiscal = entrada.nextLine();

            System.out.println("Digite o Nome do cliente: ");
            String nomeClienteNotaFiscal = entrada.nextLine();

            List<String> produtos = new ArrayList<>();
            List<Double> precos = new ArrayList<>();

            boolean adicionarMais = true;
            while (adicionarMais) {
                System.out.println("Digite o nome do produto: ");
                String produtoClienteNotaFiscal = entrada.nextLine();
                produtos.add(produtoClienteNotaFiscal);

                double precoClienteNotaFiscal = 0;
                boolean precoValido = false;
                while (!precoValido) {
                    try {
                        System.out.println("Digite o valor do produto: ");
                        precoClienteNotaFiscal = entrada.nextDouble();
                        if (precoClienteNotaFiscal < 0) {
                            System.out.println("Preço não pode ser negativo.");
                        } else {
                            precoValido = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Valor inválido. Por favor, insira um número.");
                        entrada.next();
                    }
                }

                precos.add(precoClienteNotaFiscal);
                entrada.nextLine();
                System.out.println("----Deseja adicionar mais produtos?----");
                System.out.println("[1.] SIM");
                System.out.println("[2.] NÃO");
                String resposta = entrada.nextLine().toLowerCase();
                adicionarMais = resposta.equals("1");
            }
            double total = precos.stream().mapToDouble(Double::doubleValue).sum();
            System.out.println("\n--------- NOTA FISCAL ---------");
            System.out.println("Nome do Cliente: " + nomeClienteNotaFiscal);
            System.out.println("CPF do Cliente: " + cpfClienteNotaFiscal);
            System.out.println("--------------------------------");
            System.out.println("Produtos:");
            for (int i = 0; i < produtos.size(); i++) {
                System.out.printf(" - %s: R$ %.2f%n", produtos.get(i), precos.get(i));
            }
            System.out.println("--------------------------------");
            System.out.printf("Total: R$ %.2f%n", total);
            System.out.println("--------------------------------");
        } catch (Exception e) {
            System.out.println("Erro ao gerar a segunda via da nota fiscal.");
        }
    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeDaMae() {
        return nomeDaMae;
    }

    public void setNomeDaMae(String nomeDaMae) {
        this.nomeDaMae = nomeDaMae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Map<String, String>> getSuporteAtendimentos() {
        return suporteAtendimentos;
    }

    public void setSuporteAtendimentos(List<Map<String, String>> suporteAtendimentos) {
        this.suporteAtendimentos = suporteAtendimentos;
    }

    public List<Map<String, String>> getListaPromocoes() {
        return listaPromocoes;
    }

    public void setListaPromocoes(List<Map<String, String>> listaPromocoes) {
        this.listaPromocoes = listaPromocoes;
    }
}
