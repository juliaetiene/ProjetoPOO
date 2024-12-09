package br.com.ufood.Main;

import br.com.ufood.Enum.EnumCargoFuncionario;
import br.com.ufood.Enum.EnumCategoriaProdutos;
import br.com.ufood.Enum.EnumTiposDePagamento;
import br.com.ufood.Enum.EnumTurnoFuncionarios;
import br.com.ufood.Model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = null;
        Sistema sistema = null;
        Pessoa pessoa = null;
        Funcionario funcionario = null;
        Bebida bebida = null;
        Alimento alimento = null;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (cliente == null) cliente = new Cliente("", "", "", "", 0, "", 0, 0, 0, EnumTiposDePagamento.PIX, "");
            if (sistema == null) sistema = new SistemaControle(0,"", "", 0, "", 0,EnumCategoriaProdutos.NULL, "", "");
            if (funcionario == null) funcionario = new Funcionario("", "", "", "", 0, EnumCargoFuncionario.RH, "", 0, EnumTurnoFuncionarios.MATUTINO, "", "", "");
            if (bebida == null) bebida = new Bebida(0, "", "", 0, "", 0, EnumCategoriaProdutos.NULL, 0, "", false, "", "");
            if (alimento == null) alimento = new Alimento(0, "", "", 0, "", 0, EnumCategoriaProdutos.NULL, "", "", 0, "", 0, "");

            System.out.println("\n---------- MENU ----------");
            System.out.println("OLÁ, SEJA BEM-VINDO");
            System.out.println("[1] VER MENU SISTEMA");
            System.out.println("[2] VER MENU FUNCIONARIO");
            System.out.println("[3] PORTAL DO CLIENTE");
            System.out.println("[4] SAIR");
            System.out.println("\n----------------------------");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println("\n---------- MENU SISTEMA ----------");
                    System.out.println("[1] CADASTRAR PRODUTO");
                    System.out.println("[2] REPOR ESTOQUE");
                    System.out.println("[3] ATUALIZAR PREÇO");
                    System.out.println("[4] PESQUISAR DISPONIBILIDADE DO PRODUTO");
                    System.out.println("[5] CADASTRAR FUNCIONÁRIO");
                    System.out.println("[6] REMOVER ITEM DO ESTOQUE");
                    System.out.println("[7] DEMITIR FUNCIONÁRIO");
                    System.out.println("[8] CONSULTAR FUNCIONÁRIO POR CPF");
                    System.out.println("[9] ATUALIZAR DADOS DO FUNCIONÁRIO");
                    System.out.println("[10] LISTAR CLIENTES CADASTRADOS");
                    System.out.println("[11] TOTAL ESTOQUE ALIMENTOS");
                    System.out.println("[12] PREÇO MEDIO BEBIDAS");
                    System.out.println("[13] VER PRODUTO MAIS COMPRADOS");
                    System.out.println("[14] VOLTAR");
                    System.out.println("\n----------------------------");
                    System.out.print("Escolha uma opção: ");
                    String opcaoMenuSistema = scanner.nextLine();
                    switch (opcaoMenuSistema) {
                        case "1":
                            sistema.cadastrarProduto();
                            break;
                        case "2":
                            sistema.reporEstoque();
                            break;
                        case "3":
                            sistema.atualizarPreco();
                            break;
                        case "4":
                            sistema.verificarDisponibilidadeProduto();
                            break;
                        case "5":
                            funcionario.cadastrarFuncionario();
                            break;
                        case "6":
                            sistema.removerItemDoEstoque();
                            break;
                        case "7":
                            funcionario.demitirFuncionario();
                            break;
                        case "8":
                            funcionario.consultarFuncionarioPorCpf();
                            break;
                        case "9":
                            funcionario.atualizarDadosFuncionario();
                            break;
                        case "10":
                            cliente.listarClientesCadastrados();
                            break;
                        case "11":
                            alimento.calcularValorTotalEmEstoque();
                            break;
                        case "12":
                            bebida.calcularMediaPreco();
                            break;
                        case "13":
                            cliente.visualizarProdutosMaisComprados();
                            break;
                        case "14":
                            System.out.println("Voltando ao menu principal...");
                            break;
                        default:
                            System.out.println("Opção inválida no menu do sistema. Tente novamente.");
                            break;
                    }
                    break;

                case "2":
                    System.out.println("-----MENU FUNCIONARIO---------.");
                    System.out.println("[1] ADICIONAR PROMOÇÃO");
                    System.out.println("[2] REALIZAR COMPRA");
                    System.out.println("[3] PEGAR ATENDIMENTO");
                    System.out.println("[4] GERAR SEGUNDA VIA NOTA FISCAL");
                    System.out.println("[5] VER BEBIDAS INDISPONIVEL");
                    System.out.println("[6] VER ALIMENTOS INDISPONIVEL");
                    System.out.println("[7] VERIFICAR VALIDADE ALIMENTOS");
                    System.out.println("[8] VERIFICAR VALIDADE BEBIDAS");
                    System.out.println("[9] VOLTAR");
                    System.out.println("--------------------------------");
                    System.out.print("Escolha uma opção: ");
                    String opcaoMenuFuncionario = scanner.nextLine();
                    switch (opcaoMenuFuncionario) {
                        case "1":
                            funcionario.adicionarPromocaoClientes();
                            break;
                        case "2":
                            sistema.compraRealizada();
                            break;
                        case "3":
                            funcionario.pegarAtendimentoEExcluir();
                            break;
                        case "4":
                            cliente.gerarSegundaViaNotaFiscal();
                            break;
                        case "5":
                            bebida.verProdutosBebidasEstoqueIndisponivel();
                            break;
                        case "6":
                            alimento.verProdutosEstoqueAlimentoIndisponivel();
                            break;
                        case "7":
                            alimento.verificarValidadeAlimentos();
                            break;
                        case "8":
                            bebida.verificarValidade();
                            break;
                        case "9":
                            System.out.println("Voltando ao menu principal...");
                            break;
                        default:
                            System.out.println("Opção inválida no menu do sistema. Tente novamente.");
                            break;
                    }
                    break;

                case "3":
                    if (cliente == null) {
                        System.out.println("Você precisa se cadastrar primeiro para acessar o portal do cliente.");
                    } else {
                        System.out.println("-----MENU CLIENTE-------");
                        System.out.println("[1] VER PROMOÇÕES");
                        System.out.println("[2] ATENDIMENTO SUPORTE ");
                        System.out.println("[3] LISTAR ATENDIMENTO SUPORTE ANTERIORES");
                        System.out.println("[4] FAZER CADASTRO ");
                        System.out.println("[5] ADICIONAR PRODUTOS NO CARRINHO");
                        System.out.println("[6] CONSULTAR CARRINHO ");
                        System.out.println("[7] REALIZAR COMPRAS ONLINE  ");
                        System.out.println("[8] EXIBIR HISTORICO DE COMPRAS");
                        System.out.println("[9] VER BEBIDAS COM ALCOOL");
                        System.out.println("[10] EXIBIR ALIMENTOS DISPONIVEIS");
                        System.out.println("[11] EXIBIR BEBIDAS DISPONIVEIS");
                        System.out.println("[12] VOLTAR");
                        System.out.println("----------------------------");
                        System.out.println("Digite a opção: ");
                        String opcaoMenuCliente = scanner.nextLine();
                        switch (opcaoMenuCliente) {
                            case "1":
                                cliente.verPromocoes();
                                break;
                            case "2":
                                cliente.solicitarAtendimentoOnline();
                                break;
                            case "3":
                                cliente.listarSolicitacoesAtendimentoOnline();
                                break;
                            case "4":
                                cliente.fazerCadastroCliente();
                                break;
                            case "5":
                                cliente.adicionarProdutoCarrinhoOnline();
                                break;
                            case "6":
                                cliente.consultarCarrinhoOnline();
                                break;
                            case "7":
                                cliente.realizarCompraOnline();
                                break;
                            case "8":
                                cliente.exibirHistoricoCompras();
                                break;
                            case "9":
                                bebida.verBebidasComTeorAlcoolico();
                                break;
                            case "10":
                                alimento.verProdutosEstoqueAlimentoDisponivel();
                                break;
                            case "11":
                                bebida.verProdutosBebidasEstoqueDisponivel();
                                break;
                            case "12":
                                System.out.println("Voltando ao menu principal...");
                                break;
                            default:
                                System.out.println("Opção inválida no menu do cliente. Tente novamente.");
                                break;
                        }
                    }
                    break;

                case "4":
                    System.out.println("Saindo... Até mais!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
