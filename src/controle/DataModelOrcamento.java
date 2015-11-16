package controle;

import java.util.List;  
import javax.faces.model.ListDataModel;  

import model.Corcamento;

import org.primefaces.model.SelectableDataModel;  
  
public class DataModelOrcamento extends ListDataModel<Corcamento> implements SelectableDataModel<Corcamento> {    
  
    public DataModelOrcamento() {  
    }  
  
    public DataModelOrcamento(List<Corcamento> data) {  
        super(data);  
    }  
      
    @Override  
    public Corcamento getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        @SuppressWarnings("unchecked")
		List<Corcamento> itens = (List<Corcamento>) getWrappedData();  
          
        for(Corcamento item : itens) {  
            if(Integer.toString(item.getId()).equals(rowKey))  
                return item;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Corcamento item) {  
        return Integer.toString(item.getId());  
    }

	
	
}  