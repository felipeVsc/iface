import java.util.ArrayList;

public class Rede {

    ArrayList<Conta> listaUsuariosRede;
    ArrayList<Comunidade> listaComunidades;
    ArrayList<MensagemFeed> feedNoticias;

    public Rede(){
        this.listaUsuariosRede = new ArrayList<>();
        this.listaComunidades = new ArrayList<>();
        this.feedNoticias = new ArrayList<>();
    }

    public void addMsgFeed(String msg, Conta usuarioEnvio, boolean privacidade){
        MensagemFeed novaMsgFeed = new MensagemFeed(msg,usuarioEnvio,privacidade);
        this.feedNoticias.add(novaMsgFeed);
    }

    public void removerMsgUsuario(Conta usuarioRemocao){

        ArrayList<MensagemFeed> mensagemParaRemocao = new ArrayList<>();
        for(int x=0;x<this.feedNoticias.size();x++){
            if(feedNoticias.get(x).getUsuarioEnvio().equals(usuarioRemocao)){
                mensagemParaRemocao.add(feedNoticias.get(x));
            }
        }

        for (MensagemFeed msgFeedRemocao :
                mensagemParaRemocao) {
            this.feedNoticias.remove(msgFeedRemocao);
        }
    }

    public void listarMensagens(Conta usuarioRequisicao){
        // listar as mensagens, tomando cuidado para saber se quem enviou é amigo seu ou nao
        for (MensagemFeed msgNoFeedMostrar :
                this.feedNoticias) {
            if(msgNoFeedMostrar.isPrivacidade()){
                String msgDefaultFeed = msgNoFeedMostrar.getMensagem()+"  por: "+msgNoFeedMostrar.getUsuarioEnvio().getNomeConta();
                System.out.println(msgDefaultFeed);
            }
            else{
                if(msgNoFeedMostrar.getUsuarioEnvio().getListaAmigos().contains(usuarioRequisicao) || msgNoFeedMostrar.getUsuarioEnvio().equals(usuarioRequisicao)){
                    String msgDefaultFeed = msgNoFeedMostrar.getMensagem()+"  por: "+msgNoFeedMostrar.getUsuarioEnvio().getNomeConta();
                    System.out.println(msgDefaultFeed);
                }
            }

        }
    }

    public ArrayList<MensagemFeed> getFeedNoticias() {
        return feedNoticias;
    }

    public void setFeedNoticias(ArrayList<MensagemFeed> feedNoticias) {
        this.feedNoticias = feedNoticias;
    }

    public String getAdminComunidade(Comunidade comunidade){

        for (Comunidade cmd : listaComunidades){

            if(comunidade==cmd){
                return cmd.getUsuarioAdminComunidade();
            }

        }

        return null;
    }

    public Comunidade getComunidadePeloNome(String nome){
        for (Comunidade cmd : this.listaComunidades){
            if(cmd.getNomeComunidade().equals(nome)){
                return cmd;
            }
        }
        return null;
    }


    public void addComunidade(Comunidade novaComunidade){
        this.listaComunidades.add(novaComunidade);
    }

    public void removerComunidade(Comunidade nomeComunidade){
        this.listaComunidades.remove(nomeComunidade);
    }

    public ArrayList<Comunidade> getListaComunidades() {
        return listaComunidades;
    }

    public void setListaComunidades(ArrayList<Comunidade> listaComunidades) {
        this.listaComunidades = listaComunidades;
    }

    public ArrayList<Conta> getListaUsuariosRede() {
        return listaUsuariosRede;
    }

    public void setListaUsuariosRede(ArrayList<Conta> listaUsuariosRede) {
        this.listaUsuariosRede = listaUsuariosRede;
    }

    public Conta getConta(String nomeUsuario){

        for (Conta contas : listaUsuariosRede){

            if(contas.getNomeConta().equals(nomeUsuario)){
               return contas;
            }

        }
        return null;
    }


    public void addConta(Conta contaUsuario){
        this.listaUsuariosRede.add(contaUsuario);
    }

    public ArrayList<ArrayList> retrieveAllInfo(String nomeUsuario){

        return getConta(nomeUsuario).retrieveAllConta();

    }

    public void pedirAmizade(Conta nomeContaEnvio, Conta nomeContaRecebedor){

        nomeContaRecebedor.listaPedidoAmizade.add(nomeContaEnvio);
    }

    public void enviarMensagemConta(String msg, Conta usuarioEnvio, Conta usuarioRec){
        Mensagem mensagem = new Mensagem(msg,usuarioEnvio);
        usuarioRec.listaMsgs.add(mensagem);

    }



    public void removerConta(Conta contaUsuario){
        /*
        O que remover:
        - Das listas de amigos
        - Das listas de pedidos de amizade
        - Lista de Comunidade
        - Conta
        - Lista de mensagens em comunidades e amigos
         */
        contaUsuario.removerPerfil();
        contaUsuario.removerInfoLogin();

        for (Conta user :this.listaUsuariosRede) {
            user.getListaPedidoAmizade().remove(contaUsuario);
            user.getListaAmigos().remove(contaUsuario);
            ArrayList<Mensagem> msgRemover = new ArrayList<>();
            for (Mensagem msg :
                    user.getListaMsgs()) {
                if(msg.getUsuarioEnvio().equals(contaUsuario)){
                    msgRemover.add(msg);
                }
            }
            // acho que essas daqui vao dar erro
            ArrayList<Mensagem> msgRemocao = new ArrayList<>();
            for (Mensagem msgr :
                    msgRemover) {
                msgRemocao.add(msgr);

            }

            for (Mensagem msgRemocaoUtil :
                    msgRemocao) {
                user.listaMsgs.remove(msgRemocao);
            }

        }
        ArrayList<Comunidade> comunidadeRemocao = new ArrayList<>();

        for (Comunidade cmd :
                this.listaComunidades) {

            cmd.removerMembroComunidade(contaUsuario);

        }

        for (MensagemFeed msgFeedRemocao : this.feedNoticias){
            if(msgFeedRemocao.getUsuarioEnvio().equals(contaUsuario)){
                // remover aqui as dele
                this.feedNoticias.remove(msgFeedRemocao);
            }
        }
        this.listaUsuariosRede.remove(contaUsuario);
    }

}
