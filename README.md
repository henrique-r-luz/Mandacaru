**Manual de Instalação do Sistema Mandacaru**

Roteiro :

1. instalação do cups.
1. Instalação do tomcat 7.
1. instalação do libreoffice.
1. Instalação do unoconv
1. Instalação do Mandacaru.

**Ambiente de Instalação**

A instalação do sistema foi realizada usando o sistema operacional debian-6.0.4-amd64, em um ambiente de virtualização , onde se utilizou Xen server 6.2, como hipervisor.  A configuração da máquina virtual, em que se instalou o mandacaru, possui o seguinte recurso de Hardware:

- Memória Ram  6GB.
- 4 vCPUs do Xen server 6.2.
- 20 GB de HD
- 1 network interface.

**Instalação do cups**

Considerando que o sistema debian está devidamente configurado e funcionado , vamos para o procedimento de montagem do CUPS.  A instalação do CUPS pode ser realizada através do repositório de pacotes  nativo do debian 6.0.4. Utilizando só seguinte comandos:

#*apt-get update #apt-get install cups\**

Concluída a instalação, o usuário deve acessar o cups remotamente. Isso é feito com este comando:

*#cupsctl -h 127.0.0.1:631 –remote-admin*

Para acessar o sistema de administração do cups acesse o seguinte endereço : [http://192.168.11.61:631/. O](http://192.168.11.61:631/)bserve que foi instalado a versão 1.4.4 do servidor de impressão.

**Instalação do Tomcat 7**

O tomcat 7 foi o servidor de aplicação utilizado para hospedar o sistema mandacaru. Para que, esse servidor funcione, primeiro é necessário instalar o java 7 (JRE 7). Abaixo mostra as instruções para realizar esse procedimento.

- *echo "deb h[ttp://ppa.launchpad.net/webupd8team/java/ubuntu precise](http://ppa.launchpad.net/webupd8team/java/ubuntu) main" > /etc/apt/sources.list.d/webupd8team-java.list*
- *echo "deb-src h[ttp://ppa.launchpad.net/webupd8team/java/ubuntu precise](http://ppa.launchpad.net/webupd8team/java/ubuntu) main" >> /etc/apt/sources.list.d/webupd8team-java.list*
- *apt-key adv --keyserver keyserver.ubuntu.com --recv-keys EEA14886*
- *apt-get update*
- *apt-get install oracle-java7-installer*
- *java -version*

*java version "1.7.0\_17"*

*Java(TM) SE Runtime Environment (build 1.7.0\_17-b02)*

*Java HotSpot(TM) 64-Bit Server VM (build 23.7-b01, mixed mode)*

Feito o procedimento e verificando que a versão do java é “1.7.0\_17” ou superior. É hora de instalar o tomcat 7. Para isso, execute o comando a seguir como usuário administrador.

- *cd /tmp*
- *wget http://mirror.cogentco.com/pub/apache/tomcat/tomcat-7/v7.0.55/bin/apache-*

*tomcat-7.0.55.tar.gz*

- *tar xvzf ./apache-tomcat-7.0.55.tar.gz*
\*
`           `**// mova os arquivos do tomcat 7 para  */usr/share/tomcat7* :**

- *mkdir /usr/share/tomcat7*  
- *mv /tmp/apache-tomcat-7.0.55  /usr/share/tomcat7*
\*
`           `**// criar um link simbólico tomcat para /usr/share/tomcat7** 

- *rm -f /usr/share/tomcat* 
- *ln -s /usr/share/tomcat7/apache-tomcat-7.0.37 /usr/share/tomcat*

Ao termino da configuração do tomcat 7, no intuito de facilitar a vida do administrador de rede, pode se cria um arquivo de inicialização no diretório  /etc/init.d. Esse script de inicialização é apresentada no trecho de código abaixo. 

- cd /etc/init.d/

`          `**//pode ser usado qualquer editor de texto no debian 6**

- vim /etc/init.d/tomcat 7

`           `**//escreva o  código abaixo no arquivo** 

*#!/bin/sh*

- *Provides: Tomcat*
- *Required-Start: $network*
- *Required-Stop: $network*
- *Default-Start: 2 3 5*
- *Description: Java Servlet and JSP Engine ### END INIT INFO*

 **/etc/init.d/tomcat 7** 

*case "$1" in 'start')*

`  `*/usr/share/tomcat/bin/startup.sh*

`    `*;;*

*'stop')*

`   `*/usr/share/tomcat/bin/shutdown.sh*

`    `*;;*

*\*)*

*echo "Usage: $0 { start | stop }"     ;;*

*esac*

*exit 0*

Também é necessário que o tomcat 7 seja inicializado junto do sistema operacional. Isso é feito modificando o aquivo /etc/rc.local . Como mostra os trecho abaixo.

