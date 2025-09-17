package com.example.ClubLectura_backend.services.impl;

import com.example.ClubLectura_backend.entities.Item;
import com.example.ClubLectura_backend.entities.SelectedItem;
import com.example.ClubLectura_backend.repositories.ItemRepository;
import com.example.ClubLectura_backend.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    //Attributes
    @Autowired
    ItemRepository itemRepository;

    //CRUD Methods
    @Override
    public Optional<Item> findById(long id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void delete(long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void delete(Item item) {
        itemRepository.delete(item);
    }

    @Override
    public Item getReferenceById(long itemId) {
        return itemRepository.getReferenceById(itemId);
    }

    /*
    @Override
    public Item findBySelectedItem(SelectedItem selectedItem) {
        return itemRepository.findBySelectedItem(selectedItem);
    }
    */


    //Logic Methods

    /*

    todo Revisar el guardado de items dependiendo de cómo sea eso de obtener los datos de la api

    Seguramente haya que crear un método especial para guardar los items,
    ya que los items pueden ser películas o libros.

     */
}
