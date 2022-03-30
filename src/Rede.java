import java.util.ArrayList;

public class Rede {

    ArrayList<Conta> listaUsuariosRede;


    public Rede(){
        this.listaUsuariosRede = new ArrayList<>();

    }

    public ArrayList<Conta> getListaUsuariosRede() {
        return listaUsuariosRede;
    }

    public void setListaUsuariosRede(ArrayList<Conta> listaUsuariosRede) {
        this.listaUsuariosRede = listaUsuariosRede;
    }

    public Conta getConta(String nomeUsuario){
        Conta conta = new Conta();
        for (Conta contas : listaUsuariosRede){

            if(contas.getNomeConta().equals(nomeUsuario)){
                conta = contas;
            }

        }
        return conta;
    }


    public void addConta(Conta contaUsuario){
        this.listaUsuariosRede.add(contaUsuario);
    }

    public ArrayList<String> retrieveAllInfo(String nomeUsuario){


        return getConta(nomeUsuario).retrieveAllConta();

    }

    public void pedirAmizade(Conta nomeContaEnvio, String nomeContaRecebedor){

        getConta(nomeContaRecebedor).listaPedidoAmizade.add(nomeContaEnvio.getNomeConta());
    }

    public void enviarMensagem(String mensagem, Conta usuarioEnvio, String usuarioRec){
        String padraoMsg = usuarioEnvio.getNomeConta()+" enviou:\n"+mensagem;

        getConta(usuarioRec).listaMsgs.add(padraoMsg);

    }





    public void removerConta(Conta contaUsuario){

        this.listaUsuariosRede.remove(contaUsuario);
    }
}
