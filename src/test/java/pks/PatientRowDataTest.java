package pks;

import org.junit.Test;

import static org.junit.Assert.*;

public class PatientRowDataTest {

    private PatientRowData classUnderTest;

    @Test
    public void should_create_instance_successfully() {
        String data ="2019-10-12,1,Age , 23";
        classUnderTest = new PatientRowData(data);

        assertNotNull(classUnderTest);
        assertEquals(PatientDataAttribute.AGE, classUnderTest.getAttributeName());
        assertEquals("23", classUnderTest.getAttributeValue());

    }

    @Test(expected = NumberFormatException.class)
    public void should_not_create_for_invalid_id() {
        String data ="2019-10-12,A1,Age , 23";
        classUnderTest = new PatientRowData(data);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void should_not_create_for_incomplete_data() {
        String data ="2019-10-12,1,Age ";
        classUnderTest = new PatientRowData(data);
    }
}