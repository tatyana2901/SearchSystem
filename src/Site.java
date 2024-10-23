import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Site {
    private String siteName;
    private ArrayList<String> data = new ArrayList<>();
    static ArrayList<Site> allSites = new ArrayList<>();

    public Site(String siteName) {
        {
            try {
                if (isSiteFound(siteName)) {
                    throw new DoubleObjectException("Такой сайт уже добавлен в список");
                }
                this.siteName = siteName;
                allSites.add(this);
            } catch (DoubleObjectException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Site getObjectByName(String siteName) {

        try {
            return allSites
                    .stream()
                    .filter(site -> site.getSiteName().equals(siteName))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException _) {
            return null;
        }
    }

    public static boolean isSiteFound(String siteName) {
        return allSites.stream().anyMatch(site -> site.getSiteName().equals(siteName));
    }

    public String getSiteName() {
        return siteName;
    }

    public String addKeyWord(String keyWord) {
        if (isWordFound(keyWord)) {
            return "Already exists\n" + "=====";
        } else {
            data.add(keyWord);
            return "ОК\n" + "=====";
        }
    }

    public String removeKeyWord(String keyWord) {
        if (!isWordFound(keyWord)) {
            return "Not found\n" + "=====";
        } else {
            data.remove(keyWord);
            return "OK\n" + "=====";
        }
    }


    public boolean isWordFound(String keyWord) {
        return data.contains(keyWord);
    }


    public static List<String> findSitesOnKeyWord(String keyWord) {

        long count =
                allSites
                        .stream()
                        .filter(site -> site.isWordFound(keyWord))
                        .count();

        ArrayList<String> sites = new ArrayList<>(
                allSites
                        .stream()
                        .filter(site -> site.isWordFound(keyWord))
                        .map(Site::getSiteName)
                        .sorted()
                        .limit(10)
                        .toList());

        for (int i = 0; i < sites.size(); i++) {
            sites.set(i, i + 1 + ") " + sites.get(i));
        }
        sites.addFirst("Results: " + count + " site(s) found");
        sites.addLast("=====");
        return sites;
    }

    public static List<String> reedFile(String fileName) {
        List<String> reader = null;
        try {
            reader = Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reader.removeFirst();
        return reader;
    }

    public static List<String> getResult(List<String> request) {
        List<String> result = new ArrayList<>();

        for (String r : request) {
            String[] req = r.split(" ");

            if (r.startsWith("Add")) {
                try {
                    Site site1 = getObjectByName(req[req.length - 1]);
                    String st = site1.addKeyWord(req[2]);
                    result.add(st);
                } catch (NullPointerException e) {
                    result.add(new Site(req[req.length - 1]).addKeyWord(req[2]));
                }
            } else if (r.startsWith("Remove")) {
                try {
                    result.add(getObjectByName(req[req.length - 1]).removeKeyWord(req[2]));
                } catch (Exception e) {
                    System.out.println("Попытка удаления ключевого слова из несуществующего сайта!!!");
                }
            } else if (r.startsWith("Search")) {
                result.addAll(findSitesOnKeyWord(req[1]));
            }
        }
        result.removeLast();
        return result;
    }

    public static void printResult(List<String> data) {
        data.forEach(System.out::println);
    }

}





