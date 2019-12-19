package pks.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PatientDataImplTest {

    private PatientDataImpl classUnderTest;

    @Before
    public void setUp() throws Exception {
        PatientDataLoader dataLoader = new PatientDataLoader("messages.csv");
        classUnderTest = new PatientDataImpl(dataLoader);
    }

    @Test
    public void getNumberOfEpisodes_should_return_10() {
        long numberOfEpisodes = classUnderTest.getNumberOfEpisodes();
        assertEquals(10,numberOfEpisodes);
    }



      @Test
    public void getNumberOfMalePatients_should_return_3() {
        long numberOfMalePatients = classUnderTest.getNumberOfPatientsByGender("M");
        assertEquals(3,numberOfMalePatients);
    }
    @Test
    public void getNumberOfFemalePatients_should_return_2() {
        long numberOfFemalePatients = classUnderTest.getNumberOfPatientsByGender("F");
        assertEquals(2,numberOfFemalePatients);
    }

    @Test
    public void getAverageAgeOfFemalePatients_should_return_24_5() {
        double avgAgeFemale = classUnderTest.getAverageAgeByGender("F");
        assertEquals(24.5,avgAgeFemale,0.001);
    }

    @Test
    public void getAverageAgeOfMalePatients_should_return_27() {
        double avgAgeMale = classUnderTest.getAverageAgeByGender("M");
        assertEquals(27.0,avgAgeMale,0.001);
    }
}