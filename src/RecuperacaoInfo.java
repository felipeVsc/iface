import excecoes.EntradaIncorretaException;

import java.util.Scanner;

public class RecuperacaoInfo {
    Conta usuario;
    Rede rede;
    Scanner input = new Scanner(System.in);
    public RecuperacaoInfo(Conta usuario, Rede rede) {
        this.usuario = usuario;
        this.rede = rede;
    }

    public boolean retrieveAll(){
        // Info do Usuario

        System.out.println("Voce:");
        System.out.println(this.usuario);
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

        return true;
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

    public boolean printarAmizades(){
        System.out.println("Seus amigos:");
        try{
            this.usuario.getListaAmigos().forEach(usuarioAmizade -> System.out.println(usuarioAmizade.getNomeConta()));
        } catch (NullPointerException e){
            System.out.println("Voce nao tem nenhuma amizade! Triste!");
            return false;
        }
        return true;
    }

    public void printarPedidoAmizade(){
        try{
            this.usuario.getListaPedidoAmizade().forEach(usuarioAmizadePedido -> System.out.println(usuarioAmizadePedido.getNomeConta()));
        }catch (NullPointerException e){
            System.out.println("Voce nao tem nenhum pedido de amizade.");
        }
    }
    public boolean printarMensagens(){
        // Aqui tem que ver se vai as listas de feed
        System.out.println("Suas mensagens:");
        try{
            this.usuario.getListaMsgs().forEach(mensagem -> System.out.println(mensagem.toString()));
        } catch (NullPointerException e){
            System.out.println("Voce nao tem mensagens");
            return false;
        }

        return true;
    }

    public boolean printarMensagensComunidade() throws EntradaIncorretaException{
        // Printar as mensagens de uma comunidade especifica
        printarComunidadesAdmin();
        printarComunidadesMembro();

        System.out.println("Qual o nome da comunidade que você deseja ver as mensagens?");
        String nomeComunidadeMensagem = input.next();
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
            return false;
        }

        return true;

    }
    public void printarMensagensFeed(){
        this.rede.feedNoticias.listarMensagens();
    }
}
