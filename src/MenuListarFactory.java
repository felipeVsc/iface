import excecoes.EntradaIncorretaException;

import java.util.Scanner;

public class MenuListarFactory extends Menu{
    Runnable[] array = new Runnable[20];
    RecuperacaoInfo recuperacaoInfo;
    public MenuListarFactory(RedeSocial redeSocial) {
        this.recuperacaoInfo = redeSocial.getRecuperacaoInfo();


        array[0] = () -> recuperacaoInfo.printarMensagensFeed();
        array[1] = () -> recuperacaoInfo.printarMensagens();
        array[2] = () -> {
            try {
                recuperacaoInfo.printarMensagensComunidade();
            } catch (EntradaIncorretaException e) {
                System.out.println("Nao existe!");
            }
        };
        array[3] = () -> recuperacaoInfo.retrieveAll();
        array[4] = () -> recuperacaoInfo.printarAmizades();


    }

    public void getMenu(Integer entrada){
        array[entrada].run();
    }

    @Override
    public RedeSocial executar(RedeSocial redeSocial) {
        // Listar os menus de conta e chamar o getMenu
        System.out.println("Essa é a lista de menus de Listar Info! | 0 - Msg Feed  | 1 - Msg Amigos |  2 - Msg Comunidades |  3 - Todas Informacoes |  4 - Amizades |  99 - voltar");
        Scanner input = new Scanner(System.in);
        int entrada = input.nextInt();

        if(entrada!=99){
            getMenu(entrada);
        }
        return redeSocial;
    }
}
