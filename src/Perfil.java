import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Perfil {


    String numCpfUsuario;
    String bioPerfil;
    String dataNascimentoPerfil;

    public Perfil( String numCpfUsuario, String dataNascimento, String bio) throws ParseException {
        this.numCpfUsuario = numCpfUsuario;

        Date data = new SimpleDateFormat("dd/MM/yy").parse(dataNascimento);
        this.dataNascimentoPerfil = new SimpleDateFormat("MM-dd-yyyy").format(data);
        this.bioPerfil = bio;

    }

    public ArrayList<String> retrieveAllPerfil(){
        ArrayList<String> infoUsuarios = new ArrayList<String>();
        infoUsuarios.add(this.numCpfUsuario);
        infoUsuarios.add(this.bioPerfil);
        infoUsuarios.add(this.dataNascimentoPerfil);
        return infoUsuarios;


    }


    public String getNumCpfUsuario() {
        return this.numCpfUsuario;
    }

    public void setNumCpfUsuario(String numCpfUsuarioNovo){
        this.numCpfUsuario = numCpfUsuarioNovo;
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

    public void setDataNascimentoPerfil(String dataNascimentoPerfil) throws ParseException{
        Date data = new SimpleDateFormat("dd/MM/yy").parse(dataNascimentoPerfil);
        this.dataNascimentoPerfil = new SimpleDateFormat("MM-dd-yyyy").format(data);
    }
}
