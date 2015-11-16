package convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.*;
import model.impressao.Iservidor;
import dao.*;


@FacesConverter("convertIservidor") 
public class ConvertIservidor implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if(!arg2.equals("")){
		//System.out.println("Nome>>>>>>"+arg2);
		return new DAOqueryIservidor().pesquisaUnicoNome(arg2);
		}
		else{
			return null;
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		 if(arg2 != null && arg2 instanceof Iservidor) {
			             return ((Iservidor)arg2).getNome();
			         }else{
			           return "";
			         }
		// TODO Auto-generated method stub
	
	}

}
