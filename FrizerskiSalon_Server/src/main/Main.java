/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import forme.ServerskaForma;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        
        java.awt.EventQueue.invokeLater(() -> {
            ServerskaForma sf = new ServerskaForma();
            sf.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
            sf.setVisible(true);
        });
    }
}
