
# Projeto P2 - Implementação de Rede Social - Solução dos Code Smells

### Aluno: Felipe F. Vasconcelos

### Extract Class e Extract Methods

Existiam classes muito longas, realizando diversas funcionalidades, sendo a principal a Main.java.

Além da execução do programa, eram realizados processos de Login, validações, modificações de perfil, checagem de se é administrador de uma comunidade, entre outros.

~~~java
public static void main(String[] args) {

        Rede listaUsuarios = new Rede();

        Scanner input = new Scanner(System.in);
        System.out.println("Bem-vindo.");
        Conta usuarioOn = processoLogin(input,listaUsuarios);
        
  }
~~~

Foram criadas novas classes para tomarem conta dessas funcionalidades, como por exemplo a classe Login.java, que é pertinente ao processo de Login (Entrar ou criar Conta).

~~~java
public class Login {

    Conta usuario;

    public Login(){

    }

    public Conta processoLogin(Scanner input, Rede listaUsuarios) throws ParseException, EntradaIncorretaException {

        System.out.println("Deseja entrar ou se cadastrar? 1 - Logar | 2 - Criar conta");
        String opcaoLogin;

        opcaoLogin = input.next();

        if(opcaoLogin.equals("1")){
            // Fazer o login
            return fazerLogin(listaUsuarios, input);
        }
        else if(opcaoLogin.equals("2")){
            // Criar a conta
            return addConta(input);
        }
        else {
            System.out.println("Errado!");
            return null;
        }

    }
    }
~~~

Outras mudanças como o Feed de Noticias passar a ser uma classe própria, em vez de apenas uma variável de tipo ArrayList<Mensagens>, de modo que todas as funcionalidades referentes a Feed foram passadas para a classe Feed. O mesmo aconteceu com funcionalidades referentes as comunidades que existem no sistema, onde também foi criada uma classe para esta finalidade, chamada ComunidadeGerenciamento. Anterior a isso, as duas funcionalidades se encontravam em Rede.java, acumulando funcionalidades e responsabilidades sobre a mesma.
  
Quanto ao Extract Method, alguns métodos foram criados, de modo a encurtar outras funções, reunindo suas funcionalidades em uma nova função. Por exemplo, o ato de remover as informações de usuários foi encurtado, onde anteriormente era a seguinte.
  
~~~java
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
        for (Conta user :rd.getListaUsuariosRede()) {
            // Talvez tentar juntar as duas funcoes abaixo?
            user.getListaPedidoAmizade().remove(contaUser);
            user.getListaAmigos().remove(contaUser);
            user.listaMsgs.removeIf(msg -> msg.getUsuarioEnvio().equals(contaUser));
        }
    }
~~~

  O loop foi movido para a seguinte função:
  
  ~~~java
   public void removerTodasInfoUsuarios(Conta usuarioListaGeral,Conta contaUser){
        usuarioListaGeral.getListaPedidoAmizade().remove(contaUser);
        usuarioListaGeral.getListaAmigos().remove(contaUser);
        usuarioListaGeral.listaMsgs.removeIf(msg -> msg.getUsuarioEnvio().equals(contaUser));
    }
  ~~~

 Outros loops relacionados ao print de informações foram movidos para a classe RecuperacaoInfo, que irá printar a partir de lá as informações, sendo o objeto chamado pela classe que necessita da mesma, no lugar dos loops originais.
  
  Por exemplo:
  
  Antes:
  
  ~~~java
  case 3:
                        // enviar msg para comunidade
                        for (Comunidade cmd : usuarioOn.getListaComunidadesMembro()){
                            System.out.println(cmd.getNomeComunidade());
                        }
  ~~~
  
  Depois:
  
  
  ~~~java
  recuperacaoInfo.printarComunidadesMembro();
  ~~~
  
  Entre outras mudanças foram feitas.
### Factory Pattern

Para solucionar o problema de switch statementes de "Object-Orientation Abusers", que ocorria dado o menu com mais de 16 opções, foi utilizado o padrão Factory, de modo que foram criadas subclasses da superclasse Menu, relativas ao menu de Login, Conta, Comunidade e Listar Informações.

~~~java
public class MenuFactory{


