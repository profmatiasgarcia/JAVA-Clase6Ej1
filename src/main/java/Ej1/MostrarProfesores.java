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
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MostrarProfesores {

    // nombre del controlador de JDBC y URL de la base de datos
    //static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
    static final String CONTROLADOR = "org.mariadb.jdbc.Driver";
    //static final String URL_BASEDATOS = "jdbc:mysql://localhost/instituto";
    static final String URL_BASEDATOS = "jdbc:mariadb://localhost/instituto";
    // inicia la aplicación

    public static void main(String args[]) {
        Connection conexion = null; // maneja la conexión
        Statement instruccion = null; // instrucción de consulta
        ResultSet conjuntoResultados = null; // maneja los resultados
        // se conecta a la base de datos libros y realiza una consulta
        try {
            // carga la clase controlador
            Class.forName(CONTROLADOR);
            // establece la conexión a la base de datos
            conexion = DriverManager.getConnection(URL_BASEDATOS, "root", ""); //usuario por defecto sin pass
            // crea objeto Statement para consultar la base de datos
            instruccion = conexion.createStatement();
            // consulta la base de datos
            conjuntoResultados = instruccion.executeQuery("SELECT IDProfesor, Apellido, Nombre FROM profesores");
            // procesa los resultados de la consulta
            ResultSetMetaData metaDatos = conjuntoResultados.getMetaData();
            int numeroDeColumnas = metaDatos.getColumnCount();
            System.out.println("Tabla Profesores de la base de datos Instituto:\n");
            for (int i = 1; i <= numeroDeColumnas; i++) {
                System.out.printf("%-8s\t", metaDatos.getColumnName(i));
            }
            System.out.println();
            while (conjuntoResultados.next()) {
                for (int i = 1; i <= numeroDeColumnas; i++) {
                    System.out.printf("%-8s\t", conjuntoResultados.getObject(i));
                }
                System.out.println();
            } // fin de while
        } // fin de try
        catch (SQLException excepcionSql) {
            excepcionSql.printStackTrace();
        } // fin de catch
        catch (ClassNotFoundException noEncontroClase) {
            noEncontroClase.printStackTrace();
        } // fin de catch
        finally // asegura que conjuntoResultados, instruccion y conexion estén
        // cerrados
        {
            try {
                conjuntoResultados.close();
                instruccion.close();
                conexion.close();
            } // fin de try
            catch (Exception excepcion) {
                excepcion.printStackTrace();
            } // fin de catch
        } // fin de finally
    } // fin de main
} // fin de la clase MostrarProfesores
