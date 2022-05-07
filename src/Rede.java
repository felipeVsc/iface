import java.lang.reflect.Array;
import java.util.ArrayList;

public class Rede implements Utils1{

    ArrayList<Conta> listaUsuariosRede;
    ArrayList<Comunidade> listaComunidades;
    ArrayList<MensagemFeed> feedNoticias;

    public Rede(){
        this.listaUsuariosRede = new ArrayList<>();
        this.listaComunidades = new ArrayList<>();
        this.feedNoticias = new ArrayList<>();
    }

    public void addMsgFeed(String msg, Conta usuarioEnvio, boolean privacidade, Rede rede){
        MensagemFeed novaMsgFeed = new MensagemFeed(msg,usuarioEnvio,privacidade);
        novaMsgFeed.enviarMensagem(novaMsgFeed,rede);
    }

    public void removerMsgUsuario(Conta usuarioRemocao){
//
//        ArrayList<Mensagem> mensagemParaRemocao = new ArrayList<>();
//        for(int x=0;x<this.feedNoticias.size();x++){
//            if(feedNoticias.get(x).getUsuarioEnvio().equals(usuarioRemocao)){
//                mensagemParaRemocao.add(this.feedNoticias.get(x));
//            }
//        }
//
//        for (Mensagem msgFeedRemocao :
//                mensagemParaRemocao) {
//            this.feedNoticias.remove(msgFeedRemocao);
//        }

        // testar isso daqui
        this.feedNoticias.removeIf(msgfeednt -> msgfeednt.getUsuarioEnvio().equals(usuarioRemocao)==true);
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


    public void pedirAmizade(Conta nomeContaEnvio, Conta nomeContaRecebedor){

        nomeContaRecebedor.listaPedidoAmizade.add(nomeContaEnvio);
    }

    public void enviarMensagemConta(String msg, Conta usuarioEnvio, Conta usuarioRec){
        MensagemAmizade mensagem = new MensagemAmizade(msg,usuarioEnvio);
        mensagem.enviarMensagem(mensagem,usuarioRec);

    }


    @Override
    public void removerInfo(Rede rd,Conta contaUsuario){
        /*
        O que remover:
        - Das listas de amigos
        - Das listas de pedidos de amizade
        - Lista de Comunidade
        - Conta
        - Lista de mensagens em comunidades e amigos
         */
        contaUsuario.removerInfo(rd,contaUsuario);
        for (Comunidade cmd :
                this.listaComunidades) {

            cmd.removerMembroComunidade(contaUsuario);

        }
        this.feedNoticias.removeIf(msgFeed -> msgFeed.getUsuarioEnvio().equals(contaUsuario));
        this.listaUsuariosRede.remove(contaUsuario);
    }

       @Override
    public ArrayList recuperarInfo(Rede rede, Conta c) {
        ArrayList<Mensagem> msgFeedConta = new ArrayList<>();
           for (Mensagem msgFeed :
                   this.feedNoticias) {
               if (msgFeed.getUsuarioEnvio().equals(c)) {
                   msgFeedConta.add(msgFeed);
               }
           }

        return msgFeedConta;
    }
}
