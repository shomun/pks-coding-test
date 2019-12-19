package pks.impl;

import pks.DataLoader;
import pks.PatientRowData;
import pks.model.Episode;
import pks.model.Patient;
import pks.model.PatientMedicalHistory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Load data from a CSV file and populate domain models to be used by the system
 */
public class PatientDataLoader implements DataLoader<List<Patient>> {

    private String fileName;

    private Map<Integer, Patient> patients;

    public PatientDataLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Patient> load()throws Exception{
        List<String> data = getDataFromFile(fileName);

        if(data != null && !data.isEmpty()){
            patients = new HashMap<>(data.size()-1);
            for (int i=1; i< data.size(); i++){
                try {
                    //parsing row data
                    PatientRowData rowData = new PatientRowData(data.get(i));
                    //Building a patient instance, if it is already built then use the same instance
                    Patient patient = createPatient(rowData);
                    patients.put(patient.getId(), patient);
                }catch (Exception e){
                    System.out.println("Unable to parse data : " + data.get(i));
                }
            }
        }
        return  new ArrayList<Patient>( patients.values());
    }

    private Patient createPatient(PatientRowData rowData) {
        Patient patient = patients.getOrDefault(rowData.getId(), new Patient());
        patient.setId(rowData.getId());
        switch (rowData.getAttributeName()){
            case AGE:
                patient.setAge( Integer.parseInt(rowData.getAttributeValue()));
                break;
            case GENDER:
                patient.setGender(rowData.getAttributeValue().charAt(0));
                break;
            case BLOOD_PRESSURE:
            case DIABETES:
            case GLUCOSE:
            case WCC:
                patient.addEpisodes(createEpisode(patient, rowData));
                break;
        }
        return patient;
    }

    /**
     * create Episode instance and it to patient. It will also create patient medical history data for given rowdata
     * and will add it to current episode
     * @param patient
     * @param rowData
     * @return
     */
    private Episode createEpisode(Patient patient, PatientRowData rowData) {
        Episode episode = null;
        // find a matching episode by date else create a new instance
        if(patient.getEpisodes()!= null && patient.getEpisodes().containsKey(rowData.getDate())){
            episode = patient.getEpisodes().get(rowData.getDate());
        }else{
            episode = new Episode();
        }
        episode.setDate(rowData.getDate());
        episode.addPatientMedicalHistory(createPatientMedicalHistory(rowData));
        return episode;
    }

    /**
     * create Patient Medical History data from row data
     * @param rowData
     * @return
     */
    private PatientMedicalHistory createPatientMedicalHistory(PatientRowData rowData) {
        PatientMedicalHistory patientMedicalHistory = new PatientMedicalHistory();
        patientMedicalHistory.setDate(rowData.getDate());
        patientMedicalHistory.setAttributeName(rowData.getAttributeName());
        patientMedicalHistory.setAttributeValue(rowData.getAttributeValue());
        return patientMedicalHistory;
    }

    private List<String> getDataFromFile(String fileName) throws URISyntaxException, IOException {
        try {
            Path filePath = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
            return  Files.readAllLines(filePath);

        }catch (Exception e){
            System.out.println("Reading from classpath : " + fileName);
            InputStream dataStream = getClass().getClassLoader().getResourceAsStream(fileName);
            return readFromInputStream(dataStream);

        }



    }

    private List<String> readFromInputStream(InputStream inputStream) throws IOException {
       List<String> lines = new ArrayList<>();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
