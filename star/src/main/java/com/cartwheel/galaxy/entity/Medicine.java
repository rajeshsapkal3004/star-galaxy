package com.cartwheel.galaxy.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String medicineName;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String manufactureCompany;
    @Column(nullable = false)
    private String batchNumber;
    @Column(nullable = false)
    private LocalDate manufactureDate;
    @Column(nullable = false)
    private LocalDate expiryDate;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int quantityInStock;
    @Column(nullable = false)
    private boolean prescriptionRequired;
    @Column(nullable = false)
    private boolean discontinued;

    public Medicine(Long id, String medicineName, String content, String manufactureCompany, String batchNumber, LocalDate manufactureDate, LocalDate expiryDate, double price, int quantityInStock, boolean prescriptionRequired, boolean discontinued) {
        this.id = id;
        this.medicineName = medicineName;
        this.content = content;
        this.manufactureCompany = manufactureCompany;
        this.batchNumber = batchNumber;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.prescriptionRequired = prescriptionRequired;
        this.discontinued = discontinued;
    }

    public Medicine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getManufactureCompany() {
        return manufactureCompany;
    }

    public void setManufactureCompany(String manufactureCompany) {
        this.manufactureCompany = manufactureCompany;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public boolean isPrescriptionRequired() {
        return prescriptionRequired;
    }

    public void setPrescriptionRequired(boolean prescriptionRequired) {
        this.prescriptionRequired = prescriptionRequired;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", medicineName='" + medicineName + '\'' +
                ", content='" + content + '\'' +
                ", manufactureCompany='" + manufactureCompany + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", expiryDate=" + expiryDate +
                ", price=" + price +
                ", quantityInStock=" + quantityInStock +
                ", prescriptionRequired=" + prescriptionRequired +
                ", discontinued=" + discontinued +
                '}';
    }
}