- *vim /etc/rc.local*
\*
`           `**// editra o arquivo /etc/rc.local**

*#!/bin/sh -e*

*#*

- *rc.local*

*#*

- *This script is executed at the end of each multiuser runlevel.*
- *Make sure that the script will "exit 0" on success or any other*
- *value on error.*

*#*

- *In order to enable or disable this script just change the execution*
- *bits.*

*#*

- *By default this script does nothing.*

*/usr/share/tomcat/bin/startup.sh exit 0*

**Instalação do libreoffice**

Faça download do LibreOffice no site oficial htt[ps://pt-br.libreoffice.org/. A ](https://pt-br.libreoffice.org/)versão utilizada foi : LibreOffice\_4.2.4\_Linux\_x86-64\_deb.tar.gz. Após o download do programa siga as seguintes instruções . 

- tar zxvf LibreOffice\_4.2.4\_Linux\_x86-64\_deb.tar.gz
- cd  LibreOffice\_4.2.4.2\_Linux\_x86-64\_deb
- cd DEBS/ 
- sudo dpkg -i \*.deb

**Instalação do unoconv**

O unoconv é um conversor de arquivo. A utilidade dele no sistema Mandacaru é converter todos só arquivos que são enviados para PDF. Com intuito de facilitar o trabalho de contabilidade de impressão. Abaixo e mostra o procedimento de instalação do unoconv.

- *apt-get update*

`           `*// caso seu sistema operacional não possua o programa git instale-o  “apt-get install git”*

- *cd /tmp*
- *git clone https://github.com/dagwieers/unoconv*
- *cd unoconv/*

`           `*// caso seu sistema operaciona não possua o make instale-o “apt-get install make”*

- *make install*
- *cd ../*
- *rm -rf unoconv/*
- *unoconv --listener &*

Para deamonizer o unoconv (mais prático no servidor), crie o arquivo /etc/init.d/unoconvd com o conteúdo abaixo: 

*#!/bin/sh*

\### BEGIN INIT INFO

- Provides: unoconvd
- Required-Start: $network
- Required-Stop: $network
- Default-Start: 2 3 5
- Default-Stop:
- Description: unoconvd - Converting documents to PDF by unoconv ### END INIT INFO

case "$1" in

start)

/usr/bin/unoconv --listener &

`         `;;

stop)

killall soffice.bin          ;;

restart)

killall soffice.bin

`         `sleep 1

/usr/bin/unoconv --listener &          ;;

esac

Ajuste permissões, coloque para carregamento no boot e rode o daemon:

- chmod 755 /etc/init.d/unoconvd
- update-rc.d  unoconvd defaults
- service unoconvd start

**Instalação do Mandacaru**

Com todas as pendências instaladas e funcionando chegou a hora de realizar o deploy do sistema Mandacaru . Mas antes disso, é preciso realizar algumas configurações no tomcat 7. Primeiramente iremos configura o acesso ao sistema de gerenciamento de apps do servidor de aplicação. 

//Edite o arquivo arquivos

- vim  /usr/share/tomcat/conf/tomcat-users.xml

// o aquivo tomcat-users.xml tem que possuir configuração semelhante a está <?xml version='1.0' encoding='utf-8'?>

<tomcat-users>

`  `<role rolename="manager-gui"/>

`  `<role rolename="admin-gui"/>

` `<user username="ifnmg" password="ifnmg" roles="manager-gui, admin-gui"/>

</tomcat-users>

Para melhorar a performance do tomcat 7 , com o intuito de deixá-lo  mais robusto e rápido. Faremos algumas configurações de desempenho. É importante levar em consideração que as configuração  que serão descritas agora, leva em consideração a configuração da máquina de teste que é: Memória Ram  6GB, 4 vCPUs do Xen server 6.2 e 20 GB de HD. Caso, os recursos de hardware da sua máquina forem menores, é aconselhável readequar as configurações de performance. 

//aumentar a memoria vm java

//edite o aquivo /sh 

- vim /usr/share/tomcat/bin/catalina.sh

//adicione o comando abaixo na primeira linha do arquivo catalina.sh JAVA\_OPTS="-Xms3024m -Xmx3024m -XX:PermSize=1500m -XX:MaxPermSize=1500m -server".

Feito isso podemos realizar o deplye do aquivo sistema.war , que  é o código fonte do sistema Mandacaru. Esse procedimento pode ser feito através da GUI de administração do tomcat , acessando http://ipdoservidor:8080. O Tomcat possui um valor default máximo para o uploload de arquivo de deplye, que no caso é 2 MB. Para desabilitar isso, basta ir no arquivo usr/share/tomcat/conf/server.xml. E utilizar a tag maxPostSize="-1"

<Connector ... maxPostSize="-1"> 

Para mais informações sobre uso e configuração do Mandacaru leio o Manual do usário.
