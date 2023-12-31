    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Proveedor;
import Modelo.Coche;
import Modelo.Gasolina;
import Modelo.Electrico;
import Modelo.Cliente;
import Modelo.Revisores;
import Modelo.Revisar;
import Modelo.Comprar;
import Modelo.Proveer;
import Modelo.Basededatosserializable;
import Interfaz.Ventana1;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author adrian
 */
public class Control {
    
    private Basededatosserializable xml = new Basededatosserializable();

    public Basededatosserializable getXml() {
        return xml;
    }

    public void setXml(Basededatosserializable xml) {
        this.xml = xml;
    }

    
    
    public void añadirCliente(String dni, String nombre, String direccion, String ciudad, String numero_telefono){
    
        Cliente cliente = new Cliente(dni, nombre, direccion, ciudad, numero_telefono);
        ArrayList<Cliente> vector_clientes = new ArrayList<>();
        vector_clientes = xml.deserializacionCliente();
        
        vector_clientes.add(cliente);
        xml.serializacionClientes(vector_clientes);
    
    }
    
    public void eliminarCliente(int posicionEliminar){
        
        ArrayList<Cliente> vector_clientes = new ArrayList<>();
        vector_clientes = xml.deserializacionCliente();
        
        vector_clientes.remove(posicionEliminar);
        xml.serializacionClientes(vector_clientes);

    }
    
    public void modificarCliente(int posicionModificar, String dni, String nombre, String direccion, String ciudad, String telefono){
        
        ArrayList<Cliente> vector_clientes = new ArrayList<>();
        vector_clientes = xml.deserializacionCliente();
 
        Cliente cliente = new Cliente(dni, nombre, direccion, ciudad, telefono);
        vector_clientes.set(posicionModificar, cliente);
        
        xml.serializacionClientes(vector_clientes);

    }
    
    public boolean comprobarClienteExiste(String dni){
    
        boolean existe = false;
        ArrayList<Cliente> vector_clientes = new ArrayList<>();
        vector_clientes = xml.deserializacionCliente();
        
        for(Cliente cli : vector_clientes){
        
            if(cli.getdni_cliente().equalsIgnoreCase(dni))
            {         
                existe = true;
            }
        
        }
    
        return existe;
    }
    
    public Cliente buscarCliente(String dni){
    
        Cliente cliente = new Cliente("", "", "", "", "");
        ArrayList<Cliente> vector_clientes = new ArrayList<>();
        vector_clientes = xml.deserializacionCliente();
        int i = 0;
        
        for(Cliente cli : vector_clientes){
        
            if(cli.getdni_cliente().equalsIgnoreCase(dni))
            {         
                cliente = vector_clientes.get(i);
            }
        i++;
        }
    
        return cliente;
    }
    
    public ArrayList<Cliente> getClientes(){
    
        ArrayList<Cliente> vector_clientes = new ArrayList<>();
        vector_clientes = xml.deserializacionCliente();
                
    return vector_clientes;
    }
    
    public void añadirGasolina(int vin, String marca, String modelo, float precio, String color, int deposito){
    
        Gasolina gasolina = new Gasolina(deposito, vin, marca, modelo, precio, color);
        ArrayList<Coche> vector_coches = new ArrayList<>();
        vector_coches = xml.deserializacionCoches();
        
        vector_coches.add(gasolina);
        xml.serializacionCoche(vector_coches);
    
    }
    
    public void añadirElectrico(int vin, String marca, String modelo, float precio, String color, double bateria){
    
        Electrico electrico = new Electrico(bateria, vin, marca, modelo, precio, color);
        ArrayList<Coche> vector_coches = new ArrayList<>();
        vector_coches = xml.deserializacionCoches();
      
        vector_coches.add(electrico);
        xml.serializacionCoche(vector_coches);
    
    }
    
