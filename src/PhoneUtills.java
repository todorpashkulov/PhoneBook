import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PhoneUtills {
    private static PhoneUtills phoneUtills = null;

    private PhoneUtills() {
    }

    public static PhoneUtills getPhoneUtillsInstance() {
        if (phoneUtills == null) {
            phoneUtills = new PhoneUtills();
        }

        return phoneUtills;
    }

    public List<String> readFileInList(String filePath) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public PhoneBook getPhoneBookInstanceFromFile(String filePath) {
        PhoneBook phoneBook = new PhoneBook();

        List<String> pairsList = readFileInList("phonebook.txt");

        phoneBook.getPairList().add(createPairs(pairsList));


        return phoneBook;
    }

    private String[] trimPairs(String[] splitPair) {
        for (int i = 0; i < splitPair.length; i++) {
            splitPair[i] = splitPair[i].replaceAll("^\\s+|\\s+$", "");
        }

        return splitPair;
    }

    private Pair createPairs(List<String> pairsList) {
        Pair pair = new Pair();

        Iterator<String> pairIterator = pairsList.iterator();

        while (pairIterator.hasNext()) {
            String[] splitPair = pairIterator.next().split("[|]");
            if (splitPair.length != 2) {
                continue;
            }
            splitPair = trimPairs(splitPair);
            if (!isValidNumber(splitPair[1])) {
                continue;
            }


            pair.setName(splitPair[0]);
            pair.setNumber(createNormalPhoneNumber(splitPair[1]));

        }
        return pair;
    }

    private boolean isValidNumber(String number) {
        if(!number.matches("\\d"))
        {
            return false;
        }
        if (number.length() == 10 && number.charAt(1)=='8'&& number.charAt(2)>=55 && number.charAt(2)<=57) {
           return true;
        } else if (number.length() == 13) {

        } else if (number.length() == 14) {

        }

        return false;


    }

    private String createNormalPhoneNumber(String number) {
        return number;
    }

}
