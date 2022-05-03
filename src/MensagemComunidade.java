public class MensagemComunidade extends Mensagem implements Mensagens<Comunidade>{
    // vai fazer os posts na comunidade

    public MensagemComunidade(String mensagem, Conta usuarioEnvio){
        super(mensagem,usuarioEnvio);
    }

    @Override
    public void enviarMensagem(Mensagem m, Comunidade c) {
        // enviar a mensagem p a comunidade
        c.listaMsgComunidade.add(m);
    }

    @Override
    public String toString() {
        return this.mensagem+" by:"+this.usuarioEnvio.getNomeConta();
    }
}
