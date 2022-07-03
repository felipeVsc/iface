import java.util.ArrayList;

public class Comunidade {
    String nomeComunidade;
    String descComunidade;
    ContaAdminComunidade usuarioAdminComunidade;
    ArrayList<ContaGeral> listaUsuariosComunidade;
    ArrayList<Mensagem> listaMsgComunidade;


    public Comunidade(String nomeComunidade, String descComunidade){
        this.nomeComunidade = nomeComunidade;
        this.descComunidade = descComunidade;
        this.listaUsuariosComunidade = new ArrayList<>();
        this.listaMsgComunidade = new ArrayList<>();

    }

    public ArrayList<Mensagem> getListaMsgComunidade() {
        return listaMsgComunidade;
    }

    public void setListaMsgComunidade(ArrayList<Mensagem> listaMsgComunidade) {
        this.listaMsgComunidade = listaMsgComunidade;
    }

    public void enviarMsg(String msg, Conta usuarioEnvio, Comunidade c){
        MensagemComunidade mensagem = new MensagemComunidade(msg,usuarioEnvio);
        mensagem.enviarMensagem(mensagem,c);
    }

    public void limparDadosUser(Conta usuario){
        setUsuarioAdminComunidade(null);
        this.listaUsuariosComunidade.remove(usuario);
        this.listaMsgComunidade.removeIf(msg -> msg.getUsuarioEnvio().equals(usuario));
    }

    public void pedirEntradaComunidade(Conta usuarioNovo){
        this.usuarioAdminComunidade.pedidoEntrarComunidade.add(usuarioNovo);
    }

    public void criarAdminComunidade(Conta usuario, Comunidade cmd){
        String nomeUsuario = usuario.getNomeConta();
        String loginUsuario = usuario.getLoginConta();
        ContaAdminComunidade adminCom = new ContaAdminComunidade(nomeUsuario,loginUsuario,cmd);
        setUsuarioAdminComunidade(adminCom);
    }


    public void removerMembroComunidade(Conta usuarioRemovido){
        this.listaUsuariosComunidade.remove(usuarioRemovido);
        this.listaMsgComunidade.removeIf(msgCom -> msgCom.getUsuarioEnvio().equals(usuarioRemovido));
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
        return this.usuarioAdminComunidade.getNomeConta();
    }

    public void setUsuarioAdminComunidade(ContaAdminComunidade usuarioAdminComunidade) {
        this.usuarioAdminComunidade = usuarioAdminComunidade;
    }

    public ArrayList<ContaGeral> getListaUsuariosComunidade() {
        return listaUsuariosComunidade;
    }

    public void setListaUsuariosComunidade(ArrayList<ContaGeral> listaUsuariosComunidade) {
        this.listaUsuariosComunidade = listaUsuariosComunidade;
    }

    @Override
    public String toString() {
        return "Comunidade{" +
                "nomeComunidade='" + nomeComunidade + '\'' +
                "descComunidade='" + descComunidade + '\'' +
                '}';
    }
}
