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

        if(this.usuario.getListaComunidadesMembro()!=null){
            this.usuario.getListaComunidadesMembro().forEach(comunidadeMembro -> System.out.println(comunidadeMembro.toString()));
        }

    }

    public void printarComunidadesAdmin(){
        if(this.usuario.getListaComunidadesAdmin()!=null) {
            this.usuario.getListaComunidadesAdmin().forEach(comunidadeAdmin -> System.out.println(comunidadeAdmin.toString()));
        }
    }

    public void printarAmizades(){
        if(this.usuario.getListaAmigos()!=null) {
            this.usuario.getListaAmigos().forEach(usuarioAmizade -> System.out.println(usuarioAmizade.getNomeConta()));
        }
    }

    public void printarPedidoAmizade(){
        if(this.usuario.getListaPedidoAmizade()!=null) {
            this.usuario.getListaPedidoAmizade().forEach(usuarioAmizadePedido -> System.out.println(usuarioAmizadePedido.getNomeConta()));
        }
    }
    public void printarMensagens(){
        // Aqui tem que ver se vai as listas de feed
        if(this.usuario.getListaMsgs()!=null) {
            this.usuario.getListaMsgs().forEach(mensagem -> System.out.println(mensagem.toString()));
        }
    }

    public void printarMensagensFeed(){
        this.rede.feedNoticias.listarMensagens(this.usuario);
    }
}
