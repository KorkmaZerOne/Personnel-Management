package be.intecbrussel.model;

import java.util.Date;

public class Project {

    private Date startDate;
    private String explanation;
    private double price;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "startDate=" + startDate +
                ", explanation='" + explanation + '\'' +
                ", price=" + price +
                ", endDate=" + endDate +
                '}';
    }
}
