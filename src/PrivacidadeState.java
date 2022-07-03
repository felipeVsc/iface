public class PrivacidadeState {
    Boolean privacidade;

    public PrivacidadeState(int privacidade) {
        this.privacidade = privacidade == 1;

    }

    public Boolean getPrivacidade() {
        return privacidade;
    }

    public void setPrivacidade(Boolean privacidade) {
        this.privacidade = privacidade;
    }
}
