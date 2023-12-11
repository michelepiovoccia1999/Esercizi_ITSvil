package it.itsvil.employee2.util;
import lombok.Data;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Data
public class Cifrapw {
    public static String cifraPw(String input) {
        try {
            //Il metodo getInstance statico viene chiamato con l'hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Il metodo digest() viene chiamato per calcolare il digest del messaggio di un array di byte restituito da digest() in input
            byte[] messageDigest = md.digest(input.getBytes());
            // Converti l'array di byte nella rappresentazione del segno
            BigInteger no = new BigInteger(1, messageDigest);
            // Converti il digest del messaggio in un valore esadecimale
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        //Per aver specificato algoritmi di digest del messaggio errati
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo di hash non trovato", e);
        }
    }
}