import excecoes.EntradaIncorretaException;

import java.text.ParseException;
import java.util.Scanner;

public class Login {

    Conta usuario;

    public Login(){

    }

    public Conta processoLogin(Scanner input, Rede listaUsuarios) throws ParseException, EntradaIncorretaException {

        System.out.println("Deseja entrar ou se cadastrar? 1 - Logar | 2 - Criar conta");
        String opcaoLogin;

        opcaoLogin = input.next();

        if(opcaoLogin.equals("1")){
            // Fazer o login
            return fazerLogin(listaUsuarios, input);
        }
        else if(opcaoLogin.equals("2")){
            // Criar a conta
            return addConta(input);
        }
        else {
            System.out.println("Errado!");
            return null;
        }

    }


    public Conta fazerLogin(Rede lista, Scanner input){

        System.out.println("Digite o login do usuario");
        String login = input.next();

        System.out.println("Digite a senha");
        String senha = input.next();

        if(lista.getConta(login)==null) {
            System.out.println("Usuario nao existente");
            return null;
        }

        this.usuario = lista.getConta(login);

        if(!this.usuario.getSenhaConta().equals(senha)){
            System.out.println("senha incorreta");
            return null;
        }

        return this.usuario;
    }

    public String criarLogin(Scanner input) throws EntradaIncorretaException {
        String login;
        try {
            login = input.next();
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
            nome = input.next();
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
        String senha = input.next();
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
