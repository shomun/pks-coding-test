package pks.impl;

import org.junit.Before;
import org.junit.Test;
import pks.model.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class PatientDataLoaderTest {

    private PatientDataLoader classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new PatientDataLoader("messages.csv");
    }

    @Test
    public void should_load_5_patients_successfully() throws Exception {
        List<Patient> patients = classUnderTest.load();

        assertNotNull(patients);
        assertEquals(5, patients.size());
    }

    @Test
    public void should_load_patient_with_invalid_data() throws Exception {
        classUnderTest = new PatientDataLoader("patient_data_missing_age_attribute.csv");
        List<Patient> patients = classUnderTest.load();

        assertNotNull(patients);
        assertEquals(1, patients.size());
        assertEquals(0,patients.get(0).getAge());
    }

    @Test
    public void should_load_patient_with_sorted_episode() throws Exception {
        classUnderTest = new PatientDataLoader("patient_data_episode_sorting_test.csv");
        List<Patient> patients = classUnderTest.load();
        LocalDate expectedFirstEpisodeDate = LocalDate.parse("2019-12-09");
        LocalDate expectedLastEpisodeDate = LocalDate.parse("2019-12-14");

        assertNotNull(patients);
        assertEquals(1, patients.size());
        Set<LocalDate> episodeDates = patients.get(0).getEpisodes().keySet();
        LocalDate firstEpisodeDate =episodeDates.stream().findFirst().get();
        LocalDate lastEpisodeDate = episodeDates.stream().skip(episodeDates.size()-1).findFirst().get();

        assertEquals(expectedFirstEpisodeDate,firstEpisodeDate);
        assertEquals(expectedLastEpisodeDate,lastEpisodeDate);
    }

}