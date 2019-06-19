import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PhoneBookUtills {
    private static PhoneBookUtills phoneUtills = null;

    private PhoneBookUtills() {
    }

    public static PhoneBookUtills getPhoneUtillsInstance() {
        if (phoneUtills == null) {
            phoneUtills = new PhoneBookUtills();
        }

        return phoneUtills;
    }

    public PhoneBook getPhoneBookInstanceFromFile(String filePath) {
        PhoneBook phoneBook = new PhoneBook();

        List<String> stringPairsList = readFileInList(filePath);
        if (stringPairsList.isEmpty()) {
            System.out.println("File not found!!");
            return null;
        }

        phoneBook.setPairList(createPairsList(stringPairsList));


        return phoneBook;
    }

    public boolean addPair(PhoneBook phoneBook, String name, String phoneNumber) {
        if (!isValidNumber(phoneNumber)) {
            return false;
        }
        for(Pair p:phoneBook.getPairList()){
            if(p.getNumber().equals(createNormalPhoneNumber(new Pair("Just for test",phoneNumber)).getNumber())){
                System.out.println("Number already exists");
                return false;
            }

        }


        Pair pair = new Pair();

        pair.setName(name);


        pair.setNumber(phoneNumber);


        phoneBook.getPairList().add(createNormalPhoneNumber(pair));
        return true;

    }

    public boolean deletePairByName(PhoneBook phoneBook, String name) {


        Iterator<Pair> pairIterator = phoneBook.getPairList().iterator();


        while (pairIterator.hasNext()) {
            if (pairIterator.next().getName().toLowerCase().equals(name.toLowerCase())) {
                pairIterator.remove();
                System.out.println("Removed Succesfully !!");
                return true;
            }
        }
        System.out.println("No pair with name found");
        return false;


    }

    public boolean findNumberByName(PhoneBook phoneBook, String name) {


        for (Pair p : phoneBook.getPairList()) {
            if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                System.out.println("The number of " + name + " is " + p.getNumber());
                return true;
            }

        }
        System.out.println("No pair with given name found!!!");
        return false;


    }

    public boolean printPairs(PhoneBook phoneBook) {
        if (phoneBook.getPairList().isEmpty()) {
            return false;
        }
        List<Pair> pairList = phoneBook.getPairList();

        printMostCalledPairs(phoneBook);
        pairList.sort(Comparator.comparing(Pair::getName));
        int i = 1;
        System.out.println("List of all pairs in phone book");
        for (Pair p : pairList) {
            System.out.println(i + ". " + p.toString() + System.lineSeparator());
            i++;
        }
        return true;
    }

    public boolean printMostCalledPairs(PhoneBook phoneBook) {
        List<Pair> pairList = phoneBook.getPairList();
        if(phoneBook.getPairList().isEmpty()){
            return false;
        }
        pairList.sort(Comparator.comparing(Pair::getNumberOfOutgoigCalls).reversed());
        System.out.println("Top 5 most called pairs!!!");
        int i = 1;
        for (Pair p : pairList) {
            if (i == 5 || p.getNumberOfOutgoigCalls() == 0) {
                break;
            }
            System.out.println(i + ". " + p.toString() + System.lineSeparator());
            i++;
        }
        if(i==1){
            System.out.println("You dont have any outgoing calls sorry :( !!!");
        }
        return true;
    }

    public boolean callNumberByName(PhoneBook phoneBook,String name) {

        for (Pair p : phoneBook.getPairList()) {
            if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                p.setNumberOfOutgoigCalls(p.getNumberOfOutgoigCalls() + 1);
                System.out.println("Call Successful");
                return true;
            }

        }
        System.out.println("No pair with given name found!!!");
        return false;

    }

    public boolean savePhoneBook(PhoneBook phoneBook){
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter("phonebooksave.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        for (Pair p : phoneBook.getPairList()) {
            printWriter.println(p.getName() + " | " + p.getNumber() + " | " + p.getNumberOfOutgoigCalls());
        }
        printWriter.close();
        return true;
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

        outer:
        while (pairIterator.hasNext()) {
            String[] splitPair = pairIterator.next().split("[|]");
            if (splitPair.length != 3 || splitPair[0].isEmpty() || splitPair[1].isEmpty()) {
                continue;
            }
            splitPair = trimPairs(splitPair);

            if (!isValidNumber(splitPair[1])) {
                continue;
            }

            Pair pair = new Pair();
            pair.setName(splitPair[0]);
            pair.setNumber(splitPair[1]);

            if (!splitPair[2].matches("\\d+") || splitPair[2].equals("")) {
                splitPair[2] = "0";
            }
            pair.setNumberOfOutgoigCalls(Long.parseLong(splitPair[2]));
            pair = createNormalPhoneNumber(pair);
            for (Pair p : pairs) {
                if (p.getNumber().equals(pair.getNumber())) {
                    continue outer;
                }
            }
            pairs.add(pair);

        }
        return pairs;
    }

    public boolean isValidNumber(String number) {

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

    private Pair createNormalPhoneNumber(Pair pair) {


        if (pair.getNumber().length() == 10) {
            pair.setNumber("+359" + pair.getNumber().substring(1, 10));

        } else if (pair.getNumber().length() == 14) {
            pair.setNumber("+" + pair.getNumber().substring(2, 14));

        }


        return pair;
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



}


