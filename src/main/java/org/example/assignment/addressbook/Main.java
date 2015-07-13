package org.example.assignment.addressbook;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedHttpSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import org.example.assignment.addressbook.service.ContactService;
import org.example.assignment.addressbook.ui.LayoutBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@Title("Addressbook App")
@Theme("valo")
public class Main extends UI {

    private ApplicationContext context;
    private ContactService contactService;

    @Override
    protected void init(VaadinRequest request) {
        contactService = getContactService(request);
        Component mainLayout = LayoutBuilder.buildLayout(contactService);
        setContent(mainLayout);
    }

    public ContactService getContactService(VaadinRequest request) {
        WrappedSession session = request.getWrappedSession();
        HttpSession httpSession = ((WrappedHttpSession) session).getHttpSession();
        ServletContext servletContext = httpSession.getServletContext();
        context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        return (ContactService) context.getBean("contactService");
    }

    /*  Deployed as a Servlet or Portlet.
     *
     *  You can specify additional servlet parameters like the URI and UI
     *  class name and turn on production mode when you have finished developing the application.
     */
    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = Main.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }


}
