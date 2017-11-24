package kata6.Controlador;

import java.io.IOException;
import java.util.List;
import kata6.Modelo.Histogram;
import kata6.Modelo.Mail;
import kata6.Vista.HistogramDisplay;
import kata6.Vista.MailHistogramBuilder;
import kata6.Vista.MailListReader;

public class Kata6 {
    
    private List<Mail> mailList;
    //private Histogram<String> histogram;
    Histogram<String> domains;
    Histogram<Character> letters;
   
    public static void main(String[] args) throws IOException {
        //String nameFile="";
        Kata6 kata4=new Kata6();
        kata4.execute();
    }
    
    void execute() throws IOException {
        input();
        process();
        output();
    }
    
    void input() throws IOException {
        String fileName="emails.txt";
        mailList=MailListReader.read(fileName);
    }
    
    void process() {
        //histogram = MailHistogramBuilder.build(mailList);
        MailHistogramBuilder<Mail> builder = new MailHistogramBuilder<>(mailList);
        domains=builder.build(new Attribute<Mail, String>() {
            @Override
            public String get(Mail item) {
                return item.getMail().split("@")[1];
            }
        });
        
        letters=builder.build(new Attribute<Mail, Character>() {
            @Override
            public Character get(Mail item) {
                return item.getMail().charAt(0);
            }
        });
        
    }
    
    void output() {
        HistogramDisplay histoDisplay = new HistogramDisplay(domains, "Dominios");
        histoDisplay.execute();
        histoDisplay = new HistogramDisplay(letters, "Letters");
        histoDisplay.execute();
    }
    
}
