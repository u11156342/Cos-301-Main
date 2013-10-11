/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.security.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Fiyah
 */
@Stateless
@Path("/SecurityWrapper")
public class SecurityWrapper {

    ArrayList<String> requests = new ArrayList();

    @GET
    @Path("ServerRequest/{Request}")
    @Produces("text/plain")
    public String ServerRequest(@PathParam("Request") String Request) {

        System.out.println(Request);
        try {

            StringTokenizer tokens = new StringTokenizer(Request, "`");
            byte[] bt = new byte[tokens.countTokens()];
            int c = 0;
            while (tokens.hasMoreTokens()) {
                String tok = tokens.nextToken();

                bt[c] = (byte) Integer.parseInt(tok);
                c++;

            }



            String passphrase = "Space, the final frontier. These are the voyages of the Starship Enterprise. Its five-year mission: to explore strange new worlds, to seek out new life and new civilizations, to boldly go where no man has gone before.";
            MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(passphrase.getBytes());
            SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");

            aes.init(Cipher.DECRYPT_MODE, key);
            String cleartext = new String(aes.doFinal(bt));

            //cleartext also contains the Id that needs to be taken out
            //randomnum+id + cleartext

            int index = cleartext.indexOf("+");
            String r = cleartext.substring(0, index);
            cleartext = cleartext.substring(index + 1);
            int index2 = cleartext.indexOf("+");
            String ID = cleartext.substring(0, index2);
            cleartext = cleartext.substring(index2 + 1);
            System.out.println("ID " + ID);
            System.out.println(cleartext);
            try {
                System.out.println("SEC " + r + " " + ID + " " + cleartext);
                Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(ID);

                if (requests.contains(r + " " + date + " " + cleartext)) {

               //+     return "SECURITY ERROR";
                } else {
                    requests.add(r + " " + date + " " + cleartext);
                }
            } catch (ParseException ex) {
                Logger.getLogger(SecurityWrapper.class.getName()).log(Level.SEVERE, null, ex);
            }


            String temp = "";
            try {

                URL url = new URL("http://" + cleartext);
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                System.out.println("http://" + cleartext);
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (!"".equals(temp)) {
                        temp = temp + "\n" + inputLine;
                    } else {
                        temp = inputLine;
                    }
                }
                in.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            aes.init(Cipher.ENCRYPT_MODE, key);
            byte[] ciphertext = aes.doFinal(temp.getBytes());
            String enc = "";
            for (int i = 0; i < ciphertext.length; i++) {
                enc = enc + "`" + ciphertext[i];
            }
            return enc;

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecurityWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(SecurityWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(SecurityWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(SecurityWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(SecurityWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
