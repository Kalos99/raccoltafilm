package it.prova.raccoltafilm.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Utente;
import it.prova.raccoltafilm.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteDeleteUtenteServlet
 */
@WebServlet("/utente/ExecuteDeleteUtenteServlet")
public class ExecuteDeleteUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExecuteDeleteUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idUtenteDaRimuovere = request.getParameter("idUtente");
		
		if(!NumberUtils.isCreatable(idUtenteDaRimuovere)) {
			request.setAttribute("errorMessage", "Attenzione, non è stato trovato l'utente corrispondente.");
			request.getRequestDispatcher("/utente/delete.jsp").forward(request, response);
			return;
		}
		
		try{
			Utente utenteDaRimuovere = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(Long.parseLong(idUtenteDaRimuovere));
			MyServiceFactory.getUtenteServiceInstance().disabilitaUtente(utenteDaRimuovere.getId());
			request.setAttribute("utenti_list_attribute", MyServiceFactory.getUtenteServiceInstance().listAll());
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione, si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListUtentiServlet?operationResult=SUCCESS");
	}

}
