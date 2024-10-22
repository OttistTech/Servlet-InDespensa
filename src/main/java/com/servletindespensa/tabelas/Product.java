package com.servletindespensa.tabelas;
public class Product {
   //creating attributes according to table Product
   private int product_id;
   private String description;
   private long barcode;
   private String brand;
   private String name;
   private String type;
   private double weight_volume;
   //constructor for class Product
   public Product(int product_id, String description, long barcode, String brand, String name, String type, double weight_volume) {
      this.product_id = product_id;
      this.description = description;
      this.barcode = barcode;
      this.brand = brand;
      this.name = name;
      this.type = type;
      this.weight_volume = weight_volume;
   }
   //getters&setters for atributes of class Product
   public int getProduct_id() {
      return product_id;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public long getBarcode() {
      return barcode;
   }

   public String getBrand() {
      return brand;
   }

   public void setBrand(String brand) {
      this.brand = brand;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public double getWeight_volume() {
      return weight_volume;
   }

   public void setWeight_volume(double weight_volume) {
      this.weight_volume = weight_volume;
   }

   //tostring method for class product
   @Override
   public String toString() {
      return "product_id=' " + product_id +
              ", description='" + description + '\'' +
              ", barcode=" + barcode +
              ", brand='" + brand + '\'' +
              ", name='" + name + '\'' +
              ", type='" + type + '\'' +
              ", weight_volume=" + weight_volume +
              '}';
   }
}