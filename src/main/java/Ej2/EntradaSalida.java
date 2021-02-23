package Ej2;
/**
 * @author Prof Matias Garcia.
 * <p> Copyright (C) 2017 para <a href = "https://www.profmatiasgarcia.com.ar/"> www.profmatiasgarcia.com.ar </a>
 * - con licencia GNU GPL3.
 * <p> Este programa es software libre. Puede redistribuirlo y/o modificarlo bajo los términos de la
 * Licencia Pública General de GNU según es publicada por la Free Software Foundation, 
 * bien con la versión 3 de dicha Licencia o bien (según su elección) con cualquier versión posterior. 
 * Este programa se distribuye con la esperanza de que sea útil, pero SIN NINGUNA GARANTÍA, 
 * incluso sin la garantía MERCANTIL implícita o sin garantizar la CONVENIENCIA PARA UN PROPÓSITO
 * PARTICULAR. Véase la Licencia Pública General de GNU para más detalles.
 * Debería haber recibido una copia de la Licencia Pública General junto con este programa. 
 * Si no ha sido así ingrese a <a href = "http://www.gnu.org/licenses/"> GNU org </a>
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class EntradaSalida {

    public int leerDatoEntero(String mensaje) {
        return Integer.parseInt(JOptionPane.showInputDialog(mensaje));
    }

    public float leerDatoReal(String mensaje) {
        return Float.parseFloat(JOptionPane.showInputDialog(mensaje));
    }

    public String leerCadena(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }

    public char leerChar(String mensaje) {
        return JOptionPane.showInputDialog(mensaje).charAt(0);
    }

    public String leerPassword(String mensaje) {
        final JPasswordField pass = new JPasswordField();
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                pass.requestFocusInWindow();
            }
        };

        JOptionPane.showConfirmDialog(null, new Object[]{mensaje, pass}, "Ingreso palabra clave",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        return String.valueOf(pass.getPassword());
    }

    public void mostrarCadena(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

}
