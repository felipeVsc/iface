# Code Smells

Alguns dos code-smells encontrados foram:

- Data clumps: Repetição de um conjunto de atributos que poderia ser diminuído.
- Large Class: Classes muito grandes, que poderiam ser divididas.
- Primitive Obsession: Utilização de tipos primitivos quando poderia ser possível utilizar objetos.
- Long Method: Metodos que fazem mais do que deveriam; If's aninhados

**Data Clumps**: Todas <br>
**Large Class**: 1,2 <br>
**Primitive Obsession**: 1,2,7 <br>
**Long Method**: 7,8 <br>
**Message Chains

## Data Clumps

No arquivo Rede.java, temos:

~~~java
public void pedirAmizade(Conta nomeContaEnvio, Conta nomeContaRecebedor) throws NullPointerException{

        if(nomeContaRecebedor.listaPedidoAmizade.contains(nomeContaEnvio)){
            System.out.println("Voces ja sao amigos!");
        }else{
            nomeContaRecebedor.listaPedidoAmizade.add(nomeContaEnvio);
        }


    }
~~~

Os atributos nomeContaEnvio e nomeContaRecebedor poderiam ser abstraídos em um objeto "AmizadePedido", de modo que o objeto fosse passado.

## Large Class e Long Methods

Arquivo Main.java

A função Main contém um switch com mais de 16 opções, além de realizar validações e processos de Login/Cadastro detro delas, mas poderia ser resumida apenas a chamada de funções do menu.

Algumas das funções que não deveriam estar:

~~~java
public static void modificarPerfil(Conta usuario){}
public static Conta fazerLogin(Rede lista){}
public static Comunidade ehAdminComunidade(String nomecmd, ContaGeral usuario, Rede listaUser){}
public static void modificarPerfil(Conta usuario){}
~~~

## Primitive Obssession

Uma parte do código que fica claro o uso de tipos primitivos em vez de objetos é em Perfil.java, na função:

~~~java
public void setDataNascimentoPerfil(String dataNascimentoPerfil) throws ParseException{
        Date data = new SimpleDateFormat("dd/MM/yy").parse(dataNascimentoPerfil);
        this.dataNascimentoPerfil = new SimpleDateFormat("MM-dd-yyyy").format(data);
}
~~~

Onde é passível a utilização de objeto DataDeNascimentoUsuario.

Também é utilizado valores primitivos para privacidade em MensagemFeed.java e consequentemente Rede.java, onde é utilizado um boolean.

~~~java
public void addMsgFeed(String msg, Conta usuarioEnvio, boolean privacidade, Rede rede){
        MensagemFeed novaMsgFeed = new MensagemFeed(msg,usuarioEnvio,privacidade);
        novaMsgFeed.enviarMensagem(novaMsgFeed,rede);
  }
~~~



## Message Chains e Long Method

Em Rede.java temos uma sequencia de "getX", além de if's aninhados dentro de um for, que possívelmente podem ser melhorados.

~~~java
public void listarMensagens(Conta usuarioRequisicao){
        // listar as mensagens, tomando cuidado para saber se quem enviou é amigo seu ou nao
        for (MensagemFeed msgNoFeedMostrar :
                this.feedNoticias) {
            if(msgNoFeedMostrar.isPrivacidade()){
                String msgDefaultFeed = msgNoFeedMostrar.getMensagem()+"  por: "+msgNoFeedMostrar.getUsuarioEnvio().getNomeConta();
                System.out.println(msgDefaultFeed);
            }
            else{
                if(msgNoFeedMostrar.getUsuarioEnvio().getListaAmigos().contains(usuarioRequisicao) || msgNoFeedMostrar.getUsuarioEnvio().equals(usuarioRequisicao)){
                    String msgDefaultFeed = msgNoFeedMostrar.getMensagem()+"  por: "+msgNoFeedMostrar.getUsuarioEnvio().getNomeConta();
                    System.out.println(msgDefaultFeed);
                }
            }

        }
    }
~~~