    public void eliminarCoche(int posicionEliminar){
        
        ArrayList<Coche> vector_coches = new ArrayList<>();
        vector_coches = xml.deserializacionCoches();
        
        vector_coches.remove(posicionEliminar);
        xml.serializacionCoche(vector_coches);

    }
    
    public void modificarCoche(int posicionModificar, double bateria, int deposito, int vin, String marca, String modelo, float precio, String color){
        
        ArrayList<Coche> vector_coches = new ArrayList<>();
        vector_coches = xml.deserializacionCoches();
 
        if(vector_coches.get(posicionModificar) instanceof Gasolina)
        {
            Gasolina gasolina = new Gasolina(deposito, vin, marca, modelo, precio, color);
            vector_coches.set(posicionModificar, gasolina);
            
        }else
        {
            Electrico electrico = new Electrico(bateria, vin, marca, modelo, precio, color);
            vector_coches.set(posicionModificar, electrico);
            
        }

        xml.serializacionCoche(vector_coches);

    }
    
    public boolean comprobarCocheExiste(int vin){

        boolean existe = false;
        ArrayList<Coche> vector_coches = new ArrayList<>();
        vector_coches = xml.deserializacionCoches();
        
        for(Coche co : vector_coches){
        
            if(co.getvin_coche() == vin)
            {         
                existe = true;
            }
        
        }
    
        return existe;
    }
    
    public ArrayList<Coche> getCoches(){
    
        ArrayList<Coche> vector_coches = new ArrayList<>();
        vector_coches = xml.deserializacionCoches();
        
    return vector_coches;
    }
    
    public int posicionCocheBuscado(int vin){
    
        ArrayList<Coche> vector_coches = new ArrayList<>();
        vector_coches = xml.deserializacionCoches();
        int posicion = 0, i = 0;
        
        for(Coche co : vector_coches){
        
            if(co.getvin_coche() == vin)
            {         
                posicion = i;
            }
        i++;
        }
        
    return posicion;
    }
    
    public void añadirProveedor(String codigo, String nombre){
    
        Proveedor proveedor = new Proveedor(codigo, nombre);
        ArrayList<Proveedor> vector_proveedores = new ArrayList<>();
        vector_proveedores = xml.deserializacionProveedores();
        
        vector_proveedores.add(proveedor);
        xml.serializacionProveedores(vector_proveedores);
    
    }
    
    public void eliminarProveedor(int posicionEliminar){
        
        ArrayList<Proveedor> vector_proveedores = new ArrayList<>();
        vector_proveedores = xml.deserializacionProveedores();
        
        vector_proveedores.remove(posicionEliminar);
        xml.serializacionProveedores(vector_proveedores);

    }
    
    public void modificarProveedor(int posicionModificar, String id, String nombre){
        
        ArrayList<Proveedor> vector_proveedores = new ArrayList<>();
        vector_proveedores = xml.deserializacionProveedores();
        Proveedor proveedor = new Proveedor(id, nombre);
                
        vector_proveedores.set(posicionModificar, proveedor);
        xml.serializacionProveedores(vector_proveedores);

    }
    
    public boolean comprobarProveedorExiste(String codigo){
    
        boolean existe = false;
        ArrayList<Proveedor> vector_proveedores = new ArrayList<>();
        vector_proveedores = xml.deserializacionProveedores();
        
        for(Proveedor pro : vector_proveedores){
        
            if(pro.getcod_proveedor().equalsIgnoreCase(codigo))
            {         
                existe = true;
            }
        
        }
    
        return existe;
    }
    
    public ArrayList<Proveedor> getProveedores(){
    
        ArrayList<Proveedor> vector_proveedores = new ArrayList<>();
        vector_proveedores = xml.deserializacionProveedores();
        
    return vector_proveedores;
    }
    
