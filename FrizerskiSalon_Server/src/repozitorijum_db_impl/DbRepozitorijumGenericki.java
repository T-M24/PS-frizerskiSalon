/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum_db_impl;

import domen.AbstractDomainObject;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import repozitorijum_db.DbConnectionFactory;
import repozitorijum_db.DBRepozitorijum;

/**
 *
 * @author Nikola Manjencic
 */
public class DbRepozitorijumGenericki implements DBRepozitorijum<AbstractDomainObject> {

    @Override
    public List<AbstractDomainObject> getAll(AbstractDomainObject param, String uslov) throws Exception {
        List<AbstractDomainObject> list = new ArrayList<>();
        String upit = "SELECT * FROM " + param.getTableName();
        if (uslov != null) { //ovde se vracam posle
            upit += uslov;
        }
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        list = param.getList(rs);
        rs.close();
        st.close();
        return list;
    }

    @Override
    public void add(AbstractDomainObject param) throws Exception {
        String upit = "INSERT INTO " + param.getTableName() + " (" + param.getInsertColumns()
                + ") VALUES ( " + param.getInsertValues() + " )";
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void edit(AbstractDomainObject param) throws Exception {
        String upit = "UPDATE " + param.getTableName() + " SET " + param.getEditableValues();
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();

    }

    @Override
    public void delete(AbstractDomainObject param) throws Exception {
        String upit = "DELETE FROM " + param.getTableName() + " WHERE " + param.getPrimaryKey();
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public List<AbstractDomainObject> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
