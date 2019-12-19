package pks.model;

import pks.PatientDataAttribute;

import java.time.LocalDate;
import java.util.Date;

public class PatientMedicalHistory {

    private LocalDate date;

    private PatientDataAttribute attributeName;

    private String attributeValue;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PatientDataAttribute getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(PatientDataAttribute attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}
