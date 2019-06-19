import java.io.IOException;
import java.util.Scanner;

public class Main {
    static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int choice;
        String name, number;
        PhoneBook phoneBook = PhoneBookUtills.getPhoneUtillsInstance().
                getPhoneBookInstanceFromFile("phonebooksave.txt");


        String[] menu = {
                "Menu",
                "1.Load phone book from custom file.",
                "2.Add pair to phone book.",
                "3.Find number by name.",
                "4.Delete number by name",
                "5.Call phone by name",
                "6.Print all pairs with top 5 called.",
                "0.Exit and Save"
        };


        System.out.println("Phone book loaded from default save.");
        while (true) {
            System.out.println();
            for (String menu1 : menu) {
                System.out.println(menu1);
            }

            System.out.println("Enter choice:");
            System.out.println();
            try {

                choice = IN.nextInt();
            } catch (Exception e) {
                IN.nextLine();
                continue;
            }
            if (choice < 0 || choice > 6) {
                System.out.println("Enter again");
                continue;
            }

            switch (choice) {
                case 1:
                    //FOR TEST LOAD FROM phonebook.txt
                    System.out.println("Enter name of file");
                    IN.nextLine();
                    String file = IN.nextLine();
                    phoneBook = PhoneBookUtills.getPhoneUtillsInstance().getPhoneBookInstanceFromFile(file);
                    continue;
                case 2:
                    System.out.println("Enter name for contact");
                    IN.nextLine();
                    name = IN.nextLine();
                    do {
                        System.out.println("Enter phone number");
                        number = IN.nextLine();
                    } while (!PhoneBookUtills.getPhoneUtillsInstance().addPair(phoneBook, name, number));

                    continue;
                case 3:
                    System.out.println("Enter name for pair you want to find");
                    IN.nextLine();
                    name = IN.nextLine();
                    PhoneBookUtills.getPhoneUtillsInstance().findNumberByName(phoneBook, name);
                    continue;
                case 4:
                    System.out.println("Enter name for pair you want to delete");
                    IN.nextLine();
                    name = IN.nextLine();
                    PhoneBookUtills.getPhoneUtillsInstance().deletePairByName(phoneBook, name);
                    continue;
                case 5:
                    System.out.println("Enter name pair you want to call");
                    IN.nextLine();
                    name = IN.nextLine();
                    PhoneBookUtills.getPhoneUtillsInstance().callNumberByName(phoneBook,name);
                    continue;

                case 6:
                    PhoneBookUtills.getPhoneUtillsInstance().printPairs(phoneBook);
                    continue;

                case 0:
                    System.out.println("Goodbye!");
                    //default save path
                    PhoneBookUtills.getPhoneUtillsInstance().savePhoneBook(phoneBook);
                    break;
            }
            break;
        }



        //For Testing
        //PhoneBook phoneBook= PhoneBookUtills.getPhoneUtillsInstance().getPhoneBookInstanceFromFile("phonebooksave.txt");
        //PhoneBookUtills.getPhoneUtillsInstance().addPair(phoneBook);
        //PhoneBookUtills.getPhoneUtillsInstance().deletePairByName(phoneBook);
        //PhoneBookUtills.getPhoneUtillsInstance().findNumberByName(phoneBook);
        //PhoneBookUtills.getPhoneUtillsInstance().callNumberByName(phoneBook);
        //PhoneBookUtills.getPhoneUtillsInstance().printPairs(phoneBook);
        //PhoneBookUtills.getPhoneUtillsInstance().printMostCalledPairs(phoneBook);
        //PhoneBookUtills.getPhoneUtillsInstance().savePhoneBook(phoneBook);


    }
}
