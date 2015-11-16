package controle;

import java.io.Serializable;

public enum Status implements Serializable  {
    EM_COMPRAS(3), EM_DIRETORIA_PROCESSO(2),EM_PREGAO(6), EM_PEDIDOS(1), ENTREGE(5), EM_EMPENHO(4),INSERIDO_SISTEMA(0);
    
    private final int valor;
    Status(int valorOpcao){
		valor = valorOpcao;
	}
	public int getValor(){
		return valor;
	}


}
