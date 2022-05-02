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
    };

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

    public void addMembroComunidade(Comunidade cmd){
        Scanner input = new Scanner(System.in);
        ArrayList<ContaGeral> contasAceitas = new ArrayList<>();
        for (Conta usuario : this.pedidoEntrarComunidade
        ) {
            String msg = "Usuario: "+usuario.getNomeConta()+" deseja entrar. 1 - Aceitar | 0 - Recusar\n";
            System.out.println(msg);
            int resultado = input.nextInt();
            if(resultado==1){
                System.out.println("Aceito");
                contasAceitas.add(usuario);
                usuario.listaComunidadesMembro.add(cmd);
            }else {
                System.out.println("Recusado");
            }

        }

        this.comunidadeAdmin.listaUsuariosComunidade.addAll(contasAceitas);

        limparListaPedidos();
    }

    public void removerMembroComunidade(Conta usuario){
        if(this.comunidadeAdmin.listaUsuariosComunidade.contains(usuario)){
            this.comunidadeAdmin.listaUsuariosComunidade.remove(usuario);
        }
        else{
            System.out.println("Nao existe o usuario");
        }
    }

    public void removerPedidoMembro(ContaGeral usuario){
        if(this.pedidoEntrarComunidade.contains(usuario)){
            this.pedidoEntrarComunidade.remove(usuario);
        }
        else{
            System.out.println("Nao existe o usuario");
        }
    }

    public void limparListaPedidos(){

        this.pedidoEntrarComunidade.clear();
    }
    // addMembros, removerMembros
}
