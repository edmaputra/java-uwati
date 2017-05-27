package id.edmaputra.uwati.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UwatiSessionListener implements HttpSessionListener {
	 
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("==== Session is created ====");
        event.getSession().setMaxInactiveInterval(0);
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("==== Session is destroyed ====");
    }
}
