package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.*;
import model.Ccategoria;
import model.Ccatmat;
import model.Citem;
import model.CinfoMaterial;

@ManagedBean
@ViewScoped
public class ItemControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Citem item = new Citem();
	private CinfoMaterial material = new CinfoMaterial();
	private InfoMaterialGenericoControl materialGenericoControl = new InfoMaterialGenericoControl();
	private String numeroCat;
	private String ativa = "true";// valor de ativação do nome e descrição do
									// formulário.
	private DataModelItem modelItens;
	private Ccatmat catmat;

	
	
	
	
	public Ccatmat getCatmat() {
		return catmat;
	}


	public InfoMaterialGenericoControl getMaterialGenericoControl() {
		return materialGenericoControl;
	}

	public void setMaterialGenericoControl(
			InfoMaterialGenericoControl materialGenericoControl) {
		this.materialGenericoControl = materialGenericoControl;
	}

	public DataModelItem getModelItens() {
		return modelItens;
	}

	public String getAtiva() {
		return ativa;
	}

	public void setAtiva(String ativa) {
		this.ativa = ativa;
	}

	public String getNumeroCat() {
		// if(new DAOqueryCatmatGenerico().pesquisaNumero(numero))
		return numeroCat;
	}

	public void setNumeroCat(String numeroCat) {
		this.numeroCat = numeroCat;
	}

	public CinfoMaterial getMaterial() {
		return material;
	}

	public void setMaterial(CinfoMaterial material) {
		this.material = material;
	}

	public Ccatmat getCatmatObj() {
		return item.getCatmat();
	}

	public void setCatmatObj(Ccatmat catmatObj) {
		catmat = catmatObj;
		item.setCatmat(catmatObj);
		materialGenericoControl.setCatmat(catmatObj);
		setMaterial(catmatObj.getMaterial());
		setNumeroCat(item.getCatmat().getNumero());
		setAtiva("false");

	}

	public Citem getItem() {
		return item;

	}

	public void setItem(Citem item) {
		this.item = item;
	}

	@SuppressWarnings("unused")
	private void catMatInicial() {
		// if (new DAO<CcatmatGenerico>(CcatmatGenerico.class).listaTodos()
		// .isEmpty()) {
		// FacesContext.getCurrentInstance().addMessage(
		// null,
		// new FacesMessage(FacesMessage.SEVERITY_FATAL,
		// "O catmat genérico Precisa ser cadastrado", ""));
		// } else {

		// setNumeroCat(catmatGenerico.getNumero());
		// setAtiva("false");
		// }
	}

	public ItemControl() {
		modelItens = new DataModelItem(getListaTodos());
		catMatInicial();

	}

	public void salva() {
		if (new DAOqueryItem().pesquisCatmatItem(numeroCat).isEmpty()) {
			if (new DAOqueryMaterial().pesquisaNome(material.getNome())
					.isEmpty() && new DAOqueryMaterial().pesquisaDescricao(// pesquisa
																			// tem
																			// que
																			// ser
																			// no
																			// material
																			// sdo
																			// item
					material.getDescricao()).isEmpty()) {
				new DAOinsereItem().adiciona(item, material, catmat);
				clear();
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"nome ou Descrição do material já exite",
								"Watch out for PrimeFaces!"));
			}
		} else {
			if (catmat.getTipocatmat().getSigla().equals("false")) {
                    if( new DAOqueryMaterial().pesquisaDescricao(material.getDescricao()).isEmpty()){
                    	//System.out.println("insere material alterado");
                    	new DAOinsereItem().insereMaterialItem(item, material, catmat);
                    	clear();
                    }
                    else{
                    	FacesContext.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_WARN,
										"ja existe uma descrição desse material",
										"Watch out for PrimeFaces!"));
                    	
                    }
			} else {
				FacesContext.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_WARN,
										"Catmat já exite",
										"Watch out for PrimeFaces!"));
			}
		}

	}

	public List<Citem> getListaTodos() {
		// criar uma query para criar todos itens
		return new DAO<Citem>(Citem.class).listaTodos();
	}

	public void clear() {
		item = new Citem();
		material = new CinfoMaterial();
		numeroCat = "";
		materialGenericoControl.setCatmat(null);
		setAtiva("true");
		// item.setCategoria(new Ccategoria());
		// item.setMaterial(new CinfoMaterial());
		catMatInicial();

	}

}
