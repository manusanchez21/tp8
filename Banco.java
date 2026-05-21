import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;

public class Banco {
    private HashMap<Integer, Cliente> clientes;

    public Banco() {
        clientes = new HashMap<Integer, Cliente>();
     
        try {
            cargarClientesExistentes();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> { // esto se va a ejecutar solo cuando el programa termine
            try {
                guardarClientes();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }));

    }

    public void darDeBajaCliente(Integer dni) throws ClienteNoEncontradoException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).setEstado(false);
        } else {
            throw new ClienteNoEncontradoException("El cliente a dar de baja no fue encontrado");
        }
    }

    public void darDeAltaClienteExistente(Integer dni) throws ClienteNoEncontradoException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).setEstado(true);
        } else {
            throw new ClienteNoEncontradoException("El cliente a dar de alta no fue encontrado");
        }
    }

    public void depositoDolares(Integer dni, Double monto) throws ClienteNoEncontradoException, IOException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).depositoDolares(monto);
            ;
        } else {
            throw new ClienteNoEncontradoException("El cliente a dar de baja no fue encontrado");
        }
    }

    public void depositoPesos(Integer dni, Double monto) throws ClienteNoEncontradoException, IOException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).depositoPesos(monto);
        } else {
            throw new ClienteNoEncontradoException("El cliente a dar de baja no fue encontrado");
        }
    }

    public void extraccionDolares(Integer dni, Double monto)
            throws ClienteNoEncontradoException, IOException, SaldoInsuficienteExeption {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).extraccionDolares(monto);
        } else {
            throw new ClienteNoEncontradoException("El cliente a dar de baja no fue encontrado");
        }
    }

    public void extraccionPesos(Integer dni, Double monto)
            throws ClienteNoEncontradoException, IOException, SaldoInsuficienteExeption {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).extraccionPesos(monto);
        } else {
            throw new ClienteNoEncontradoException("El cliente a dar de baja no fue encontrado");
        }
    }

    public void agregarClientePlata(ClientePlata c, Integer dni) throws InstanceAlreadyExistsException {
        if (clientes.containsKey(dni)) {
            throw new InstanceAlreadyExistsException("Esa persona ya tiene una cuenta");
        } else {
            clientes.put(dni, c);
        }
    }

    public void agregarCliente(Cliente cliente) throws InstanceAlreadyExistsException {
        if (clientes.containsKey(cliente.getDni())) {
            throw new InstanceAlreadyExistsException("Esa persona ya tiene una cuenta");
        }
        clientes.put(cliente.getDni(), cliente);
    }

    public Cliente getCliente(Integer dni) {
        return clientes.get(dni);
    }

    public ArrayList<Cliente> getClientes() {
        return new ArrayList<Cliente>(clientes.values());
    }

    public boolean tieneCliente(Integer dni) {
        return clientes.containsKey(dni);
    }

    public List<Transaccion> listarTransaccionesCliente(Integer dni) {
        Cliente cliente = clientes.get(dni);
        if (cliente == null) {
            return new ArrayList<>();
        }
        return obtenerTodasLasTransaccionesDeCliente(cliente);
    }

    public Map<Cliente, List<Transaccion>> getTransaccionesPorMes(int mes, int anio) {
        return getTransaccionesFiltradas(mes, anio);
    }

    public Map<Cliente, List<Transaccion>> getTransaccionesPorAnio(int anio) {
        return getTransaccionesFiltradas(-1, anio);
    }

    public Map<Cliente, List<Transaccion>> getTransaccionesTodas() {
        return getTransaccionesFiltradas(-1, -1);
    }

    private Map<Cliente, List<Transaccion>> getTransaccionesFiltradas(int mes, int anio) {
        Map<Cliente, List<Transaccion>> resultado = new HashMap<>();
        for (Cliente cliente : clientes.values()) {
            List<Transaccion> transacciones = obtenerTodasLasTransaccionesDeCliente(cliente);
            List<Transaccion> filtradas = new ArrayList<>();
            for (Transaccion t : transacciones) {
                int transMes = t.getFecha().getMonthValue();
                int transAnio = t.getFecha().getYear();
                if ((anio < 0 || transAnio == anio) && (mes < 0 || transMes == mes)) {
                    filtradas.add(t);
                }
            }
            if (!filtradas.isEmpty() || (mes < 0 && anio < 0 && !transacciones.isEmpty())) {
                if (mes < 0 && anio < 0) {
                    resultado.put(cliente, transacciones);
                } else {
                    resultado.put(cliente, filtradas);
                }
            }
        }
        return resultado;
    }

    private List<Transaccion> obtenerTodasLasTransaccionesDeCliente(Cliente cliente) {
        List<Transaccion> todas = new ArrayList<>();
        todas.addAll(cliente.getTransaccionesPesos());
        todas.addAll(cliente.getTransaccionesDolares());
        return todas;
    }

    private void guardarClientes() throws IOException {
        try (FileWriter writer = new FileWriter("clientes.txt");) {// esto es try-with-resources, hace que writer.close
                                                                   // se ejecute siempre
            for (Cliente cliente : clientes.values()) {
                writer.append(cliente.toString() + "," + cliente.getCodigoNivel() + "\n"); // 0 si es cliente, 1 cliente
                                                                                           // plata, 2 cliente oro, 3
                                                                                           // cliente platino
            }
        } catch (IOException e) {
            throw new IOException("Error al guardar clientes al finalizar el programa", e);
        }
    }

    private ArrayListTransacciones crearArraysTransacciones(int numeroCuentaDolares, int numeroCuentaPesos)
            throws IOException {
        ArrayListTransacciones transacciones = new ArrayListTransacciones();

        try (BufferedReader br = new BufferedReader(new FileReader("transacciones.txt"))) {
            String linea;

            double saldoDolares = 0;
            double saldoPesos = 0;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (Integer.parseInt(datos[0], 10) == numeroCuentaDolares) {
                    Transaccion transaccion = new Transaccion(datos[2], Integer.parseInt(datos[1]), Integer.parseInt(datos[3], 10), Double.parseDouble(datos[4]));
                    transacciones.agregarTransaccionDolares(transaccion);
                    if (Integer.parseInt(datos[3], 10) == 0) {
                        saldoDolares -= Double.parseDouble(datos[4]);
                    } else {
                        saldoDolares += Double.parseDouble(datos[4]);
                    }
                } else if (Integer.parseInt(datos[0], 10) == numeroCuentaPesos) {
                    Transaccion transaccion = new Transaccion(datos[2], Integer.parseInt(datos[1]), Integer.parseInt(datos[3], 10), Double.parseDouble(datos[4]));
                    transacciones.agregarTransaccionPesos(transaccion);
                    if (Integer.parseInt(datos[3], 10) == 0) {
                        saldoPesos -= Double.parseDouble(datos[4]);
                    } else {
                        saldoPesos += Double.parseDouble(datos[4]);
                    }
                }
            }
            transacciones.setSaldoDolares(saldoDolares);
            transacciones.setSaldoPesos(saldoPesos);
        }

        return transacciones;
    }

    private void cargarClientesExistentes() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("clientes.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                ArrayListTransacciones transacciones = crearArraysTransacciones(Integer.parseInt(datos[4]),
                        Integer.parseInt(datos[5]));
                Boolean estado;
                if (datos[3].equals("false")) {
                    estado = false;
                } else {
                    estado = true;
                }
                Cliente cliente = null;
                switch (Integer.parseInt(datos[6], 10)) {
                    case 0:
                        cliente = new Cliente(Integer.parseInt(datos[0], 10), datos[1], estado, datos[2],
                                transacciones.getSaldoPesos(), transacciones.getSaldoDolares(),
                                Integer.parseInt(datos[4]), Integer.parseInt(datos[5], 10),
                                transacciones.getTransaccionesDolares(), transacciones.getTransaccionesPesos());
                        break;
                    case 1:
                        cliente = new ClientePlata(Integer.parseInt(datos[0], 10), datos[1], estado, datos[2],
                                transacciones.getSaldoPesos(), transacciones.getSaldoDolares(),
                                Integer.parseInt(datos[4]), Integer.parseInt(datos[5], 10),
                                transacciones.getTransaccionesDolares(), transacciones.getTransaccionesPesos());
                        break;
                    case 2:
                        cliente = new ClienteOro(Integer.parseInt(datos[0], 10), datos[1], estado, datos[2],
                                transacciones.getSaldoPesos(), transacciones.getSaldoDolares(),
                                Integer.parseInt(datos[4]), Integer.parseInt(datos[5], 10),
                                transacciones.getTransaccionesDolares(), transacciones.getTransaccionesPesos());
                        break;
                        case 3:
                        cliente = new ClientePlatino(Integer.parseInt(datos[0], 10), datos[1], estado, datos[2],
                                transacciones.getSaldoPesos(), transacciones.getSaldoDolares(),
                                Integer.parseInt(datos[4]), Integer.parseInt(datos[5], 10),
                                transacciones.getTransaccionesDolares(), transacciones.getTransaccionesPesos());
                        break;
                    default:
                        break;
                }
                this.clientes.put(Integer.parseInt(datos[0], 10), cliente);
            }
        }
    }
}