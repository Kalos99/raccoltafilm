package it.prova.raccoltafilm.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.model.Sesso;
import it.prova.raccoltafilm.model.Utente;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteSearchUtenteServlet
 */
@WebServlet("/utente/ExecuteSearchUtenteServlet")
public class ExecuteSearchUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteSearchUtenteServlet() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usenrameParam = request.getParameter("nickName");
		String dataCreazioneParam = request.getParameter("dataDiNascita");

		Utente example = new Utente(nomeParam, cognomeParam, usenrameParam, UtilityForm.parseDateArrivoFromString(dataCreazioneParam));

		try {
			request.setAttribute("utenti_list_attribute", MyServiceFactory.getUtenteServiceInstance().findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/utente/search.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListUtentiServlet?operationResult=SUCCESS");
	}

}
