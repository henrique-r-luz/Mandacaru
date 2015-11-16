package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.DAO;
import dao.DAOisereMateriasGenerico;
import dao.DAOqueryCatmat;

import model.Ccatmat;
import model.CinfoMaterialGenerico;


@ManagedBean
@ViewScoped
public class InfoMaterialGenericoControl {

	
	private CinfoMaterialGenerico material = new CinfoMaterialGenerico();
	private Ccatmat catmat = new Ccatmat();
	
	
	public Ccatmat getCatmat() {
		return catmat;
	}

	public void setCatmat(Ccatmat catmat) {
		this.catmat = catmat;
	}

	public CinfoMaterialGenerico getMaterial() {
		return material;
	}

	public void setMaterial(CinfoMaterialGenerico material) {
		this.material = material;
	}
	
	
	
	public List<CinfoMaterialGenerico> getListaTodos(){
		return new DAOqueryCatmat().listatodosCtamatGenricos(catmat.getId());
		
	}
	
	
	public void salva(){
		//System.out.println("catMat>>>>>>>>"+catmat);
		new DAOisereMateriasGenerico().adiciona(catmat, material);
		clear();
		//new DAO<CinfoMaterialGenerico>(CinfoMaterialGenerico.class).adiciona(material);
	}
	
	public String getVerificaTipoCtamat(){
		return new DAOqueryCatmat().tipoCatmat(catmat.getId());
	}
	
	
	
	public void clear(){
		 material = new CinfoMaterialGenerico();
		
	}
	
	
	
	
}
