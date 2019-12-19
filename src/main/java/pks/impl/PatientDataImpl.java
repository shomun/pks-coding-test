package pks.impl;

import pks.PatientData;
import pks.model.Patient;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PatientDataImpl implements PatientData {

    private PatientDataLoader patientDataLoader;

    private List<Patient> patients;

    public PatientDataImpl(PatientDataLoader patientDataLoader) throws Exception {
        this.patientDataLoader = patientDataLoader;
        initData();
    }

    public void initData() throws Exception {
        patients = patientDataLoader.load();

    }

    @Override
    public long getNumberOfEpisodes() {
        return patients.stream().map(patient -> patient.getEpisodes().keySet()).flatMap(Set::stream).count();
    }

    @Override
    public long getNumberOfPatients() {
        return patients.size();
    }

    @Override
    public long getNumberOfPatientsByGender(String gender) {
        long numberOfPatient = 0;
        List<Patient> patientsByGender = getPatientsByGender(gender);
        if(patientsByGender != null){
            numberOfPatient = patientsByGender.size();
        }
        return numberOfPatient;
    }

    /**
     * find patients by gender
     * @param gender
     * @return
     */
    private List<Patient> getPatientsByGender(String gender){
        return patients.stream().filter(patient -> patient.getGender() == gender.charAt(0)).collect(Collectors.toList());

    }

    @Override
    public double getAverageAgeByGender(String gender) {
        double avgAge = 0;
        List<Patient> patientsByGender = getPatientsByGender(gender);
        if(patientsByGender != null){
            IntSummaryStatistics statistics = patientsByGender.stream().mapToInt(patient -> patient.getAge()).summaryStatistics();
            avgAge = statistics.getAverage();
        }
       return avgAge;
    }

  }
