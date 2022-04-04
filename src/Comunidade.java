import java.util.ArrayList;

public class Comunidade {
    String nomeComunidade;
    String descComunidade;
    String usuarioAdminComunidade;
    ArrayList<Conta> listaUsuariosComunidade;
    ArrayList<Mensagem> listaMsgComunidade;


    public Comunidade(String nomeComunidade, String descComunidade, String usuarioAdminComunidade){
        this.nomeComunidade = nomeComunidade;
        this.descComunidade = descComunidade;
        this.usuarioAdminComunidade = usuarioAdminComunidade;
        this.listaUsuariosComunidade = new ArrayList<>();
        this.listaMsgComunidade = new ArrayList<>();

    }

    public ArrayList<Mensagem> getListaMsgComunidade() {
        return listaMsgComunidade;
    }

    public void setListaMsgComunidade(ArrayList<Mensagem> listaMsgComunidade) {
        this.listaMsgComunidade = listaMsgComunidade;
    }

    public void enviarMsg(String msg, Conta usuarioEnvio){
        Mensagem mensagem = new Mensagem(msg,usuarioEnvio);
        this.listaMsgComunidade.add(mensagem);
    }

    public void limparDadosUser(Conta usuario){
        setUsuarioAdminComunidade("");
        this.listaUsuariosComunidade.remove(usuario);
        ArrayList<Mensagem> msgRemover = new ArrayList<>();
        for (Mensagem msg :
                this.listaMsgComunidade) {
            if(msg.getUsuarioEnvio().equals(usuario)){
                msgRemover.add(msg);
            }
        }
        for (Mensagem msgr: msgRemover
             ) {
            this.listaMsgComunidade.remove(msgr);
        }
    }

    public void addMembroComunidade(Conta usuarioNovo){
        this.listaUsuariosComunidade.add(usuarioNovo);
    }

    public void removerMembroComunidade(Conta usuarioRemovido){

        this.listaUsuariosComunidade.remove(usuarioRemovido);
    }
    public String getNomeComunidade() {
        return nomeComunidade;
    }

    public void setNomeComunidade(String nomeComunidade) {
        this.nomeComunidade = nomeComunidade;
    }

    public String getDescComunidade() {
        return descComunidade;
    }

    public void setDescComunidade(String descComunidade) {
        this.descComunidade = descComunidade;
    }

    public String getUsuarioAdminComunidade() {
        return usuarioAdminComunidade;
    }

    public void setUsuarioAdminComunidade(String usuarioAdminComunidade) {
        this.usuarioAdminComunidade = usuarioAdminComunidade;
    }

    public ArrayList<Conta> getListaUsuariosComunidade() {
        return listaUsuariosComunidade;
    }

    public void setListaUsuariosComunidade(ArrayList<Conta> listaUsuariosComunidade) {
        this.listaUsuariosComunidade = listaUsuariosComunidade;
    }
}
