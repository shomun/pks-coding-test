package pks;

import pks.impl.PatientDataImpl;
import pks.impl.PatientDataLoader;

public class App {

	public static void main(String[] args) {
		PatientDataLoader patientDataLoader = new PatientDataLoader("messages.csv");
		PatientData patientData = null; // TODO read messages.csv
		try {
			patientData = new PatientDataImpl(patientDataLoader);
			System.out.println("Number of patients: " + patientData.getNumberOfPatients());

			System.out.println("Number of patients male: " + patientData.getNumberOfPatientsByGender("M") );
			System.out.println("Number of patients female: " + patientData.getNumberOfPatientsByGender("F") );

			System.out.println("Number of episodes: " + patientData.getNumberOfEpisodes());

			System.out.println("Average age male: " + patientData.getAverageAgeByGender("M") );
			System.out.println("Average age female: " + patientData.getAverageAgeByGender("F"));


		} catch (Exception e) {
			e.printStackTrace();
		}



	}

}
