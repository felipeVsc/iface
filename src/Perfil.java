import java.util.ArrayList;

public class Perfil {

    String cidadeNascimentoPerfil = "";
    String cidadeAtualPerfil = "";
    String bioPerfil = "";
    String dataNascimentoPerfil = "";

    public Perfil( String cidadeAtual, String cidadeNascimento, String dataNascimento, String bio){
        this.cidadeAtualPerfil = cidadeAtual;
        this.cidadeNascimentoPerfil = cidadeNascimento;
        this.dataNascimentoPerfil = dataNascimento;
        this.bioPerfil = bio;

    }

    public ArrayList<String> retrieveAllPerfil(){
        ArrayList<String> infoUsuarios = new ArrayList<String>();
        infoUsuarios.add(this.cidadeAtualPerfil);
        infoUsuarios.add(this.cidadeNascimentoPerfil);
        infoUsuarios.add(this.bioPerfil);
        infoUsuarios.add(this.dataNascimentoPerfil);
        return infoUsuarios;


    }

    public String getCidadeNascimentoPerfil() {
        return cidadeNascimentoPerfil;
    }

    public void setCidadeNascimentoPerfil(String cidadeNascimentoPerfil) {
        this.cidadeNascimentoPerfil = cidadeNascimentoPerfil;
    }

    public String getCidadeAtualPerfil() {
        return cidadeAtualPerfil;
    }

    public void setCidadeAtualPerfil(String cidadeAtualPerfil) {
        this.cidadeAtualPerfil = cidadeAtualPerfil;
    }

    public String getBioPerfil() {
        return bioPerfil;
    }

    public void setBioPerfil(String bioPerfil) {
        this.bioPerfil = bioPerfil;
    }

    public String getDataNascimentoPerfil() {
        return dataNascimentoPerfil;
    }

    public void setDataNascimentoPerfil(String dataNascimentoPerfil) {
        this.dataNascimentoPerfil = dataNascimentoPerfil;
    }
}
