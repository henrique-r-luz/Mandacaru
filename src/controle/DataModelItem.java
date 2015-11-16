package controle;

import java.io.Serializable;
import java.util.List;  
import javax.faces.model.ListDataModel;  

import model.Citem;

import org.primefaces.model.SelectableDataModel;  
  
public class DataModelItem extends ListDataModel<Citem> implements SelectableDataModel<Citem>   {    
  
    public DataModelItem() {  
    }  
  
    public DataModelItem(List<Citem> data) {  
        super(data);  
    }  
      
    @Override  
    public Citem getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        @SuppressWarnings("unchecked")
		List<Citem> itens = (List<Citem>) getWrappedData();  
          
        for(Citem item : itens) {  
            if(Integer.toString(item.getId()).equals(rowKey))  
                return item;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Citem item) {  
        return Integer.toString(item.getId());  
    }

	
	
}  