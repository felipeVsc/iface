public abstract class ContaGeral {
    String nomeConta;
    String loginConta;

    public ContaGeral(String nome, String login){
        this.nomeConta = nome;
        this.loginConta = login;
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
}
