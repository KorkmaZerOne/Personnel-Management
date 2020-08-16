package be.intecbrussel.model;

import java.time.LocalDate;

public class Project {
    private int id;
    private String explanation;
    private LocalDate startDate;
    private int price;
    private LocalDate endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", explanation='" + explanation +
                ", startDate=" + startDate +
                ", price=" + price +
                ", endDate=" + endDate +
                '}';
    }
}
