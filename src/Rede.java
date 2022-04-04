import java.util.ArrayList;

public class Rede {

    ArrayList<Conta> listaUsuariosRede;
    ArrayList<Comunidade> listaComunidades;


    public Rede(){
        this.listaUsuariosRede = new ArrayList<>();
        this.listaComunidades = new ArrayList<>();

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

            for (Mensagem msgr :
                    msgRemover) {
                user.getListaMsgs().remove(msgr);
            }

        }
        for (Comunidade cmd :
                this.listaComunidades) {
            cmd.removerMembroComunidade(contaUsuario);

        }
        this.listaUsuariosRede.remove(contaUsuario);
    }

}
