package jbs.tool;

import jbs.*;
import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import java.util.*;
import java.util.regex.*;
import java.util.concurrent.*;
import java.util.stream.*;

import java.nio.file.*;


public class AnalyticUtils {

    public static String readFile(File f) 
    throws FileNotFoundException, IOException {

        InputStreamReader isr =  null;
        InputStream ins= null;
        StringBuffer sbuf = new StringBuffer();
        BufferedReader in = null;
        FileInputStream fis = null;
        String ret = null;
        try {
            fis = new FileInputStream(f);

            isr = new InputStreamReader(fis);
            in = new BufferedReader(isr);
            String inputLine;
            //read whole page as raw
            while (in!=null && (inputLine = in.readLine()) != null) {
                sbuf.append( inputLine + "\r\n" );
            }
            if( in!=null) in.close();
            ret = sbuf.toString();

            // } catch( Exception e ) {
            //   e.printStackTrace();
            // System.exit(2);
            //throw new RuntimeException(e);

        } finally {
            if( fis!=null)  fis.close();
            if( in!=null) in.close();
            if( isr!=null) isr.close();
            if( ins!=null)  ins.close();

            ret = sbuf.toString();
        }

        return ret;
    }

    public static boolean regex( String regex, String raw) {
        if(raw==null) return false;
        Pattern pattern = Pattern.compile(regex);
        boolean ret = false;
        Matcher matcher = pattern.matcher(raw);
        while(matcher.find()) {
            System.out.println(
                "matched "+matcher.group(1));
            ret |= true;
        }
        return ret;
    } // end regex    


    public static boolean validatePhoneNumber(String phoneNo) {
        // validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}"))
            return true;
            // validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
            return true;
            // validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
            return true;
            // validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)[-|\\s]\\d{3}-\\d{4}"))
            return true;
            // return false if nothing matches the input
        else
            return false;

    }


    public static void main(String... args) {
        boolean b = false;
        File fout = null;
        HashSet<String> set = new HashSet<String>();
        try {
            // b = regex( JConstants.LOG_URL_REGEX, 
            AnalyticUtils.download(
                "https://www.flightdata.com/flights/123",
                null,
                set
            ) ;
            System.out.println(set.toString());
            // AnalyticUtils.readFileAsString(
            // JConstants.SHARED_FLIGHT_FILE_NAME));
            // validatePhoneNumber("(814) 404-4003");
        } catch( Exception e) {
            e.printStackTrace();
        }

        System.out.printf("%s\n", b);
    }
}
