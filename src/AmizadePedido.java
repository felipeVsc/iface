public class AmizadePedido {

    Conta usuarioEnvioPedido;
    Conta usuarioRecebidorPedido;

    public AmizadePedido(Conta usuarioEnvioPedido, Conta usuarioRecebidorPedido) {
        this.usuarioEnvioPedido = usuarioEnvioPedido;
        this.usuarioRecebidorPedido = usuarioRecebidorPedido;
    }

    public Conta getUsuarioEnvioPedido() {
        return usuarioEnvioPedido;
    }

    public void setUsuarioEnvioPedido(Conta usuarioEnvioPedido) {
        this.usuarioEnvioPedido = usuarioEnvioPedido;
    }

    public Conta getUsuarioRecebidorPedido() {
        return usuarioRecebidorPedido;
    }

    public void setUsuarioRecebidorPedido(Conta usuarioRecebidorPedido) {
        this.usuarioRecebidorPedido = usuarioRecebidorPedido;
    }
}
