package controle.quota;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.quota.DAOatualizaQuota;


public class Agendamento implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		DAOatualizaQuota dao;
		// TODO Auto-generated method stub
		//System.out.println("olaaaaaaaaaa");
		dao = new  DAOatualizaQuota();
		dao.atualiza();
		
		
		
	}
	
	

}
