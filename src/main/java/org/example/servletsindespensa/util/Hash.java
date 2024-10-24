package org.example.servletsindespensa.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Hash {

    public String hashing(String password) {
        try {
            // Cria uma instância do MessageDigest para o algoritmo SHA-256
            MessageDigest algoritmo_hash = MessageDigest.getInstance("SHA-256");

            // Gera o hash a partir da senha
            byte[] hashBytes = algoritmo_hash.digest(password.getBytes());

            // Converte o array de bytes em uma String hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar o hash da senha", e);
        }
    }



}