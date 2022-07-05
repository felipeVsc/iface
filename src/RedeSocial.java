import excecoes.EntradaIncorretaException;

import java.text.ParseException;
import java.util.ArrayList;

public class RedeSocial {

    Conta usuario;
    Rede listaUsuarios;
    RecuperacaoInfo recuperacaoInfo;

    public RedeSocial() throws ParseException, EntradaIncorretaException {

        this.usuario = new Login().processoLogin(this.listaUsuarios);
        this.listaUsuarios = new Rede();
        this.recuperacaoInfo = new RecuperacaoInfo(this.usuario,this.listaUsuarios);


        if(!this.listaUsuarios.listaUsuariosRede.contains(this.usuario)){
            this.listaUsuarios.listaUsuariosRede.add(this.usuario);

        }



    }

    public Conta getUsuario() {
        return usuario;
    }

    public void setUsuario(Conta usuario) {
        this.usuario = usuario;
    }

    public Rede getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(Rede listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public RecuperacaoInfo getRecuperacaoInfo() {
        return recuperacaoInfo;
    }

    public void setRecuperacaoInfo(RecuperacaoInfo recuperacaoInfo) {
        this.recuperacaoInfo = recuperacaoInfo;
    }
}
