package org.example.servletsindespensa.model;
public class Tag {
   //Creating the id and description objects
   private int id;
   private String description;

   //Creating the constructor method
   public Tag(int id, String name) {
      this.id = id;
      this.description = name;
   }

   //Creating the id and name getter methods
   public int getId() {

      return this.id;
   }

   public String getNome() {
      return this.description;
   }

   //Creating the name setter method
   public void setName(String name) {
      this.description = name;
   }

   //Creating the toString method
   @Override
   public String toString() {
      return "id=" + id +
              "name='" + description + '\'';
   }
}