package controle.quota;

import java.util.Date;
import java.util.GregorianCalendar;

import model.quota.QgrupoImpressao;

public  class ConfiguracaoQuota {
	
	
	public static Date dataNow(){
		GregorianCalendar calendar = new GregorianCalendar(); 
		calendar.set(GregorianCalendar.HOUR_OF_DAY,0);
		calendar.set(GregorianCalendar.MINUTE,0);
		calendar.set(GregorianCalendar.SECOND,0);
		calendar.set(GregorianCalendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	
	public  static Date ajustaData(QgrupoImpressao g){
		String[] auxstr = g.getDataAtualizacao().toString().split("-");

		int mes = Integer.parseInt(auxstr[1]) - 1;
		
	//	Date aux = new Date();
		return new Date(Integer.parseInt(auxstr[0])-1900, mes,
				Integer.parseInt(auxstr[2]));
	}

}
