package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static final SessionFactory session = buildSessionFactory();

	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
		try
		{
			Configuration cfg= new Configuration();
			cfg.configure("/META-INF/hibernate.cfg.xml");
			return cfg.buildSessionFactory();
		}catch(Throwable e)
		{
			System.out.println("Deu pau! Manolo!/n " + e);
			throw new ExceptionInInitializerError();
		}
	}

	public static SessionFactory getSession() {
		return session;
	}
	
	/*
	// método para gerar as conexoes com o banco de dados do hibernate
    private static final SessionFactory sessionFactory;

    static {
        try {

            Configuration configuration = new Configuration();

            configuration.configure("/META-INF/hibernate.cfg.xml");
            org.hibernate.service.ServiceRegistry serviceRegistry =
            		new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
           
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {

            System.err.println("Deu pau! Manolo!/n " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    */
}
