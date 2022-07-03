import excecoes.EntradaIncorretaException;

import java.util.ArrayList;
import java.util.Scanner;

public class ComunidadeGerenciamento{
    ArrayList<Comunidade> listaComunidades;

    public ComunidadeGerenciamento() {
        this.listaComunidades = new ArrayList<>();
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

    public void criarComunidade(Scanner input, Conta usuario){
        System.out.println("Digite o nome da comunidade que deseja criar");
        input.nextLine();

        String nomeDaComunidade = input.nextLine();
        System.out.println("Digite a descricao da comunidade");

        input.nextLine();
        String descricaoCom = input.nextLine();

        Comunidade novaCom = usuario.criarComunidade(nomeDaComunidade,descricaoCom);
        addComunidade(novaCom,usuario);

    }

    public void addComunidade(Comunidade novaComunidade, Conta usuarioAdmin){
        this.listaComunidades.add(novaComunidade);
        novaComunidade.criarAdminComunidade(usuarioAdmin,novaComunidade);
    }

    public void removerComunidade(Comunidade nomeComunidade){
        this.listaComunidades.remove(nomeComunidade);
    }

    public boolean ehAdminDaComunidade(Conta usuarioAdmin, Comunidade comunidadeAdmin){
        return comunidadeAdmin.usuarioAdminComunidade.getNomeConta().equals(usuarioAdmin.getNomeConta());
    }

    public void enviarMensagemParaComunidade(Conta usuario, Scanner input){
        System.out.println("Qual o nome da comunidade que você deseja enviar a mensagem?");
        input.nextLine();

        String nomeComunidade = input.nextLine();
        System.out.println("Qual a mensagem?");
        String msgCom = input.nextLine();

        Comunidade cmdParaEnviar = getComunidadePeloNome(nomeComunidade);

        if(cmdParaEnviar!=null){
            cmdParaEnviar.enviarMsg(msgCom,usuario,cmdParaEnviar);
        }
        else{
            System.out.println("Voce nao faz parte dessa comunidade");
        }
    }

    public void entrarComunidade(Conta usuario, Scanner input) throws EntradaIncorretaException{
        System.out.println("Digite o nome da comunidade que deseja entrar");
        input.nextLine();
        String nomeComNova = input.nextLine();
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
        }
    }

    public void remocaoMembros(Conta usuario, Scanner input, Rede listaUsuarios){
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

        }

        boolean ehAdminComunidade = ehAdminDaComunidade(usuario,comunidadeRemocao);
        if(ehAdminComunidade){
            comunidadeRemocao.usuarioAdminComunidade.removerMembroComunidade(listaUsuarios,input);
        }else{
            System.out.println("Voce nao eh o administrador dessa comunidade");
        }

    }

    public void listarPedidosEntradaComunidade(Conta usuario, Scanner input){
        System.out.println("Pedidos | Digite o nome da comunidade");
        input.nextLine();
        String nomeComunidadeAdmin = input.nextLine();

        Comunidade comunidadePedidosEntrada = getComunidadePeloNome(nomeComunidadeAdmin);
        boolean ehComunidadeAdmin = ehAdminDaComunidade(usuario,comunidadePedidosEntrada);
        if(ehComunidadeAdmin){
            // listar os pedidos
            comunidadePedidosEntrada.usuarioAdminComunidade.addMembroComunidade();
        }
        else{
            System.out.println("Voce nao eh o administrador");
        }
    }


}
