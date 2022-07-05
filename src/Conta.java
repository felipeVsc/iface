import excecoes.NumCpfInvalidoException;

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

    public Conta(String nome, String login, String senha) throws ParseException {
        super(nome,login);
        this.senhaConta = senha;
        this.listaAmigos = new ArrayList<>();
        this.listaMsgs = new ArrayList<>();

        this.listaComunidadesMembro = new ArrayList<>();
        this.listaPedidoAmizade = new ArrayList<>();
        setPerfil(null,"00/00/00",null);
        this.listaComunidadesAdmin = new ArrayList<>();


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
        rd.getListaUsuariosRede().forEach(usuario -> removerTodasInfoUsuarios(usuario,contaUser));
    }

    public void removerTodasInfoUsuarios(Conta usuarioListaGeral,Conta contaUser){
        usuarioListaGeral.getListaPedidoAmizade().remove(contaUser);
        usuarioListaGeral.getListaAmigos().remove(contaUser);
        usuarioListaGeral.listaMsgs.removeIf(msg -> msg.getUsuarioEnvio().equals(contaUser));
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
        ContaAdminComunidade adminComunidadeNova = new ContaAdminComunidade(this.nomeConta,this.loginConta,novaComunidade);

        novaComunidade.setUsuarioAdminComunidade(adminComunidadeNova);
        this.listaComunidadesMembro.add(novaComunidade);
        this.listaComunidadesAdmin.add(novaComunidade);
        return novaComunidade;
    }

    public ArrayList<Conta> getListaPedidoAmizade() {
        return listaPedidoAmizade;
    }


    public Comunidade retornarComunidadePeloNome(String nome,Rede listaCom){
        for (Comunidade cmd : listaCom.listaComunidades.getListaComunidades()){
            // mostrar todas
            if(cmd.getNomeComunidade().equals(nome)){
                return cmd;
            }
        }
        return null;
    }

    public void entrarComunidade(Rede listaCom, Scanner input, Conta instancia){
        // listar todas as comunidades e depois voce diz qual que quer entrar

        listaCom.listaComunidades.getListaComunidades().forEach(cmd -> System.out.println(cmd.getNomeComunidade()));

        System.out.println("Digite o nome da comunidade que voce deseja entrar");
        input.nextLine();
        String nomeComunidadeEntrar = input.nextLine();

        Comunidade comunidadeEntrar = retornarComunidadePeloNome(nomeComunidadeEntrar,listaCom);
        comunidadeEntrar.pedirEntradaComunidade(instancia);
    }
    public boolean requisicoesAmizade(){
        System.out.println("Os seus pedidos de amizade são:");
        Scanner input = new Scanner(System.in);
        ArrayList<Conta> aceitos = new ArrayList<>();

        for (Conta usuario : this.listaPedidoAmizade){

            System.out.println(usuario.getNomeConta());
            System.out.println("Deseja aceitar? 1 - Sim | 2 - Nao");
            int aceite = 2;
            try{
                aceite = input.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Digite uma das opcoes: 1 - Sim | 2 - Nao");
                requisicoesAmizade();

            }finally {
                if(aceite==1){
                    aceitos.add(usuario);
                }
            }
        }

        this.listaAmigos.addAll(aceitos);
        this.listaPedidoAmizade.clear();

        return true;
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

        ArrayList<ArrayList> infoGeral = new ArrayList<>();

        infoGeral.add(this.listaPedidoAmizade);
        infoGeral.add(this.listaAmigos);
        infoGeral.add(this.listaComunidadesMembro);
        infoGeral.add(this.listaMsgs);
        infoGeral.add(rede.recuperarInfo(rede,c));
        return infoGeral;
    }



    public String getPerfil(){
        return perfilConta.toString();
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

    // Modificar Perfil

    public boolean modificarPerfil(){
        System.out.println("Modificar perfil");
        Scanner input = new Scanner(System.in);
        System.out.println("O que voce deseja modificar? 1 - CPF | 2 - Bio | 3 - Data de Nascimento  | 6 - Nada");
        int msg = input.nextInt();

        while(msg!=6){

            if (msg==1){
                modificarCpfPerfil(input);
            }
            else if(msg==2){
                modificarBioPerfil(input);
            }
            else if(msg==3){
                modificarDataNascimentoPerfil(input);
            }

            System.out.println("O que voce deseja modificar? 1 - CPF | 2 - Bio | 3 - Data de Nascimento  | 6 - Nada");
            msg = input.nextInt();

        }
        return true;
    }

    public void modificarCpfPerfil(Scanner input){
        System.out.println("Digite o CPF com apenas numeros");
        input.nextLine();
        try{
            String numCpf = input.nextLine();
            this.perfilConta.setNumCpfUsuario(numCpf);
        } catch (NumCpfInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarDataNascimentoPerfil(Scanner input){
        System.out.println("Digite a data de nascimento no formato DD/MM/AAAA");
        String dtNascimento = input.next();
        this.perfilConta.setDataNascimentoPerfil(dtNascimento);
    }

    public void modificarBioPerfil(Scanner input){
        System.out.println("Digite a bio");
        input.nextLine();
        String bio = input.nextLine();
        this.perfilConta.setBioPerfil(bio);
    }

    // as listas irao com os id e nao como string resolver isso
    @Override
    public String toString() {
        return "Conta{" +
                "senhaConta='" + senhaConta + '\'' +
                ", perfilConta=" + getPerfil() +
                ", nomeConta='" + nomeConta + '\'' +
                ", loginConta='" + loginConta + '\'' +
                '}';
    }
}