    HashMap<Integer,Object> menuMapa = new HashMap<>();
    RedeSocial redesocial;
    public MenuFactory(RedeSocial redesocial) throws ParseException, EntradaIncorretaException {

        this.redesocial = redesocial;
        this.redesocial.listaUsuarios.gerarInicio(redesocial);

        this.menuMapa.put(1, new MenuContaFactory(redesocial));
        this.menuMapa.put(2, new MenuComunidadeFactory(redesocial));
        this.menuMapa.put(3, new MenuListarFactory(redesocial));
        this.menuMapa.put(4, new Login());

    }

    public Object getMenu(Integer entrada){
        return this.menuMapa.get(entrada);
    }
~~~

Dentro desses subfactories, existe uma classe executar() que realizará a chamada de acordo com cada classe subfilha. O menu foi reconstruído, de modo que a chamada principal será feita e cada subclasse, ao ser chamada, irá lidar da forma que for mais conveniente, por exemplo, ao ser chamada MenuContaFactory, ela irá exibir os menus referente a conta, onde dentro irá existir mais uma aplicação de factory que irá ser responsável pelo menu interno.

### Data Clumps

Para a questão de pedidos de amizade, existiam atributos separados que poderiam ser reunidos em um objeto, por exemplo, tinhamos em Rede.java:

~~~java
public void pedirAmizade(Conta nomeContaEnvio, Conta nomeContaRecebedor) throws NullPointerException{

        if(nomeContaRecebedor.listaPedidoAmizade.contains(nomeContaEnvio)){
            System.out.println("Voces ja sao amigos!");
        }else{
            nomeContaRecebedor.listaPedidoAmizade.add(nomeContaEnvio);
        }


    }
~~~

Onde os atributos "nomeContaEnvio" e "nomeContaRecebedor" foram unificados em um único objeto nomeado AmizadePedido, de modo que a chamada dessa mesma função passou a ser com esse objeto, em vez dos dois campos anteriores.

~~~java
public void pedirAmizade(AmizadePedido pedidoAmizade) throws NullPointerException{

        Conta usuarioEnvioPedido = pedidoAmizade.getUsuarioEnvioPedido();
        Conta usuarioRecebedor = pedidoAmizade.getUsuarioRecebidorPedido();

        if(!usuarioEnvioPedido.listaPedidoAmizade.contains(usuarioRecebedor)){
            System.out.println("Voces ja sao amigos!");
        }else{
            usuarioRecebedor.listaPedidoAmizade.add(usuarioEnvioPedido);
        }
~~~

Outro caso de Data Clumps era o da passagem repetida de paramêtros iguais. De modo que paramêtros como usuarioOn, referente ao usuario online naquela sessão, e listaUsuarios, referente a lista de todos os usuarios do sistema, eram quase sempre passados para qualquer chamada de função.

~~~java
listaUsuarios.listaComunidades.remocaoMembros(usuarioOn,input,listaUsuarios);
listaUsuarios.removerInfo(listaUsuarios,usuarioOn);
listaUsuarios.listaComunidades.entrarComunidade(usuarioOn,input);
~~~

Para resolver esse data clump, foi criada a classe RedeSocial, que reune as informações de usuario online, lista de usuários e mais alguns auxiliares que acabavam sendo também passados repetidas vezes.

~~~java
 public RedeSocial() throws ParseException, EntradaIncorretaException {
        this.usuario = new Login().processoLogin(this.listaUsuarios);
        this.listaUsuarios = new Rede();
        this.recuperacaoInfo = new RecuperacaoInfo(this.usuario,this.listaUsuarios);
    }
~~~

### Primitive Obsession

Antes, a ideia de privacidade de uma mensagem no Feed era realizada por meio de um boolean, onde indicada se a mensagem era privada ou não. 

Classes como a seguinte demonstram esse fato.

~~~java

public void addMsgFeed(String msg, Conta usuarioEnvio, boolean privacidade, Rede rede){
        MensagemFeed novaMsgFeed = new MensagemFeed(msg,usuarioEnvio,privacidade);
        novaMsgFeed.enviarMensagem(novaMsgFeed,rede);
  }
~~~

Para solucionar esse problema foi criado um objeto PrivacidadeState, que irá conter dentro de si essa condicional. A partir disso, não é mais necessário passar o boolean, mas sim o objeto PrivacidadeState.

~~~java
PrivacidadeState privacidade = gerarPrivacidade(input);
MensagemFeed novaMsgFeed = new MensagemFeed(mensagemFeedNevio,usuarioEnvio,privacidade);
~~~

