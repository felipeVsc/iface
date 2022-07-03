import excecoes.EntradaIncorretaException;

import java.util.ArrayList;
import java.util.Scanner;

public class Feed {
    ArrayList<MensagemFeed> feedNoticia;

    public Feed(Rede rede) {
        this.feedNoticia = new ArrayList<>();

    }

    public ArrayList<MensagemFeed> getFeedNoticia() {
        return feedNoticia;
    }

    public void setFeedNoticia(ArrayList<MensagemFeed> feedNoticia) {
        this.feedNoticia = feedNoticia;
    }

    public void removerMsgUsuario(Conta usuarioRemocao){
        this.feedNoticia.removeIf(mensagemFeedNoticia -> mensagemFeedNoticia.getUsuarioEnvio().equals(usuarioRemocao));
    }

    public void addMsgFeed(Scanner input, Conta usuarioEnvio, Rede rede) throws EntradaIncorretaException {
        System.out.println("Digite a mensagem que voce deseja enviar");
        input.nextLine();

        String mensagemFeedNevio = input.nextLine();
        System.out.println("Qual a privacidade? 1 - Todos | 2 - Amigos");

        PrivacidadeState privacidade = gerarPrivacidade(input);

        MensagemFeed novaMsgFeed = new MensagemFeed(mensagemFeedNevio,usuarioEnvio,privacidade);
        novaMsgFeed.enviarMensagem(novaMsgFeed,rede);

        System.out.println("Mensagem Enviada");
    }

    public PrivacidadeState gerarPrivacidade(Scanner input) throws EntradaIncorretaException {
        int priv = input.nextInt();
        try{
            if(priv!=1 && priv!=2) {
                throw new EntradaIncorretaException("Entrada incorreta, tente novamente");

            }
        } catch (EntradaIncorretaException e) {
            System.out.println(e.getMessage());
            return gerarPrivacidade(input);
        }
        // Instanciar aqui a nova?
        return new PrivacidadeState(priv);
    }

    public void listarMensagens(Conta usuarioRequisicao){
        // listar as mensagens, tomando cuidado para saber se quem enviou é amigo seu ou nao
        System.out.println("Feed de Noticias:");
        for (MensagemFeed msgNoFeedMostrar :
                this.feedNoticia) {
            if(!msgNoFeedMostrar.isPrivacidade()){
                printarMensagemPrivada(msgNoFeedMostrar,usuarioRequisicao);
            }
            System.out.println(msgNoFeedMostrar);
        }
    }

    public void printarMensagemPrivada(MensagemFeed msgNoFeedMostrar, Conta usuarioRequisicao){
        Conta usuarioEnvio = msgNoFeedMostrar.getUsuarioEnvio();
        if(usuarioEnvio.getListaAmigos().contains(usuarioRequisicao) || usuarioEnvio.equals(usuarioRequisicao)){
            System.out.println(msgNoFeedMostrar);
        }
    }
}
