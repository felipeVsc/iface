import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Conta {
    String nomeConta;
    String loginConta;
    private String senhaConta;
    Perfil perfilConta;

    ArrayList<Conta> listaAmigos;
    ArrayList<Conta> listaPedidoAmizade;

    ArrayList<Mensagem> listaMsgs;

    ArrayList<Comunidade> listaComunidadesAdmin;
    ArrayList<Comunidade> listaComunidadesMembro;

    public Conta(String nome, String login, String senha){
        this.nomeConta = nome;
        this.loginConta = login;
        this.senhaConta = senha;
        this.listaAmigos = new ArrayList<>();
        this.listaMsgs = new ArrayList<>();
        this.listaComunidadesAdmin = new ArrayList<>();
        this.listaComunidadesMembro = new ArrayList<>();
        this.listaPedidoAmizade = new ArrayList<>();
        this.perfilConta = new Perfil("","","","");


    }

    public void removerPerfil(){
        setPerfil("","","","");

    }

    public void removerInfoLogin(){
        setLoginConta("");
        setSenhaConta("");
        setNomeConta("");
    }

    public void setListaPedidoAmizade(ArrayList<Conta> listaPedidoAmizade) {
        this.listaPedidoAmizade = listaPedidoAmizade;
    }

    public ArrayList<Comunidade> getListaComunidadesAdmin() {
        return listaComunidadesAdmin;
    }

    public void setListaComunidadesAdmin(ArrayList<Comunidade> listaComunidadesAdmin) {
        this.listaComunidadesAdmin = listaComunidadesAdmin;
    }

    public ArrayList<Comunidade> getListaComunidadesMembro() {
        return listaComunidadesMembro;
    }

    public void setListaComunidadesMembro(ArrayList<Comunidade> listaComunidadesMembro) {
        this.listaComunidadesMembro = listaComunidadesMembro;
    }

    public Comunidade criarComunidade(String nomeComunidade, String descCom){
        Comunidade novaComunidade = new Comunidade(nomeComunidade,descCom,this.nomeConta);
        this.listaComunidadesMembro.add(novaComunidade);
        this.listaComunidadesAdmin.add(novaComunidade);
        return novaComunidade;
    }

    public ArrayList<Conta> getListaPedidoAmizade() {
        return listaPedidoAmizade;
    }


    public Comunidade retornarComunidadePeloNome(String nome,Rede listaCom){
        for (Comunidade cmd : listaCom.getListaComunidades()){
            // mostrar todas
            if(cmd.getNomeComunidade().equals(nome)){
                return cmd;
            }
        }
        return null;
    }

    public void entrarComunidade(Rede listaCom, Scanner input, Conta instancia){
        // listar todas as comunidades e depois voce diz qual que quer entrar

        for (Comunidade cmd : listaCom.getListaComunidades()){
            // mostrar todas
            System.out.println(cmd.getNomeComunidade()); // falta aqui mostrar a descricao
        }
        System.out.println("Digite o nome da comunidade que voce deseja entrar");
        String nomeComunidadeEntrar = input.nextLine();
        Comunidade comunidadeEntrar = retornarComunidadePeloNome(nomeComunidadeEntrar,listaCom);
        this.listaComunidadesMembro.add(comunidadeEntrar);
        comunidadeEntrar.addMembroComunidade(instancia);
        // tratar aqui depois para o possivel erro do nome da comundiade

    }
    public void requisicoesAmizade(){
        Scanner input = new Scanner(System.in);
        ArrayList<Conta> aceitos = new ArrayList<>();
        ArrayList<Conta> negados = new ArrayList<>();
        for (Conta usuario : this.listaPedidoAmizade){
            System.out.println(usuario.getNomeConta());
            System.out.println("Deseja aceitar? 1 - Sim | 2 - Nao");
            int aceite = input.nextInt();
            if(aceite==1){
                aceitos.add(usuario);
            }
            else{
                negados.add(usuario);
            }
        }

        for(Conta usuario : aceitos){
            addRequisicaoAmizade(usuario);
            removerRequisicaoAmizade(usuario);
        }
        for(Conta usuario : negados){
            removerRequisicaoAmizade(usuario);
        }

    }

    public void removerRequisicaoAmizade(Conta usuario){
        this.listaPedidoAmizade.remove(usuario);
    }

    public void addRequisicaoAmizade(Conta usuario){
        this.listaAmigos.add(usuario);
    }


    public ArrayList<Conta> getListaAmigos() {
        return listaAmigos;
    }

    public void setListaAmigos(ArrayList<Conta> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

    public ArrayList<Mensagem> getListaMsgs() {
        return listaMsgs;
    }

    public void setListaMsgs(ArrayList<Mensagem> listaMsgs) {
        this.listaMsgs = listaMsgs;
    }

    public ArrayList<Conta> listarPedidos(){
        return this.listaPedidoAmizade;
    }




    public ArrayList<ArrayList> retrieveAllConta(){
        ArrayList infoConta = new ArrayList<>();

        infoConta.add(this.nomeConta);
        infoConta.add(this.loginConta);
        infoConta.add(this.senhaConta);
        infoConta.add(this.perfilConta.getBioPerfil());
        infoConta.add(this.perfilConta.getCidadeAtualPerfil());
        infoConta.add(this.perfilConta.getCidadeNascimentoPerfil());
        infoConta.add(this.perfilConta.getDataNascimentoPerfil());

        ArrayList<ArrayList> infoGeral = new ArrayList<>();

        infoGeral.add(infoConta);
        infoGeral.add(this.listaPedidoAmizade);
        infoGeral.add(this.listaAmigos);
        infoGeral.add(this.listaComunidadesMembro);
        infoGeral.add(this.listaMsgs);
        return infoGeral;
    }



    public ArrayList<String> getPerfil(){
        return perfilConta.retrieveAllPerfil();
    }

    public void setPerfil(String cidadeAtual, String cidadeNascimento, String dataNascimento, String bio){
        this.perfilConta = new Perfil(cidadeAtual,cidadeNascimento,dataNascimento,bio);

    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public String getLoginConta() {
        return loginConta;
    }

    public void setLoginConta(String loginConta) {
        this.loginConta = loginConta;
    }

    public String getSenhaConta() {
        return senhaConta;
    }

    public void setSenhaConta(String senhaConta) {
        this.senhaConta = senhaConta;
    }
}
