package main;
import java.util.Scanner;

import java.util.List;
import patient.Patient;
import tools.Tools;

public class Main {
    public static void main(String[] args) {
		Scanner input_reader = new Scanner(System.in);

		// Формування списку пацієнтів з файлу
		List<Patient> patients = Tools.patientsFromFile("..\\info.txt");
		
		// Перевірка чи вдалось зчитати список пацієнтів
		if (patients == null){
			System.out.println("Can't read info from this file!");
			return;
		}

 		// Друк отриманого списку та доступних команд
 		Tools.clearScreen();
		Tools.printTableOfPatients(patients);
		System.out.println("\n\n                              Commands:\n");
		System.out.println("all (a)                          -   display all patients");
		System.out.println("phone (p) [pattern]              -   get all patients with phones stars with [pattern]          (Example: phone 122)");
		System.out.println("diagnosis (d) [diagnosis_name]   -   get all patients with diagnosis                            (Example: diagnosis ALS)");
		System.out.println("card (c) [start] [end]           -   get all patients with card numbers in inverval [start-end] (Example: card 12 100");
		System.out.println("clear (cls)                      -   clear screen\n\n");

		// Очікування команд користувача
		while (true) {
			System.out.print("Enter command: ");
			String command = input_reader.nextLine();	// Зчитування команди
			Tools.executeCommand(patients, command);
		}
    }
}