import java.util.ArrayList;

public class MensagemAmizade extends Mensagem implements Mensagens<Conta>{

    public MensagemAmizade(String mensagem, Conta usuarioEnvio) {
        super(mensagem,usuarioEnvio);
    }

    public ArrayList decodeMsg(){
        ArrayList<String> lista = new ArrayList<>();
        String resultado = getUsuarioEnvio().getNomeConta()+" by:"+getMensagem();
        lista.add(resultado);
        return lista;
    }
    @Override
    public void enviarMensagem(Mensagem msgUsuarioRecebedor, Conta usuarioRecebedor){
        // parte da interfac
        usuarioRecebedor.listaMsgs.add(msgUsuarioRecebedor);
    }

    @Override
    public String toString() {
        return this.getUsuarioEnvio().getNomeConta()+"says: "+this.mensagem;
    }
}
