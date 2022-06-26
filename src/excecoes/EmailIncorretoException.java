package excecoes;

public class EmailIncorretoException extends Exception{
    // Email nao estiver no formato correto
    public EmailIncorretoException(String message) {
        super(message);
    }
}
