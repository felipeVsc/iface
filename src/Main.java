import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Rede listaUsuarios = new Rede();
        while(true){

            Scanner input = new Scanner(System.in);
            boolean contaCriada = false;
            System.out.println("Deseja criar uma conta? 1 - Sim | 2 - Nao");
            int msg = input.nextInt();
            Conta user;
            if(msg == 1){
                user = Main.addConta(listaUsuarios);
                System.out.println("Criada");

                System.out.println("Deseja enviar mensagem? 1 - Sim | 2 - Nao");
                int infoMsg = input.nextInt();
                if(infoMsg==1){
                    System.out.println("Digite o nome do usuario que voce deseja enviar: ");
                    String nomeUsuario = input.next();
                    System.out.println("Digite a mensagem:");
                    String mensagemPEnvio = input.next();
                    listaUsuarios.enviarMensagem(mensagemPEnvio,user,nomeUsuario);
                }
                else{
                    break;
                }
            }
            else{
                break;
            }
            System.out.println(listaUsuarios.getListaUsuariosRede());

        }


    }
    public static Conta addConta(Rede lista){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o login");
        String login = input.next();
        System.out.println("Digite o nome");
        String nome = input.next();
        System.out.println("Digite a senha");
        String senha = input.next();
        Conta usuario = new Conta(nome,login,senha);

        System.out.println("Deseja mudar o perfil? 1 - Sim | 2 - Nao");
        int msgPerfil = input.nextInt();
        if(msgPerfil==1){
            modificarPerfil(usuario);
        }

        lista.addConta(usuario);

        return usuario;

    }

    public static void modificarPerfil(Conta usuario){
        Scanner input = new Scanner(System.in);

        System.out.println("O que voce deseja modificar? 1 - Cidade Atual | 2 - Cidade de Nascimento | 3 - Bio | 4 - Data de Nascimento  | 6 - Nada");
        int msg = input.nextInt();
        String cidadeAtual = "";
        String cidadeNasc = "";
        String bio = "";
        String dtNascimento = "";

        while(msg!=6){
            if (msg==1){
                System.out.println("Digite a cidade atual");
                cidadeAtual = input.next();

            }
            else if(msg==2){
                System.out.println("Digite a cidade de nascimento");
                cidadeNasc = input.next();
            }
            else if(msg==3){

                System.out.println("Digite a bio");
                bio = input.next();
            }
            else if(msg==4){
                System.out.println("Digite a data de nascimento");
                dtNascimento = input.next();
            }
            System.out.println("O que voce deseja modificar? 1 - Cidade Atual | 2 - Cidade de Nascimento | 3 - Bio | 4 - Data de Nascimento  | 6 - Nada");
            msg = input.nextInt();

        }




        usuario.setPerfil(cidadeAtual,cidadeNasc,dtNascimento,bio);

    }
}
