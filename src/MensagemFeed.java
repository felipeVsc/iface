public class MensagemFeed {
    String mensagem;
    Conta usuarioEnvio;
    boolean privacidade; // true = todos, false = apenas amigos

    public MensagemFeed(String mensagem, Conta usuarioEnvio, boolean privacidade) {
        this.mensagem = mensagem;
        this.usuarioEnvio = usuarioEnvio;
        this.privacidade = privacidade;
    }


    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Conta getUsuarioEnvio() {
        return usuarioEnvio;
    }

    public void setUsuarioEnvio(Conta usuarioEnvio) {
        this.usuarioEnvio = usuarioEnvio;
    }

    public boolean isPrivacidade() {
        return privacidade;
    }

    public void setPrivacidade(boolean privacidade) {
        this.privacidade = privacidade;
    }
}
