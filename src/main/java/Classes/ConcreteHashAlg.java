package Classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

/**
 * Implementazione contreta dell'algoritmo di hashing
 * @see HashInterface
 */

public class ConcreteHashAlg implements HashInterface{

    /**
     * Generata MD5
     * @param Key Chiave di cui si vuole ottenere la stringa MD5
     * @return Stringa che rappresenta la rappresentazione MD5 della chiave passata in input
     */
    @Override
    public String HashValue(String Key) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(Key.getBytes());
            BigInteger bigInt = new BigInteger(1, messageDigest);
            return bigInt.toString(16);
        }catch (NoSuchAlgorithmException e){

        }
        return null;
    }
}
