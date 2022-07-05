import excecoes.EntradaIncorretaException;

import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws EntradaIncorretaException, ParseException {

        System.out.println("Bem-vindo.");
        Scanner input = new Scanner(System.in);


        RedeSocial redesocial = new RedeSocial();

        while(true){
            System.out.println("digite numers 1 - conta | 2 - comunidade | 3 - listar informacoes | 4 - sair da conta");

            MenuFactory menu = new MenuFactory(redesocial);
            int entrada = input.nextInt();
            if(entrada==99){
                break;
            }
            Menu teste = (Menu) menu.getMenu(entrada);
            redesocial = teste.executar(menu.redesocial);

        }


//        Rede listaUsuarios = new Rede();
//        Login sistemaLogin = new Login();
//
//        Conta usuarioOn = sistemaLogin.processoLogin(listaUsuarios);
//        RecuperacaoInfo recuperacaoInfo = new RecuperacaoInfo(usuarioOn,listaUsuarios);

//        RedeSocial redesocial = new RedeSocial();
//        Conta usuarioOn = redesocial.getUsuario();
//        Rede listaUsuarios = redesocial.getListaUsuarios();
//        RecuperacaoInfo recuperacaoInfo = redesocial.getRecuperacaoInfo();
//        boolean continua = true;
//        while (continua) {
//            if(usuarioOn==null){
//                System.out.println("Faça o login!");
//                usuarioOn = sistemaLogin.processoLogin(listaUsuarios);
//            }
//            else {
//                if(!listaUsuarios.listaUsuariosRede.contains(usuarioOn)){
//                    listaUsuarios.listaUsuariosRede.add(usuarioOn);
//                }
//
//                System.out.println("Logado no sistema! digite as opcoes");
//
//                System.out.println("0 - Sair da Conta | 1 - Modificar Perfil | 2 - Enviar Mensagem para Amigo\n3 - Enviar Mensagem para Comunidade | 4 - Enviar Pedido de Amizade | 5 - Entrar em Comunidade\n6 - Criar Comunidade | 7 - Listar Pedidos de Amizade | 8 - Listar Informacoes\n9 - Terminar Conta | 10 - Lista de Mensagens | 11 - Lista de Amigos\n12 - Mensagens Comunidade | 13 - Enviar mensagens no feed | 14 - Listar Mensagem Feed | 15 - Remover membro comunidade\n | 16 - Requisicoes Comunidade | 99 - sair do programa");
//                int opcao = input.nextInt();
//                switch (opcao) {
//                    case 0:
//                        // sair da conta
//                        System.out.println("Voce saiu da conta");
//                        usuarioOn = null;
//                        break;
//                    case 1:
//                        // modificar perfil
//                        usuarioOn.modificarPerfil();
//                        break;
//
//                    case 2:
//                        // enviar msg para amigo
//                        listaUsuarios.enviarMensagemConta();
//                        break;
//                    case 3:
//                        // enviar msg para comunidade
//                        listaUsuarios.listaComunidades.enviarMensagemParaComunidade();
//                        break;
//                    case 4:
//                        // enviar pedido de amizade
//                        listaUsuarios.PedidoContasAmizade();
//                        break;
//                    case 5:
//                        // entrar na comunidade
//                        listaUsuarios.listaComunidades.entrarComunidade();
//                        break;
//                    case 6:
//                        // criar comunidade
//                        listaUsuarios.listaComunidades.criarComunidade();
//                        break;
//                    case 7:
//                        // Listar pedido de amizades
//                        usuarioOn.requisicoesAmizade();
//                        break;
//                    case 8:
//                        // Listar Informacoes
//                        // acho que isso aqui vai quebrar
//                        recuperacaoInfo.retrieveAll();
//                        break;
//                    case 9:
//                        // Terminar conta
//                        // implementar aqui para que todas as informacoes sejam removidas
//                        listaUsuarios.removerInfo(listaUsuarios,usuarioOn);
//                        usuarioOn = null;
//                        break;
//                    case 10:
//                        // lista de msgs
//                        recuperacaoInfo.printarMensagens();
//                        break;
//                    case 11:
//                        // lista de amigos
//                        recuperacaoInfo.printarAmizades();
//                        break;
//                    case 12:
//                        // mensagem das comunidades
//                        recuperacaoInfo.printarMensagensComunidade();
//                        break;
//                    case 13:
//                        // Mandar mensagen no feed de noticias
//                        listaUsuarios.feedNoticias.addMsgFeed();
//                        break;
//                    case 14:
//                        // Listar Feed de Noticias
//                        listaUsuarios.feedNoticias.listarMensagens();
//                        break;
//                    case 15:
//                        // remover membro da comunidade
//                        listaUsuarios.listaComunidades.remocaoMembros();
//                        break;
//                    case 16:
//                        // pedidos de entrar na comunidade
//                        listaUsuarios.listaComunidades.listarPedidosEntradaComunidade();
//                        break;
//                    case 99:
//
//                        System.out.println("Tchau!");
//                        continua=false;
//                        break;
//                }
//            }


//        }

    }
}
