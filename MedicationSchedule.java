import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MedicationSchedule
{
    private int medicationID;
    private String medicineName;
    private Date startDate;
    private Date endDate;
    private int noDoses;
    private boolean isLifetime;
    private FrequencyType frequencyType;
    Map<Integer, Date> doses = new HashMap<Integer, Date>();

    public MedicationSchedule(int medicationID, String medicineName, Date startDate,
            boolean isLifetime, FrequencyType frequencyType){// pass
        this.startDate = startDate;
        this.medicineName = medicineName;
        this.startDate = startDate;
        this.frequencyType = frequencyType;
        this.isLifetime = isLifetime;
    }
    
    public String getMedicineName(){
        return medicineName;
    }

    public Date getStartDate(){
        return startDate;
    }

    public int getNoDoses(){
        return noDoses;
    }

    public boolean isLifetime(){
        return isLifetime;
    }
    public FrequencyType getFrequencyType(){
        return frequencyType;
    }
    public void updateDosesforLifeTime(){
        if (frequencyType == FrequencyType.FOUR_HOURS){
            getDoses();// teel
        }
    }
    public Map getDoses(){
    	
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        if (frequencyType == FrequencyType.FOUR_HOURS){
            for (int i = 0; i < noDoses; i++){
                doses.put(i, calendar.getTime());
                calendar.add(Calendar.HOUR_OF_DAY, 4);
            }
        }
        if (frequencyType == FrequencyType.ONCE_A_DAY || frequencyType == FrequencyType.BEFORE_BED){
            for (int i = 0; i < noDoses; i++){
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                doses.put(i, calendar.getTime());
            }
        }
        if (frequencyType == FrequencyType.WITH_MEALS){
            for (int i = 0; i < noDoses; i++){
                calendar.add(Calendar.HOUR_OF_DAY, 12);
                doses.put(i, calendar.getTime());
            }
        }
        return doses;
    }
    public Date getEndDate(){
        endDate = updateEndDate();
        return endDate;
    }

    public int getNoOfDoese()
    {
        noDoses = updateNoDoses();
        return noDoses;
    }

    private Date updateEndDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        if (frequencyType == FrequencyType.LIFETIME)
        {
            return null;
        }

        if (frequencyType == FrequencyType.FOUR_HOURS)
        {
            calendar.add(Calendar.HOUR_OF_DAY, 4 * noDoses);
        }

        if (frequencyType == FrequencyType.ONCE_A_DAY || frequencyType == FrequencyType.BEFORE_BED)
        {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        if (frequencyType == FrequencyType.WITH_MEALS)
        {
            calendar.add(Calendar.HOUR_OF_DAY, 12 * noDoses);
        }

        return calendar.getTime();
    }

    private int updateNoDoses()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        long time = calendar.getTimeInMillis() - startDate.getTime();
        int updatedDoses = 0;

        if (frequencyType == FrequencyType.LIFETIME)
        {
            return 0;
        }

        if (frequencyType == FrequencyType.FOUR_HOURS)
        {
            updatedDoses = (int) (time / 1000 * 60 * 60 * 4);
        }

        if (frequencyType == FrequencyType.ONCE_A_DAY || frequencyType == FrequencyType.BEFORE_BED)
        {
            updatedDoses = (int) (time / 1000 * 60 * 60 * 24);
        }
        if (frequencyType == FrequencyType.WITH_MEALS){
            updatedDoses = (int) (time / 1000 * 60 * 60 * 12);
        }

        return updatedDoses;
    }
    public int getMedicationID(){
        return medicationID;
    }
}
enum FrequencyType{
    FOUR_HOURS, ONCE_A_DAY, WITH_MEALS, BEFORE_BED, PRN, LIFETIME;
}
