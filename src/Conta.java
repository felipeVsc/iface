import java.lang.reflect.Array;
import java.util.ArrayList;

public class Conta {
    String nomeConta;
    String loginConta;

    private String senhaConta;
    Perfil perfilConta;

    ArrayList<Conta> listaAmigos;
    ArrayList<String> listaPedidoAmizade;

    ArrayList<String> listaMsgs;

    public Conta(String nome, String login, String senha){
        this.nomeConta = nome;
        this.loginConta = login;
        this.senhaConta = senha;
        this.listaAmigos = new ArrayList<>();
        this.listaMsgs = new ArrayList<>();


    }
    public Conta(){

    }

    public ArrayList<String> getListaPedidoAmizade() {
        return listaPedidoAmizade;
    }

    public void setListaPedidoAmizade(ArrayList<String> listaPedidoAmizade) {
        this.listaPedidoAmizade = listaPedidoAmizade;
    }

//    public void requisicoesAmizade(){
//        for(int x = 0;x<this.listaPedidoAmizade.size();x++){
//            String nomeUserPedido = this.listaPedidoAmizade.get(x);
//            System.out.println("Requisicao do usuario: deseja aceitar? 1 - Sim | 2 - Nao");
//            int aceitarIf = input.nextInt();
//            if(aceitarIf==1){
//
//            }
//
//        }
//    }

    public ArrayList<Conta> getListaAmigos() {
        return listaAmigos;
    }

    public void setListaAmigos(ArrayList<Conta> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

    public ArrayList<String> getListaMsgs() {
        return listaMsgs;
    }

    public void setListaMsgs(ArrayList<String> listaMsgs) {
        this.listaMsgs = listaMsgs;
    }

    public ArrayList<String> listarPedidos(){
        return this.listaPedidoAmizade;
    }




    public ArrayList<String> retrieveAllConta(){
        ArrayList<String> infoConta = new ArrayList<String>();
        infoConta.add(this.nomeConta);
        infoConta.add(this.loginConta);
        infoConta.add(this.senhaConta);
        infoConta.add(this.perfilConta.getBioPerfil());
        infoConta.add(this.perfilConta.getCidadeAtualPerfil());
        infoConta.add(this.perfilConta.getCidadeNascimentoPerfil());
        infoConta.add(this.perfilConta.getDataNascimentoPerfil());
        return infoConta;
    }

    public ArrayList<String> getPerfil(){
        return perfilConta.retrieveAllPerfil();
    }

    public void setPerfil(String cidadeAtual, String cidadeNascimento, String dataNascimento, String bio){
        this.perfilConta = new Perfil(cidadeAtual,cidadeNascimento,dataNascimento,bio);

    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public String getLoginConta() {
        return loginConta;
    }

    public void setLoginConta(String loginConta) {
        this.loginConta = loginConta;
    }

    public String getSenhaConta() {
        return senhaConta;
    }

    public void setSenhaConta(String senhaConta) {
        this.senhaConta = senhaConta;
    }
}
