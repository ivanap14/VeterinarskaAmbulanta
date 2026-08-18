/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.domain;

import java.io.Serializable;

import java.sql.*;

import java.sql.*;

public interface GenericEntity extends Serializable {

    public String getTableName();
    
    public String getTableAlias();

    public String getColumnNamesForInsert();

    public String getInsertValues();

    public void setIdFromRS(Long id);

    public GenericEntity getEntityFromResultSet(ResultSet rs) throws SQLException;

    public String getJoinQuery();

    public String setAttributeValues();

    public String getQueryCondition();
    
    
}
