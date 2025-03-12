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
import edu.unisabana.dyas.samples.entities.TipoItem;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class MyBatisExample {

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

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        while (true) {
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("1. Ver resumen de clientes");
            System.out.println("2. Buscar un cliente");
            System.out.println("3. Insertar un nuevo item");
            System.out.println("4. Consultar lista de items");
            System.out.println("5. Buscar un item");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            try (SqlSession sqlss = sessionfact.openSession()) {
                switch (opcion) {
                    case 1:
                        verResumenClientes(sqlss);
                        break;
                    case 2:
                        System.out.print("Ingrese el ID del cliente: ");
                        int idCliente = scanner.nextInt();
                        buscarCliente(sqlss, idCliente);
                        break;
                    case 3:
                        insertarItem(sqlss, scanner);
                        break;
                    case 4:
                        consultarItems(sqlss);
                        break;
                    case 5:
                        System.out.print("Ingrese el ID del item: ");
                        int idItem = scanner.nextInt();
                        buscarItem(sqlss, idItem);
                        break;
                    case 6:
                        System.out.println("Saliendo del programa...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
                sqlss.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void verResumenClientes(SqlSession sqlss) {
        ClienteMapper cm = sqlss.getMapper(ClienteMapper.class);
        System.out.println("RESUMEN DE CLIENTES: ");
        System.out.println(cm.consultarClientes());
    }

    public static void buscarCliente(SqlSession sqlss, int id) {
        ClienteMapper cm = sqlss.getMapper(ClienteMapper.class);
        Cliente cliente = cm.consultarCliente(id);
        if (cliente != null) {
            System.out.println("Cliente encontrado: " + id);
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Telefono: " + cliente.getTelefono());
            System.out.println("Direccion: " + cliente.getDireccion());
            System.out.println("Email: " + cliente.getEmail());
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public static void insertarItem(SqlSession sqlss, Scanner scanner) {
        ItemMapper itemMapper = sqlss.getMapper(ItemMapper.class);
        Item newItem = new Item();

        System.out.print("Ingrese ID del item: ");
        newItem.setId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Ingrese nombre del item: ");
        newItem.setNombre(scanner.nextLine());
        System.out.print("Ingrese descripción del item: ");
        newItem.setDescripcion(scanner.nextLine());
        System.out.print("Ingrese fecha de lanzamiento (YYYY-MM-DD): ");
        newItem.setFechalanzamiento(Date.valueOf(scanner.nextLine()));
        System.out.print("Ingrese tarifa por día: ");
        newItem.setTarifaxdia(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Ingrese formato de renta: ");
        newItem.setFormatorenta(scanner.nextLine());
        System.out.print("Ingrese género: ");
        newItem.setGenero(scanner.nextLine());
        System.out.print("Ingrese ID del tipo de item: ");
        int tipoId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese descripción del tipo de item: ");
        String tipoDesc = scanner.nextLine();
        newItem.setTipo(new TipoItem(tipoId, tipoDesc));
        
        itemMapper.insertarItem(newItem);
        System.out.println("Item insertado exitosamente.");
    }

    public static void consultarItems(SqlSession sqlss) {
        ItemMapper itemMapper = sqlss.getMapper(ItemMapper.class);
        System.out.println("LISTA DE ITEMS:");
        System.out.println(itemMapper.consultarItems());
    }

    public static void buscarItem(SqlSession sqlss, int id) {
        ItemMapper itemMapper = sqlss.getMapper(ItemMapper.class);
        Item itemConsultado = itemMapper.consultarItem(id);
        if (itemConsultado != null) {
            System.out.println("Item encontrado:");
            System.out.println("Id: " + itemConsultado.getId());
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
    }
}
