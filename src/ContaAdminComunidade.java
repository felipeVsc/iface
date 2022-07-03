import java.util.ArrayList;
import java.util.Scanner;

public class ContaAdminComunidade extends ContaGeral{
    Comunidade comunidadeAdmin;
    ArrayList<Conta> pedidoEntrarComunidade;
    ArrayList<ContaGeral> aceitosComunidade;
    public ContaAdminComunidade(String nome, String login, Comunidade comunidade){
        super(nome,login);
        this.comunidadeAdmin = comunidade;
        this.pedidoEntrarComunidade = new ArrayList<>();
        this.aceitosComunidade = new ArrayList<>();
    }

    public Comunidade getComunidadeAdmin() {
        return comunidadeAdmin;
    }

    public void setComunidadeAdmin(Comunidade comunidadeAdmin) {
        this.comunidadeAdmin = comunidadeAdmin;
    }

    public ArrayList<Conta> getPedidoEntrarComunidade() {
        return pedidoEntrarComunidade;
    }

    public void setPedidoEntrarComunidade(ArrayList<Conta> pedidoEntrarComunidade) {
        this.pedidoEntrarComunidade = pedidoEntrarComunidade;
    }

    public void addMembroComunidade(){
        Scanner input = new Scanner(System.in);
        ArrayList<ContaGeral> contasAceitas = new ArrayList<>();
        for (Conta usuario : this.pedidoEntrarComunidade) {
            String msg = "Usuario: "+usuario.getNomeConta()+" deseja entrar. 1 - Aceitar | 0 - Recusar\n";
            System.out.println(msg);

            int resultado = input.nextInt();
            if(resultado==1){
                System.out.println("Aceito");
                contasAceitas.add(usuario);
                usuario.listaComunidadesMembro.add(this.comunidadeAdmin);
            }else {
                System.out.println("Recusado");
            }

        }

        this.comunidadeAdmin.listaUsuariosComunidade.addAll(contasAceitas);

        this.pedidoEntrarComunidade.clear();
    }

    public void removerMembroComunidade(Rede listaUsuarios, Scanner input){

        System.out.println("Digite o nome do usuario a ser removido");
        String usuarioRemover = input.next();
        Conta contaRemover = listaUsuarios.getConta(usuarioRemover);
        if(this.comunidadeAdmin.listaUsuariosComunidade.contains(contaRemover)){
            // remover
            this.comunidadeAdmin.listaUsuariosComunidade.remove(contaRemover);
            contaRemover.listaComunidadesMembro.remove(this.comunidadeAdmin);

        }
        else{
            System.out.println("usuario nao existe");
        }
    }



    public void removerPedidoMembro(Conta usuario){
        if(pedidoEntrarComunidade.contains(usuario)){
            pedidoEntrarComunidade.remove(usuario);
        }
        else{
            System.out.println("Nao existe o usuario");
        }
    }

}
