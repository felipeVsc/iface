package excecoes;

public class NumCpfInvalidoException extends Exception{
    // Nao estiver no formato correto
    public NumCpfInvalidoException(String msgErro){
        super(msgErro);
    }
}
