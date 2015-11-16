 package convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.*;
import dao.*;


@FacesConverter("convertCategoria") 
public class ConvertCategoria implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if(!arg2.equals("")){
		
		return new DAOqueryCategoria().pesquisaUnicoNome(arg2);
		}
		else{
			return null;
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		 if(arg2 != null && arg2 instanceof Ccategoria) {
			             return ((Ccategoria)arg2).getNome();
			         }else{
			           return "";
			         }
		// TODO Auto-generated method stub
	
	}

}
