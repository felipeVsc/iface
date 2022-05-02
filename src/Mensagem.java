import java.util.ArrayList;

public abstract class Mensagem {
    String mensagem;
    Conta usuarioEnvio;

    public Mensagem(String mensagem, Conta usuarioEnvio) {
        this.mensagem = mensagem;
        this.usuarioEnvio = usuarioEnvio;
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
}