    public void añadirRevisor(String codigo, String nombre){
    
        ArrayList<Revisores> vector_revisores = new ArrayList<>();
        vector_revisores = xml.deserializacionRevisores();
        Revisores revisor = new Revisores(codigo, nombre);
        
        vector_revisores.add(revisor);
        xml.serializacionRevisores(vector_revisores);
    
    }
    
    public void eliminarRevisor(int posicionEliminar){
        
        ArrayList<Revisores> vector_revisores = new ArrayList<>();
        vector_revisores = xml.deserializacionRevisores();
        
        vector_revisores.remove(posicionEliminar);
        xml.serializacionRevisores(vector_revisores);

    }
    
    public void modificarRevisor(int posicionModificar, String id, String nombre){
 
        ArrayList<Revisores> vector_revisores = new ArrayList<>();
        vector_revisores = xml.deserializacionRevisores();
        Revisores revisor = new Revisores(id, nombre);
                
        vector_revisores.set(posicionModificar, revisor);
        xml.serializacionRevisores(vector_revisores);

    }
    
    public boolean comprobarRevisorExiste(String codigo){
    
        boolean existe = false;
        ArrayList<Revisores> vector_revisores = new ArrayList<>();
        vector_revisores = xml.deserializacionRevisores();
        
        for(Revisores revi : vector_revisores){
        
            if(revi.getcod_revisor_revisores().equalsIgnoreCase(codigo))
            {        
                existe = true;
            }
        
        }
    
        return existe;
    }
    
    public ArrayList<Revisores> getRevisores(){
    
        ArrayList<Revisores> vector_revisores = new ArrayList<>();
        vector_revisores = xml.deserializacionRevisores();
        
    return vector_revisores;
    }
    
    public void añadirCompra(String matricula, LocalDateTime fechayhora, String dni, int vin){
    
        Comprar compra = new Comprar(matricula,fechayhora,dni,vin);
        ArrayList<Comprar> vector_compras = new ArrayList<>();
        vector_compras = xml.deserializacionCompras();
        
        vector_compras.add(compra);
        xml.serializacionCompras(vector_compras);
    
    }
    
    public void eliminarCompra(int posicionEliminar){
        
        ArrayList<Comprar> vector_compras = new ArrayList<>();
        vector_compras = xml.deserializacionCompras();
        
        vector_compras.remove(posicionEliminar);
        xml.serializacionCompras(vector_compras);
    }
    
    public void modificarCompra(int posicionModificar, String matricula, LocalDateTime fechayhora, String dni, int vin){
        
        ArrayList<Comprar> vector_compras = new ArrayList<>();
        vector_compras = xml.deserializacionCompras();
 
        Comprar compra = new Comprar(matricula,fechayhora,dni,vin);
        vector_compras.set(posicionModificar, compra);
        
        xml.serializacionCompras(vector_compras);

    }
    
    public boolean comprobarCompraExiste(String matricula, LocalDateTime fecha){
    
        boolean existe = false;
        ArrayList<Comprar> vector_compras = new ArrayList<>();
        vector_compras = xml.deserializacionCompras();
        
        for(Comprar com : vector_compras){
        
            if(com.getmatricula_comprar().equalsIgnoreCase(matricula))
            {         
                existe = true;
            }
        
        }
    
        return existe;
    }
    
    public boolean comprobarCompraCocheCliente(String matricula, String dni, int vin){
    
        boolean puedeComprarlo = false;
        ArrayList<Comprar> vector_compras = new ArrayList<>();
        vector_compras = xml.deserializacionCompras();
        ArrayList<Comprar> vector_compras_ordenado = new ArrayList<>();
        
        for(int i = vector_compras.size() - 1; i > -1; i--){
        
            vector_compras_ordenado.add(vector_compras.get(i));
        
        }
        
        for(Comprar com : vector_compras_ordenado){
        
            if((com.getvin_comprar() == vin) && (com.getdni_comprar().equalsIgnoreCase(dni) == true) && (com.getmatricula_comprar().equalsIgnoreCase(matricula) == true))
            {
                puedeComprarlo = false;
                return puedeComprarlo;
            }
             
            if(((com.getvin_comprar() == vin) && (com.getdni_comprar().equalsIgnoreCase(dni) == false) && (com.getmatricula_comprar().equalsIgnoreCase(matricula) == true)) || ((com.getvin_comprar() != vin) && (com.getdni_comprar().equalsIgnoreCase(dni) == true) && (com.getmatricula_comprar().equalsIgnoreCase(matricula) != true)))
            {
                puedeComprarlo = true;
                return puedeComprarlo;
            }
        
        }
        
    return puedeComprarlo;
    }
    
