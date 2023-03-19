package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NotFoundExceptionTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product book1 = new Book(1, "Samsung", 800, "Гоголь");
    Product smartphone1 = new Smartphone(2, "Samsung", 20_000, "Корея");
    Product book2 = new Book(3, "Отцы и дети", 700, "Тургенев");
    Product book3 = new Book(4, "Ревизор", 500, "Гоголь");
    Product smartphone2 = new Smartphone(5, "Iphone", 50_000, "Китай");

    @Test
    void removeWhenProductExist() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone2);
        repository.removeById(5);
        Product[] expected = {book1, smartphone1, book2, book3};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void removeWhenProductNotExist() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone2);
        Assertions.assertThrows(NotFoundException.class,
                () -> repository.removeById(10)
        );
    }

}
