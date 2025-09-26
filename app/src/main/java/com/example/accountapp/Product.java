package com.example.accountapp;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Product implements Serializable {
    private String id;
    private String name;
    private String description;
    private String asin;
    private String brand;
    private String model;
    private String color;
    private String volume;
    private double weight;
    private double purchasePrice;
    private double packagingCost;
    private double shippingCost;
    private double amazonFee;
    private double adCost;
    private double sellingPrice;
    private int stockOrigin;
    private int stockDestination;

    public Product() {
        // Firestore needs empty constructor
    }

    // ----- Getters & Setters -----
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAsin() { return asin; }
    public void setAsin(String asin) { this.asin = asin; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getVolume() { return volume; }
    public void setVolume(String volume) { this.volume = volume; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(double purchasePrice) { this.purchasePrice = purchasePrice; }

    public double getPackagingCost() { return packagingCost; }
    public void setPackagingCost(double packagingCost) { this.packagingCost = packagingCost; }

    public double getShippingCost() { return shippingCost; }
    public void setShippingCost(double shippingCost) { this.shippingCost = shippingCost; }

    public double getAmazonFee() { return amazonFee; }
    public void setAmazonFee(double amazonFee) { this.amazonFee = amazonFee; }

    public double getAdCost() { return adCost; }
    public void setAdCost(double adCost) { this.adCost = adCost; }

    public double getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(double sellingPrice) { this.sellingPrice = sellingPrice; }

    public int getStockOrigin() { return stockOrigin; }
    public void setStockOrigin(int stockOrigin) { this.stockOrigin = stockOrigin; }

    public int getStockDestination() { return stockDestination; }
    public void setStockDestination(int stockDestination) { this.stockDestination = stockDestination; }

    // ----- Helper method for Firestore -----
    @Exclude
    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("description", description);
        result.put("asin", asin);
        result.put("brand", brand);
        result.put("model", model);
        result.put("color", color);
        result.put("volume", volume);
        result.put("weight", weight);
        result.put("purchasePrice", purchasePrice);
        result.put("packagingCost", packagingCost);
        result.put("shippingCost", shippingCost);
        result.put("amazonFee", amazonFee);
        result.put("adCost", adCost);
        result.put("sellingPrice", sellingPrice);
        result.put("stockOrigin", stockOrigin);
        result.put("stockDestination", stockDestination);
        return result;
    }

    @Exclude
    public double getProfitPerUnit() {
        double cost = purchasePrice + packagingCost + shippingCost + amazonFee + adCost;
        return sellingPrice - cost;
    }
}