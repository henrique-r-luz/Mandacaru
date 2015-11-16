package controle;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import model.Ccotacao;
import model.Citem;
import model.Corcamento;

public class ValorMedio {

	public List<Corcamento> valoMedioTotal(List<Corcamento> itensProcesso) {

	  
		for (Corcamento obj : itensProcesso) {
			@SuppressWarnings("unused")
			BigDecimal numero = new BigDecimal(obj.getCotacao().size());
			BigDecimal soma = new BigDecimal(0);
			for (Ccotacao cota : obj.getCotacao()) {

				soma = soma.add(cota.getValor());
			}
			BigDecimal result = new BigDecimal(0);

			if (!(numero.intValue() == 0)) {
				result = soma.divide(numero, MathContext.DECIMAL32).setScale(2,
						BigDecimal.ROUND_HALF_DOWN);
			}

			obj.setValorMedio(result);
		}
		return itensProcesso;
	}

	public List<Citem> valoMedioTotalItens(List<Citem> itensProcesso) {

		for (Citem obj : itensProcesso) {

			// List<Corcamento> orca = obj.getOrcamento();
            System.out.println("quandidade de orcamento>>>>>"+obj.getOrcamento());
		//	for (Corcamento orca : obj.getOrcamento()) {

				BigDecimal numero = new BigDecimal(obj.getTransOrcamento().getCotacao().size());
				BigDecimal soma = new BigDecimal(0);
				for (Ccotacao cota : obj.getTransOrcamento().getCotacao()) {

					soma = soma.add(cota.getValor());
				}
				BigDecimal result = new BigDecimal(0);

				if (!(numero.intValue() == 0)) {
					result = soma.divide(numero, MathContext.DECIMAL32)
							.setScale(2, BigDecimal.ROUND_HALF_DOWN);
				}
                System.out.println("result"); 
				obj.getTransOrcamento().setValorMedio(result);
			//}
			
		}
		return itensProcesso;
	}

}
