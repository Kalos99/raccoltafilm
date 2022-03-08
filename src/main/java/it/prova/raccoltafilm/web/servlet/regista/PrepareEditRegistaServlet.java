package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;

@WebServlet("/PrepareEditRegistaServlet")
public class PrepareEditRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareEditRegistaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String parametroIdDelRegistaDaAggiornare = request.getParameter("idRegista");
		
		if(!NumberUtils.isCreatable(parametroIdDelRegistaDaAggiornare)) {
			request.setAttribute("errorMessage", "Attenzione, non è stato trovato il regista corrispondente.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		try{
			Regista registaPerAggiornamento = MyServiceFactory.getRegistaServiceInstance().caricaSingoloElemento(Long.parseLong(parametroIdDelRegistaDaAggiornare));
			request.setAttribute("RegistaCheSiVuoleAggiornare", registaPerAggiornamento);
		} catch (Exception e){
			//qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/regista/edit.jsp").forward(request, response);	
	}
}