    public Comprar buscarCompra(String matricula, LocalDateTime fecha){
    
        Comprar compra = new Comprar();
        ArrayList<Comprar> vector_compras = new ArrayList<>();
        vector_compras = xml.deserializacionCompras();
        int i = 0;
        
        for(Comprar com : vector_compras){
        
            if((com.getmatricula_comprar().equalsIgnoreCase(matricula)) && (com.getFechayHora() == fecha))
            {         
                compra = vector_compras.get(i);
            }
        i++;
        }
    
        return compra;
    }
    
    public ArrayList<Comprar> getCompras(){
    
        ArrayList<Comprar> vector_compras = new ArrayList<>();
        vector_compras = xml.deserializacionCompras();
                
    return vector_compras;
    }
        
   public void añadirRevision(String codigo_revision, String codigo_revisor, int vin){
    
        Revisar revision = new Revisar(codigo_revision, vin, codigo_revisor);
        ArrayList<Revisar> vector_revisiones = new ArrayList<>();
        vector_revisiones = xml.deserializacionRevisiones();
        
        vector_revisiones.add(revision);
        xml.serializacionRevisiones(vector_revisiones);
    
    }
    
    public void eliminarRevision(int posicionEliminar){
        
        ArrayList<Revisar> vector_revisiones = new ArrayList<>();
        vector_revisiones = xml.deserializacionRevisiones();
        
        vector_revisiones.remove(posicionEliminar);
        xml.serializacionRevisiones(vector_revisiones);
    }
    
    public void modificarRevision(int posicionModificar, String codigo_revision, String codigo_revisor, int vin){
        
        ArrayList<Revisar> vector_revisiones = new ArrayList<>();
        vector_revisiones = xml.deserializacionRevisiones();
 
        Revisar revision = new Revisar(codigo_revision, vin, codigo_revisor);
        vector_revisiones.set(posicionModificar, revision);
        
        xml.serializacionRevisiones(vector_revisiones);

    }
    
    public boolean comprobarRevisionExiste(String codigo){
    
        boolean existe = false;
        ArrayList<Revisar> vector_revisiones = new ArrayList<>();
        vector_revisiones = xml.deserializacionRevisiones();
        
        for(Revisar revi : vector_revisiones){
        
            if(revi.getcod_revision_revisar().equalsIgnoreCase(codigo))
            {         
                existe = true;
            }
        
        }
    
        return existe;
    }
    
    public boolean comprobarRevisionCocheRevisor(String codigo_revision, String codigo_revisor, int vin){
    
        boolean revisado = false;
        ArrayList<Revisar> vector_revisiones = new ArrayList<>();
        vector_revisiones = xml.deserializacionRevisiones();
        
        for(Revisar revi : vector_revisiones){
        
            if((revi.getcod_revisor_revisar().equalsIgnoreCase(codigo_revisor) == true) || (revi.getvin_revisar() == vin))
            {
                revisado = true;
                return revisado;
            }
            
        }
        
    return revisado;
    }
    
    public Revisar buscarRevision(String codigo){
    
        Revisar revision = new Revisar("", 0, "");
        ArrayList<Revisar> vector_revisiones = new ArrayList<>();
        vector_revisiones = xml.deserializacionRevisiones();
        int i = 0;
        
        for(Revisar revi : vector_revisiones){
        
            if((revi.getcod_revision_revisar().equalsIgnoreCase(codigo)))
            {         
                revision = vector_revisiones.get(i);
            }
        i++;
        }
    
        return revision;
    }
    
