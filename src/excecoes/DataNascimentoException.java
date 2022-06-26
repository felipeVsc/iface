package excecoes;

public class DataNascimentoException extends Exception{
    // Data de nascimento nao confere com o formato
    public DataNascimentoException(String message) {
        super(message);
    }
}
