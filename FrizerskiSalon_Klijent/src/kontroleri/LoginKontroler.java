/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Frizer;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author Nikola Manjencic
 */
public class LoginKontroler {

    private final LoginForma lf;

    public LoginKontroler(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                try {
                    String username = lf.getjTextFieldUsername().getText().trim();
                    String password = String.valueOf(lf.getjPasswordField().getPassword());
                    Komunikacija.getInstance().konekcija();
                    Frizer ulogovani = Komunikacija.getInstance().login(username,password);
                    
                    if(ulogovani == null){
                        JOptionPane.showMessageDialog(lf, "Korisnik sa ovim parametrima ne postoji!","Greska",JOptionPane.ERROR_MESSAGE);
                    } else{
                        /////////////
                        JOptionPane.showMessageDialog(lf, "Prijava na sistem je uspesna!", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                        Koordinator.getInstance().setUlogovani(ulogovani);
                        koordinator.Koordinator.getInstance().otvoriGlavnuFormu();
                        lf.dispose();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LoginKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }

}
