package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.entity.Brand;
import org.example.entity.Cars;
import org.example.entity.Colour;
import org.example.operation.Insert;
import org.example.operation.Select;
import org.example.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args)
    {
        Logger.getLogger("").setLevel(Level.OFF);
        SessionFactory factory = HibernateUtil.getSessionFactory();
        EntityManager entityManager = factory.createEntityManager();

        backupDB(entityManager);
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Operation available: \n\tselect \n\tinsert");
        System.out.println("Enter the operation:");
        String operation = scanner.nextLine();

        switch (operation) {
            case "select" -> {

                System.out.println("""
                        Operation available:
                        \t1. Display the entire table
                        \t2. Display the rows by column value
                        \t3. Display a sorted table by field""");
                System.out.println("Enter the number:");
                operation = scanner.nextLine();
                System.out.println("Enter the table:");
                String table = scanner.nextLine();
                switch (operation) {
                    case "1" -> Select.selectFrom(entityManager, table);
                    case "2" -> {
                        System.out.println("Enter the column:");
                        String column = scanner.nextLine();
                        System.out.println("Enter the value:");
                        String value = scanner.nextLine();
                        Select.selectWhere(entityManager, table, column, value);
                    }
                    case "3" -> {
                        System.out.println("Enter the column:");
                        String column1 = scanner.nextLine();
                        Select.selectOrderBy(entityManager, table, column1);
                    }
                    default -> System.out.println("Wrong operation");
                }
            }
            case "insert" -> {
                System.out.println("Enter the table:");
                String table = scanner.nextLine();
                switch (table) {
                    case "Cars" -> Insert.insertCars(entityManager, scanner);
                    case "Disc" -> Insert.insertColour(entityManager, scanner);
                    case "Brand" -> Insert.insertBrand(entityManager, scanner);
                }

            }
            default -> System.out.println("Wrong operation");
        }
        scanner.close();
        entityManager.close();
        factory.close();
    }

    private static void backupDB(EntityManager entityManager) {
        EntityTransaction tx;
        tx = entityManager.getTransaction();
        tx.begin();
        Brand brand1 = new Brand();
        brand1.setYear(2002);
        brand1.setBrand("Tesla");


        Colour colour1 = new Colour();
        colour1.setColour("Red");
        colour1.setFirm("GOOD COLOUR");
        colour1.setCapacity(85);
        Colour colour2 = new Colour();
        colour2.setColour("Black");
        colour2.setFirm("GOOD COLOUR");
        colour2.setCapacity(35);
        List<Colour> colours = new ArrayList<Colour>();
        colours.add(colour1);
        colours.add(colour2);

        Cars car1 = new Cars();
        car1.setBrand(brand1);
        car1.setPrice(22000);
        car1.setModel("Tesla S");
        car1.setColour(colours);
        car1.setYear(2019);
        Cars car2 = new Cars();
        car2.setBrand(brand1);
        car2.setPrice(45000);
        car2.setModel("Tesla X");
        car2.setColour(colours);
        car2.setYear(2022);
        List<Cars> cars = new ArrayList<Cars>();
        cars.add(car1);
        cars.add(car2);

        brand1.setCars(cars);
        colour1.setCars(cars);
        colour2.setCars(cars);

        entityManager.persist(brand1);
        entityManager.persist(colour1);
        entityManager.persist(colour2);
        entityManager.persist(car1);
        entityManager.persist(car2);

        tx.commit();
    }

}