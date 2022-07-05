import excecoes.EntradaIncorretaException;

import java.text.ParseException;

public abstract class Menu {

    public abstract RedeSocial executar(RedeSocial redesocial) throws ParseException, EntradaIncorretaException;
}
