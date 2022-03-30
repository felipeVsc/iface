import java.lang.reflect.Array;
import java.util.ArrayList;

public class Conta {
    String nomeConta;
    String loginConta;

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
