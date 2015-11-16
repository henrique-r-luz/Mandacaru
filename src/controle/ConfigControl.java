package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import controle.quota.ConexCups;
import controle.quota.Label;
import controle.quota.TestaConexao;
import dao.DAO;
import model.Ccategoria;
import model.ConfigModel;

@ManagedBean
@ViewScoped
public class ConfigControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ConfigModel conf = new ConfigModel();

	public ConfigModel getConf() {
		return conf;
	}

	public void setConf(ConfigModel conf) {
		this.conf = conf;
	}

	public void salvar() {
		if (getListaTodos().size() == 0) {
			
			

				new DAO<ConfigModel>(ConfigModel.class).adiciona(conf);

			
		} else {
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							Label.getAddConfi(),
							"Watch out for PrimeFaces!"));
		}

		clear();
	}

	public void alterar() {
	
			new DAO<ConfigModel>(ConfigModel.class).atualiza(conf);
		
	}

	public void clear() {
		conf = new ConfigModel();
	}

	public List<ConfigModel> getListaTodos() {
		return new DAO<ConfigModel>(ConfigModel.class).listaTodos();
	}

}
