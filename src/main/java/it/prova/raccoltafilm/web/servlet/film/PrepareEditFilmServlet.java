package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.MyServiceFactory;

@WebServlet("/PrepareEditFilmServlet")
public class PrepareEditFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareEditFilmServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String parametroIdDelFilmDaAggiornare = request.getParameter("idFilm");
		
		if(!NumberUtils.isCreatable(parametroIdDelFilmDaAggiornare)) {
			request.setAttribute("errorMessage", "Attenzione, non è stato trovato il regista corrispondente.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		try{
			Film filmPerAggiornamento = MyServiceFactory.getFilmServiceInstance().caricaSingoloElemento(Long.parseLong(parametroIdDelFilmDaAggiornare));
			request.setAttribute("registi_list_attribute", MyServiceFactory.getRegistaServiceInstance().listAllElements());
			request.setAttribute("filmCheSiVuoleAggiornare", filmPerAggiornamento);
		} catch (Exception e){
			//qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/film/edit.jsp").forward(request, response);	
	}
}
