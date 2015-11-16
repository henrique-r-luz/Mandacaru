package controle.impressao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import model.impressao.IdocCopias;

public class ContaCopias implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<IdocCopias> listaTodos;
	private int total = 0;
	private BigDecimal valor = BigDecimal.ZERO;
	public List<IdocCopias> getListaTodos() {
		return listaTodos;
	}
	public void setListaTodos(List<IdocCopias> listaTodos) {
		this.listaTodos = listaTodos;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public ContaCopias(List<IdocCopias> LsitaCopias){
		this.setListaTodos(LsitaCopias);
		contaPaginas();
	}
	
	
	public void contaPaginas(){
		 BigDecimal aux = BigDecimal.ZERO;
		 BigDecimal aux2 = valor;
		for(IdocCopias obj: listaTodos){
			aux = obj.getCotacao().getValor().multiply(new BigDecimal(obj.getNumerodeCopias()));
			valor = aux.add(aux2);
			aux2 = valor;
			
			total += obj.getNumerodeCopias();
			
		}
		
	}

}
