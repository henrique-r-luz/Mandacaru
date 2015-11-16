package controle.laboratorio;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.security.PublicKey;
import java.util.concurrent.TimeUnit;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.transport.verification.HostKeyVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteComando implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Logger log = LoggerFactory.getLogger(RemoteComando.class);
	private final String machine;
	private final String user;
	private final String password;

	public RemoteComando(final String machine, final String user,
			final String password) {
	
		this.machine = machine;
		this.user = user;
		this.password = password;
	}

	public String executeCommand(final String command) throws IOException {

		// Cliente SSH
		final SSHClient ssh = new SSHClient();
		String resp="";

		try {
			// Configura tipo de KeyVerifier
			setupKeyVerifier(ssh);
			// Conecta com a maquina remota
			ssh.connect(machine);
			// Autenticacao
			ssh.authPassword(user, password);

			// Executa comando remoto
			resp = executeCommandBySSH(ssh, command);
		} catch (RuntimeException e) {
			resp="erro";
			System.out.println("n�o executou o comando");
		} finally {

			ssh.disconnect();
		}
		return resp;
	}

	private String executeCommandBySSH(final SSHClient ssh, final String command)
			throws ConnectionException, IOException, TransportException {

		final Session session = ssh.startSession();
		BufferedReader bf = null;
		String resp = "";

		try {
			// Executa comando
			final Command cmd = session.exec(command);
			bf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
			String line;
			

			// Imprime saida, se exister
           
			while ((line = bf.readLine()) != null) {
				//System.out.println("retorno>>>>" + line);
				resp = resp + line;
			}
			//System.out.println("resp>>>" + resp);
			// Aguarda
			cmd.join(1, TimeUnit.SECONDS);
			
		} catch (IOException ioe) {
			System.out.println("n�o executou o comando"+ioe.getMessage());
		} finally {
			secureClose(bf);
			secureClose(session);
		}
		return resp;
	}

	private void setupKeyVerifier(final SSHClient ssh) {
		ssh.addHostKeyVerifier(new HostKeyVerifier() {
			@Override
			public boolean verify(String arg0, int arg1, PublicKey arg2) {
				return true; // sem verificacao
			}
		});
	}

	private void secureClose(final Closeable resource) {
		try {
			if (resource != null) {
				resource.close();
			}
		} catch (IOException ex) {
			log.error("Erro ao fechar recurso", ex);
		}
	}

	// Leia mais em: Executando Shell Scripts utilizando Java
	// http://www.devmedia.com.br/executando-shell-scripts-utilizando-java/26494#ixzz2tlsbinPQ

	// Leia mais em: Executando Shell Scripts utilizando Java
	// http://www.devmedia.com.br/executando-shell-scripts-utilizando-java/26494#ixzz2tlrnb44Q

}
