package org.example.servletsindespensa.model;
public class Cep {
   //creating attributes according to table Cep
   private int cep_id;

   //constructor for class Adm
   public Cep(int cep_id) {
      this.cep_id = cep_id;
   }

   //getters&setters for class Adm attributes

   public int getCep_id() {
      return cep_id;
   }

   //class adm's tostring method
   public String toString() {
      return "cep_id=" + cep_id;
   }
}
