import excecoes.EntradaIncorretaException;

import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws EntradaIncorretaException, ParseException {

        Rede listaUsuarios = new Rede();
        Login sistemaLogin = new Login();

        Scanner input = new Scanner(System.in);
        System.out.println("Bem-vindo.");

        boolean continua = true;
        Conta usuarioOn = sistemaLogin.processoLogin(input,listaUsuarios);

        RecuperacaoInfo recuperacaoInfo = new RecuperacaoInfo(usuarioOn,listaUsuarios);
        while (continua) {
            if(usuarioOn==null){
                System.out.println("Faça o login!");
                usuarioOn = sistemaLogin.processoLogin(input,listaUsuarios);
            }
            else {
                if(!listaUsuarios.listaUsuariosRede.contains(usuarioOn)){
                    listaUsuarios.listaUsuariosRede.add(usuarioOn);
                }
                System.out.println("Dentro, digite as opcoes");
                /*
                System.out.println("0 - Sair da Conta | 1 - Modificar Perfil | 2 - Enviar Mensagem para Amigo
                \n3 - Enviar Mensagem para Comunidade | 4 - Enviar Pedido de Amizade | 5 - Entrar em Comunidade\n6
                 - Criar Comunidade | 7 - Listar Pedidos de Amizade | 8 - Listar Informacoes\n9 - Terminar Conta
                 | 10 - Lista de Mensagens | 11 - Lista de Amigos\n12 - Mensagens Comunidade |
                13 - Enviar mensagens no feed | 14 - Listar Mensagem Feed | 15 - Remover membro comunidade");
                 */

                System.out.println("0 - Sair da Conta | 1 - Modificar Perfil | 2 - Enviar Mensagem para Amigo\n3 - Enviar Mensagem para Comunidade | 4 - Enviar Pedido de Amizade | 5 - Entrar em Comunidade\n6 - Criar Comunidade | 7 - Listar Pedidos de Amizade | 8 - Listar Informacoes\n9 - Terminar Conta | 10 - Lista de Mensagens | 11 - Lista de Amigos\n12 - Mensagens Comunidade | 13 - Enviar mensagens no feed | 14 - Listar Mensagem Feed | 15 - Remover membro comunidade\n | 16 - Requisicoes Comunidade | 99 - sair do programa");
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
                        usuarioOn.modificarPerfil();
                        break;

                    case 2:
                        // enviar msg para amigo
                        try{
                            listaUsuarios.enviarMensagemConta(input,usuarioOn);
                        } catch(NullPointerException e){
                            System.out.println("Usuario nao existente");
                            break;
                        }

                        break;
                    case 3:
                        // enviar msg para comunidade
                        recuperacaoInfo.printarComunidadesMembro();
                        recuperacaoInfo.printarComunidadesAdmin();

                        System.out.println("Qual o nome da comunidade que você deseja enviar a mensagem?");
                        input.nextLine();

                        String nomeComunidade = input.nextLine();
                        System.out.println("Qual a mensagem?");
                        String msgCom = input.nextLine();

                        Comunidade cmdParaEnviar = listaUsuarios.listaComunidades.getComunidadePeloNome(nomeComunidade);

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
                        try{
                            Conta usuarioRecebedorPedido = listaUsuarios.getConta(nomeUsuarioRecebedor);
                            AmizadePedido pedidoAmizade = new AmizadePedido(usuarioOn,usuarioRecebedorPedido);
                            listaUsuarios.pedirAmizade(pedidoAmizade);
                        } catch (NullPointerException e){
                            System.out.println("Usuario nao existe");
                        }

                        break;
                    case 5:
                        // entrar na comunidade
                        System.out.println("Digite o nome da comunidade que deseja entrar");
                        input.nextLine();
                        String nomeComNova = input.nextLine();
                        try{
                            Comunidade cmdEntrada = listaUsuarios.listaComunidades.getComunidadePeloNome(nomeComNova);
                            if (cmdEntrada==null){
                                throw new EntradaIncorretaException("Comunidade nao existe");
                            } else {
                                cmdEntrada.pedirEntradaComunidade(usuarioOn);
                                System.out.println("O admin da comunidade recebeu o seu pedido");
                            }
                        } catch (EntradaIncorretaException e){
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 6:
                        // criar comunidade
                        listaUsuarios.listaComunidades.criarComunidade(input,usuarioOn);
                        break;

                    case 7:
                        // Listar pedido de amizades
                        System.out.println("Os seus pedidos de amizade são:");
                        usuarioOn.requisicoesAmizade();
                        break;
                    case 8:
                        // Listar Informacoes
                        // acho que isso aqui vai quebrar

                        recuperacaoInfo.retrieveAll();
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
                        recuperacaoInfo.printarMensagens();
                        break;
                    case 11:
                        // lista de amigos
                        System.out.println("Seus amigos:");
                        recuperacaoInfo.printarAmizades();
                        break;
                    case 12:
                        // mensagem das comunidades
                        recuperacaoInfo.printarComunidadesAdmin();
                        recuperacaoInfo.printarComunidadesMembro();

                        System.out.println("Qual o nome da comunidade que você deseja ver as mensagens?");
                        input.nextLine();
                        String nomeComunidadeMensagem = input.nextLine();
                        try{
                            Comunidade comunidadeMsg = listaUsuarios.listaComunidades.getComunidadePeloNome(nomeComunidadeMensagem);
                            if (comunidadeMsg==null){
                                throw new EntradaIncorretaException("Comunidade nao existe");
                            }
                            else{
                                comunidadeMsg.getListaMsgComunidade().forEach(mensagem -> System.out.println(mensagem.toString()));
                            }
                        } catch (EntradaIncorretaException e) {
                            System.out.println(e.getMessage());
                        }


                        break;
                    case 13:
                        // Mandar mensagen no feed de noticias

                        listaUsuarios.feedNoticias.addMsgFeed(input,usuarioOn, listaUsuarios);
                        break;
                    case 14:
                        // Listar Feed de Noticias
                        System.out.println("Feed de Noticias:");
                        listaUsuarios.feedNoticias.listarMensagens(usuarioOn);
                        break;
                    case 15:
                        // remover membro da comunidade
                        System.out.println("Remocao de membro; Digite o nome da comunidade");
                        String nomeComunidadeRemocao = input.next();
                        Comunidade comunidadeRemocao;
                        try{
                            comunidadeRemocao = listaUsuarios.listaComunidades.getComunidadePeloNome(nomeComunidadeRemocao);
                            if(comunidadeRemocao==null){
                                throw new EntradaIncorretaException("Comunidade nao existe");
                            }

                        } catch (EntradaIncorretaException e){
                            System.out.println(e.getMessage());
                            break;
                        }

                        boolean ehAdminComunidade = listaUsuarios.listaComunidades.ehAdminDaComunidade(usuarioOn,comunidadeRemocao);


                        if(ehAdminComunidade){
                            // ele eh o admin
                            comunidadeRemocao.usuarioAdminComunidade.removerMembroComunidade(listaUsuarios,input);
                        }else{
                            System.out.println("Voce nao eh o administrador dessa comunidade");
                        }
                        break;
                    case 16:
                        // pedidos de entrar na comunidade
                        System.out.println("Pedidos | Digite o nome da comunidade");
                        input.nextLine();
                        String nomeComunidadeAdmin = input.nextLine();

                        Comunidade comunidadePedidosEntrada = listaUsuarios.listaComunidades.getComunidadePeloNome(nomeComunidadeAdmin);
                        boolean ehComunidadeAdmin = listaUsuarios.listaComunidades.ehAdminDaComunidade(usuarioOn,comunidadePedidosEntrada);
                        if(ehComunidadeAdmin){
                            // listar os pedidos
                            comunidadePedidosEntrada.usuarioAdminComunidade.addMembroComunidade();
                        }
                        else{
                            System.out.println("Voce nao eh o administrador");
                        }
                        break;
                    case 99:
                        System.out.println("Tchau!");
                        continua=false;
                        break;
                }
            }


        }

    }
}
