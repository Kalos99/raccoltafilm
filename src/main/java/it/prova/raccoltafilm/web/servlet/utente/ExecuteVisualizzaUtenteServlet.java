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
 * Servlet implementation class ExecuteVisualizzaUtenteServlet
 */
@WebServlet("/utente/ExecuteVisualizzaUtenteServlet")
public class ExecuteVisualizzaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExecuteVisualizzaUtenteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idUtenteParam = request.getParameter("idUtente");

		if (!NumberUtils.isCreatable(idUtenteParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: non esiste un utente con questo id");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		try {
			Utente utenteInstance = MyServiceFactory.getUtenteServiceInstance().caricaSingoloUtenteConRuoli(Long.parseLong(idUtenteParam));

			if (utenteInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListUtentiServlet?operationResult=NOT_FOUND").forward(request, response);
				return;
			}
			request.setAttribute("visualizza_utente_attr", utenteInstance);
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: non è stato possibile caricare le informazioni dell'utente");
			request.getRequestDispatcher("/utente/list.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/utente/show.jsp").forward(request, response);
	}



}
