import java.util.ArrayList;

public class Rede {

    ArrayList<Conta> listaUsuariosRede;

    public Rede(){
        this.listaUsuariosRede = new ArrayList<>();

    }

    public void addConta(Conta contaUsuario){
        this.listaUsuariosRede.add(contaUsuario);
    }

    public ArrayList<String> retrieveAllInfo(String nomeUsuario){
        // aqui teria que rodar um for each e sair comparando o nome dos usuarios
        // quando bater, pega e retorna as informacoes
        // tratar aqui para quando nao achar o usuario
        ArrayList<String> informacoes = new ArrayList<>();

        for (Conta contas : listaUsuariosRede){

            if(contas.getNomeConta().equals(nomeUsuario)){
                informacoes = contas.retrieveAllConta();
            }


        }

        return informacoes;

    }

    public void removerConta(Conta contaUsuario){

        this.listaUsuariosRede.remove(contaUsuario);
    }
}
