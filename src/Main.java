import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws IOException {
        Site.getResult(Site.reedFile("notes.txt"));
        /*Site site1 = new Site("ifmo.ru/school/io");
        Site site2 = new Site("ifm.ru/school/io");
        site2.addKeyWord("ПРИВЕЕЕЕЕЕЕТ");
        Site site3 = new Site("abc");
        System.out.println(Site.getObjectByName("ifmool/io").getData());*/
       /* Site site4 = new Site("letter");
        Site site5 = new Site("powder");
        System.out.println(site1.addKeyWord("keykey"));
        site4.addKeyWord("ddddddddd");
        site2.addKeyWord("keyWorld");
        System.out.println(site2.addKeyWord("keyWorld")); ;
        System.out.println(site5.addKeyWord("keyWorld"));
        site3.addKeyWord("123");
        site5.addKeyWord("never");*/
//        Site.allSites.forEach(site -> System.out.println(site.getSiteName()));
       // System.out.println(Site.findSitesOnKeyWord("keyWorld"));

       // Site.reedFile("notes.txt");

    }
}