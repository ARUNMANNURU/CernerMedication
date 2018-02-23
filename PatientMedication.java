import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientMedication
{
    List<MedicationSchedule> medicationSchedules = new ArrayList<MedicationSchedule>();

    // Map<Person, List<MedicationSchedule>> medicationSchedulestoPerson = new HashMap<Person,
    // List<MedicationSchedule>>();

    private void addMedicationSchedulestoPerson()
    {
        Person person1 = new Person();
        MedicationSchedule medicationSchedule = new MedicationSchedule(123, "paraccetamal",
                new Date(), false, FrequencyType.FOUR_HOURS);
        MedicationSchedule medicationSchedule2 = new MedicationSchedule(123, "paraccetamal",
                new Date(), false, FrequencyType.ONCE_A_DAY);
        medicationSchedules.add(medicationSchedule);
        medicationSchedules.add(medicationSchedule2);
        // medicationSchedulestoPerson.put(person1, medicationSchedules);

    }

    public boolean isSameMedication(MedicationSchedule medicationSchedule1,
            MedicationSchedule MedicationSchedule2)
    {
        if (medicationSchedule1.getMedicineName().equals(medicationSchedule1.getMedicineName())
                && medicationSchedule1.getStartDate() == MedicationSchedule2.getStartDate()
                && medicationSchedule1.getEndDate() == MedicationSchedule2.getEndDate())
        {
            return true;
        }
        return false;
    }
}
