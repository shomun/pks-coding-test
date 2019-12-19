package pks.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Episode {

    private LocalDate date;

    private List<PatientMedicalHistory> patientMedicalHistories;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<PatientMedicalHistory> getPatientMedicalHistories() {
        return patientMedicalHistories;
    }

    public void setPatientMedicalHistories(List<PatientMedicalHistory> patientMedicalHistories) {
        this.patientMedicalHistories = patientMedicalHistories;
    }

    public void addPatientMedicalHistory(PatientMedicalHistory patientMedicalHistory) {
        if(this.patientMedicalHistories == null){
            this.patientMedicalHistories = new ArrayList<>();
        }
        this.patientMedicalHistories.add(patientMedicalHistory);
    }
}
