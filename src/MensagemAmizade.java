import java.util.ArrayList;

public class MensagemAmizade extends Mensagem implements Mensagens<Conta>{

    public MensagemAmizade(String mensagem, Conta usuarioEnvio) {
        super(mensagem,usuarioEnvio);
    }

    @Override
    public void enviarMensagem(Mensagem msgUsuarioRecebedor, Conta usuarioRecebedor) throws NullPointerException{
        
        usuarioRecebedor.listaMsgs.add(msgUsuarioRecebedor);
    }

    @Override
    public String toString() {
        return this.getUsuarioEnvio().getNomeConta()+"por: "+this.mensagem;
    }
}
