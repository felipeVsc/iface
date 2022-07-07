import excecoes.EntradaIncorretaException;

import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws EntradaIncorretaException, ParseException {

        System.out.println("Bem-vindo.");
        Scanner input = new Scanner(System.in);


        RedeSocial redesocial = new RedeSocial();

        while(true){
            MenuFactory menu = new MenuFactory(redesocial);
            int entrada;

            if(menu.redesocial.usuario.getNomeConta().equals("removido")){
                entrada = 4;

            }
            else{
                System.out.println("Menu Principal ->  1 - Conta | 2 - Comunidade | 3 - Listar Informacoes | 4 - sair da conta | 99 - Finalizar Programa");
                entrada = input.nextInt();
            }

            if(entrada==99){
                break;
            }

            Menu execMenu = (Menu) menu.getMenu(entrada);
            redesocial = execMenu.executar(menu.redesocial);

        }



    }
}
