package dao;

import java.io.Serializable;

import model.Cpedidos;
import model.CstatusPedidos;

public class DAOatualizaPedidoStatus implements Serializable  {
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void atualizaEstatusDiretoria(Cpedidos ped){
		
		ped.setStatusPedidos(new DAO<CstatusPedidos>(CstatusPedidos.class).buscaPorId(1));
		new DAO<Cpedidos>(Cpedidos.class).atualiza(ped);
		
	}

}
