import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Rede implements Utils1{

    ArrayList<Conta> listaUsuariosRede;
    ComunidadeGerenciamento listaComunidades;

    Feed feedNoticias;
    Scanner input = new Scanner(System.in);
    Conta usuario;


    public Rede(){
        this.listaUsuariosRede = new ArrayList<Conta>();
        this.listaComunidades = new ComunidadeGerenciamento();
        this.feedNoticias = new Feed();
    }

    public Menu finalizar(){
        System.out.println("Tchauzinho!");
        return null;
    }

    public void gerarInicio(RedeSocial redesocial){
        this.listaComunidades = new ComunidadeGerenciamento(redesocial);
        this.feedNoticias = new Feed(redesocial);
        this.usuario = redesocial.getUsuario();
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
        // O pedido em si
        Conta usuarioEnvioPedido = pedidoAmizade.getUsuarioEnvioPedido();
        Conta usuarioRecebedor = pedidoAmizade.getUsuarioRecebidorPedido();

        if(usuarioEnvioPedido.listaPedidoAmizade.contains(usuarioRecebedor)){
            System.out.println("Voces ja sao amigos!");
        }else{
            usuarioRecebedor.listaPedidoAmizade.add(usuarioEnvioPedido);
        }

    }

    public boolean PedidoContasAmizade(){
        // O ato de pedir, a ação que vai puxar o pedido
        System.out.println("Para quem você deseja enviar o pedido de amizade?");

        String nomeUsuarioRecebedor = input.next();
        try{
            Conta usuarioRecebedorPedido = getConta(nomeUsuarioRecebedor);
            AmizadePedido pedidoAmizade = new AmizadePedido(usuario,usuarioRecebedorPedido);
            pedirAmizade(pedidoAmizade);
        } catch (NullPointerException e){
            System.out.println("Usuario nao existe");
            return false;
        }
        return true;
    }

    public boolean enviarMensagemConta(){
        // ver questao de throw de erro aqui
        System.out.println("Digite o nome do usuario que deseja enviar a mensagem");
        String nomeUsuarioReceptor = input.next();
        Conta usuarioReceptor = getConta(nomeUsuarioReceptor);

        System.out.println("Digite a mensagem");
        input.nextLine();
        String msg = input.nextLine();

        try{
            MensagemAmizade mensagem = new MensagemAmizade(msg,usuario);
            mensagem.enviarMensagem(mensagem,usuarioReceptor);
        } catch (NullPointerException e){
            System.out.println("Usuario nao existente");
            return false;
        }

        return true;
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
        contaUsuario.removerInfo(rd,contaUsuario);
        this.listaComunidades.listaComunidades.forEach(cmd -> cmd.removerMembroComunidade(contaUsuario));
        this.feedNoticias.feedNoticia.removeIf(msgFeed -> msgFeed.getUsuarioEnvio().equals(contaUsuario));
        this.listaUsuariosRede.remove(contaUsuario);
        System.out.println("Usuario Removido");

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
