import java.util.HashMap;

public class MenuFactory{

    HashMap<Integer,Object> menuMapa = new HashMap<>();
    RedeSocial redesocial;

    public MenuFactory(RedeSocial redesocial) {

        this.redesocial = redesocial;
        this.redesocial.listaUsuarios.gerarInicio(redesocial);

        this.menuMapa.put(1, new MenuContaFactory(redesocial));
        this.menuMapa.put(2, new MenuComunidadeFactory(redesocial));
        this.menuMapa.put(3, new MenuListarFactory(redesocial));
        this.menuMapa.put(4, new Login());

    }

    public Object getMenu(Integer entrada){
        return this.menuMapa.get(entrada);
    }

    public RedeSocial getRedesocial() {
        return redesocial;
    }

    public void setRedesocial(RedeSocial redesocial) {
        this.redesocial = redesocial;
    }
}
