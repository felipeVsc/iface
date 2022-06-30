public class Validacoes {
    public Validacoes() {
    }

    public boolean contemEspaco(String texto){
        return texto.contains(" ");
    }

    public boolean contemCaracterEspecial(String texto){
        return texto.matches("/^[A-Za-z0-9 ]+$/");
    }

}
