package codeurjc.ssdd.grupo1.trainfyre.dto.IncidencesDTOs;

public class PieChartInfo {
    private String line;
    private double percentage;

    public PieChartInfo() {
    }

    public PieChartInfo(String line, double percentage) {
        this.line = line;
        this.percentage = percentage;
    }

    public String getLine() {
        return line;
    }
    public void setLine(String line) {
        this.line = line;
    }
    public double getPercentage() {
        return percentage;
    }
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }  
}
