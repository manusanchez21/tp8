import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.management.InstanceAlreadyExistsException;

public class Banco {
    private HashMap<Integer,Cliente> clientes;

    public Banco(){
        clientes = new HashMap<Integer,Cliente>();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> { // esto se va a ejecutar solo cuando el programa termine
            try{
                guardarClientes();
            } catch(IOException e){
                System.err.println(e.getMessage());
            }
        }));

        // Aca se tiene que leer clientes.txt y transacciones.txt y hacer la logica para llamar a las funciones clientesPlata(), ClientesOro(), ClientesPlatino(), tambien se va a tener que llamar a Transaccion(), para ir creando cada array de transacciones y se va usar setNumeroDeCuentaCounter para setearlo al mayor numero
    }

    public void darDeBajaCliente(Integer dni) throws ClienteNoEncontradoException{
        if (clientes.containsKey(dni)) {
            clientes.get(dni).setEstado(false);
        }else{
            throw new ClienteNoEncontradoException("El cliente a dar de baja no fue encontrado");
        }
    }

    public void darDeAltaClienteExistente(Integer dni) throws ClienteNoEncontradoException{
        if (clientes.containsKey(dni)) {
            clientes.get(dni).setEstado(true);
        }else{
            throw new ClienteNoEncontradoException("El cliente a dar de alta no fue encontrado");
        }
    }

    public void depositoDolares(Integer dni, Double monto) throws ClienteNoEncontradoException, IOException{
        if (clientes.containsKey(dni)) {
            clientes.get(dni).depositoDolares(monto);;
        }else{
            throw new ClienteNoEncontradoException("El cliente a dar de baja no fue encontrado");
        }
    }

    public void depositoPesos(Integer dni, Double monto) throws ClienteNoEncontradoException, IOException{
        if (clientes.containsKey(dni)) {
            clientes.get(dni).depositoPesos(monto);
        }else{
            throw new ClienteNoEncontradoException("El cliente a dar de baja no fue encontrado");
        }
    }

    public void extraccionDolares(Integer dni, Double monto) throws ClienteNoEncontradoException, IOException, SaldoInsuficienteExeption{
        if (clientes.containsKey(dni)) {
            clientes.get(dni).extraccionDolares(monto);
        }else{
            throw new ClienteNoEncontradoException("El cliente a dar de baja no fue encontrado");
        }
    }

    public void extraccionPesos(Integer dni, Double monto) throws ClienteNoEncontradoException, IOException, SaldoInsuficienteExeption{
        if (clientes.containsKey(dni)) {
            clientes.get(dni).extraccionPesos(monto);
        }else{
            throw new ClienteNoEncontradoException("El cliente a dar de baja no fue encontrado");
        }
    }
    
    public void agregarClientePlata(ClientePlata c, Integer dni) throws InstanceAlreadyExistsException{
        if (clientes.containsKey(dni)) {
            throw new InstanceAlreadyExistsException("Esa persona ya tiene una cuenta");
        }else{
            clientes.put(dni, c);
        }
    }

    private void guardarClientes() throws IOException{
        try (FileWriter writer = new FileWriter("clientes.txt");){// esto es try-with-resources, hace que writer.close se ejecute siempre
            for (Cliente cliente : clientes.values()) {
                writer.append(cliente.toString() + "\n");
            }
        } catch (IOException e) {
            throw new IOException("Error al guardar clientes al finalizar el programa", e);
        }
    }
}