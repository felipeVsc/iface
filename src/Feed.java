import excecoes.EntradaIncorretaException;

import java.util.ArrayList;
import java.util.Scanner;

public class Feed {
    ArrayList<MensagemFeed> feedNoticia;
    Scanner input = new Scanner(System.in);

    RedeSocial redesocial;
    Conta usuario;
    Rede listaUsuarios;
    RecuperacaoInfo recuperacaoInfo;

    public Feed(RedeSocial redesocial) {
        this.feedNoticia = redesocial.listaUsuarios.getFeedNoticias().getFeedNoticia();
        this.redesocial = redesocial;
        this.usuario = redesocial.getUsuario();
        this.listaUsuarios = redesocial.getListaUsuarios();
        this.recuperacaoInfo = redesocial.getRecuperacaoInfo();
    }

    public Feed(){
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

    public MensagemFeed addMsgFeed() throws EntradaIncorretaException {
        System.out.println("Digite a mensagem que voce deseja enviar");

        String mensagemFeedEnvio = input.nextLine();
        input.nextLine();
        System.out.println("Qual a privacidade? 1 - Todos | 2 - Amigos");

        PrivacidadeState privacidade = gerarPrivacidade();
        MensagemFeed novaMsgFeed = new MensagemFeed(mensagemFeedEnvio,redesocial.usuario,privacidade);
        novaMsgFeed.enviarMensagem(novaMsgFeed,redesocial.listaUsuarios);
        System.out.println("Mensagem Enviada");

        return novaMsgFeed;
    }

    public PrivacidadeState gerarPrivacidade() throws EntradaIncorretaException {
        int priv = input.nextInt();
        try{
            if(priv!=1 && priv!=2) {
                throw new EntradaIncorretaException("Entrada incorreta, tente novamente");

            }
        } catch (EntradaIncorretaException e) {
            System.out.println(e.getMessage());
            return gerarPrivacidade();
        }
        // Instanciar aqui a nova?
        return new PrivacidadeState(priv);
    }

    public boolean listarMensagens(){
        // listar as mensagens, tomando cuidado para saber se quem enviou é amigo seu ou nao
        System.out.println("Feed de Noticias:");
        for (MensagemFeed msgNoFeedMostrar :
                this.feedNoticia) {
            if(!msgNoFeedMostrar.isPrivacidade()){
                printarMensagemPrivada(msgNoFeedMostrar,usuario);
            }
            System.out.println(msgNoFeedMostrar);
        }

        return true;
    }

    public void printarMensagemPrivada(MensagemFeed msgNoFeedMostrar, Conta usuarioRequisicao){
        Conta usuarioEnvio = msgNoFeedMostrar.getUsuarioEnvio();
        if(usuarioEnvio.getListaAmigos().contains(usuarioRequisicao) || usuarioEnvio.equals(usuarioRequisicao)){
            System.out.println(msgNoFeedMostrar);
        }
    }
}
