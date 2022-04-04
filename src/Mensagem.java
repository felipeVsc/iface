import java.util.ArrayList;

public class Mensagem {
    String mensagem;
    Conta usuarioEnvio;

    public Mensagem(String mensagem, Conta usuarioEnvio) {
        this.mensagem = mensagem;
        this.usuarioEnvio = usuarioEnvio;
    }

    public ArrayList decodeMsg(){
        ArrayList<String> lista = new ArrayList<>();
        String resultado = getUsuarioEnvio().getNomeConta()+getMensagem();
        lista.add(resultado);
        return lista;

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
