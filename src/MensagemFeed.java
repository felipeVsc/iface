public class MensagemFeed extends Mensagem implements Mensagens<Rede>{
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

    @Override
    public void enviarMensagem(Mensagem m, Rede c){
        c.feedNoticias.add((MensagemFeed) m);
    }


    @Override
    public String toString() {
        return this.getMensagem()+" por:"+this.getUsuarioEnvio().getNomeConta();
    }
}
