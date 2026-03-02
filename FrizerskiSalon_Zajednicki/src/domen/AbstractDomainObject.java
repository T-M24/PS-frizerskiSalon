/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

public interface AbstractDomainObject extends Serializable{
    public String getTableName();
    
    public List<AbstractDomainObject> getList(ResultSet rs) throws Exception;
    
    public String getInsertColumns();
    
    public String getInsertValues();
    
    public String getPrimaryKey();
    
    public AbstractDomainObject getObjectFromRS(ResultSet rs) throws Exception;
    
    public String getEditableValues();
}
