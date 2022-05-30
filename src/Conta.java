import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Conta extends ContaGeral implements Utils1{

    private String senhaConta;
    Perfil perfilConta;

    ArrayList<Conta> listaAmigos;
    ArrayList<Conta> listaPedidoAmizade;

    ArrayList<Mensagem> listaMsgs;

    ArrayList<Comunidade> listaComunidadesAdmin;
    ArrayList<Comunidade> listaComunidadesMembro;

    public Conta(String nome, String login, String senha){
        super(nome,login);
        this.senhaConta = senha;
        this.listaAmigos = new ArrayList<>();
        this.listaMsgs = new ArrayList<>();

        this.listaComunidadesMembro = new ArrayList<>();
        this.listaPedidoAmizade = new ArrayList<>();
        this.perfilConta = null;


    }

    @Override
    public void removerInfo(Rede rd, Conta contaUser) {
        // Aqui remove as informacoes da conta
        removerPerfil();
        removerInfoLogin();
        this.listaComunidadesMembro.clear();
        this.listaPedidoAmizade.clear();
        this.listaMsgs.clear();
        this.listaAmigos.clear();
        // Aqui vai remover as informacoes que estao em outras contas
        for (Conta user :rd.getListaUsuariosRede()) {
            // Talvez tentar juntar as duas funcoes abaixo?
            user.getListaPedidoAmizade().remove(contaUser);
            user.getListaAmigos().remove(contaUser);
            user.listaMsgs.removeIf(msg -> msg.getUsuarioEnvio().equals(contaUser));
        }
    }


    public void removerPerfil(){
        this.perfilConta = null;

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
        Comunidade novaComunidade = new Comunidade(nomeComunidade,descCom);
        novaComunidade.setUsuarioAdminComunidade(new ContaAdminComunidade(getNomeConta(),getLoginConta(),novaComunidade));
        this.listaComunidadesMembro.add(novaComunidade);
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
        input.nextLine();
        String nomeComunidadeEntrar = input.nextLine();
        Comunidade comunidadeEntrar = retornarComunidadePeloNome(nomeComunidadeEntrar,listaCom);
        comunidadeEntrar.pedirEntradaComunidade(instancia);
        // tratar aqui depois para o possivel erro do nome da comundiade

    }
    public void requisicoesAmizade(){
        Scanner input = new Scanner(System.in);
        ArrayList<Conta> aceitos = new ArrayList<>();
        ArrayList<Conta> negados = new ArrayList<>();
        for (Conta usuario : this.listaPedidoAmizade){
            System.out.println(usuario.getNomeConta());
            System.out.println("Deseja aceitar? 1 - Sim | 2 - Nao");
            int aceite = 2;
            try{
                aceite = input.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Digite uma das opcoes: 1 - Sim | 2 - Nao");
            }finally {
                if(aceite==1){
                    aceitos.add(usuario);
                }
                else{
                    negados.add(usuario);
                }
            }


        }


        this.listaAmigos.addAll(aceitos);
        this.listaPedidoAmizade.removeAll(aceitos);
        this.listaPedidoAmizade.removeAll(negados);

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



    @Override
    public ArrayList<ArrayList> recuperarInfo(Rede rede, Conta c){
        ArrayList infoConta = new ArrayList<>();

        infoConta.add(this.nomeConta);
        infoConta.add(this.loginConta);
        infoConta.add(this.senhaConta);
        infoConta.add(this.perfilConta.getBioPerfil());
        infoConta.add(this.perfilConta.getNumCpfUsuario());
        infoConta.add(this.perfilConta.getDataNascimentoPerfil());

        ArrayList<ArrayList> infoGeral = new ArrayList<>();

        infoGeral.add(infoConta);
        infoGeral.add(this.listaPedidoAmizade);
        infoGeral.add(this.listaAmigos);
        infoGeral.add(this.listaComunidadesMembro);
        infoGeral.add(this.listaMsgs);
        infoGeral.add(rede.recuperarInfo(rede,c));
        return infoGeral;
    }



    public ArrayList<String> getPerfil(){
        return perfilConta.retrieveAllPerfil();
    }

    public void setPerfil(String numCpfUsuario,  String dataNascimento, String bio) throws ParseException{
        this.perfilConta = new Perfil(numCpfUsuario,dataNascimento,bio);

    }

    public String getSenhaConta() {
        return senhaConta;
    }

    public void setSenhaConta(String senhaConta) {
        this.senhaConta = senhaConta;
    }
}
