package convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.*;
import dao.*;


@FacesConverter("convertCatmat") 
public class ConvertCatmat implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if(!arg2.equals("")){
		
		return new DAOqueryCatmat().pesquisaUnicoNumero(arg2);
		}
		else{
			return null;
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		 if(arg2 != null && arg2 instanceof Ccatmat) {
			             return ((Ccatmat)arg2).getNumero();
			         }else{
			           return "";
			         }
		// TODO Auto-generated method stub
	
	}

}
