package br.com.ufood.Model;

import br.com.ufood.Contract.IFuncionario;
import br.com.ufood.Enum.EnumCargoFuncionario;
import br.com.ufood.Enum.EnumTurnoFuncionarios;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Funcionario extends Pessoa implements IFuncionario {
    private EnumCargoFuncionario cargoFuncionaro;
    private String matricula;
    private double salario;
    private EnumTurnoFuncionarios turnoFuncionarios;
    private String diaAdmissao;
    private String senhaSistemaFuncionario;
    private ArrayList<Funcionario> funcionariosCadastrados;
    private String nome;



    public Funcionario(String cpf,
                       String nomeDaMae,
                       String email,
                       String telefone,
                       int id,
                       EnumCargoFuncionario cargoFuncionaro,
                       String matricula,
                       double salario,
                       EnumTurnoFuncionarios turnoFuncionarios,
                       String diaAdmissao,
                       String senhaSistemaFuncionario,
                       String nome) {
        super(cpf, nomeDaMae, email, telefone, id);
        this.nome = nome;
        this.cargoFuncionaro = cargoFuncionaro;
        this.matricula = matricula;
        this.salario = salario;
        this.turnoFuncionarios = turnoFuncionarios;
        this.diaAdmissao = diaAdmissao;
        this.senhaSistemaFuncionario = senhaSistemaFuncionario;
        this.funcionariosCadastrados = new ArrayList<>();
    }

    public void cadastrarFuncionario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do novo funcionário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o CPF do novo funcionário: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite o nome da Mãe do funcionário: ");
        String nomeDaMae = scanner.nextLine();

        System.out.print("Digite o email do novo funcionário: ");
        String email = scanner.nextLine();

        System.out.print("Digite o telefone do novo funcionário: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o ID do novo funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String[] historicoDeCompras = new String[0];

        System.out.print("Digite o cargo do funcionário: ");
        String cargoStr = scanner.nextLine();
        EnumCargoFuncionario cargo = EnumCargoFuncionario.valueOf(cargoStr.toUpperCase());

        System.out.print("Digite a matrícula do funcionário: ");
        String matricula = scanner.nextLine();

        System.out.print("Digite o salário do funcionário: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite o turno do funcionário: ");
        String turnoStr = scanner.nextLine();
        EnumTurnoFuncionarios turno = EnumTurnoFuncionarios.valueOf(turnoStr.toUpperCase());

        System.out.print("Digite o dia de admissão: ");
        String diaAdmissao = scanner.nextLine();

        System.out.print("Digite a senha do sistema do funcionário: ");
        String senhaSistemaFuncionario = scanner.nextLine();

        Funcionario novoFuncionario = new Funcionario(
                cpf,
                nomeDaMae,
                email,
                telefone,
                id,
                cargo,
                matricula,
                salario,
                turno,
                diaAdmissao,
                senhaSistemaFuncionario,
                nome);
        funcionariosCadastrados.add(novoFuncionario);
        System.out.println("Funcionário com o CPF: " + novoFuncionario.getCpf() + " cadastrado com sucesso.");
    }

    public void demitirFuncionario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o cpf do funcionário para demissão: ");
        String cpfFuncionario = scanner.nextLine();

        for (Funcionario funcionario : funcionariosCadastrados) {
            if (funcionario.getCpf().equals(cpfFuncionario)) {
                funcionariosCadastrados.remove(funcionario);
                System.out.println("Funcionário com CPF: " + cpfFuncionario + " foi demitido.");
                return;
            }
        }
        System.out.println("Funcionário não encontrado.");
    }

    public void consultarFuncionarioPorCpf() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o CPF do funcionário para consulta: ");
        String cpfFuncionario = scanner.nextLine();

        for (Funcionario funcionario : funcionariosCadastrados) {
            if (funcionario.getCpf().equals(cpfFuncionario)) {
                System.out.println("Funcionário encontrado: ");
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("CPF: " + funcionario.getCpf());
                System.out.println("Matrícula: " + funcionario.getMatricula());
                System.out.println("Cargo: " + funcionario.getCargoFuncionaro());
                System.out.println("Salário: R$" + funcionario.getSalario());
                return;
            }
        }
        System.out.println("Funcionário com CPF " + cpfFuncionario + " não encontrado.");
    }

    public void promocaoFuncionario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a matrícula do funcionário para promoção: ");
        String matriculaFuncionario = scanner.nextLine();

        System.out.print("Digite o novo cargo do funcionário: ");
        String novoCargoStr = scanner.nextLine();
        EnumCargoFuncionario novoCargo = EnumCargoFuncionario.valueOf(novoCargoStr.toUpperCase());

        System.out.print("Digite o novo salário do funcionário: ");
        double novoSalario = scanner.nextDouble();
        scanner.nextLine();
        for (Funcionario funcionario : funcionariosCadastrados) {
            if (funcionario.getMatricula().equals(matriculaFuncionario)) {
                funcionario.setCargoFuncionaro(novoCargo);
                funcionario.setSalario(novoSalario);
                System.out.println("Funcionário " + matriculaFuncionario + " promovido para " + novoCargo + " com salário de R$" + novoSalario);
                return;
            }
        }
        System.out.println("Funcionário não encontrado.");
    }


    public void adicionarPromocaoClientes() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o produto que está em promoção:");
        String produto = entrada.nextLine();
        System.out.println("Digite a descrição do produto:");
        String descricao = entrada.nextLine();

        Map<String, String> promocao = Map.of("Produto", produto, "Descricao", descricao);
        listaPromocoes.add(promocao);
        System.out.println("Promoção adicionada com sucesso!");
    }

    public void pegarAtendimentoEExcluir() {
        Scanner scanner = new Scanner(System.in);

        if (getSuporteAtendimentos().isEmpty()) {
            System.out.println("Nenhuma solicitação de atendimento registrada.");
            return;
        }

        System.out.println("Atendimentos pendentes:");
        boolean encontrouPendentes = false;

        for (int i = 0; i < getSuporteAtendimentos().size(); i++) {
            Map<String, String> atendimento = getSuporteAtendimentos().get(i);
            String status = atendimento.get("Status");
            if (status == null || "Pendente".equalsIgnoreCase(status.trim())) {
                encontrouPendentes = true;
                System.out.println("ID: " + i);
                System.out.println("CPF: " + atendimento.get("CPF"));
                System.out.println("Descrição: " + atendimento.get("Descricao"));
                System.out.println("-----------------------------");
            }
        }

        if (!encontrouPendentes) {
            System.out.println("Nenhum atendimento pendente disponível.");
            return;
        }

        System.out.print("Digite o ID do atendimento que deseja pegar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id < 0 || id >= getSuporteAtendimentos().size()) {
            System.out.println("ID inválido.");
            return;
        }
        Map<String, String> atendimento = getSuporteAtendimentos().get(id);
        String status = atendimento.get("Status");
        if (status != null && !"Pendente".equalsIgnoreCase(status.trim())) {
            System.out.println("Esse atendimento já foi resolvido ou está em andamento.");
            return;
        }
        getSuporteAtendimentos().remove(id);
        System.out.println("Atendimento removido da lista e atribuído ao funcionário.");
    }



    public void atualizarDadosFuncionario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o CPF do funcionário que deseja atualizar: ");
        String cpfFuncionario = scanner.nextLine();
        for (Funcionario funcionario : funcionariosCadastrados) {
            if (funcionario.getCpf().equals(cpfFuncionario)) {
                System.out.println("Funcionário encontrado:");
                System.out.println("---------------------------:");
                System.out.println("[1.] Nome");
                System.out.println("[2.] Email");
                System.out.println("[3.] Telefone");
                System.out.println("[4.] Cargo");
                System.out.println("[5.] Salário");
                System.out.println("[6.] Turno");
                System.out.println("---------------------------:");
                System.out.println("Digite o número do campo que deseja atualizar: ");
                int escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1:
                        System.out.print("Digite o novo nome: ");
                        String novoNome = scanner.nextLine();
                        funcionario.setNome(novoNome);
                        break;
                    case 2:
                        System.out.print("Digite o novo email: ");
                        String novoEmail = scanner.nextLine();
                        funcionario.setEmail(novoEmail);
                        break;
                    case 3:
                        System.out.print("Digite o novo telefone: ");
                        String novoTelefone = scanner.nextLine();
                        funcionario.setTelefone(novoTelefone);
                        break;
                    case 4:
                        System.out.print("Digite o novo cargo: ");
                        String novoCargoStr = scanner.nextLine();
                        try {
                            EnumCargoFuncionario novoCargo = EnumCargoFuncionario.valueOf(novoCargoStr.toUpperCase());
                            funcionario.setCargoFuncionaro(novoCargo);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Cargo inválido.");
                        }
                        break;
                    case 5:
                        System.out.print("Digite o novo salário: ");
                        double novoSalario = scanner.nextDouble();
                        funcionario.setSalario(novoSalario);
                        break;
                    case 6:
                        System.out.print("Digite o novo turno: ");
                        String novoTurnoStr = scanner.nextLine();
                        try {
                            EnumTurnoFuncionarios novoTurno = EnumTurnoFuncionarios.valueOf(novoTurnoStr.toUpperCase());
                            funcionario.setTurnoFuncionarios(novoTurno);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Turno inválido.");
                        }
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        return;
                }

                System.out.println("Dados do funcionário atualizados com sucesso!");
                return;
            }
        }
        System.out.println("Funcionário com CPF " + cpfFuncionario + " não encontrado.");
    }


    public EnumCargoFuncionario getCargoFuncionaro() {
        return cargoFuncionaro;
    }

    public void setCargoFuncionaro(EnumCargoFuncionario cargoFuncionaro) {
        this.cargoFuncionaro = cargoFuncionaro;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public EnumTurnoFuncionarios getTurnoFuncionarios() {
        return turnoFuncionarios;
    }

    public void setTurnoFuncionarios(EnumTurnoFuncionarios turnoFuncionarios) {
        this.turnoFuncionarios = turnoFuncionarios;
    }

    public String getDiaAdmissao() {
        return diaAdmissao;
    }

    public void setDiaAdmissao(String diaAdmissao) {
        this.diaAdmissao = diaAdmissao;
    }

    public String getSenhaSistemaFuncionario() {
        return senhaSistemaFuncionario;
    }

    public void setSenhaSistemaFuncionario(String senhaSistemaFuncionario) {
        this.senhaSistemaFuncionario = senhaSistemaFuncionario;
    }

    public ArrayList<Funcionario> getFuncionariosCadastrados() {
        return funcionariosCadastrados;
    }

    public void setFuncionariosCadastrados(ArrayList<Funcionario> funcionariosCadastrados) {
        this.funcionariosCadastrados = funcionariosCadastrados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
