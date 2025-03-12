package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import edu.unisabana.dyas.samples.entities.Item;

public interface ItemMapper {

    /**
     * Retorna la lista de todos los items.
     * @return Lista de items.
     */
    public List<Item> consultarItems();
    
    /**
     * Retorna un item según su id.
     * @param id Identificador del item.
     * @return El item encontrado o null.
     */
    public Item consultarItem(int id);
    
    /**
     * Inserta un nuevo item en la base de datos.
     * Se utiliza el alias "item" para mapear sus propiedades en el XML.
     * @param item Objeto Item a insertar.
     */
    public void insertarItem(@Param("item") Item item);
}
