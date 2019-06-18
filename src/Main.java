import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

      PhoneBook phoneBook= PhoneBookUtills.getPhoneUtillsInstance().getPhoneBookInstanceFromFile("phonebooksave.txt");
        //PhoneBookUtills.getPhoneUtillsInstance().addPair(phoneBook);
        // PhoneBookUtills.getPhoneUtillsInstance().deletePairByName(phoneBook);
        //PhoneBookUtills.getPhoneUtillsInstance().findNumberByName(phoneBook);
        //PhoneBookUtills.getPhoneUtillsInstance().callNumberByName(phoneBook);
        PhoneBookUtills.getPhoneUtillsInstance().printPairs(phoneBook);
        //PhoneBookUtills.getPhoneUtillsInstance().printMostCalledPairs(phoneBook);
        //PhoneBookUtills.getPhoneUtillsInstance().savePhoneBook(phoneBook);



    }
}
