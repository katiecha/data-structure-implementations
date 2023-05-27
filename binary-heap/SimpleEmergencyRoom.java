package binary_heap;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // dequeue
    // compareTo returns neg for less than, 0 for equal to, pos for greater than
    public Patient dequeue() {
        Patient priorityPatient = patients.get(0);
        int removableIndex = 0;
        if (patients.size() == 0) {
            return null;
        } else {
            for (int i = 0; i < size(); i++){
                Patient currentPatient = patients.get(i);
                if (((currentPatient.getPriority()).compareTo(priorityPatient.getPriority())) < 0) {
                    priorityPatient = currentPatient;
                    removableIndex = i;
                }
            }
            patients.remove(removableIndex);
        } return priorityPatient;
    }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}
