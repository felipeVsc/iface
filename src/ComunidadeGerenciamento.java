import excecoes.EntradaIncorretaException;

import java.util.ArrayList;
import java.util.Scanner;

public class ComunidadeGerenciamento {
    ArrayList<Comunidade> listaComunidades;
    Scanner input = new Scanner(System.in);

    RedeSocial redesocial;
    Conta usuario;
    Rede listaUsuarios;
    RecuperacaoInfo recuperacaoInfo;

    public ComunidadeGerenciamento(RedeSocial redesocial) {
        this.redesocial = redesocial;
        this.listaComunidades = redesocial.listaUsuarios.listaComunidades.getListaComunidades();
        this.usuario = redesocial.getUsuario();
        this.listaUsuarios = redesocial.getListaUsuarios();
        this.recuperacaoInfo = redesocial.getRecuperacaoInfo();
    }

    public ComunidadeGerenciamento(){
        this.listaComunidades = new ArrayList<>();
    }

    public void setRedesocial(RedeSocial redesocial) {
        this.redesocial = redesocial;
    }

    public ArrayList<Comunidade> getListaComunidades() {
        return listaComunidades;
    }

    public void setListaComunidades(ArrayList<Comunidade> listaComunidades) {
        this.listaComunidades = listaComunidades;
    }

    public String getAdminComunidade(Comunidade comunidade){
        for (Comunidade cmd : listaComunidades){

            if(comunidade==cmd){
                return cmd.getUsuarioAdminComunidade();
            }

        }
        return null;
    }

    public Comunidade getComunidadePeloNome(String nome){
        for (Comunidade cmd : this.listaComunidades){
            if(cmd.getNomeComunidade().equals(nome)){
                return cmd;
            }
        }
        return null;
    }

    public RedeSocial criarComunidade(){
        System.out.println("Digite o nome da comunidade que deseja criar");

        String nomeDaComunidade = input.next();
        System.out.println("Digite a descricao da comunidade");
        input.nextLine();
        String descricaoCom = input.nextLine();
        Comunidade novaCom = this.usuario.criarComunidade(nomeDaComunidade,descricaoCom);
        addComunidade(novaCom,this.usuario);


        return redesocial;

    }

    public void addComunidade(Comunidade novaComunidade, Conta usuarioAdmin){
        this.listaComunidades.add(novaComunidade);
        this.redesocial.listaUsuarios.listaComunidades.listaComunidades.add(novaComunidade);



        novaComunidade.criarAdminComunidade(usuarioAdmin,novaComunidade);
    }

    public void removerComunidade(Comunidade nomeComunidade){
        this.listaComunidades.remove(nomeComunidade);
    }

    public boolean ehAdminDaComunidade(Conta usuarioAdmin, Comunidade comunidadeAdmin){
        return comunidadeAdmin.usuarioAdminComunidade.getNomeConta().equals(usuarioAdmin.getNomeConta());
    }

    public boolean enviarMensagemParaComunidade(){

        recuperacaoInfo.printarComunidadesMembro();

        System.out.println("Qual o nome da comunidade que você deseja enviar a mensagem?");
        String nomeComunidade = input.next();
        System.out.println("Qual a mensagem?");
        input.nextLine();
        String msgCom = input.nextLine();

        Comunidade cmdParaEnviar = getComunidadePeloNome(nomeComunidade);

        if(cmdParaEnviar!=null){
            cmdParaEnviar.enviarMsg(msgCom,usuario,cmdParaEnviar);
        }
        else{
            System.out.println("Voce nao faz parte dessa comunidade");
            return false;
        }

        return true;
    }

    public boolean entrarComunidade() throws EntradaIncorretaException{
        System.out.println("Digite o nome da comunidade que deseja entrar");
        String nomeComNova = input.next();
        try{
            Comunidade cmdEntrada = getComunidadePeloNome(nomeComNova);
            if (cmdEntrada==null){
                throw new EntradaIncorretaException("Comunidade nao existe");
            } else {
                cmdEntrada.pedirEntradaComunidade(usuario);
                System.out.println("O admin da comunidade recebeu o seu pedido");
            }
        } catch (EntradaIncorretaException e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean remocaoMembros(){
        System.out.println("Remocao de membro; Digite o nome da comunidade");
        String nomeComunidadeRemocao = input.next();
        Comunidade comunidadeRemocao = null;
        try{
            comunidadeRemocao = getComunidadePeloNome(nomeComunidadeRemocao);
            if(comunidadeRemocao==null){
                throw new EntradaIncorretaException("Comunidade nao existe");
            }

        } catch (EntradaIncorretaException e){
            System.out.println(e.getMessage());
            return false;
        }

        boolean ehAdminComunidade = ehAdminDaComunidade(usuario,comunidadeRemocao);
        if(ehAdminComunidade){
            comunidadeRemocao.usuarioAdminComunidade.removerMembroComunidade(listaUsuarios,input);
        }else{
            System.out.println("Voce nao eh o administrador dessa comunidade");
            return false;
        }

        return true;
    }

    public boolean listarPedidosEntradaComunidade(){
        System.out.println("Pedidos | Digite o nome da comunidade");

        String nomeComunidadeAdmin = input.next();

        Comunidade comunidadePedidosEntrada = getComunidadePeloNome(nomeComunidadeAdmin);
        boolean ehComunidadeAdmin = ehAdminDaComunidade(usuario,comunidadePedidosEntrada);
        if(ehComunidadeAdmin){
            // listar os pedidos
            comunidadePedidosEntrada.usuarioAdminComunidade.addMembroComunidade();
        }
        else{
            System.out.println("Voce nao eh o administrador");
            return false;
        }
        return true;
    }


}
