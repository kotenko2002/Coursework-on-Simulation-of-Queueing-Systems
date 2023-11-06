package Other;

public class LanguagePackage {
    private double startTime;

    public LanguagePackage(double startTime) {
        this.startTime = startTime;
    }

    public double getTimeSpentInSystem(double endTime) {
        return endTime - startTime;
    }
}
