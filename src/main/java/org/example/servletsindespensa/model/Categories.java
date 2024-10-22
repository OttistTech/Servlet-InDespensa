package org.example.servletsindespensa.model;
public class Categories {
   //creating attributes according to table Adm
   private int category_id;
   private String category_name;

   //constructor for class Adm
   public Categories(int category_id, String category_name) {
      this.category_id = category_id;
      this.category_name = category_name;
   }
   //getters&setters for class Adm attributes
   public int getCategory_id() {
      return category_id;
   }

   public String getCategory_name() {
      return category_name;
   }

   public void setCategory_name(String category_name) {
      this.category_name = category_name;
   }

   //class adm's tostring method
   public String toString() {
      return "category_id=" + category_id +
              "category_name='" + category_name;
   }
}
