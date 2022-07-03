import excecoes.NumCpfInvalidoException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

//    public ArrayList<String> retrieveAllPerfil(){
//        ArrayList<String> infoUsuarios = new ArrayList<>();
//        infoUsuarios.add(this.numCpfUsuario);
//        infoUsuarios.add(this.bioPerfil);
//        infoUsuarios.add(this.dataNascimentoPerfil);
//        return infoUsuarios;
//    }

    @Override
    public String toString() {
        return "Perfil{" +
                "numCpfUsuario='" + numCpfUsuario + '\'' +
                ", bioPerfil='" + bioPerfil + '\'' +
                ", dataNascimentoPerfil='" + dataNascimentoPerfil + '\'' +
                '}';
    }

    public String getNumCpfUsuario() {
        return this.numCpfUsuario;
    }

    public void setNumCpfUsuario(String numCpfUsuarioNovo) throws NumCpfInvalidoException {
        if(!numCpfUsuarioNovo.matches("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")){
            throw new NumCpfInvalidoException("CPF INVALIDO");
        }else {
            this.numCpfUsuario = numCpfUsuarioNovo;
        }


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

    public void setDataNascimentoPerfil(String dataNascimentoPerfil){
        try{
            Date data = new SimpleDateFormat("dd/MM/yy").parse(dataNascimentoPerfil);
            this.dataNascimentoPerfil = new SimpleDateFormat("MM-dd-yyyy").format(data);

        } catch (ParseException e){
            System.out.println("Formato incorrento, tente novamente");
        }

    }


}
