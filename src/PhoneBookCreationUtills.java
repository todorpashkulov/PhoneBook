import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PhoneBookCreationUtills {
    private static PhoneBookCreationUtills phoneUtills = null;

    private PhoneBookCreationUtills() {
    }

    public static PhoneBookCreationUtills getPhoneUtillsInstance() {
        if (phoneUtills == null) {
            phoneUtills = new PhoneBookCreationUtills();
        }

        return phoneUtills;
    }

    private List<String> readFileInList(String filePath) {
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

        List<String> stringPairsList = readFileInList("phonebook.txt");

        phoneBook.setPairList(createNormalPhoneNumberPairsList(stringPairsList));


        return phoneBook;
    }

    private String[] trimPairs(String[] splitPair) {
        for (int i = 0; i < splitPair.length; i++) {
            splitPair[i] = splitPair[i].replaceAll("^\\s+|\\s+$", "");
        }

        return splitPair;

    }

    private List<Pair> createPairsList(List<String> stringPairsList) {


        Iterator<String> pairIterator = stringPairsList.iterator();
        List<Pair> pairs = new ArrayList<>();

        while (pairIterator.hasNext()) {
            String[] splitPair = pairIterator.next().split("[|]");
            if (splitPair.length != 2) {
                continue;
            }
            splitPair = trimPairs(splitPair);

            if (!isValidNumber(splitPair[1])) {
                continue;
            }

            Pair pair = new Pair();
            pair.setName(splitPair[0]);
            pair.setNumber(splitPair[1]);
            pairs.add(pair);

        }
        return pairs;
    }

    private boolean isValidNumber(String number) {

        //for 0898626344
        if (number.length() == 10 &&
                number.charAt(0) == '0' &&
                number.charAt(1) == '8' &&
                number.charAt(2) >= 55 &&
                number.charAt(2) <= 57 &&
                number.matches("\\d+")
        ) {
            return true;
            //for +359 number
        } else if (number.length() == 13 &&
                number.substring(0, 4).equals("+359") &&
                number.charAt(4) == '8' &&
                number.charAt(5) >= 55 &&
                number.charAt(5) <= 57 &&
                number.substring(1, 13).matches("\\d+")
        ) {
            return true;
            //for 00359 number
        } else if (number.length() == 14 &&
                number.matches("\\d+") &&
                number.substring(0, 5).equals("00359") &&
                number.charAt(5) == '8' &&
                number.charAt(6) >= 55 &&
                number.charAt(6) <= 57
        ) {
            return true;

        }

        return false;


    }

    private List<Pair> createNormalPhoneNumberPairsList(List<String> stringPairsList) {
        List<Pair> pairList=createPairsList(stringPairsList);

        for(Pair p:pairList){
            if(p.getNumber().length()==13){
                p.setNumber("0"+p.getNumber().substring(4,13));

            }else if(p.getNumber().length()==14){
                p.setNumber("0"+p.getNumber().substring(5,14));

            }
        }


        return pairList;
    }

}
