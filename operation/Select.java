package org.example.operation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class Select {

    public static void selectFrom(EntityManager entityManager, String table) {
        Query q = entityManager.createQuery("SELECT c FROM " + table + " c");
        List list = q.getResultList();
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    public static void selectWhere(EntityManager entityManager, String table, String column, String value) {
        Query q = entityManager.createQuery("SELECT c FROM " + table + " c WHERE c." + column + " = '" + value + "'");
        List list = q.getResultList();
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    public static void selectOrderBy(EntityManager entityManager, String table, String column) {
        Query q = entityManager.createQuery("SELECT c FROM " + table + " c ORDER BY c." + column);
        List list = q.getResultList();
        for (Object obj : list) {
            System.out.println(obj);
        }
    }
}
