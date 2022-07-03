import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Rede implements Utils1{

    ArrayList<Conta> listaUsuariosRede;
    ComunidadeGerenciamento listaComunidades;
    Feed feedNoticias;

    public Rede(){
        this.listaUsuariosRede = new ArrayList<>();
        this.listaComunidades = new ComunidadeGerenciamento();
        this.feedNoticias = new Feed(null);
    }


    public Feed getFeedNoticias() {
        return feedNoticias;
    }

    public void setFeedNoticias(Feed feedNoticias) {
        this.feedNoticias = feedNoticias;
    }


    public ComunidadeGerenciamento getListaComunidades() {
        return listaComunidades;
    }

    public void setListaComunidades(ComunidadeGerenciamento listaComunidades) {
        this.listaComunidades = listaComunidades;
    }

    public ArrayList<Conta> getListaUsuariosRede() {
        return listaUsuariosRede;
    }

    public void setListaUsuariosRede(ArrayList<Conta> listaUsuariosRede) {
        this.listaUsuariosRede = listaUsuariosRede;
    }

    public Conta getConta(String nomeUsuario){

        for (Conta contas : listaUsuariosRede){

            if(contas.getNomeConta().equals(nomeUsuario)){
               return contas;
            }

        }
        return null;
    }

    public void addConta(Conta contaUsuario){
        this.listaUsuariosRede.add(contaUsuario);
    }

    public void pedirAmizade(AmizadePedido pedidoAmizade) throws NullPointerException{

        Conta usuarioEnvioPedido = pedidoAmizade.getUsuarioEnvioPedido();
        Conta usuarioRecebedor = pedidoAmizade.getUsuarioRecebidorPedido();

        if(usuarioEnvioPedido.listaPedidoAmizade.contains(usuarioRecebedor)){
            System.out.println("Voces ja sao amigos!");
        }else{
            usuarioRecebedor.listaPedidoAmizade.add(usuarioEnvioPedido);
        }

    }

    public void enviarMensagemConta(Scanner input, Conta usuarioEnvio){
        // ver questao de throw de erro aqui
        System.out.println("Digite o nome do usuario que deseja enviar a mensagem");
        String nomeUsuarioReceptor = input.next();
        Conta usuarioReceptor = getConta(nomeUsuarioReceptor);

        System.out.println("Digite a mensagem");
        input.nextLine();
        String msg = input.nextLine();

        MensagemAmizade mensagem = new MensagemAmizade(msg,usuarioEnvio);
        mensagem.enviarMensagem(mensagem,usuarioReceptor);

    }

    @Override
    public void removerInfo(Rede rd,Conta contaUsuario){
        /*
        O que remover:
        - Das listas de amigos
        - Das listas de pedidos de amizade
        - Lista de Comunidade
        - Conta
        - Lista de mensagens em comunidades e amigos
         */
//        contaUsuario.removerInfo(rd,contaUsuario);
//        this.listaComunidades.forEach(cmd -> cmd.removerMembroComunidade(contaUsuario));
//        this.feedNoticias.removeIf(msgFeed -> msgFeed.getUsuarioEnvio().equals(contaUsuario));
//        this.listaUsuariosRede.remove(contaUsuario);
    }

    @Override
    public ArrayList<Mensagem> recuperarInfo(Rede rede, Conta c) {
//       ArrayList<Mensagem> msgFeedConta = new ArrayList<>();
//       for (Mensagem msgFeed :
//               this.feedNoticias) {
//           if (msgFeed.getUsuarioEnvio().equals(c)) {
//               msgFeedConta.add(msgFeed);
//           }
//
//       }
//        return msgFeedConta;
//    }
        return null;
    }
}
