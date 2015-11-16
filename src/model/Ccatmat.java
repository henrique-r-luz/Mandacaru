package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

public class Ccatmat implements Serializable   {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@Column(unique=true)
	private String numero;
	@OneToMany(mappedBy="catmat")
	private List<Citem> item = new ArrayList<Citem>() ;
	@OneToOne
    private CinfoMaterial material;
	@ManyToOne(fetch=FetchType.EAGER)
	private CtipoCatMat tipocatmat;
	@OneToMany
    private List<CinfoMaterialGenerico> materialGenerico = new ArrayList<CinfoMaterialGenerico>();

	
	
	
	
	public void addMaterialGenerico(CinfoMaterialGenerico material){
		materialGenerico.add(material);
	}
	
	
	public void removeMaterialGenrico(CinfoMaterialGenerico material){
		materialGenerico.remove(material);
	}
	
	public List<CinfoMaterialGenerico> getMaterialGenerico() {
		return materialGenerico;
	}


	public void setMaterialGenerico(List<CinfoMaterialGenerico> materialGenerico) {
		this.materialGenerico = materialGenerico;
	}


	public void addItem(Citem itemObj){
		item.add(itemObj);
	}
    
	
	public void removeItem(Citem itemObj){
		item.remove(itemObj);
	}
	
	public CtipoCatMat getTipocatmat() {
		return tipocatmat;
	}

	public void setTipocatmat(CtipoCatMat tipocatmat) {
		this.tipocatmat = tipocatmat;
	}

	public CinfoMaterial getMaterial() {
		return material;
	}

	public void setMaterial(CinfoMaterial material) {
		this.material = material;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	

	public List<Citem> getItem() {
		return item;
	}

	public void setItem(List<Citem> item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ccatmat other = (Ccatmat) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
