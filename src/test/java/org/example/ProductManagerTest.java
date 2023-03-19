package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product book1 = new Book(1, "Samsung", 800, "Гоголь");
    Product smartphone1 = new Smartphone(2, "Samsung", 20_000, "Корея");
    Product book2 = new Book(3, "Отцы и дети", 700, "Тургенев");
    Product book3 = new Book(4, "Ревизор", 500, "Гоголь");
    Product smartphone2 = new Smartphone(5, "Iphone", 50_000, "Китай");
    @Test
    void shouldAdd() {
        manager.add(book1);
        Product[] expected = {book1};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    void shouldAddAll() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone2);
        Product[] expected = {book1, smartphone1, book2, book3, smartphone2};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    void shouldSearchBy() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone2);
        String name = "Ревизор";
        Product[] expected = {book3};
        Product[] actual = manager.searchBy(name);
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    void shouldSearchWhenFewProductsSuit() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone2);
        String name = "Samsung";
        Product[] expected = { book1, smartphone1 };
        Product[] actual = manager.searchBy(name);
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    void shouldSearchWhenProductsNotSuit() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone2);
        String name = "Август";
        Product[] expected = {};
        Product[] actual = manager.searchBy(name);
        Assertions.assertArrayEquals(expected, actual);
    }

}
