public class MensagemFeed extends Mensagem{
    boolean privacidade; // true = todos, false = apenas amigos

    public MensagemFeed(String mensagem, Conta usuarioEnvio, boolean privacidade) {
        super(mensagem,usuarioEnvio);
        this.privacidade = privacidade;
    }
    public boolean isPrivacidade() {
        return privacidade;
    }

    public void setPrivacidade(boolean privacidade) {
        this.privacidade = privacidade;
    }
}
