
package com.servletindespensa.tabelas;
public class Adm {
   //creating attributes according to table Adm
   private String name;
   private String email;
   private int password;


   //constructor for class Adm
   public Adm(String name, String email, int password) {
      this.name = name;
      this.email = email;
      this.password = password;
   }
   //getters&setters for class Adm attributes
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public int getPassword() {
      return password;
   }

   public void setPassword(int password) {
      this.password = password;
   }

   //class adm's tostring method
   public String toString() {
      return "name='" + name + '\'' +
              ", email='" + email + '\'' +
              ", password=" + password;
   }
}
