package controle.quota;

import java.io.Serializable;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RuncomdLinux implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Runtime run;
	private Process p;
	private Scanner scanner;

	public RuncomdLinux() {
		run = Runtime.getRuntime();
	}

	public void executecomando(String url) {
		try {
			 p = run.exec("ping localhost -c 1");
			 scanner = new Scanner(p.getInputStream());
			String resultado = scanner.useDelimiter("$$").next();
			// System.out.println("resultado>>>"+resultado);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean executeImpressao(String url, int NumeroCopias, String range,
			String impressora, String nome, boolean paisagem, boolean frenteVerso) {
		
		// converte pdf
		// unoconv -f pdf ques.odt
		String resp = "";
		String aux = "";
		
		String sideTwo = "";
		String side = "";
		
		int cont = 0;
		int cont2 = 0;
		
		try {
			// modelo de script
			// lpr -U henrique.luz -P ti -o page-ranges=2 -C henrique01
			// edital_processo_selecao_mestrado_modelagem_computacional_sistemas_02_2014.pdf
			String str = "";
			if (paisagem == true) {
				str = "landscape";
			} else {
				str = "portrait";
			}
			
			if (frenteVerso)
				side = "two-sided-long-edge";
			
			/*
			 orientacao (landscape/portrait),
			 quantidade cópias (#),
			 usuario
			 impressora
			 page-ranges
			 arquivo
			*/ 
			
//			String commandTemplate = "lpr -U usuariop -P prt-proto-01 -#1 -o page-ranges=1-2 -o landscape -o sides=one-sided";
			String commandTemplate = "lpr -U %s       -P %s           -#%s -o page-ranges=%s -o %s        -o sides=%s %s";
			String command = String.format(commandTemplate, nome, impressora, NumeroCopias, range, str, side, url);
			
//			 run.exec("lpr -o " + str + " -#" + NumeroCopias
//					+ " -U " + nome + " -P " + impressora + "  -o page-ranges="
//					+ range + " " + url);
			 run.exec(command);
			//Scanner scanner = new Scanner(p.getInputStream());
			// erro = scanner.useDelimiter("$$").next();
			// shell.executeCommand("ifconfig");
			
			while (resp == "") {

				// Aguarde 5 segundos

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try{
				 p = run.exec("lpstat -W all " + impressora
						+ " | grep  '" + nome + "'");
				// p2 = run.exec("ls");
				 scanner = new Scanner(p.getInputStream());
				 resp = scanner.useDelimiter("$$").next();
				 //executa se o comando não possui resposta
				}catch (NoSuchElementException e) {
				    e.printStackTrace();
					
				}
				// cont2++;

			}

			if (resp == "") {
				String[] job = aux.split("-");
				run.exec("lprm -P " + impressora + " " + job[1]);
				//shell.executeCommand("lprm -P " + impressora + " " + job[1]);
				return false;
			} else {
				return true;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		// Leia mais em: Executando Shell Scripts utilizando Java
		// http://www.devmedia.com.br/executando-shell-scripts-utilizando-java/26494#ixzz2tlsscBUe

	}
	
	public boolean executeImpressao(String url, int NumeroCopias, String range,
			String impressora, String nome, boolean paisagem) {
		
		
		//sides=two-sided-long-edge
		
		
		return executeImpressao(url, NumeroCopias, range, impressora, nome, paisagem, false);
		
	}

	public String verificaImpressora(String impressora)  {
		String resp = "";
		
		
			// modelo de script
			// lpr -U henrique.luz -P ti -o page-ranges=2 -C henrique01
			// edital_processo_selecao_mestrado_modelagem_computacional_sistemas_02_2014.pdf
             
			 try {
				p = run.exec("ping " + impressora + " -c 1");
				 scanner = new Scanner(p.getInputStream());
				 resp = scanner.useDelimiter("$$").next();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (NoSuchElementException e) {
			    
				e.printStackTrace();
			}
			
			//resp = shell.executeCommand("ping " + impressora + " -c 1");

		
        
		return resp;
      
	}

	public void convertArq(String url) {

		try {
            
			
			 run.exec("unoconv -f pdf " + url);
			
			//Scanner scanner = new Scanner(p.getInputStream());

			// shell.executeCommand("ifconfig");

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
