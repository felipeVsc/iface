package excecoes;

public class EntradaIncorretaException extends Exception{
    // Tudo que for entrada com formato incorreto, tipo com espaço
    public EntradaIncorretaException(String msgErro){
        super(msgErro);
    }
}
