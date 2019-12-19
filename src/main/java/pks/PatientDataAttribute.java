package pks;

import java.util.Arrays;
import java.util.Optional;

public enum PatientDataAttribute {

    AGE("Age"),
    GENDER("Gender"),
    BLOOD_PRESSURE("Blood pressure"),
    GLUCOSE("Glucose"),
    DIABETES("Diabetes"),
    WCC("WCC");

    private String attribute;

    PatientDataAttribute(String attribute) {
        this.attribute = attribute;
    }

    public static PatientDataAttribute fromText(String text) {
        Optional<PatientDataAttribute>  patientDataAttribute = Arrays.stream(values())
                .filter(bl -> bl.attribute.equalsIgnoreCase(text.trim()))
                .findFirst();
        return (patientDataAttribute.isPresent()) ? patientDataAttribute.get() : null;
    }
}
