package kata4.Controlador;

import java.io.IOException;
import java.util.List;
import kata4.Modelo.Histogram;
import kata4.Modelo.Mail;
import kata4.Vista.HistogramDisplay;
import kata4.Vista.MailHistogramBuilder;
import kata4.Vista.MailListReader;

public class Kata4 {

   
    public static void main(String[] args) throws IOException {
      String filename = "emails.txt";
      List<Mail> mailList = MailListReader.read(filename);
      Histogram<String> histogram = MailHistogramBuilder.build(mailList);
      HistogramDisplay histoDisplay = new HistogramDisplay(histogram);
      histoDisplay.execute();
    }
    
}
