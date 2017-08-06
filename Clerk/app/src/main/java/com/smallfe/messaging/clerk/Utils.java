package com.smallfe.messaging.clerk;

import java.util.Locale;

/**
 * Created by mehmet on 04.08.2017.
 */

public class Utils {

    private static String alphabet = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ";

    public static String decrypt(String cypherText){
        String[] ciphers = cypherText.toUpperCase(new Locale("tr","TR")).split("\\r?\\n");
        int charIndexInCipher = 0;
        int shift = 0;
        StringBuilder sb = null;
        for (int i = 0; i < ciphers.length; i++) {
            String cipher = ciphers[i];
            sb = new StringBuilder();
            for (int j = 0; j < cipher.length(); j++) {
                charIndexInCipher = alphabet.indexOf(cipher.charAt(j));
                shift = (charIndexInCipher+28)%29;
                sb.append(alphabet.charAt(shift));
            }
            if (i<ciphers.length-1) sb.append("\n");
        }
        try {
            return sb.toString();
        }catch (NullPointerException ex){
            return null;
        }
    }

    public static String decrypt(String cypherText, int magicNumber){
        String[] ciphers = cypherText.toUpperCase(new Locale("tr","TR")).split("\\r?\\n");
        int charIndexInCipher = 0;
        int shift = 0;
        StringBuilder sb = null;
        for (int i = 0; i < ciphers.length; i++) {
            String cipher = ciphers[i];
            sb = new StringBuilder();
            for (int j = 0; j < cipher.length(); j++) {
                charIndexInCipher = alphabet.indexOf(cipher.charAt(j));
                shift = (charIndexInCipher+(29-(magicNumber%29)))%29;
                sb.append(alphabet.charAt(shift));
            }
            if (i<ciphers.length-1) sb.append("\n");
        }
        try {
            return sb.toString();
        }catch (NullPointerException ex){
            return null;
        }
    }

    public static String encrypt(String plainText){
        String[] plains = plainText.toUpperCase(new Locale("tr","TR")).split("\\r?\\n");
        int charIndexInPlain = 0;
        int shift = 0;
        StringBuilder sb = null;
        for (String plainWWS : plains) {
            String plain = plainWWS.replaceAll("\\s+","");
            sb=new StringBuilder();
            for (int j = 0; j < plain.length(); j++) {
                charIndexInPlain = alphabet.indexOf(plain.charAt(j));
                shift = (charIndexInPlain+1)%29;
                sb.append(alphabet.charAt(shift));
            }
        }
        try {
            return sb.toString();
        }catch (NullPointerException ex){
            return null;
        }
    }

    public static String encrypt(String plainText, int magicNumber){
        String[] plains = plainText.toUpperCase(new Locale("tr","TR")).split("\\r?\\n");
        int charIndexInPlain = 0;
        int shift = 0;
        StringBuilder sb = null;
        for (String plainWWS : plains) {
            String plain = plainWWS.replaceAll("\\s+","");
            sb=new StringBuilder();
            for (int j = 0; j < plain.length(); j++) {
                charIndexInPlain = alphabet.indexOf(plain.charAt(j));
                shift = (charIndexInPlain+magicNumber)%29;
                sb.append(alphabet.charAt(shift));
            }
        }
        try {
            return sb.toString();
        }catch (NullPointerException ex){
            return null;
        }
    }

}
