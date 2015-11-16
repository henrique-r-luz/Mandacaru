 package convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.*;
import dao.*;


@FacesConverter("convertFracasso") 
public class ConvertFracasso implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if(!arg2.equals("")){
		
		return (Cfracassados) new DAOqueryFracassados().pesquisaNome(arg2).get(0);
		}
		else{
			return null;
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		 if(arg2 != null && arg2 instanceof Cfracassados) {
			             return ((Cfracassados)arg2).getTitulo();
			         }else{
			           return "";
			         }
		// TODO Auto-generated method stub
	
	}

}
