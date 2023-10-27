import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // DECLARACIONES
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        String opcion = "";

        // BUCLE PARA MOSTRAR EL MENÚ DE OPCIONES
        while (!salir) {

            System.out.println("""
                    \n----------------------------------------------
                    1. Crear base de datos
                    2. Crear tablas e insertar datos de inicio
                    3. Insertar nuevo Departamento
                    4. Insertar nuevo Empleado               
                    0. Salir
                    ----------------------------------------------""");

            opcion = sc.nextLine();                                                                         // Leer y guardar la opción del usuario.

            // ESTRUCTURA PARA LA LLAMADA A LOS MÉTODOS
            switch (opcion) {
                case "0" -> salir = true;                                                                   // Fin de la ejecución del menú.

                case "1" -> database.gestionDB.creaDB();                                                    // Llamada para crear la BBDD.

                case "2" -> {
                    database.gestionDB.crearTablaDeps();                                               // Llamada para crear las tablas.
                    database.gestionDB.crearTablaEmps();
                }


                case "3" -> code.nuevoDepartamento.pedirDatosDepto();                                            // Llamada para crear nuevo departamento.

                case "4"  -> code.InsNuevoEmpleado.insertarEmpleado();                                            // Llamada para crear nuevo empleado.

                default ->
                        System.out.println("\n>>>OPCIÓN NO VÁLIDA: Introduzca una opción del menú");        // Informar al usuario de un error cometido.
            }
        }
    }
}
