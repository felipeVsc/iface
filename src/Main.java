import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //ArrayList<Conta> listaUsuarios = new ArrayList<Conta>();

        Rede listaUsuarios = new Rede();

        Conta usuario1 = new Conta("felipe","felipevsc","123asd");
        Conta usuario2 = new Conta("felipe2","felipevsc2","123asd2");


        System.out.println(usuario2.getNomeConta());
        usuario2.setPerfil("maceio2","mcz2","30/05/20022","oi");

        listaUsuarios.addConta(usuario2);
        System.out.println(listaUsuarios.retrieveAllInfo("felipe2"));

        listaUsuarios.removerConta(usuario2);
        System.out.println(listaUsuarios.retrieveAllInfo("felipe2"));
    }
}
