package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.exceptions.FilmAssociatiException;
import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteRegistaServlet")
public class ExecuteDeleteRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteDeleteRegistaServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idRegistaDaRimuovere = request.getParameter("idRegista");
		
		if(!NumberUtils.isCreatable(idRegistaDaRimuovere)) {
			request.setAttribute("errorMessage", "Attenzione, non è stato trovato il regista corrispondente.");
			request.getRequestDispatcher("/regista/delete.jsp").forward(request, response);
			return;
		}
		
		try{
			Regista registaDaRimuovere = MyServiceFactory.getRegistaServiceInstance().caricaSingoloElemento(Long.parseLong(idRegistaDaRimuovere));
			MyServiceFactory.getRegistaServiceInstance().rimuovi(registaDaRimuovere.getId());
			request.setAttribute("registi_list_attribute", MyServiceFactory.getRegistaServiceInstance().listAllElements());
		} catch (FilmAssociatiException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Impossibile rimuovere: sono presenti dei film associati a questo regista");
			try {
				request.setAttribute("registaCheSiVuoleEliminare", MyServiceFactory.getRegistaServiceInstance().caricaSingoloElemento(Long.parseLong(idRegistaDaRimuovere)));
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			request.getRequestDispatcher("/regista/delete.jsp").forward(request, response);
			return;
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione, si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListRegistaServlet?operationResult=SUCCESS");
	}

}
