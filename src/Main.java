import excecoes.EntradaIncorretaException;

import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws EntradaIncorretaException, ParseException {

        System.out.println("Bem-vindo.");
        Scanner input = new Scanner(System.in);


        RedeSocial redesocial = new RedeSocial();

        while(true){
            System.out.println("digite numers 1 - conta | 2 - comunidade | 3 - listar informacoes | 4 - sair da conta");

            MenuFactory menu = new MenuFactory(redesocial);
            int entrada = input.nextInt();
            if(entrada==99){
                break;
            }
            Menu teste = (Menu) menu.getMenu(entrada);
            redesocial = teste.executar(menu.redesocial);

        }



    }
}
