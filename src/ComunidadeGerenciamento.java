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

}
