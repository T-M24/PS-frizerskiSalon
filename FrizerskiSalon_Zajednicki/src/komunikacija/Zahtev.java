/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.Serializable;


public class Zahtev implements Serializable{
    private Operacija operation;
    private Object parametar;

    public Operacija getOperacija() {
        return operation;
    }

    public void setOperacija(Operacija operation) {
        this.operation = operation;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }

    public Zahtev(Operacija operation, Object parametar) {
        this.operation = operation;
        this.parametar = parametar;
    }

    public Zahtev() {
    }

}

