package it.prova.raccoltafilm.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.model.Ruolo;
import it.prova.raccoltafilm.model.Sesso;
import it.prova.raccoltafilm.model.StatoUtente;
import it.prova.raccoltafilm.model.Utente;

public class UtilityForm {

	public static Regista createRegistaFromParams(String nomeInputParam, String cognomeInputParam,
			String nickNameInputParam, String dataDiNascitaStringParam, String sessoParam) {

		Regista result = new Regista(nomeInputParam, cognomeInputParam, nickNameInputParam);
		result.setSesso(StringUtils.isBlank(sessoParam)?null:Sesso.valueOf(sessoParam));
		result.setDataDiNascita(parseDateArrivoFromString(dataDiNascitaStringParam));
		return result;
	}

	public static boolean validateRegistaBean(Regista registaToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(registaToBeValidated.getNome())
				|| StringUtils.isBlank(registaToBeValidated.getCognome())
				|| StringUtils.isBlank(registaToBeValidated.getNickName()) 
				|| registaToBeValidated.getSesso() == null
				|| registaToBeValidated.getDataDiNascita() == null) {
			return false;
		}
		return true;
	}
	
	public static Utente createUtenteFromParams( String userameInputParam, String passwordInputParam, String nomeInputParam, String cognomeInputParam, String[] ruoliInputParam) {
		Set<Ruolo> ruoliUtente = new HashSet<Ruolo>();
		Utente result = new Utente(userameInputParam, passwordInputParam, nomeInputParam, cognomeInputParam);
		result.setDateCreated(Calendar.getInstance().getTime());
		result.setStato(StatoUtente.CREATO);
		for(String ruoloId : ruoliInputParam) {
			if(NumberUtils.isCreatable(ruoloId)) {
				Ruolo ruoloDaInserire = new Ruolo();
				ruoloDaInserire.setId(Long.parseLong(ruoloId));
				ruoliUtente.add(ruoloDaInserire);
			}
		}
		result.setRuoli(ruoliUtente);
		return result;
	}
	
	public static boolean validateUtenteBean(Utente utenteToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(utenteToBeValidated.getNome())
				|| StringUtils.isBlank(utenteToBeValidated.getCognome())
				|| StringUtils.isBlank(utenteToBeValidated.getUsername()) 
				|| utenteToBeValidated.getPassword() == null) {
			return false;
		}
		return true;
	}
	
	public static Film createFilmFromParams(String titoloInputParam, String genereInputParam,
			String minutiDurataInputParam, String dataPubblicazioneStringParam, String registaIdStringParam) {

		Film result = new Film(titoloInputParam, genereInputParam);
		if (NumberUtils.isCreatable(minutiDurataInputParam)) {
			result.setMinutiDurata(Integer.parseInt(minutiDurataInputParam));
		}
		result.setDataPubblicazione(parseDateArrivoFromString(dataPubblicazioneStringParam));
		if (NumberUtils.isCreatable(registaIdStringParam)) {
			result.setRegista(new Regista(Long.parseLong(registaIdStringParam)));
		}
		return result;
	}

	public static boolean validateFilmBean(Film filmToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(filmToBeValidated.getTitolo())
				|| StringUtils.isBlank(filmToBeValidated.getGenere())
				|| filmToBeValidated.getMinutiDurata() == null 
				|| filmToBeValidated.getMinutiDurata() < 1
				|| filmToBeValidated.getRegista() == null
				|| filmToBeValidated.getRegista().getId() == null 
				|| filmToBeValidated.getRegista().getId() < 1) {
			return false;
		}
		return true;
	}

	public static Date parseDateArrivoFromString(String dataDiNascitaStringParam) {
		if (StringUtils.isBlank(dataDiNascitaStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascitaStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
}