    public ArrayList<Revisar> getRevisiones(){
    
        ArrayList<Revisar> vector_revisiones = new ArrayList<>();
        vector_revisiones = xml.deserializacionRevisiones();
                
    return vector_revisiones;
    }
    
    public void añadirProvision(String codigo_proveedor, int vin){
    
        Proveer provision = new Proveer(codigo_proveedor, vin);
        ArrayList<Proveer> vector_provisiones = new ArrayList<>();
        vector_provisiones = xml.deserializacionProvsiones();
        
        vector_provisiones.add(provision);
        xml.serializacionProvisiones(vector_provisiones);
    
    }
    
    public void eliminarProvision(int posicionEliminar){
        
        ArrayList<Proveer> vector_provisiones = new ArrayList<>();
        vector_provisiones = xml.deserializacionProvsiones();
        
        vector_provisiones.remove(posicionEliminar);
        xml.serializacionProvisiones(vector_provisiones);
    }
    
    public void modificarProvision(int posicionModificar, String codigo_proveedor, int vin){
        
        ArrayList<Proveer> vector_provisiones = new ArrayList<>();
        vector_provisiones = xml.deserializacionProvsiones();
 
        Proveer provision = new Proveer(codigo_proveedor, vin);
        vector_provisiones.set(posicionModificar, provision);
        
        xml.serializacionProvisiones(vector_provisiones);

    }
    
    public boolean comprobarProvisionExiste(String codigo_proveedor, int vin){
    
        boolean existe_proveedor = false, existe_coche = false, existe = false;
        ArrayList<Proveer> vector_provisiones = new ArrayList<>();
        vector_provisiones = xml.deserializacionProvsiones();
        ArrayList<Coche> vector_coches = new ArrayList<>();
        vector_coches = xml.deserializacionCoches();
        
        for(Proveer prove : vector_provisiones){
        
            if((prove.getcod_proveedor_proveer().equalsIgnoreCase(codigo_proveedor)))
            {         
                existe_proveedor = true;
            }
        
        }
        
        for(Coche coche : vector_coches){
        
            if((coche.getvin_coche() == vin))
            {         
                existe_proveedor = true;
            }
        
        }
        
        if((existe_proveedor == true) && (existe_coche == true))
        {
            existe = true;
        }
    
        return existe;
    }
    
    public boolean comprobarProvisionCocheProveedor(String codigo_proveedor, int vin){
    
        boolean proveido = false;
        ArrayList<Proveer> vector_provisiones = new ArrayList<>();
        vector_provisiones = xml.deserializacionProvsiones();
        
        for(Proveer prove : vector_provisiones){
        
            if((prove.getcod_proveedor_proveer().equalsIgnoreCase(codigo_proveedor)) && (prove.getvin_proveer() == vin))
            {
                proveido = true;
                return proveido;
            }
        
        }
        
    return proveido;
    }
    
    public Proveer buscarProvision(String codigo){
    
        Proveer provision = new Proveer("", 0);
        ArrayList<Proveer> vector_provisiones = new ArrayList<>();
        vector_provisiones = xml.deserializacionProvsiones();
        int i = 0;
        
        for(Proveer prove : vector_provisiones){
        
            if((prove.getcod_proveedor_proveer().equalsIgnoreCase(codigo)))
            {         
                provision = vector_provisiones.get(i);
            }
        i++;
        }
    
        return provision;
    }
    
    public ArrayList<Proveer> getProvisiones(){
    
        ArrayList<Proveer> vector_provisiones = new ArrayList<>();
        vector_provisiones = xml.deserializacionProvsiones();
                
    return vector_provisiones;
    }
    
}
