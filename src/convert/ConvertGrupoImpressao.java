package convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.*;
import model.impressao.Iservidor;
import model.quota.QgrupoImpressao;
import model.quota.Qimpressora;
import dao.*;
import dao.quota.DAOqueryGrupoImpressao;
import dao.quota.DAOqueryImpressora;


@FacesConverter("convertGrupoImpressao") 
public class ConvertGrupoImpressao implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if(!arg2.equals("")){
		
		return new  DAOqueryGrupoImpressao().listaTodosNome(arg2);
		}
		else{
			
			return null;
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		 if(arg2 != null && arg2 instanceof QgrupoImpressao) {
			
			             return ((QgrupoImpressao)arg2).getNome();
			         }else{
			        	// System.out.println("Nome>>>>>>"+arg2);
			           return "";
			         }
		// TODO Auto-generated method stub
	
	}

}
