import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.aston.MyCollections.MyArrayList;
import ru.aston.MyCollections.QuickSort;


public class MyArrayListTest {
    
    @Test
    public void testAdd() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for(int i = 0; i < 5; i++) {
            assertTrue(list.add(i));
            assertEquals(i + 1, list.size());
            assertEquals(i, (int) list.get(i));
        }

    }

    @Test
    public void testQuickSort() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for(int i = 5; i > 0; i--) {
            list.add(i);
        }

        QuickSort<Integer> quickSort = new QuickSort<>();
        list.sort();

        assertEquals("[1, 2, 3, 4, 5]", list.toString());
    }

    @Test
    public void testSort() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for(int i = 5; i >= 0; i--){
            list.add(i);
        }

        list.sort();
        assertEquals("[0, 1, 2, 3, 4, 5]", list.toString());
    }

    @Test
    public void testRemoveAll() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertTrue(list.removeAll());
        assertEquals(0, list.size());

        assertFalse(list.removeAll());
    }

    @Test
    public void testRemove() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for(int i = 0; i < 5; i++) {
            list.add(i);
        }

        list.remove(1);
        assertEquals("[0, 2, 3, 4]", list.toString());
    }

    @Test
    public void testGet() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }

        for(int i = 0; i < 10; i++) {
            assertEquals((Integer)i, list.get(i));
        }

        try {
            list.get(-1);
            fail("Expected IndexOutOfBoundsException");
        } catch(IndexOutOfBoundsException e) {

        }

        try {
            list.get(10);
            fail("Expected IndexOutOfBoundsException");
        } catch(IndexOutOfBoundsException e) {

        }
    }

    @Test
    public void testSet() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for(int i = 0; i < 5; i++) {
            list.add(i);
        }

        list.set(1, 5);
        assertEquals("[0, 5, 2, 3, 4]", list.toString());
    }

    @Test
    public void testSize() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for(int i = 0; i < 5; i++){
            list.add(i);
        }

        assertEquals(5, list.size());
    }

}
