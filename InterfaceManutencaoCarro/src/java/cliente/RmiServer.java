/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.util.List;
import unioeste.manutencao.bo.cliente.Cliente;

/**
 *
 * @author leoscalco
 */
public interface RmiServer extends java.rmi.Remote{
    public List<Cliente> listarClientes() throws java.rmi.RemoteException;
}
