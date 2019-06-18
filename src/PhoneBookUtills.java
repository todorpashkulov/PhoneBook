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

        List<String> stringPairsList = readFileInList("phonebook.txt");

        phoneBook.setPairList(createPairsList(stringPairsList));
        for (Pair p : phoneBook.getPairList()) {
            p = createNormalPhoneNumber(p);
        }


        return phoneBook;
    }

    public void addPair(PhoneBook phoneBook) {
        Scanner scanner = new Scanner(System.in);
        Pair pair = new Pair();
        System.out.println("Enter name for contact");
        pair.setName(scanner.nextLine());
        do {
            System.out.println("Enter valid phone number");
            pair.setNumber(scanner.nextLine());


        } while (!isValidNumber(pair.getNumber()));

        phoneBook.getPairList().add(createNormalPhoneNumber(pair));


    }

    public void deletePairByName(PhoneBook phoneBook) {
        Scanner scanner = new Scanner(System.in);

        String name;

        Iterator<Pair> pairIterator=phoneBook.getPairList().iterator();

        System.out.println("Enter the name of the pair you want to delete");
        name = scanner.nextLine().toLowerCase();
        while (pairIterator.hasNext())
        {
            if(pairIterator.next().getName().toLowerCase().equals(name)){
                pairIterator.remove();
                System.out.println("Removed Succesfully !!");
                return;
            }
        }
        System.out.println("No pair with name found");



    }

    public void findNumberByName(PhoneBook phoneBook){
        Scanner scanner = new Scanner(System.in);
        String name;
        System.out.println("Enter the name of the number you want to find");
        name=scanner.nextLine();
        for(Pair p:phoneBook.getPairList()) {
            if(p.getName().toLowerCase().equals(name.toLowerCase())){
                System.out.println("The number of "+name+" is "+p.getNumber());
                return;
            }

        }
        System.out.println("No pair with given name found!!!");


    }

    public void printPairs(PhoneBook phoneBook){
        List<Pair> pairList=phoneBook.getPairList();
        pairList.sort(Comparator.comparing(Pair::getName));
        printMostCalledPairs(phoneBook);
        int i=1;
        System.out.println("List of all pairs in phone book");
        for(Pair p :pairList){
            System.out.println(i+". "+p.toString()+System.lineSeparator());
            i++;
        }

    }

    public void printMostCalledPairs(PhoneBook phoneBook){
        List<Pair> pairList=phoneBook.getPairList();
        pairList.sort(Comparator.comparing(Pair::getNumberOfOutgoigCalls).reversed());
        System.out.println("Top 5 most called pairs!!!");
        int i=1;
        for(Pair p:pairList){
            if(i==5||p.getNumberOfOutgoigCalls()==0)
            {
                break;
            }
            System.out.println(i+". "+p.toString()+System.lineSeparator());
            i++;
        }
    }

    public void callNumberByName(PhoneBook phoneBook){
        Scanner scanner = new Scanner(System.in);
        String name;
        System.out.println("Enter the name of the number you want to Call");
        name=scanner.nextLine();
        for(Pair p:phoneBook.getPairList()) {
            if(p.getName().toLowerCase().equals(name.toLowerCase())){
                p.setNumberOfOutgoigCalls(p.getNumberOfOutgoigCalls()+1);
                System.out.println("Call Successful");
                return;
            }

        }
        System.out.println("No pair with given name found!!!");

    }

    public void savePhoneBook(PhoneBook phoneBook) throws IOException {
        PrintWriter printWriter=new PrintWriter(new FileWriter("phonebooksave.txt"));
        for (Pair p:phoneBook.getPairList()){
            printWriter.println(p.getName()+" | "+p.getNumber()+" | "+p.getNumberOfOutgoigCalls());
        }
        printWriter.close();
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
            if(!splitPair[2].matches("\\d+")|| splitPair[2].isEmpty()){
                splitPair[2]="0";
            }
            pair.setNumberOfOutgoigCalls(Long.parseLong(splitPair[2]));
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


