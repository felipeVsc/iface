import java.util.ArrayList;

public class Conta {
    String nomeConta;
    String loginConta;
    private String senhaConta;
    Perfil perfilConta;

    public Conta(String nome, String login, String senha){
        this.nomeConta = nome;
        this.loginConta = login;
        this.senhaConta = senha;


    }

    public ArrayList<String> getPerfil(){
        // aqui tem que retornar as info do perfil

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
