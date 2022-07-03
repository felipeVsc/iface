import excecoes.EntradaIncorretaException;

import java.util.Scanner;

public class RecuperacaoInfo {
    Conta usuario;
    Rede rede;

    public RecuperacaoInfo(Conta usuario, Rede rede) {
        this.usuario = usuario;
        this.rede = rede;
    }

    public void retrieveAll(){
        // Info do Usuario

        System.out.println(usuario.toString());
        //
        System.out.println("Comunidades que voce eh membro:");
        printarComunidadesMembro();

        System.out.println("Comunidades que voce eh admin:");
        printarComunidadesAdmin();

        System.out.println("Amizades:");
        printarAmizades();

        System.out.println("Pedido de Amizades:");
        printarPedidoAmizade();

        System.out.println("Mensagens:");
        printarMensagens();

        System.out.println("Mensagens no Feed");
        printarMensagensFeed();
    }

    // Printar as coisas da lista de amizade, comunidade e mensagens

    public void printarComunidadesMembro(){

        try{
            this.usuario.getListaComunidadesMembro().forEach(comunidadeMembro -> System.out.println(comunidadeMembro.toString()));
        } catch (NullPointerException e){
            System.out.println("Voce nao eh membro de nenhuma comunidade");
        }

    }

    public void printarComunidadesAdmin(){
        try{
            this.usuario.getListaComunidadesAdmin().forEach(comunidadeAdmin -> System.out.println(comunidadeAdmin.toString()));
        }catch (NullPointerException e){
            System.out.println("Voce nao eh administrador de nenhuma comunidade");
        }
    }

    public void printarAmizades(){
        System.out.println("Seus amigos:");
        try{
            this.usuario.getListaAmigos().forEach(usuarioAmizade -> System.out.println(usuarioAmizade.getNomeConta()));
        } catch (NullPointerException e){
            System.out.println("Voce nao tem nenhuma amizade! Triste!");
        }
    }

    public void printarPedidoAmizade(){
        try{
            this.usuario.getListaPedidoAmizade().forEach(usuarioAmizadePedido -> System.out.println(usuarioAmizadePedido.getNomeConta()));
        }catch (NullPointerException e){
            System.out.println("Voce nao tem nenhum pedido de amizade.");
        }
    }
    public void printarMensagens(){
        // Aqui tem que ver se vai as listas de feed
        System.out.println("Suas mensagens:");
        try{
            this.usuario.getListaMsgs().forEach(mensagem -> System.out.println(mensagem.toString()));
        } catch (NullPointerException e){
            System.out.println("Voce nao tem mensagens");
        }
    }

    public void printarMensagensComunidade(Scanner input) throws EntradaIncorretaException{
        // Printar as mensagens de uma comunidade especifica
        printarComunidadesAdmin();
        printarComunidadesMembro();

        System.out.println("Qual o nome da comunidade que você deseja ver as mensagens?");
        input.nextLine();
        String nomeComunidadeMensagem = input.nextLine();
        try{
            Comunidade comunidadeMsg = this.rede.listaComunidades.getComunidadePeloNome(nomeComunidadeMensagem);
            if (comunidadeMsg==null){
                throw new EntradaIncorretaException("Comunidade nao existe");
            }
            else{
                comunidadeMsg.getListaMsgComunidade().forEach(mensagem -> System.out.println(mensagem.toString()));
            }
        } catch (EntradaIncorretaException e) {
            System.out.println(e.getMessage());
        }

    }
    public void printarMensagensFeed(){
        this.rede.feedNoticias.listarMensagens(this.usuario);
    }
}
