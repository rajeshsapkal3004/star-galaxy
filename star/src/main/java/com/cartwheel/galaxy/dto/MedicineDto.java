package com.cartwheel.galaxy.dto;

import java.time.LocalDate;

public class MedicineDto {

    private String medicineName;
    private String content;
    private String manufactureCompany;
    private String batchNumber;
    private LocalDate manufactureDate;
    private LocalDate expiryDate;
    private double price;
    private int quantityInStock;
    private boolean prescriptionRequired;
    private boolean discontinued;

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
        return "MedicineDto{" +
                "medicineName='" + medicineName + '\'' +
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
