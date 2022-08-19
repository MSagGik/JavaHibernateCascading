package ru.msaggik.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.msaggik.hibernate.model.Item;
import ru.msaggik.hibernate.model.Person;

public class App {
    public static void main( String[] args ) {
        // подключение файла конфигурации hibernate.properties и классов Person и Item
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        // создание сессии из configuration
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        // сессия
        Session session = sessionFactory.getCurrentSession();

        try {
            // начало транзакции
            session.beginTransaction();

            // создание нового пользователя
            Person person = new Person("Alice", 25);
            // создание новых товаров у данного пользователя
            person.addItem(new Item("Scooter"));
            person.addItem(new Item("Radio"));
            person.addItem(new Item("Glasses"));
            // сохранение данных пользователя и его товаров используя каскадирование Hibernate
            session.save(person);

            // закрытие транзакции
            session.getTransaction().commit();
        } finally {
            // закрытие сессии
            sessionFactory.close();
        }
    }
}