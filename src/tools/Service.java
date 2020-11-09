package tools;

import patient.Patient;
import java.util.regex.*;
import java.util.List;
import java.util.ArrayList;

public class Service{
	/** Отримати список пацієнтів з вказаним діагнозом серед вказаного списку
	* @param patientsList Список пацієнтів
	* @param diagnosis Діагноз
	* @return Список пацієнтів з вказаним діагнозом
	*/
	public static List<Patient> getAllDiagnosis(List<Patient> patientsList, String diagnosis){
		List<Patient> result_list = new ArrayList<>();

		for (Patient current_patient : patientsList) {
			if (current_patient.getDiagnosis().equals(diagnosis))
				result_list.add(current_patient);
		}

		return result_list;
	}


	/** Отримати список пацієнтів з номером картки в інтервалі
	* @param patientsList Список пацієнтів
	* @param start Початок інтервалу
	* @param end Кінець інтервалу
	* @return Список пацієнтів з номером картки в інтервалі
	*/
	public static List<Patient> getCardNumberInterval(List<Patient> patientsList, int start, int end){
		List<Patient> result_list = new ArrayList<>();

		for (Patient current_patient : patientsList) {
			if (current_patient.getCardNumber() >= start && current_patient.getCardNumber() <= end)
				result_list.add(current_patient);
		}

		return result_list;
	}

	/** Отримати список пацієнтів з телефонним номером, початок якого співпдає з вказаним патерном
	* @param patientsList Список пацієнтів
	* @param pattern Патерн, з яким буде звірятись початок номеру
	* @param end Кінець інтервалу
	* @return Список пацієнтів з номером картки в інтервалі
	*/
	public static List<Patient> getPhoneNumberStartsWith(List<Patient> patientsList, String pattern){
		List<Patient> result_list = new ArrayList<>();

		// Створення патерну, який буде звіряти лише з початком номеру
		Pattern target_pattern = Pattern.compile("^" + pattern);
		Matcher matcher;

		for (Patient current_patient : patientsList) {
			// Порівнювання номеру пацієнта з патерном
			matcher = target_pattern.matcher(current_patient.getPhoneNumber());
			if (matcher.find()) result_list.add(current_patient);
		}

		return result_list;
	}

}