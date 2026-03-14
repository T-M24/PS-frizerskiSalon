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

                    if (username.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra su obavezni!", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Komunikacija.getInstance().konekcija();
                    Frizer ulogovani = Komunikacija.getInstance().login(username, password);

                    if (ulogovani == null) {
                        JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni!", "Greška", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra su ispravni!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        Koordinator.getInstance().setUlogovani(ulogovani);
                        try {
                            koordinator.Koordinator.getInstance().otvoriGlavnuFormu();
                            lf.dispose();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(lf, "Ne može da se otvori glavna forma i meni!", "Greška", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(lf, "Ne može da se otvori glavna forma i meni!", "Greška", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(LoginKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void otvoriFormu() {
        lf.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        lf.setVisible(true);
    }

}
