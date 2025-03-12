/*
 * Copyright (C) 2015 cesarvefe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.unisabana.dyas.samples.services.client;

import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.unisabana.dyas.samples.entities.Cliente;
import edu.unisabana.dyas.samples.entities.Item;
import edu.unisabana.dyas.samples.entities.ItemRentado;
import edu.unisabana.dyas.samples.entities.TipoItem;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author cesarvefe
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
    System.out.println("RESUMEN DE CLIENTES: ");
    SqlSessionFactory sessionfact = getSqlSessionFactory();
    
    // Usamos try-with-resources para asegurar el cierre al final
    try (SqlSession sqlss = sessionfact.openSession()) {
        // Obtener mapper de Cliente
        ClienteMapper cm = sqlss.getMapper(ClienteMapper.class);
        System.out.println(cm.consultarClientes());
        
        // Consultar un cliente específico
        int id = 123456789;
        Cliente cliente = cm.consultarCliente(id);
        System.out.println("BUSQUEDA DE CLIENTE: ");
        if (cliente != null) {
            System.out.println("Cliente encontrado: "+ id);
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Telefono: " + cliente.getTelefono());
            System.out.println("Direccion: " + cliente.getDireccion());
            System.out.println("Email: " + cliente.getEmail());
        } else {
            System.out.println("Cliente no encontrado.");
        }
        
        // Obtener mapper de Item
        ItemMapper itemMapper = sqlss.getMapper(ItemMapper.class);
        Item newItem = new Item();
        newItem.setId(11);
        newItem.setNombre("Nuevooo");
        newItem.setDescripcion("Descrip");
        newItem.setFechalanzamiento(Date.valueOf("2008-01-23"));
        newItem.setTarifaxdia(1000);
        newItem.setFormatorenta("Semanal");
        newItem.setGenero("Mueble");
        TipoItem tipoItem = new TipoItem(2, "Mueble");
        newItem.setTipo(tipoItem);
        itemMapper.insertarItem(newItem);
        
        System.out.println("LISTA DE ITEMS:");
        System.out.println(itemMapper.consultarItems());
        
        Item itemConsultado = itemMapper.consultarItem(10);
        if (itemConsultado != null) {
            System.out.println("\nItem nuevo encontrado:");
            System.out.println("ID: " + itemConsultado.getId());
            System.out.println("Nombre: " + itemConsultado.getNombre());
            System.out.println("Descripción: " + itemConsultado.getDescripcion());
            System.out.println("Fecha Lanzamiento: " + itemConsultado.getFechalanzamiento());
            System.out.println("Tarifa por día: " + itemConsultado.getTarifaxdia());
            System.out.println("Formato renta: " + itemConsultado.getFormatorenta());
            System.out.println("Género: " + itemConsultado.getGenero());
            System.out.println("Tipo: " + itemConsultado.getTipo().getID() + ": " + itemConsultado.getTipo().getDescripcion());
        } else {
            System.out.println("Item no encontrado.");
        }
        
        // Commit al finalizar todas las operaciones
        sqlss.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}