package Classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class ConcreteHashAlg implements HashInterface{
    @Override
    public String HashValue(String Key) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(Key.getBytes());
            BigInteger bigInt = new BigInteger(1, messageDigest);
            return bigInt.toString(16);
        }catch (NoSuchAlgorithmException e){

        }
        return "";
    }
}
