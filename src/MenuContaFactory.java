import excecoes.EntradaIncorretaException;

import java.util.Scanner;

public class MenuContaFactory extends Menu{

    Runnable[] array = new Runnable[20];
    Feed feedNoticias;

    public MenuContaFactory(RedeSocial redeSocial) {
        this.feedNoticias = redeSocial.listaUsuarios.getFeedNoticias();

        array[0] = () -> redeSocial.listaUsuarios.finalizar();
        array[1] = () -> redeSocial.usuario.modificarPerfil();
        array[2] = () -> redeSocial.listaUsuarios.enviarMensagemConta();
        array[3] = () -> redeSocial.listaUsuarios.PedidoContasAmizade();
        array[4] = () -> {

            try {
                feedNoticias.addMsgFeed();
            } catch (EntradaIncorretaException e) {
                System.out.println("Entrada incorreta");
            }
        };
        array[5] = () -> redeSocial.usuario.requisicoesAmizade();
        array[6] = () -> redeSocial.listaUsuarios.removerInfo(redeSocial.listaUsuarios,redeSocial.usuario);

    }

    public void getMenu(Integer entrada){
        array[entrada].run();
    }

    @Override
    public RedeSocial executar(RedeSocial redeSocial) {
        // Listar os menus de conta e chamar o getMenu
        System.out.println("Essa é a lista de menus de Conta! 0 - Finalizar Prog |  1 - Modificar Perfil | 2 - Enviar Mensagem Amigo | 3 - Enviar Pedido Amizade | 4 - Enviar Msg Feed |  5 - Listar Pedidos de Amizade | 6 - Finalizar Conta | 99 - voltar");
        Scanner input = new Scanner(System.in);
        int entrada = input.nextInt();

        if(entrada!=99){
            getMenu(entrada);
        }
        if(entrada==6){
            getMenu(entrada);
            redeSocial.usuario.setNomeConta("removido");
        }

        redeSocial.listaUsuarios.setFeedNoticias(feedNoticias);


        return redeSocial;
    }
}
