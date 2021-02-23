package Ej1;
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
// Cuando se abre la aplicación se muestran los datos de una 
//consulta predeterminada con los datos 
// de la tabla Profesores de la Base de Datos instituto
// luego si se cambia la consulta muestra los resultados de la nueva consulta

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.Box;

public class VentanaResultadosConsulta extends JFrame {

    // URL de la Base de Datos, nombre de usuario y contraseña para JDBC
    //static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
    static final String CONTROLADOR = "org.mariadb.jdbc.Driver";
    //static final String URL_BASEDATOS = "jdbc:mysql://localhost/instituto";
    static final String URL_BASEDATOS = "jdbc:mariadb://localhost/instituto";
    static final String USER = "root";
    static final String PASS = "";

    // la consulta predeterminada obtiene todos los datos de la tabla autores
    static final String CONSULTA_PREDETERMINADA = "SELECT * FROM profesores";

    private ResultSetTableModel modeloTabla;
    private JTextArea areaConsulta;

    // crea objeto ResultSetTableModel y GUI
    public VentanaResultadosConsulta() {
        super("Visualizacion de los resultados de la consulta");

        // crea objeto ResultSetTableModel
        // y muestra la consulta de BD en un JTable
        try {
            // crea objeto TableModel para los resultados de la consulta SELECT
            // * FROM autores
            modeloTabla = new ResultSetTableModel(CONTROLADOR, URL_BASEDATOS, USER, PASS, CONSULTA_PREDETERMINADA);

            // establece objeto JTextArea en el que el usuario escribe las
            // consultas
            areaConsulta = new JTextArea(CONSULTA_PREDETERMINADA, 3, 100);
            areaConsulta.setWrapStyleWord(true);
            areaConsulta.setLineWrap(true);

            JScrollPane scrollPane = new JScrollPane(areaConsulta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            // establece objeto JButton para enviar las consultas
            JButton botonEnviar = new JButton("Enviar consulta");

            // crea objeto Box para manejar la colocación de areaConsulta y
            // botonEnviar en la GUI
            Box boxNorte = Box.createHorizontalBox();
            boxNorte.add(scrollPane);
            boxNorte.add(botonEnviar);

            // crea delegado de JTable para modeloTabla
            JTable tablaResultados = new JTable(modeloTabla);

            // coloca los componentes de la GUI en el panel de contenido
            add(boxNorte, BorderLayout.NORTH);
            add(new JScrollPane(tablaResultados), BorderLayout.CENTER);

            // crea componente de escucha de eventos para botonEnviar
            botonEnviar.addActionListener(
                    new ActionListener() {
                // pasa la consulta al modelo de la tabla
                public void actionPerformed(ActionEvent evento) {
                    // realiza una nueva consulta
                    try {
                        modeloTabla.establecerConsulta(areaConsulta.getText());
                    } // fin de try
                    catch (SQLException excepcionSql) {
                        JOptionPane.showMessageDialog(null, excepcionSql.getMessage(), "Error en Base de Datos",
                                JOptionPane.ERROR_MESSAGE);

                        // trata de recuperarse de una consulta inválida
                        // del usuario
                        // ejecutando la consulta predeterminada
                        try {
                            modeloTabla.establecerConsulta(CONSULTA_PREDETERMINADA);
                            areaConsulta.setText(CONSULTA_PREDETERMINADA);
                        } // fin de try
                        catch (SQLException excepcionSql2) {
                            JOptionPane.showMessageDialog(null, excepcionSql2.getMessage(),
                                    "Error en Base de Datos", JOptionPane.ERROR_MESSAGE);

                            // verifica que esté cerrada la conexión a
                            // la Base de Datos
                            modeloTabla.desconectarDeBaseDatos();

                            System.exit(1); // termina la aplicación
                        } // fin de catch interior
                    } // fin de catch exterior
                } // fin de actionPerformed
            } // fin de la clase interna ActionListener
            ); // fin de la llamada a addActionListener

            setSize(500, 250); // establece el tamaño de la ventana
            setVisible(true); // muestra la ventana

        } // fin de try
        catch (ClassNotFoundException noEncontroClase) {
            JOptionPane.showMessageDialog(null, "No se encontro controlador de Base de Datos",
                    "No se encontro el controlador", JOptionPane.ERROR_MESSAGE);

            System.exit(1); // termina la aplicación
        } // fin de catch
        catch (SQLException excepcionSql) {
            JOptionPane.showMessageDialog(null, excepcionSql.getMessage(), "Error en Base de Datos",
                    JOptionPane.ERROR_MESSAGE);

            // verifica que esté cerrada la conexión a la Base de Datos
            modeloTabla.desconectarDeBaseDatos();

            System.exit(1); // termina la aplicación
        } // fin de catch

        // cierra la ventana cuando el usuario sale de la aplicación (se
        // sobrescribe
        // el valor predeterminado de HIDE_ON_CLOSE)
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // verifica que esté cerrada la conexión a la Base de Datos cuando el
        // usuario sale de la aplicación
        addWindowListener(
                new WindowAdapter() {
            // se desconecta de la Base de Datos y sale cuando se ha
            // cerrado la ventana
            public void windowClosed(WindowEvent evento) {
                modeloTabla.desconectarDeBaseDatos();
                System.exit(0);
            } // fin del método windowClosed
        } // fin de la clase interna WindowAdapter
        ); // fin de la llamada a addWindowListener
    } // fin del constructor de VentanaResultadosConsulta

    // ejecuta la aplicación
} // fin de la clase VentanaResultadosConsulta
