import excecoes.EntradaIncorretaException;

import java.util.Scanner;

public class MenuComunidadeFactory extends Menu {
    Runnable[] array = new Runnable[20];
    ComunidadeGerenciamento comunidadeGerenciamento;

    public MenuComunidadeFactory(RedeSocial redeSocial) {
        this.comunidadeGerenciamento = redeSocial.listaUsuarios.getListaComunidades();

        array[0] = () -> {
            try {
                comunidadeGerenciamento.entrarComunidade();
            } catch (EntradaIncorretaException e) {
                System.out.println("Nao existe!");
            }
        };
        array[1] = () -> comunidadeGerenciamento.criarComunidade();
        array[2] = () -> comunidadeGerenciamento.remocaoMembros();
        array[3] = () -> comunidadeGerenciamento.listarPedidosEntradaComunidade();
        array[4] = () -> comunidadeGerenciamento.enviarMensagemParaComunidade();
    }

    public void getMenu(Integer entrada){
        array[entrada].run();
    }

    @Override
    public RedeSocial executar(RedeSocial redeSocial) {
        // Listar os menus de conta e chamar o getMenu
        System.out.println("Essa é a lista de menus de Comunidade! 0 - Entrar Comunidade | 1 - Criar Comunidade | 2 - Remover Membro | 3 - Lista Pedidos Entrada | 4 - Enviar Mensagem | 99 - voltar");
        Scanner input = new Scanner(System.in);

        int entrada = input.nextInt();

        if(entrada!=99){
            getMenu(entrada);
        }
        return redeSocial;
    }
}
