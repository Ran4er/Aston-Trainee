import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.aston.model.MyArrayList;
import ru.aston.model.MyList;
import ru.aston.model.otherClass.Person;
import ru.aston.util.QuickSort;


public class QuickSortTest {
    
    private MyList<String> listStr;
    private MyList<Double> listDoub;
    private MyList<Integer> listInt;
    private MyList<Character> listChar;
    private MyList<Person> listPerson;

    @Test
    public void testWithString() {
        listStr = getListString();
        QuickSort.quicksort(listStr);
        assertEquals("[abc, cba, def]", listStr.toString());
    }

    @Test
    public void testWithDouble() {
        listDoub = getListDouble();
        QuickSort.quicksort(listDoub);
        assertEquals("[5.09, 5.1, 90.5]", listDoub.toString());
    }

    @Test
    public void testWithInteger() {
        listInt = getListInteger();
        QuickSort.quicksort(listInt);
        assertEquals("[1, 3, 5]", listInt.toString());
    }

    @Test
    public void testWithCharacter() {
        listChar = getListCharacter();
        QuickSort.quicksort(listChar);
        assertEquals("[a, b, c]", listChar.toString());
    }

    @Test
    public void testWithPersonByAge() {
        listPerson = getListPersonSortByAge();
        QuickSort.quicksort(listPerson);
        assertEquals("[Genady Zivenkov 19, Forman Kovalev 21, Fred Colnman 24]", listPerson.toString());
    }

    @Test
    public void testWithPersonByName() {
        listPerson = getListPersonFirstName();
        QuickSort.quicksort(listPerson);
        assertEquals("[Alex Aldeman 18, Bob Borisov 18, John Thompson 18]", listPerson.toString());
    }

    private MyList<String> getListString() {
        MyList<String> list = new MyArrayList<>();
        list.add("abc");
        list.add("def");
        list.add("cba");
        return list;
    }

    private MyList<Integer> getListInteger() {
        MyList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(5);
        list.add(3);
        return list;
    }

    private MyList<Double> getListDouble() {
        MyList<Double> list = new MyArrayList<>();
        list.add(5.09);
        list.add(5.1);
        list.add(90.5);
        return list;
    }

    private MyList<Character> getListCharacter() {
        MyList<Character> list = new MyArrayList<>();
        list.add('a');
        list.add('c');
        list.add('b');
        return list;
    }

    private MyList<Person> getListPersonSortByAge() {
        MyList<Person> list = new MyArrayList<>();
        list.add(new Person(24, "Fred", "Colnman"));
        list.add(new Person(19, "Genady", "Zivenkov"));
        list.add(new Person(21, "Forman", "Kovalev"));
        return list;
    }

    private MyList<Person> getListPersonFirstName() {
        MyList<Person> list = new MyArrayList<>();
        list.add(new Person(18, "John", "Thompson"));
        list.add(new Person(18, "Alex", "Aldeman"));
        list.add(new Person(18, "Bob", "Borisov"));
        return list;
    }


}
