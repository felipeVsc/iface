import excecoes.EntradaIncorretaException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login extends Menu {

    Conta usuario;
    Scanner input;

    public Login(){
        this.input = new Scanner(System.in);
    }


    @Override
    public RedeSocial executar(RedeSocial redesocial) throws ParseException, EntradaIncorretaException {
        Conta nova = processoLogin(redesocial.listaUsuarios);
        this.usuario = nova;
        redesocial.setUsuario(nova);
        ArrayList<Conta> listaUsuariosNaRede = redesocial.listaUsuarios.listaUsuariosRede;
        if(!listaUsuariosNaRede.contains(nova)){
            redesocial.listaUsuarios.listaUsuariosRede.add(nova);
        }
        redesocial.listaUsuarios.usuario = nova;
        redesocial.listaUsuarios.listaComunidades.usuario = nova;
        redesocial.recuperacaoInfo.usuario = nova;

        return redesocial;

    }

    public Conta processoLogin(Rede listaUsuarios) throws ParseException, EntradaIncorretaException {

        System.out.println("Deseja entrar ou se cadastrar? 1 - Logar | 2 - Criar conta");
        String opcaoLogin;

        opcaoLogin = this.input.next();

        if(opcaoLogin.equals("1")){
            // Fazer o login
            Conta lgd = fazerLogin(listaUsuarios, input);
            return this.usuario;
        }
        else if(opcaoLogin.equals("2")){
            // Criar a conta
            return addConta(input);
        }
        else {
            System.out.println("Errado! tente novamente");
            processoLogin(listaUsuarios);
        }

        return null;
    }

    public Conta fazerLogin(Rede lista, Scanner input){

        System.out.println("Digite o login do usuario");
        String login = this.input.next();

        System.out.println("Digite a senha");
        String senha = this.input.next();

        Conta usuarioQuerendoLogar = lista.getConta(login);
        this.usuario = usuarioQuerendoLogar;
        if(usuarioQuerendoLogar==null || (!usuarioQuerendoLogar.getSenhaConta().equals(senha))){
            System.out.println("Erro tente novamente");
            fazerLogin(lista,input);
        }else{
            setUsuario(usuarioQuerendoLogar);
            return usuarioQuerendoLogar;
        }
        return null;
    }

    public String criarLogin(Scanner input) throws EntradaIncorretaException {
        String login;
        try {
            login = this.input.next();
            if (login.contains(" ") && !login.matches("/^[A-Za-z0-9 ]+$/")) {
                throw new EntradaIncorretaException("Entrada incorreta, por favor nao utilizar espacos");
            }
        } catch(EntradaIncorretaException e){
            System.out.println(e.getMessage());
            return criarLogin(input);
        }
        return login;
    }

    public String criarNome(Scanner input) throws EntradaIncorretaException{
        String nome;
        // AJEITAR ISSO DAQUI DEPOIS PARA O BUG DO ESPAÇO
        // regex
        try{
            nome = this.input.next();
            nome = nome.replace("\n", "").replace("\r", "");
            if(!nome.matches("^[A-Za-z0-9-\\n ]+$")){
                throw new EntradaIncorretaException("Tem caractere especial no seu nome!");
            }
        } catch (EntradaIncorretaException e){

            System.out.println(e.getMessage());
            return criarNome(input);
        }
        return nome;
    }

    public String criarSenha(Scanner input) throws EntradaIncorretaException{
        String senha = this.input.next();
        try {
            if (senha.contains(" ")) {
                throw new EntradaIncorretaException("Tem espaco na sua senha!");
            }
        } catch (EntradaIncorretaException e){
            System.out.println(e.getMessage());
            return criarSenha(input);
        }

        return senha;

    }

    public Conta addConta(Scanner input) throws EntradaIncorretaException, ParseException {
        String login,nome,senha;

        System.out.println("Digite o login");
        login = criarLogin(input);

        System.out.println("Digite o nome");
        nome = criarNome(input);

        System.out.println("Digite a senha");
        senha = criarSenha(input);

        this.usuario = new Conta(nome,login,senha);
        return this.usuario;

    }

    public Conta getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Conta usuario) {
        this.usuario = usuario;
    }


}
