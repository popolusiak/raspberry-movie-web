package eu.suhajko.movie.scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;


/**
 * Created by marek.melis on 4/12/17.
 */
public class MD5CheckSum {

    private MD5CheckSum() {
    }


    public static byte[] createChecksum(File file) throws MD5CheckSumException {

        try (InputStream fis = new FileInputStream(file);) {
            byte[] buffer = new byte[1024];
            MessageDigest complete = MessageDigest.getInstance("MD5");
            int numRead;

            do {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            }
            while (numRead != -1);
            return complete.digest();
        }
        catch (Exception e) {
            throw new MD5CheckSumException(String.format("Exception when trying to convert file %s to MD5", file.getName()), e);
        }


    }

    // see this How-to for a faster way to convert
    // a byte array to a HEX string
    public static String getMD5Checksum(File file) {
        byte[] b = new byte[0];
        try {
            b = createChecksum(file);
        }
        catch (Exception e) {
            Logger logger = LoggerFactory.getLogger("MD5CheckSum");
            logger.error("Exception while getting md5 from file {}", file.getName(), e);
            return "";
        }
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < b.length; i++) {
            result.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}
