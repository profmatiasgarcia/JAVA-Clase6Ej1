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
public class Controlador {

    private AccDatos modelo;

    public Controlador(AccDatos modelo) {
        this.modelo = modelo;

    }

    public void ejecutar() {
        EntradaSalida s = new EntradaSalida();
        String menu = "1- Mostrar por consola\n2- Mostrar por ventana\n3- Salir\n\n";

        int op;
        do {
            op = s.leerDatoEntero(menu + "Ingrese la opcion que desea:");
            switch (op) {
                case 1: {
                    new VistaConsola(modelo.getConjuntoResultados(), "materias");
                    break;
                }
                case 2: {
                    new VistaVentana(modelo.getConjuntoResultados(), "materias");
                    break;
                }
                case 3: {
                    modelo.cerrarBD();
                    break;
                }
            }
        } while (op > 0 && op < 3);

    }
}
