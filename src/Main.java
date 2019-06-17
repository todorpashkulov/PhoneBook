import java.util.List;

public class Main {
    public static void main(String[] args) {

       PhoneBook phoneBook=PhoneUtills.getPhoneUtillsInstance().getPhoneBookInstanceFromFile("phonebook.txt");
        System.out.println(phoneBook.getPairList());
    }
}
