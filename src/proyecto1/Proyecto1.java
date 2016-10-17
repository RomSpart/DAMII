/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import Vista.View1;
import Vista.View2;
import Vista.View3;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author dam2
 */
public class Proyecto1 {

    private static View1 v1;
    private static View2 v2;
    private static View3 v3;
    private static UserManager um;
    public boolean logged = false;    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        um = new UserManager();
        v1 = new View1();
        view1Up();

    }

    private static void view1Up() {

        if (v2 != null) {
            v2.setVisible(false);
        }
        if (v3 != null) {
            v3.setVisible(false);
            v3.setEnabled(false);
        }

        v1.setVisible(true);
        v1.setEnabled(true);

    }

    private static void view2Up(String user) {

        if (v3 != null) {
            v3.setVisible(false);
        }

        v1.setVisible(false);
        v2 = new View2();
        v2.setVisible(true);
        v2.setEnabled(true);
        v2.setUserToken(user);

    }

    private static void view3Up(String user) {

        if (v2 != null) {
            v2.setVisible(false);
        }

        v3 = new View3(user);
        v3.setVisible(true);
        v3.setEnabled(true);
        v1.setVisible(false);

    }

    public static boolean logIn(String username, String password) {

        if (um.buscarUsuario(username)) {
            if (um.comprobarLogin(username, password)) {
                view3Up(username);
                return true;
            } else {
                //System.out.println("Compare failed");
                return false;
            }
        } else {
            //System.out.println("No user founded.");
            return false;
        }

    }

    public static void goToMain() {
        view1Up();
    }

    public static void goToRegister(String user) {
        view2Up(user);
    }

    public static boolean register(String name, String surname, String dni,
            String permissions, String email, String username, String password) throws IOException {

        if (um.crearNuevoUsuario(name, surname, dni, permissions, email, username, password)) {
            return true;
        } else {
            return false;
        }

    }

    public static void modify(String username, String campo, String nuevo) {

        if (campo.equals("password")) {
            String pass = JOptionPane.showInputDialog(v2, "Introduce tu contraseña antigua:", "Modificar Contraseña", JOptionPane.ERROR_MESSAGE);
            if (um.comprobarLogin(username, pass)) {
                um.modificarUsuario(username, campo, nuevo);
                JOptionPane.showMessageDialog(v2, "Modificación realizada con éxito: Password.");
            } else {
                JOptionPane.showMessageDialog(v2, "Fallo. La contraseña no coincide.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (campo.equals("username")) {

            if (!(um.modificarUsuario(username, campo, nuevo))) {
                JOptionPane.showMessageDialog(v2, "Fallo. El nombre de usuario ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(v2, "Modificación realizada con éxito: Username.");
            }
        } else {
            um.modificarUsuario(username, campo, nuevo);

        }
    }

    public static String[] showData(String username) {

        String[] cadena = new String[6];

        cadena[0] = um.userData(username).getName();
        cadena[1] = um.userData(username).getSurname();
        cadena[2] = um.userData(username).getEmail();
        cadena[3] = um.userData(username).getDni();
        cadena[4] = um.userData(username).getUsername();
        cadena[5] = um.userData(username).getPassword();

        return cadena;
    }

    public static void accesibility(int i) {

        if (i > 0) {
            if (v1.isVisible()) {

                v1.getjButton1().setFont(new Font(v1.getjButton1().getName(), Font.PLAIN, 20));
                v1.getjButton2().setFont(new Font(v1.getjButton2().getName(), Font.BOLD, 20));
                v1.getjButton3().setFont(new Font(v1.getjButton3().getName(), Font.BOLD, 20));

                v1.getjLabel1().setFont(new Font(v1.getjLabel1().getName(), Font.BOLD, 20));
                v1.getjLabel2().setFont(new Font(v1.getjLabel2().getName(), Font.BOLD, 20));

            } else if (v2.isVisible()) {
                v2.setSize(470, 500);

                v2.getjButton1().setFont(new Font(v2.getjButton1().getName(), Font.BOLD, 20));
                v2.getjButton2().setFont(new Font(v2.getjButton2().getName(), Font.BOLD, 20));

                v2.getjLabel1().setFont(new Font(v2.getjLabel1().getName(), Font.BOLD, 20));
                v2.getjLabel2().setFont(new Font(v2.getjLabel2().getName(), Font.BOLD, 20));
                v2.getjLabel3().setFont(new Font(v2.getjLabel3().getName(), Font.BOLD, 20));
                v2.getjLabel4().setFont(new Font(v2.getjLabel4().getName(), Font.BOLD, 20));
                v2.getjLabel5().setFont(new Font(v2.getjLabel5().getName(), Font.BOLD, 20));
                v2.getjLabel6().setFont(new Font(v2.getjLabel6().getName(), Font.BOLD, 20));
            } else if (v3.isVisible()) {
                v3.setSize(520, 540);
                v3.getjButton1().setFont(new Font(v3.getjButton1().getName(), Font.BOLD, 20));
                v3.getjButton2().setFont(new Font(v3.getjButton2().getName(), Font.BOLD, 20));
                v3.getjButton3().setFont(new Font(v3.getjButton3().getName(), Font.BOLD, 20));

                v3.getjLabel1().setFont(new Font(v3.getjLabel1().getName(), Font.BOLD, 20));
                v3.getjLabel2().setFont(new Font(v3.getjLabel2().getName(), Font.BOLD, 20));
                v3.getjLabel3().setFont(new Font(v3.getjLabel3().getName(), Font.BOLD, 20));
                v3.getjLabel4().setFont(new Font(v3.getjLabel4().getName(), Font.BOLD, 20));
                v3.getjLabel5().setFont(new Font(v3.getjLabel5().getName(), Font.BOLD, 20));
                v3.getjLabel6().setFont(new Font(v3.getjLabel6().getName(), Font.BOLD, 20));
                v3.getjLabel7().setFont(new Font(v3.getjLabel7().getName(), Font.BOLD, 20));
                v3.getjLabel8().setFont(new Font(v3.getjLabel8().getName(), Font.BOLD, 20));
                v3.getjLabel9().setFont(new Font(v3.getjLabel9().getName(), Font.BOLD, 20));
                v3.getjLabel10().setFont(new Font(v3.getjLabel10().getName(), Font.BOLD, 20));
                v3.getjLabel11().setFont(new Font(v3.getjLabel11().getName(), Font.BOLD, 20));
                v3.getjLabel12().setFont(new Font(v3.getjLabel12().getName(), Font.BOLD, 20));
            }
        } else if (i < 0) {
            if (v1.isVisible()) {

                v1.getjButton1().setFont(new Font(v1.getjButton1().getName(), Font.BOLD, 12));
                v1.getjButton2().setFont(new Font(v1.getjButton2().getName(), Font.BOLD, 12));
                v1.getjButton3().setFont(new Font(v1.getjButton3().getName(), Font.BOLD, 12));

                v1.getjLabel1().setFont(new Font(v1.getjLabel1().getName(), Font.BOLD, 12));
                v1.getjLabel2().setFont(new Font(v1.getjLabel2().getName(), Font.BOLD, 12));

            } else if (v2.isVisible()) {
                v2.setSize(414, 439);
                v2.getjButton1().setFont(new Font(v2.getjButton1().getName(), Font.BOLD, 12));
                v2.getjButton2().setFont(new Font(v2.getjButton2().getName(), Font.BOLD, 12));

                v2.getjLabel1().setFont(new Font(v2.getjLabel1().getName(), Font.BOLD, 12));
                v2.getjLabel2().setFont(new Font(v2.getjLabel2().getName(), Font.BOLD, 12));
                v2.getjLabel3().setFont(new Font(v2.getjLabel3().getName(), Font.BOLD, 12));
                v2.getjLabel4().setFont(new Font(v2.getjLabel4().getName(), Font.BOLD, 12));
                v2.getjLabel5().setFont(new Font(v2.getjLabel5().getName(), Font.BOLD, 12));
                v2.getjLabel6().setFont(new Font(v2.getjLabel6().getName(), Font.BOLD, 12));
            } else if (v3.isVisible()) {
                v3.setSize(415, 427);
                v3.getjButton1().setFont(new Font(v3.getjButton1().getName(), Font.BOLD, 12));
                v3.getjButton2().setFont(new Font(v3.getjButton2().getName(), Font.BOLD, 12));
                v3.getjButton3().setFont(new Font(v3.getjButton3().getName(), Font.BOLD, 12));

                v3.getjLabel1().setFont(new Font(v3.getjLabel1().getName(), Font.BOLD, 12));
                v3.getjLabel2().setFont(new Font(v3.getjLabel2().getName(), Font.BOLD, 12));
                v3.getjLabel3().setFont(new Font(v3.getjLabel3().getName(), Font.BOLD, 12));
                v3.getjLabel4().setFont(new Font(v3.getjLabel4().getName(), Font.BOLD, 12));
                v3.getjLabel5().setFont(new Font(v3.getjLabel5().getName(), Font.BOLD, 12));
                v3.getjLabel6().setFont(new Font(v3.getjLabel6().getName(), Font.BOLD, 12));
                v3.getjLabel7().setFont(new Font(v3.getjLabel7().getName(), Font.BOLD, 12));
                v3.getjLabel8().setFont(new Font(v3.getjLabel8().getName(), Font.BOLD, 12));
                v3.getjLabel9().setFont(new Font(v3.getjLabel9().getName(), Font.BOLD, 12));
                v3.getjLabel10().setFont(new Font(v3.getjLabel10().getName(), Font.BOLD, 12));
                v3.getjLabel11().setFont(new Font(v3.getjLabel11().getName(), Font.BOLD, 12));
                v3.getjLabel12().setFont(new Font(v3.getjLabel12().getName(), Font.BOLD, 12));
            }
        }
    }

    public static void eraseUser(String userName) {

        String pass = JOptionPane.showInputDialog(v3, "Introduce tu contraseña:");
        if (um.comprobarLogin(userName, pass)) {

            if (um.deleteUser(userName)) {
                JOptionPane.showMessageDialog(v3, "Usuario " + userName + " borrado con éxito.");
                view1Up();
            } else {
                JOptionPane.showMessageDialog(v3, "Fallo al borrar usuario.");
            }

        } else {
            JOptionPane.showMessageDialog(v3, "Contraseña incorrecta.");
        }
    }

    public static void closeAndSave() throws IOException {
        um.closeAndWrite();
        System.exit(0);
    }

}
