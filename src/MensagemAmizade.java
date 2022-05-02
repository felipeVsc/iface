import java.util.ArrayList;

public class MensagemAmizade extends Mensagem {

    public MensagemAmizade(String mensagem, Conta usuarioEnvio) {
        super(mensagem,usuarioEnvio);
    }

    public ArrayList decodeMsg(){
        ArrayList<String> lista = new ArrayList<>();
        String resultado = getUsuarioEnvio().getNomeConta()+getMensagem();
        lista.add(resultado);
        return lista;
    }

}
