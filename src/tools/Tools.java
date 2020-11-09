package tools;
import java.util.List;
import java.util.ArrayList;
import patient.Patient;
import main.Main.*;
import java.io.*;
import java.util.regex.*;
import java.util.Scanner;

public class Tools{
	/** Зчитування списку пацієнтів з файлу
	* @param filename Назва файлу
	* @return Список пацієнтів
	*/
	public static List<Patient> patientsFromFile(String filename){
		List<Patient> patientsList = new ArrayList<>();
		try{
			BufferedReader fileReader = new BufferedReader(new FileReader(filename));
			String buffer;
			// Построкове зчитування інформації про кожного пацієнта
			while ((buffer = fileReader.readLine()) != null) {
				String[] splitted_string = buffer.split(",( *\\t*)");

				Patient patientFromFile = new Patient(
					Integer.parseInt(splitted_string[0]),
					splitted_string[1],
					splitted_string[2],
					splitted_string[3],
					splitted_string[4],
					splitted_string[5],
					Integer.parseInt(splitted_string[6]),
					splitted_string[7]);

				patientsList.add(patientFromFile);
			}
			fileReader.close();

		} catch (FileNotFoundException ex) {
			System.out.print(String.format("File [%s] not found!\nEnter correct path: ", filename));
			Scanner input_reader = new Scanner(System.in);
			filename = input_reader.nextLine();
			return patientsFromFile(filename);

		} catch(Exception ex) { return null; }

		return patientsList;
	}


	/** Друкує вказаний список пацієнтів у вигляді таблиці та повертає їх кількість
	* @param patientsList Список пацієнтів
	* @return Кількість пацієнтів у списку
	*/
	public static int printTableOfPatients(List<Patient> patientsList){
		System.out.println(".---------------------------------------------------------------------------------------------------------------.");
		System.out.println("|  ID  |               NAME               |        ADRESS        | PHONE NUMBER | CARD NUMBER |    DIAGNOSIS    |");
		System.out.println("|------+----------------------------------+----------------------+--------------+-------------+-----------------|");
		for (int i = 0; i < patientsList.size(); i++){
			System.out.println(String.format("| %4s | %32s | %20s | %12s |     %-7d | %15s |",
			centerString(Integer.toString(patientsList.get(i).getId()), 4), centerString(patientsList.get(i).getName() + ' ' + patientsList.get(i).getSecondName() + ' ' + patientsList.get(i).getMiddleName(), 32),
			centerString(patientsList.get(i).getAddress(), 20), centerString(patientsList.get(i).getPhoneNumber(), 12), patientsList.get(i).getCardNumber(), centerString(patientsList.get(i).getDiagnosis(), 15)));
		}
		System.out.println("'---------------------------------------------------------------------------------------------------------------'");
		System.out.println("Amount: " + patientsList.size());
		return patientsList.size();
	}


	/** Виконання заданої команди
	* @param execution_list Список, відносно якого виконується команда
	* @param command Команда, яку потрібно виконати
	* @return true в разі, якщо команда успішно виконана, false якщо ні
	*/
	public static boolean executeCommand(List<Patient> execution_list, String command){
		String[] commands = command.split(" ");		// Розбиття введеної команди на аргументи

		//			Виклик функції відповідно до певної команди
		// Можливо, тут краще було використати switch case 

		// Друк списку пацієнтів
		if (commands[0].equals("all") || commands[0].equals("a")){ printTableOfPatients(execution_list); }

		// Очищення екрану
		else if (commands[0].equals("clear") || commands[0].equals("cls")) { clearScreen(); }

		// Пошук телефону за паттерном
		else if (commands[0].equals("phone") || commands[0].equals("p")) {
			try { printTableOfPatients(Service.getPhoneNumberStartsWith(execution_list, commands[1])); }
			catch (Exception ex) {
				System.out.println("\t\tWRONG INPUT:\tphone|p\tstarts_with");
				return false;
			}
		}

		// Пошук вказаного діагнозу
		else if (commands[0].equals("diagnosis") || commands[0].equals("d")) {
			try { printTableOfPatients(Service.getAllDiagnosis(execution_list, commands[1])); }
			catch (Exception ex) {
				System.out.println("\t\tWRONG INPUT:\tdiagnosis|d\tdiagnosis_name");
				return false;
			}
		}

		// Пошук карт у діапазоні
		else if (commands[0].equals("card") || commands[0].equals("c")) {
			try { printTableOfPatients(Service.getCardNumberInterval(execution_list, Integer.parseInt(commands[1]), Integer.parseInt(commands[2]))); }
			catch (Exception ex) {
				System.out.println("\t\tWRONG INPUT:\tcard|c\tSTART\tEND");
				return false;
			}
		}

		// Якщо введено невідому команду - друкується список доступних команд
		else {
			System.out.println("\n\n                              Commands:\n");
			System.out.println("all (a)                          -   display all patients");
			System.out.println("phone (p) [pattern]              -   get all patients with phones stars with [pattern]          (Example: phone 122)");
			System.out.println("diagnosis (d) [diagnosis_name]   -   get all patients with diagnosis                            (Example: diagnosis ALS)");
			System.out.println("card (c) [start] [end]           -   get all patients with card numbers in inverval [start-end] (Example: card 12 100");
			System.out.println("clear (cls)                      -   clear screen\n\n");
			return false;
		}
		return true;
	}


	/** Вирівнювання тексту на середину рядка
	* @param input_string Рядок, який вирівнюємо
	* @param len Яку ширину має займати рядок
	* @return Вирівняний рядок
	*/
	public static String centerString(String input_string, int len){
		if (input_string.length() >= len) return input_string;
		String result = "";
		for (int i = 0; i < (len - input_string.length()) / 2; i++) { result = ' ' + result; }
		result += input_string;
		while (result.length() < len) { result = result + ' '; }

		return result;
	}


	/** Очищення екрану */
	public static void clearScreen() {
		try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); }
		catch (Exception ex) { System.out.println(ex.toString()); }
	}
}