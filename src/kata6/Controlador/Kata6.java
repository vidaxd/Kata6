package kata6.Controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import kata6.Modelo.Histogram;
import kata6.Modelo.Mail;
import kata6.Modelo.Person;
import kata6.Vista.DataBaseList;
import kata6.Vista.HistogramDisplay;
import kata6.Vista.MailHistogramBuilder;
import kata6.Vista.MailListReader;

public class Kata6 {
    
    private List<Mail> mailList;
    //private Histogram<String> histogram;
    private Histogram<String> domains;
    private Histogram<Character> letters;
    private List<Person> people;
    private Histogram<Character> peoples;
    
   
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        //String nameFile="";
        Kata6 kata6=new Kata6();
        kata6.execute();
        
    }
    
    void execute() throws IOException, ClassNotFoundException, SQLException {
        input();
        process();
        output();
    }
    
    void input() throws IOException, ClassNotFoundException, SQLException {
        String fileName="emails.txt";
        mailList=MailListReader.read(fileName);
        people = DataBaseList.read();
    }
    
    void process() {
        //histogram = MailHistogramBuilder.build(mailList);
        MailHistogramBuilder<Mail> builder = new MailHistogramBuilder<>(mailList);
        MailHistogramBuilder<Person> builder2 = new MailHistogramBuilder<>(people);
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
        
        peoples=builder2.build(new Attribute<Person, Character>() {
            @Override
            public Character get(Person item) {
                return item.getGender();
            }
        });
        
        
    }
    
    void output() {
        HistogramDisplay histoDisplay = new HistogramDisplay(domains, "Dominios");
        histoDisplay.execute();
        histoDisplay = new HistogramDisplay(letters, "Letters");
        histoDisplay.execute();
        histoDisplay = new HistogramDisplay(peoples, "Genders");
        histoDisplay.execute();
    }
    
}
