package pks;

import pks.model.Patient;

import java.util.List;

public interface PatientData {

	public long getNumberOfEpisodes();

	public long getNumberOfPatients();

	public long getNumberOfPatientsByGender(String gender);

	public double getAverageAgeByGender(String gender);

}
