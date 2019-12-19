package pks;

import java.time.LocalDate;

/**
 * Represent Data from a row and translate them into system usable form
 * It will also helps to be easily pass around different functions or components
 */
public class PatientRowData {
    private LocalDate date;
    private int id;
    private PatientDataAttribute attributeName;
    private String attributeValue;

    /**
     * build the data object from a comma-separated string and predefined set of columns
     * @param data
     */
    public PatientRowData(String data) {
        String[] rowData = data.split(",");
        this.date = LocalDate.parse(rowData[0]);
        this.id = Integer.parseInt(rowData[1].trim());
        this.attributeName = PatientDataAttribute.fromText(rowData[2].trim());
        this.attributeValue = rowData[3].trim();

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
