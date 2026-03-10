/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import repozitorijum_db_impl.DbRepozitorijumGenericki;
import repozitorijum.Repozitorijum;
import repozitorijum_db.DBRepozitorijum;


public abstract class ApstraktnaGenerickaOperacija {
    protected final Repozitorijum broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepozitorijumGenericki();
    }
    
    public final void izvrsi(Object objekat, String kljuc) throws Exception{
        try{
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat,kljuc);
            potvrdiTransakciju();
        } catch(Exception e){
            ponistiTransakciju();
            throw e;
        } finally{
//            ugasiKonekciju(); OVDE SE VRATITI
        }
    }

    protected abstract void preduslovi(Object param) throws Exception;

    private void ponistiTransakciju() throws Exception{
        ((DBRepozitorijum) broker).rollback();
    }

    private void zapocniTransakciju() throws Exception{
        ((DBRepozitorijum) broker).connect();
    }

    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;

    private void potvrdiTransakciju() throws Exception{
        ((DBRepozitorijum) broker).commit();
    }

    private void ugasiKonekciju() throws Exception{
        ((DBRepozitorijum) broker).disconnect();
    }
}
