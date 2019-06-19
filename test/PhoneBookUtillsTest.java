import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookUtillsTest {


    @Test
    void getPhoneUtillsInstance() {
        assertNotNull(PhoneBookUtills.getPhoneUtillsInstance());
    }

    @Test
    void getPhoneBookInstanceFromFile() {
        assertNotNull(PhoneBookUtills.getPhoneUtillsInstance().getPhoneBookInstanceFromFile("phonebook.txt"));
    }

    @Test
    void addPair() {
        PhoneBook phoneBook = new PhoneBook();

        assertTrue(PhoneBookUtills.getPhoneUtillsInstance().addPair(phoneBook, "TestName", "0898626348"));
    }

    @Test
    void deletePairByName() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.getPairList().add(new Pair("TestName", "JustForTest"));

        assertTrue(PhoneBookUtills.getPhoneUtillsInstance().deletePairByName(phoneBook, "TestName"));

    }

    @Test
    void findNumberByName() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.getPairList().add(new Pair("TestName", "JustForTest"));

        assertTrue(PhoneBookUtills.getPhoneUtillsInstance().findNumberByName(phoneBook, "TestName"));
    }

    @Test
    void printPairs() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.getPairList().add(new Pair("TestName", "JustForTest"));
        phoneBook.getPairList().add(new Pair("TestName1", "JustForTest1"));
        assertTrue(PhoneBookUtills.getPhoneUtillsInstance().printPairs(phoneBook));
    }

    @Test
    void printMostCalledPairs() {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.getPairList().add(new Pair("TestName1", "JustForTest1",4));
        phoneBook.getPairList().add(new Pair("TestName11", "JustForTest11",0));
        assertTrue(PhoneBookUtills.getPhoneUtillsInstance().printMostCalledPairs(phoneBook));

    }

    @Test
    void callNumberByName() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.getPairList().add(new Pair("TestName", "JustForTest",0));
        assertTrue(PhoneBookUtills.getPhoneUtillsInstance().callNumberByName(phoneBook,"TestName"));
        assertEquals(1,phoneBook.getPairList().get(0).getNumberOfOutgoigCalls());

    }

    @Test
    void savePhoneBook() {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.getPairList().add(new Pair("TestName1", "JustForTest1",4));
        phoneBook.getPairList().add(new Pair("TestName11", "JustForTest11",0));
        assertTrue(PhoneBookUtills.getPhoneUtillsInstance().savePhoneBook(phoneBook));
    }

    @Test
    void isValidNumber(){
        assertTrue(PhoneBookUtills.getPhoneUtillsInstance().isValidNumber("+359898626366"));
        assertTrue(PhoneBookUtills.getPhoneUtillsInstance().isValidNumber("00359898626366"));
        assertTrue(PhoneBookUtills.getPhoneUtillsInstance().isValidNumber("0898626366"));
    }



}