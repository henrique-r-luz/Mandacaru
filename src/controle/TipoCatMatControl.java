package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.DAO;
import dao.DAOqueryTipoCatMat;

import model.CtipoCatMat;


@ManagedBean
@ViewScoped
public class TipoCatMatControl implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CtipoCatMat tipocatmat = new CtipoCatMat();

	
	public  TipoCatMatControl(){
		
		if(new DAOqueryTipoCatMat().pesquisaTipoCat().isEmpty()){
			
			tipocatmat.setNome("Genérico");
			tipocatmat.setSigla("false");
			new  DAO<CtipoCatMat>(CtipoCatMat.class).adiciona(tipocatmat);
			tipocatmat = new CtipoCatMat();
			tipocatmat.setNome("Específico");
			tipocatmat.setSigla("true");
			new  DAO<CtipoCatMat>(CtipoCatMat.class).adiciona(tipocatmat);
		}else{
			
		}
		
	}
	
	public CtipoCatMat getTipocatmat() {
		return tipocatmat;
	}

	public void setTipocatmat(CtipoCatMat tipocatmat) {
		this.tipocatmat = tipocatmat;
	}
	
	
	public void salva(){
	new DAO<CtipoCatMat>(CtipoCatMat.class).adiciona(tipocatmat);
	clear();
	}
	
	
	public void clear(){
		tipocatmat = new CtipoCatMat();
	}
	
	
	public List<CtipoCatMat> getListaTodos(){
		return new DAO<CtipoCatMat>(CtipoCatMat.class).listaTodos();
	}

}
