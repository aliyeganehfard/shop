package model.util;

import model.entity.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SingleTonConnection {
    private SingleTonConnection() {
    }
    public static class lazyHolder{
        static SessionFactory INSTANCE;

        static{
            var registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Customer.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance(){
        return lazyHolder.INSTANCE;
    }
}
