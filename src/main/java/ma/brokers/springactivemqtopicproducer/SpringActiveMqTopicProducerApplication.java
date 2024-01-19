package ma.brokers.springactivemqtopicproducer;

import ma.brokers.springactivemqtopicproducer.models.Company;
import ma.brokers.springactivemqtopicproducer.models.Product;
import ma.brokers.springactivemqtopicproducer.publisher.MyJmsPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringActiveMqTopicProducerApplication implements CommandLineRunner {

    @Autowired
    MyJmsPublisher publisher;
    public static void main(String[] args) {
        SpringApplication.run(SpringActiveMqTopicProducerApplication.
                class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*
         * Apple company & products
         */
// initial company and products
        Product iphone7 = new Product("Iphone X");
        Product iPadPro = new Product("IPadPro");
        List<Product> appleProducts = new
                ArrayList<Product>(Arrays.asList(iphone7, iPadPro));
        Company apple = new Company("Apple", appleProducts);
// send message to ActiveMQ
        publisher.send(apple);
        /*
         * Samsung company and products
         */
        Product galaxySx = new Product("Galaxy SXX");
        Product gearSy = new Product("Gear YYY");
        List<Product> samsungProducts = new
                ArrayList<Product>(Arrays.asList(galaxySx, gearSy));
        Company samsung = new Company("Samsung", samsungProducts);
        /*
         * send message to ActiveMQ
         */
        publisher.send(samsung);
    }


    }
