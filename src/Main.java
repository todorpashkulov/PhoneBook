public class Main {
    public static void main(String[] args) {

      PhoneBook phoneBook= PhoneBookCreationUtills.getPhoneUtillsInstance().getPhoneBookInstanceFromFile("phonebook.txt");
        System.out.println(phoneBook.getPairList());

    }
}
