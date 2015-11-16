package convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.CtipoCatMat;


import dao.*;


@FacesConverter("convertTipoCatMat") 
public class ConvertTipoCatMat implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if(!arg2.equals("")){
		return new DAOqueryTipoCatMat().pesquisaNomeUnicoNome(arg2);
		}
		else{
			return null;
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		 if(arg2 != null && arg2 instanceof CtipoCatMat) {
			             return ((CtipoCatMat)arg2).getNome();
			         }else{
			           return "";
			         }
		// TODO Auto-generated method stub
	
	}

}
