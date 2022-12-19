package org.example.operation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.example.entity.Brand;
import org.example.entity.Cars;
import org.example.entity.Colour;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Insert {
    public static void insertCars(EntityManager entityManager, Scanner scanner) {
        EntityTransaction tx;
        tx = entityManager.getTransaction();
        tx.begin();
        Cars car = new Cars();

        System.out.println("Enter the brand name:");
        String brandName = scanner.nextLine();
        Query q = entityManager.createQuery("SELECT c FROM Brand c WHERE c.brand = '" + brandName + "'");
        List list = q.getResultList();
        Brand brand = (Brand) list.get(0);
        brand.getCars().add(car);
        car.setBrand(brand);

        System.out.println("Enter the model:");
        String model = scanner.nextLine();
        car.setModel(model);

        System.out.println("Enter the colours:");
        String coloursName = scanner.nextLine();
        String[] colourName = coloursName.replaceAll(" ", "").split(",");
        List<Colour> colours = new ArrayList<Colour>();
        for(String colour:colourName){
            q = entityManager.createQuery("SELECT c FROM Colour c WHERE c.colour = '" + colour + "'");
            List listColour = q.getResultList();
            Colour colour1 = (Colour) listColour.get(0);
            colour1.getCars().add(car);
            colours.add(colour1);
        }
        car.setColour(colours);

        System.out.println("Enter the price:");
        Integer price = scanner.nextInt();
        car.setPrice(price);

        System.out.println("Enter the year:");
        Integer year = scanner.nextInt();
        car.setYear(year);

        entityManager.persist(car);
        tx.commit();

        Select.selectFrom(entityManager, "Cars");
    }

    public static void insertColour(EntityManager entityManager, Scanner scanner) {
        EntityTransaction tx;
        tx = entityManager.getTransaction();
        tx.begin();
        Colour colour = new Colour();

        System.out.println("Enter the colour name:");
        String colourName = scanner.nextLine();
        colour.setColour(colourName);

        System.out.println("Enter the firm name:");
        String firmName = scanner.nextLine();
        colour.setFirm(firmName);

        System.out.println("Enter the capacity:");
        Integer capacity = scanner.nextInt();
        colour.setCapacity(capacity);

        entityManager.persist(colour);
        tx.commit();

        Select.selectFrom(entityManager, "Colour");
    }

    public static void insertBrand(EntityManager entityManager, Scanner scanner) {
        EntityTransaction tx;
        tx = entityManager.getTransaction();
        tx.begin();
        Brand brand = new Brand();
        System.out.println("Enter the brand name:");
        String brandName = scanner.nextLine();
        brand.setBrand(brandName);
        System.out.println("Enter the year:");
        Integer year = scanner.nextInt();
        brand.setYear(year);
        entityManager.persist(brand);
        tx.commit();

        Select.selectFrom(entityManager, "Brand");
    }
}
