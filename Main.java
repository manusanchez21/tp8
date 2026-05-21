import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero(scanner, "Ingrese una opción: ");
            switch (opcion) {
                case 1:
                    mostrarDetalleCliente(banco, scanner);
                    break;
                case 2:
                    listarTodosLosClientes(banco);
                    break;
                case 3:
                    listarTransaccionesPorCliente(banco, scanner);
                    break;
                case 4:
                    informePorMes(banco, scanner);
                    break;
                case 5:
                    informePorAnio(banco, scanner);
                    break;
                case 6:
                    informeTodasLasOperaciones(banco);
                    break;
                case 7:
                    altaCliente(banco, scanner);
                    break;
                case 8:
                    operacionesFinancieras(banco, scanner);
                    break;
                case 9:
                    System.out.println("Saliendo. Los datos se guardarán al terminar.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 9);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== BANCO =====");
        System.out.println("1 - Detalle de cliente por número de cliente");
        System.out.println("2 - Listado de todos los clientes del banco");
        System.out.println("3 - Listado de transacciones por cliente");
        System.out.println("4 - Informe de operaciones en un mes determinado");
        System.out.println("5 - Informe de operaciones en un año determinado");
        System.out.println("6 - Informe de todas las operaciones");
        System.out.println("7 - Agregar cliente nuevo");
        System.out.println("8 - Operaciones de depósito/extracción");
        System.out.println("9 - Salir");
    }

    private static int leerEntero(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } else {
                scanner.nextLine();
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private static double leerMonto(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                if (valor >= 0) {
                    return valor;
                }
            } else {
                scanner.nextLine();
            }
            System.out.println("Ingrese un monto válido mayor o igual a 0.");
        }
    }

    private static void mostrarDetalleCliente(Banco banco, Scanner scanner) {
        int dni = leerEntero(scanner, "Ingrese DNI del cliente: ");
        Cliente cliente = banco.getCliente(dni);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        imprimirDetalleCliente(cliente);
    }

    private static void listarTodosLosClientes(Banco banco) {
        if (banco.getClientes().isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("\nListado de clientes:");
        for (Cliente cliente : banco.getClientes()) {
            System.out.println("- DNI: " + cliente.getDni() + ", Nombre: " + cliente.getNombre() + ", Nivel: " + nivelTexto(cliente.getCodigoNivel()) + ", Estado: " + cliente.getEstado());
        }
    }

    private static void listarTransaccionesPorCliente(Banco banco, Scanner scanner) {
        int dni = leerEntero(scanner, "Ingrese DNI del cliente: ");
        if (!banco.tieneCliente(dni)) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        List<Transaccion> transacciones = banco.listarTransaccionesCliente(dni);
        if (transacciones.isEmpty()) {
            System.out.println("El cliente no tiene transacciones registradas.");
            return;
        }
        ordenarPorFecha(transacciones);
        System.out.println("\nTransacciones del cliente " + dni + ":");
        imprimirTransacciones(transacciones);
    }

    private static void informePorMes(Banco banco, Scanner scanner) {
        int mes = leerEntero(scanner, "Ingrese mes (1-12): ");
        int anio = leerEntero(scanner, "Ingrese año (por ejemplo 2025): ");
        if (mes < 1 || mes > 12) {
            System.out.println("Mes inválido.");
            return;
        }
        Map<Cliente, List<Transaccion>> resultados = banco.getTransaccionesPorMes(mes, anio);
        imprimirInforme(resultados, "Informe de operaciones para " + mes + "/" + anio);
    }

    private static void informePorAnio(Banco banco, Scanner scanner) {
        int anio = leerEntero(scanner, "Ingrese año (por ejemplo 2025): ");
        Map<Cliente, List<Transaccion>> resultados = banco.getTransaccionesPorAnio(anio);
        imprimirInforme(resultados, "Informe de operaciones para el año " + anio);
    }

    private static void informeTodasLasOperaciones(Banco banco) {
        Map<Cliente, List<Transaccion>> resultados = banco.getTransaccionesTodas();
        imprimirInforme(resultados, "Informe de todas las operaciones registradas");
    }

    private static void altaCliente(Banco banco, Scanner scanner) {
        int dni = leerEntero(scanner, "Ingrese DNI del nuevo cliente: ");
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.println("Seleccione el nivel de cliente: 0-Estándar, 1-Plata, 2-Oro, 3-Platino");
        int nivel = leerEntero(scanner, "Nivel: ");

        Cliente cliente;
        switch (nivel) {
            case 1:
                cliente = new ClientePlata(dni, nombre);
                break;
            case 2:
                cliente = new ClienteOro(dni, nombre);
                break;
            case 3:
                cliente = new ClientePlatino(dni, nombre);
                break;
            default:
                cliente = new Cliente(dni, nombre);
                break;
        }

        try {
            banco.agregarCliente(cliente);
            System.out.println("Cliente agregado correctamente.");
        } catch (Exception e) {
            System.out.println("No se pudo agregar el cliente: " + e.getMessage());
        }
    }

    private static void operacionesFinancieras(Banco banco, Scanner scanner) {
        int dni = leerEntero(scanner, "Ingrese DNI del cliente: ");
        if (!banco.tieneCliente(dni)) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        System.out.println("1 - Depósito en pesos");
        System.out.println("2 - Depósito en dólares");
        System.out.println("3 - Extracción en pesos");
        System.out.println("4 - Extracción en dólares");
        int operacion = leerEntero(scanner, "Seleccione operación: ");
        double monto = leerMonto(scanner, "Ingrese monto: ");
        try {
            switch (operacion) {
                case 1:
                    banco.depositoPesos(dni, monto);
                    System.out.println("Depósito en pesos realizado.");
                    break;
                case 2:
                    banco.depositoDolares(dni, monto);
                    System.out.println("Depósito en dólares realizado.");
                    break;
                case 3:
                    banco.extraccionPesos(dni, monto);
                    System.out.println("Extracción en pesos realizada.");
                    break;
                case 4:
                    banco.extraccionDolares(dni, monto);
                    System.out.println("Extracción en dólares realizada.");
                    break;
                default:
                    System.out.println("Operación inválida.");
            }
        } catch (Exception e) {
            System.out.println("Error en la operación: " + e.getMessage());
        }
    }

    private static void imprimirDetalleCliente(Cliente cliente) {
        System.out.println("\nDetalle del cliente:");
        System.out.println("DNI: " + cliente.getDni());
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Fecha de ingreso: " + cliente.getAnioIngreso());
        System.out.println("Estado: " + (cliente.getEstado() ? "Activo" : "Inactivo"));
        System.out.println("Nivel: " + nivelTexto(cliente.getCodigoNivel()));
        System.out.println("Cuenta dólares: " + cliente.getNumeroCajaDeAhorroDolares() + " | Saldo: " + cliente.getSaldoDolares());
        System.out.println("Cuenta pesos: " + cliente.getNumeroCajaDeAhorroPesos() + " | Saldo: " + cliente.getSaldoPesos());
    }

    private static void imprimirInforme(Map<Cliente, List<Transaccion>> resultados, String titulo) {
        System.out.println("\n" + titulo);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron operaciones.");
            return;
        }

        int totalDepositos = 0;
        int totalExtracciones = 0;
        double montoDepositos = 0;
        double montoExtracciones = 0;

        for (Map.Entry<Cliente, List<Transaccion>> entry : resultados.entrySet()) {
            Cliente cliente = entry.getKey();
            List<Transaccion> transacciones = entry.getValue();
            ordenarPorFecha(transacciones);
            System.out.println("\nCliente: " + cliente.getNombre() + " (DNI: " + cliente.getDni() + ")");
            imprimirTransacciones(transacciones);
            for (Transaccion t : transacciones) {
                if (t.getTipoDeTransaccion() == 0) {
                    totalExtracciones++;
                    montoExtracciones += t.getMonto();
                } else {
                    totalDepositos++;
                    montoDepositos += t.getMonto();
                }
            }
        }

        System.out.println("\nResumen general:");
        System.out.println("Depósitos: " + totalDepositos + " | Total depositado: " + montoDepositos);
        System.out.println("Extracciones: " + totalExtracciones + " | Total extraído: " + montoExtracciones);
    }

    private static void imprimirTransacciones(List<Transaccion> transacciones) {
        for (Transaccion transaccion : transacciones) {
            System.out.println(" - " + transaccion.getFecha() + " | " + transaccion.getTipoTransaccionTexto() + " | "
                    + transaccion.getMonedaTexto() + " | " + transaccion.getMonto());
        }
    }

    private static void ordenarPorFecha(List<Transaccion> transacciones) {
        Collections.sort(transacciones, Comparator.comparing(Transaccion::getFecha));
    }

    private static String nivelTexto(String codigoNivel) {
        switch (codigoNivel) {
            case "1":
                return "Plata";
            case "2":
                return "Oro";
            case "3":
                return "Platino";
            default:
                return "Estándar";
        }
    }
}
