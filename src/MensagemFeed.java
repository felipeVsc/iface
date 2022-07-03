public class MensagemFeed extends Mensagem implements Mensagens<Rede>{
    PrivacidadeState privacidade; // true = todos, false = apenas amigos

    public MensagemFeed(String mensagem, Conta usuarioEnvio, PrivacidadeState privacidade) {
        super(mensagem,usuarioEnvio);
        this.privacidade = privacidade;
    }
    public boolean isPrivacidade() {
        return  this.privacidade.getPrivacidade();
    }
    public void setPrivacidade(PrivacidadeState privacidade) {
        this.privacidade = privacidade;
    }

    @Override
    public void enviarMensagem(Mensagem m, Rede c){
        c.feedNoticias.feedNoticia.add((MensagemFeed) m);
    }

    @Override
    public String toString() {

        return this.getMensagem()+" por:"+this.getUsuarioEnvio().getNomeConta();

    }
}
