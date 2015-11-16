package controle.quota;

import java.io.Serializable;

public class Label implements Serializable {

	private static  String dadosSalvo = "Os dados foram salvos!";
	private static String erroCadastro  = "Ocorreu um erro no cadastro"; 
	private static String cpfExist = "O CPF já exite!";
	private static String cpfOperacao = "Operação não concluída, cpf já existe";
	private static String transConcluida = "Transação não pode ser concluída";
	private static String dataFinal = "Data Final tem que ser maior que a inicial!";
	private static String dataInicial = "Data Inicial tem que ser menor que a Final!";
	private static String configLadap =  "A configuração do LDAP já foi realizada!"; 
	private static String testLdap = "Teste Conexão com LDAP  realizada!!";
	private static String  erroTesteldap = "Conexão com LDAP não realizada!";
	private static String  grupoImpressao = "O grupo de impressão não possui impressora cadastrada";
	private static String  servidorGrupo = "Servidor não possui Grupo de impressão";
	private static String  formatoArquivo = "Só podem ser enviados aquivos dos seguintes formato: docx, doc, odt, ods, xlsx, xls, pdf, jpg, png, ott";
    private static String  naoPossuiGrupo = "Você não possui grupo de Impressão. Procure o administrador do sistema";
	private static String naoCupsImpressora = "O servidor CUPS não enviou o documento de impressão";
	private static String  naoQuota =   "Não possui quota de impressão";
    private static String   definirPaginas  =   "É necessério definir as páginas para impressão";
    private static String numeroPagina = "O número de cópias não pode ser 0";
	private static String validadeQuota =  "Validade da quota expirou. Procure o administrador do sistema";
    private static String desligada  = "Impressora está fora da rede ou desligada!";    
    private static String definicaoPagina  =  "A definição de paginas está incorreta";
    private static String erroPagina =   "A definição de paginas está incorreta"; 
    private static String addConfi = "Já existe Configuração, para alterar pressione o botão editar";
    private static String grupoExit = "O grupo já existe!";
    private static String senhaConfirmada = "Senha não confirmada";
    private static String loginExite = "Login já existe";
    private static String excluir = "Os dados foram excluidos";
    private static String cotacaoExite = "Já existe cotação para o período definido";
    private static String valorCopia = "O valor não pode ser 0,00";
    private static String periodoCotacao = "Já existe cotação para o período definido";
    private static String alteraCotacao =  "A cotação não pode ser alterada, pois existe cópias vinculadas a mesma";
    private static String documentoImpressora =  "Documento enviado para impressora!";
    private static String transacaoNao =  "Transação não pode ser concluída";
    private static String conexaoLdap = "O sistema não pode realizar conexão com LDAP!!";
    private static String usuarioSenha = "O usuário ou senha não encontrado!!";
    private static String usuarioCadastrado = "O Usuário não foi cadastrado no sistema.";
    
    
  
    public static String getUsuarioCadastrado() {
  		return  usuarioCadastrado;
  	}
    
    public static String getConexaoLdap() {
  		return  conexaoLdap;
  	}
    
    public static String getUsuarioSenha() {
  		return  usuarioSenha;
  	}
    
    public static String getTransacaoNao() {
  		return  transacaoNao;
  	}  
    
    public static String getDocumentoImpressora() {
  		return documentoImpressora;
  	}  
    
    public static String getAlteraCotacao() {
		return alteraCotacao;
	}    
    
    public static String getPeriodoCotacao() {
		return periodoCotacao;
	}
    
    public static String getValorCopia() {
		return valorCopia;
	}
    
    public static String getDataInicial() {
		return dataInicial;
	}
    
    public static String getCotacaoExite() {
		return cotacaoExite;
	}
    
    
    public static String getSenhaConfirmada() {
		return senhaConfirmada;
	}
    
    public static String getExcluir() {
		return excluir;
	}
    
    public static String getLoginExite() {
		return loginExite;
	}
    
    
	public static String getGrupoExit() {
		return grupoExit;
	}

	public static String getAddConfi() {
		return addConfi;
	}

	public static String getErroPagina() {
		return erroPagina;
	}

	public static String getDefinicaoPagina() {
		return definicaoPagina;
	}

	public static String getDesligada() {
		return desligada;
	}

	public static String getValidadeQuota() {
		return validadeQuota;
	}

	public static String getNumeroPagina() {
		return numeroPagina;
	}

	public static String getDefinirPaginas() {
		return definirPaginas;
	}

	public static String getNaoQuota() {
		return naoQuota;
	}

	public static String getNaoCupsImpressora() {
		return naoCupsImpressora;
	}

	public static String getNaoPossuiGrupo() {
		return naoPossuiGrupo;
	}

	public static String getFormatoArquivo() {
		return formatoArquivo;
	}

	public static String getServidorGrupo() {
		return servidorGrupo;
	}

	public static String getDadosSalvo() {
		return dadosSalvo;
	}

	public static String getErroCadastro() {
		return erroCadastro;
	}

	public static String getCpfExist() {
		return cpfExist;
	}

	public static String getCpfOperacao() {
		return cpfOperacao;
	}

	public static String getTransConcluida() {
		return transConcluida;
	}

	public static String getDataFinal() {
		return dataFinal;
	}

	public static String getConfigLadap() {
		return configLadap;
	}

	public static String getTestLdap() {
		return testLdap;
	}

	public static String getErroTesteldap() {
		return erroTesteldap;
	}

	public static String getGrupoImpressao() {
		return grupoImpressao;
	}
	
	
	
	
	
	
	
	
}