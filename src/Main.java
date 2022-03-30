public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Conta usuario = new Conta("felipe","felipevsc","123asd");

        System.out.println(usuario.getNomeConta());
        usuario.setPerfil("maceio","mcz","30/05/2002","oi");
        System.out.println(usuario.getPerfil());
        usuario.perfilConta.setBioPerfil("nova bio");
        System.out.println(usuario.getPerfil());
    }
}
