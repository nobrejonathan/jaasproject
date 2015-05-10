/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ucb.talp.model.lazy;

import br.ucb.talp.model.User;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Jonathan
 */
public class LazyUserDataModel extends LazyDataModel<User> {
    private static final long serialVersionUID = -1898861078913124125L;
    
    private List<User> datasource;

    public LazyUserDataModel(List<User> datasource) {
        setDatasource(datasource);
    }

    @Override
    public User getRowData(String rowKey) {
        for (User user : getDatasource()) {
            if (user.getId().toString().equals(rowKey)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(User object) {
        return object.getId();
    }

    @Override
    public int getRowCount() {
        return getDatasource().size();
    }
    
    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<User> data = new ArrayList<>();
        
        for (User user : getDatasource()) {
            Boolean match = true;
            
            if (filters != null) {
                for (Iterator<String> iterator = filters.keySet().iterator(); iterator.hasNext();) {
                    try {
                        String filterProperty = iterator.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(user.getClass().getField(filterProperty).get(user));
                        
                        if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                        
                    } catch (Exception ex) {
                        Logger.getLogger(LazyUserDataModel.class.getName()).log(Level.SEVERE, null, ex);
                        match = false;
                    }
                }
            }
            
            if (match) {
                data.add(user);
            }
        }
        
        // Row Count
        int dataSize = data.size();
        setRowCount(dataSize);
        
        // Paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }
    
    public List<User> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<User> datasource) {
        this.datasource = datasource;
    }
}
