import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Rede listaUsuarios = new Rede();

        Scanner input = new Scanner(System.in);
        System.out.println("Bem-vindo.");
        Conta usuarioOn = processoLogin(input,listaUsuarios);
        while (true) {
            if(usuarioOn==null){
                System.out.println("Faça o login!");
                usuarioOn = processoLogin(input,listaUsuarios);
            }
            else {
                System.out.println("Dentro, digite as opcoes");
                /*
                System.out.println("0 - Sair da Conta | 1 - Modificar Perfil | 2 - Enviar Mensagem para Amigo
                \n3 - Enviar Mensagem para Comunidade | 4 - Enviar Pedido de Amizade | 5 - Entrar em Comunidade\n6
                 - Criar Comunidade | 7 - Listar Pedidos de Amizade | 8 - Listar Informacoes\n9 - Terminar Conta
                 | 10 - Lista de Mensagens | 11 - Lista de Amigos\n12 - Mensagens Comunidade |
                13 - Enviar mensagens no feed | 14 - Listar Mensagem Feed | 15 - Remover membro comunidade");
                 */
                System.out.println("0 - Sair da Conta | 1 - Modificar Perfil | 2 - Enviar Mensagem para Amigo\n3 - Enviar Mensagem para Comunidade | 4 - Enviar Pedido de Amizade | 5 - Entrar em Comunidade\n6 - Criar Comunidade | 7 - Listar Pedidos de Amizade | 8 - Listar Informacoes\n9 - Terminar Conta | 10 - Lista de Mensagens | 11 - Lista de Amigos\n12 - Mensagens Comunidade | 13 - Enviar mensagens no feed | 14 - Listar Mensagem Feed | 15 - Remover membro comunidade\n | 16 - Requisicoes Comunidade");
                int opcao = input.nextInt();
                switch (opcao) {
                    case 0:
                        // sair da conta
                        System.out.println("Voce saiu da conta");
                        usuarioOn = null;
                        break;
                    case 1:
                        // modificar perfil
                        System.out.println("Modificar perfil");
                        modificarPerfil(usuarioOn);
                        break;
                    case 2:
                        // enviar msg para amigo
                        System.out.println("Digite o nome do usuario que deseja enviar a mensagem");
                        String nomeUsuarioReceptor = input.next();
                        System.out.println("Digite a mensagem");
                        input.nextLine();
                        String msg = input.nextLine();
                        listaUsuarios.enviarMensagemConta(msg,usuarioOn,listaUsuarios.getConta(nomeUsuarioReceptor));
                        break;
                    case 3:
                        // enviar msg para comunidade
                        for (Comunidade cmd : usuarioOn.getListaComunidadesMembro()){
                            System.out.println(cmd.getNomeComunidade());
                        }
                        System.out.println("Qual o nome da comunidade que você deseja enviar a mensagem?");
                        input.nextLine();

                        String nomeComunidade = input.nextLine();
                        System.out.println("Qual a mensagem?");
                        String msgCom = input.nextLine();
                        Comunidade cmdParaEnviar = null;
                        for (Comunidade cmd : usuarioOn.getListaComunidadesMembro()){
                            if(cmd.getNomeComunidade().equals(nomeComunidade)){
                                cmdParaEnviar = cmd;
                            }
                        }
                        if(cmdParaEnviar!=null){
                            cmdParaEnviar.enviarMsg(msgCom,usuarioOn,cmdParaEnviar);
                        }
                        else{
                            System.out.println("Voce nao faz parte dessa comunidade");
                        }


                        break;
                    case 4:
                        // enviar pedido de amizade
                        System.out.println("Para quem você deseja enviar o pedido de amizade?");
                        input.nextLine();
                        String nomeUsuarioRecebedor = input.nextLine();
                        listaUsuarios.pedirAmizade(usuarioOn,listaUsuarios.getConta(nomeUsuarioRecebedor));
                        break;
                    case 5:
                        // entrar na comunidade
                        System.out.println("Digite o nome da comunidade que deseja entrar");
                        input.nextLine();
                        String nomeComNova = input.nextLine();
                        Comunidade cmdEntrada = listaUsuarios.getComunidadePeloNome(nomeComNova);
                        // aqui tem que adicionar a parte de pedirRequisicao
                        cmdEntrada.pedirEntradaComunidade(usuarioOn);
                        System.out.println("O admin da comunidade recebeu o seu pedido");
                        break;
                    case 6:
                        // criar comunidade
                        System.out.println("Digite o nome da comunidade que deseja criar");
                        input.nextLine();
                        String nomeDaComunidade = input.nextLine();
                        System.out.println("Digite a descricao da comunidade");
                        input.nextLine();
                        String descricaoCom = input.nextLine();
                        Comunidade novaCom = usuarioOn.criarComunidade(nomeDaComunidade,descricaoCom);
                        novaCom.criarAdminComunidade(usuarioOn,novaCom);
                        listaUsuarios.addComunidade(novaCom);
                        break;
                    case 7:
                        // Listar pedido de amizades
                        System.out.println("Os seus pedidos de amizade são:");
                        usuarioOn.requisicoesAmizade();
                        break;
                    case 8:
                        // Listar Informacoes
                        ArrayList<ArrayList> info = usuarioOn.recuperarInfo(listaUsuarios,usuarioOn);
                        for (ArrayList lista: info) {
                            if(lista.size()!=0){

                                for(int x=0;x<lista.size();x++){

                                    if(lista.get(0) instanceof Conta){
                                        System.out.println("Amizades");

                                        Conta cnt = (Conta) lista.get(x);
                                        String aceito = usuarioOn.getListaAmigos().contains(cnt) ? "Amigo":"Pedido";
                                        String resposta = cnt.getNomeConta()+" status: "+aceito;
                                        System.out.println(resposta);


                                    }
                                    else if( lista.get(0) instanceof Comunidade){
                                        System.out.println("Comunidades");

                                        Comunidade cnt = (Comunidade) lista.get(x);

                                        String adminSimNao = cnt.getUsuarioAdminComunidade().equals(usuarioOn.getNomeConta()) ? "Admin":"Membro";
                                        String msfDefault = cnt.getNomeComunidade()+"  status: "+adminSimNao;
                                        System.out.println(msfDefault);

                                    }
                                    else if(lista.get(0) instanceof Mensagem){
                                        System.out.println("Mensagens");

                                        Mensagem cnt = (Mensagem) lista.get(x);
//                                        String mensagemPadrao = cnt.getMensagem()+" by: "+cnt.getUsuarioEnvio().getNomeConta();
                                        System.out.println(cnt.toString());
                                    }
                                    else{
                                        String entradas = (String) lista.get(x);
                                        System.out.println(entradas);
                                    }

                                }


                            }

                        }

                        break;
                    case 9:
                        // Terminar conta
                        // implementar aqui para que todas as informacoes sejam removidas
                        listaUsuarios.removerInfo(listaUsuarios,usuarioOn);
                        System.out.println("Usuario Removido");
                        usuarioOn = null;
                        break;
                    case 10:
                        // lista de msgs
                        System.out.println("Suas mensagens:");
                        for (Mensagem mensagemInterna :
                                usuarioOn.getListaMsgs()) {
//                            String defaultMsg = mensagemInterna.getUsuarioEnvio().getNomeConta()+" enviou: "+mensagemInterna.getMensagem();
                            System.out.println(mensagemInterna.toString());
                        }
                        break;
                    case 11:
                        // lista de amigos
                        System.out.println("Seus amigos:");
                        for (Conta contaAmigoInterno : usuarioOn.getListaAmigos()){
                            System.out.println(contaAmigoInterno.getNomeConta());
                        }
                        break;
                    case 12:
                        // mensagem das comunidades
                        for (Comunidade cmd : usuarioOn.getListaComunidadesMembro()){
                            System.out.println(cmd.getNomeComunidade());
                        }
                        System.out.println("Qual o nome da comunidade que você deseja ver as mensagens?");
                        input.nextLine();
                        String nomeComunidadeMensagem = input.nextLine();

                        Comunidade comunidadeMsg = listaUsuarios.getComunidadePeloNome(nomeComunidadeMensagem);

                        for (Mensagem mensagemComunidadeVer : comunidadeMsg.getListaMsgComunidade()
                                ) {

//                            String msgDefaultComunidade = mensagemComunidadeVer.getUsuarioEnvio().getNomeConta()+"  enviou: "+mensagemComunidadeVer.getMensagem();
                            System.out.println(mensagemComunidadeVer.toString());
                        }
                        break;
                    case 13:
                        // Mandar mensagen no feed de noticias
                        System.out.println("Digite a mensagem que voce deseja enviar");
                        input.nextLine();
                        String msgFeedNoticiasEnvio = input.nextLine();
                        System.out.println("Qual a privacidade? 1 - Todos | 2 - Amigos");
                        int entradaPrivacidade = input.nextInt();
                        boolean privMensagemFeed = entradaPrivacidade == 1;
                        listaUsuarios.addMsgFeed(msgFeedNoticiasEnvio,usuarioOn,privMensagemFeed,listaUsuarios);
                        System.out.println("Mensagem Enviada");
                        break;
                    case 14:
                        // Listar Feed de Noticias
                        System.out.println("Feed de Noticias:");
                        listaUsuarios.listarMensagens(usuarioOn);
                        break;
                    case 15:
                        // remover membro da comunidade
                        System.out.println("Remocao de membro; Digite o nome da comunidade");
                        String nomeComunidadeRemocao = input.next();
                        Comunidade comunidadeRemocao = listaUsuarios.getComunidadePeloNome(nomeComunidadeRemocao);
                        if(comunidadeRemocao.getUsuarioAdminComunidade().equals(usuarioOn.getNomeConta())){
                            // ele eh o admin
                            System.out.println("Digite o nome do usuario a ser removido");
                            String usuarioRemover = input.next();
                            Conta contaRemover = listaUsuarios.getConta(usuarioRemover);
                            if(comunidadeRemocao.listaUsuariosComunidade.contains(contaRemover)){
                                // remover
                                comunidadeRemocao.listaUsuariosComunidade.remove(contaRemover);
                                contaRemover.listaComunidadesMembro.remove(comunidadeRemocao);

                            }
                            else{
                                System.out.println("usuario nao existe");
                            }
                        }else{
                            System.out.println("Voce nao eh o administrador dessa comunidade");
                        }
                        break;
                    case 16:
                        // pedidos de entrar na comunidade
                        System.out.println("Pedidos | Digite o nome da comunidade");
                        input.nextLine();
                        String nomeComunidadeAdmin = input.nextLine();
                        Comunidade comunidadeAdmin = ehAdminComunidade(nomeComunidadeAdmin,usuarioOn,listaUsuarios);
                        if(comunidadeAdmin!=null){
                            // listar os pedidos

                            comunidadeAdmin.usuarioAdminComunidade.addMembroComunidade(comunidadeAdmin);
                        }
                        else{
                            System.out.println("Voce nao eh o administrador");
                        }
                        break;
                }
            }


        }

    }

    public static Comunidade ehAdminComunidade(String nomecmd, ContaGeral usuario, Rede listaUser){
        Comunidade comunidadeAdmin = listaUser.getComunidadePeloNome(nomecmd);
        if(comunidadeAdmin.getUsuarioAdminComunidade().equals(usuario.getNomeConta())){
            return comunidadeAdmin;
        }
        return null;
    }

    public static Conta fazerLogin(Rede lista){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome do usuario");
        String login = input.next();
        System.out.println("Digite a senha");
        String senha = input.next();
        Conta usuario;
        if(lista.getConta(login)!=null){
            usuario = lista.getConta(login);
            if(usuario.getSenhaConta().equals(senha)){
                return usuario;
            }else{
                System.out.println("senha incorreta");
                return null;
            }

        }
        else{
            System.out.println("Usuario nao existente");
            return null;
        }


    }

    public static Conta processoLogin(Scanner input, Rede listaUsuarios){
        System.out.println("Deseja entrar ou se cadastrar? 1 - Logar | 2 - Criar conta");
        int opcaoLogin = input.nextInt();
        Conta usuarioOn;
        switch (opcaoLogin) {
            case 1:
                // logar na conta;
                usuarioOn = fazerLogin(listaUsuarios);
                return usuarioOn;

            case 2:
                // criar conta;
                usuarioOn = addConta(listaUsuarios);
                return usuarioOn;

            default:
                return null;

        }
    }

    public static Conta addConta(Rede lista){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o login");
        String login = input.next();
        System.out.println("Digite o nome");
        input.nextLine();
        String nome = input.nextLine();
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
            // switch-case aqui
            if (msg==1){
                System.out.println("Digite a cidade atual");
                input.nextLine();
                cidadeAtual = input.nextLine();

            }
            else if(msg==2){
                System.out.println("Digite a cidade de nascimento");
                input.nextLine();
                cidadeNasc = input.nextLine();
            }
            else if(msg==3){

                System.out.println("Digite a bio");
                input.nextLine();
                bio = input.nextLine();
            }
            else if(msg==4){
                System.out.println("Digite a data de nascimento no formato DD/MM/AAAA");
                dtNascimento = input.next();
            }
            System.out.println("O que voce deseja modificar? 1 - Cidade Atual | 2 - Cidade de Nascimento | 3 - Bio | 4 - Data de Nascimento  | 6 - Nada");
            msg = input.nextInt();

        }

        usuario.setPerfil(cidadeAtual,cidadeNasc,dtNascimento,bio);

    }
}
