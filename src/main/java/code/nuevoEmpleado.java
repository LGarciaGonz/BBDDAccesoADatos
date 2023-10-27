package code;

import libs.Leer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class nuevoEmpleado {

    public static void pedirDatosEmp() {

        System.out.println("\n-----[ INTRODUCE LOS DATOS DEL NUEVO EMPLEADO ]-----");

        Scanner sc = new Scanner(System.in);

        int numEmpleado = 0;
        String apellido = "";
        String oficio = "";
        int dir = 0;
        Date fechaAlt;
        String fechaComoTexto = "";
        float salario = 0;
        float comision = 0;
        int numDepto = 0;

        try {

            // LEER NÚMERO DE EMPLEADO ---------------
            System.out.print("Número de empleado: ");
            Leer.pedirEntero(String.valueOf(numEmpleado));

            // LEER APELLIDO ---------------
            System.out.print("Apellido: ");
            Leer.pedirCadena(apellido);

            // LEER OFICIO ---------------
            System.out.print("Oficio: ");
            Leer.pedirCadena(oficio);

            // LEER DIR ---------------
            System.out.print("Código de director: ");
            Leer.pedirEntero(String.valueOf(dir));

            // LEER FECHA DE ALTA ---------------
            System.out.print("Fecha de alta (Formato yyyy/MM/dd): ");
            Leer.pedirCadena(fechaComoTexto);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            try {
                fechaAlt = sdf.parse(fechaComoTexto);
            } catch (ParseException e) {
                System.err.println("\n>>> ERROR: No se ha podido leer la fecha correctamente.");
            }

            // LEER SALARIO ---------------
            System.out.print("Salario: ");
            Leer.pedirFloat(String.valueOf(salario));

            // LEER COMISIÓN ---------------
            System.out.print("Comisión: ");
            Leer.pedirFloat(String.valueOf(comision));

            // LEER NÚMERO DE DEPTO ---------------
            System.out.print("Número de departamento: ");
            Leer.pedirEntero(String.valueOf(numDepto));

        } catch (InputMismatchException e) {
            System.err.println("\n>>> ERROR: EL TIPO DE DATO INTRODUCIDO NO ES CORRECTO");
            nuevoEmpleado.pedirDatosEmp();
        }
    }
}
