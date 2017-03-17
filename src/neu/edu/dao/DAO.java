package neu.edu.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sun.istack.logging.Logger;

public class DAO {
	

    
	private static final ThreadLocal sessionThread = new ThreadLocal();
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    protected DAO() {
    }

    public static Session getSession()
    {
        Session session = (Session) DAO.sessionThread.get();
        
        if (session == null)
        {
            session = sessionFactory.openSession();
            DAO.sessionThread.set(session);
        }
        return session;
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
           
        }
        DAO.sessionThread.set(null);
    }

    public static void close() {
        getSession().close();
        DAO.sessionThread.set(null);
    }

}
