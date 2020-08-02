import junit.framework.TestCase;
import org.junit.Test;

import java.util.Comparator;

public class LinkedListTests extends TestCase {
    private DoubleLinkedList<String> testList;

    protected void setUp() {
        testList = new DoubleLinkedList<>();
    }

    @Test
    public void testListIsEmpty() {
        assertEquals(0, testList.getSize());
    }

    @Test
    public void testAddFirstElement() {
        testList.addFirst("1");
        assertEquals(1, testList.getSize());
        assertTrue(testList.contains("1"));
    }

    @Test
    public void testIfAddedFirst() {
        testList.addFirst("1");
        testList.addFirst("2");
        assertEquals(2, testList.getSize());
        assertEquals("1", testList.getLast());
        assertEquals("2", testList.getFirst());
    }

    @Test
    public void testAddLastElement() {
        testList.addLast("1");
        assertEquals(1, testList.getSize());
        assertTrue(testList.contains("1"));
    }

    @Test
    public void testIfAddedLast() {
        testList.addLast("1");
        testList.addLast("2");
        assertEquals(2, testList.getSize());
        assertEquals("1", testList.getFirst());
        assertEquals("2", testList.getLast());
    }

    @Test
    public void testAddFirstNull() {
        testList.addFirst(null);
        assertEquals(0, testList.getSize());
    }

    @Test
    public void testAddLastNull() {
        testList.addLast(null);
        assertEquals(0, testList.getSize());
    }

    @Test
    public void testAddOnIndexEnd() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        testList.add(2, "4");
        assertEquals("4", testList.get(2));
        assertEquals(4, testList.getSize());
    }

    @Test
    public void testAddOnIndexOutOfBounds() {
        testList.add(5, "1");
        assertEquals(0, testList.getSize());
    }

    @Test
    public void testAddOnIndexStart() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        testList.add(0, "4");
        testList.add(1, "7");
        testList.add(5, "5");
        assertEquals("4", testList.get(0));
        assertEquals("7", testList.get(1));
        assertEquals("5", testList.getLast());
        assertEquals(6, testList.getSize());
    }

    @Test
    public void testInsertAllNotEmpty() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        DoubleLinkedList<String> testListNew = new DoubleLinkedList<>();
        testListNew.addFirst("10");
        testListNew.addFirst("20");
        testListNew.addFirst("30");
        testList.addAll(1, testListNew);
        assertEquals(6, testList.getSize());
        assertEquals("30", testList.get(1));
        assertEquals("20", testList.get(2));
        assertEquals("10", testList.get(3));
        assertEquals("2", testList.get(4));
        assertEquals("1", testList.getLast());
        assertEquals("3", testList.getFirst());
    }

    @Test
    public void testInsertAllEmpty() {
        DoubleLinkedList<String> testListNew = new DoubleLinkedList<>();
        testListNew.addFirst("10");
        testListNew.addFirst("20");
        testListNew.addFirst("30");
        testList.addAll(0, testListNew);
        assertEquals("30", testList.getFirst());
        assertEquals("10", testList.getLast());
        assertEquals("20", testList.get(1));
        assertEquals(3, testList.getSize());
    }

    @Test
    public void testInsertAllEmptyToEmpty() {
        DoubleLinkedList<String> testListNew = new DoubleLinkedList<>();
        testList.addAll(0, testListNew);
        assertEquals(0, testList.getSize());
    }

    @Test
    public void testInsertAllFirstPos() {
        DoubleLinkedList<String> testListNew = new DoubleLinkedList<>();
        testListNew.addFirst("10");
        testListNew.addFirst("20");
        testListNew.addFirst("30");
        testList.addFirst("1");
        testList.addAll(0, testListNew);
        assertEquals("30", testList.getFirst());
        assertEquals("1", testList.getLast());
        assertEquals("20", testList.get(1));
        assertEquals(4, testList.getSize());
    }

    @Test
    public void testInsertAllLastPos() {
        DoubleLinkedList<String> testListNew = new DoubleLinkedList<>();
        testListNew.addFirst("10");
        testListNew.addFirst("20");
        testListNew.addFirst("30");
        testList.addFirst("1");
        testList.addAll(1, testListNew);
        assertEquals("1", testList.getFirst());
        assertEquals("10", testList.getLast());
        assertEquals("30", testList.get(1));
        assertEquals(4, testList.getSize());
    }

    @Test
    public void testInsertAllLSecondHalf() {
        DoubleLinkedList<String> testListNew = new DoubleLinkedList<>();
        testListNew.addFirst("10");
        testListNew.addFirst("20");
        testListNew.addFirst("30");
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        testList.addFirst("4");
        testList.addAll(3, testListNew);
        assertEquals("4", testList.getFirst());
        assertEquals("1", testList.getLast());
        assertEquals("2", testList.get(2));
        assertEquals("30", testList.get(3));
        assertEquals("10", testList.get(5));
        assertEquals(7, testList.getSize());
    }

    @Test
    public void testSort() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        testList.sort((s, t1) -> s.compareTo(t1));
        assertEquals("1", testList.getFirst());
        assertEquals("2", testList.get(1));
        assertEquals("3", testList.getLast());
        assertEquals(3, testList.getSize());
    }

    @Test
    public void testAddSorted() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("4");
        testList.addSorted("3", (s, t1) -> s.compareTo(t1));
        assertEquals("1", testList.getFirst());
        assertEquals("2", testList.get(1));
        assertEquals("3", testList.get(2));
        assertEquals("4", testList.getLast());
        assertTrue(testList.contains("3"));
    }

    @Test
    public void testRemoveFirst() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        testList.removeFirst();
        assertEquals("2", testList.getFirst());
        assertEquals("1", testList.getLast());
        assertEquals(2, testList.getSize());
        assertFalse(testList.contains("3"));
    }

    @Test
    public void testRemoveLast() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        testList.removeLast();
        assertEquals("3", testList.getFirst());
        assertEquals("2", testList.getLast());
        assertEquals(2, testList.getSize());
        assertFalse(testList.contains("1"));
    }

    @Test
    public void testRemoveValue() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        testList.remove("2");
        assertEquals("3", testList.getFirst());
        assertEquals("1", testList.getLast());
        assertEquals(2, testList.getSize());
        assertFalse(testList.contains("2"));
    }

    @Test
    public void testRemoveValueFalse() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        assertFalse(testList.remove("8"));
        assertEquals(3, testList.getSize());
    }

    @Test
    public void testClear() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        testList.clear();
        assertEquals(0, testList.getSize());
    }

    @Test
    public void testReverse() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        testList.reverse();
        assertEquals("1", testList.getFirst());
        assertEquals("3", testList.getLast());
        assertEquals("2", testList.get(1));
        assertEquals(3, testList.getSize());
    }

    @Test
    public void testRotate() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        testList.addFirst("4");
        testList.addFirst("5");
        testList.addFirst("6");
        testList.rotate(2);
        assertEquals("2", testList.getFirst());
        assertEquals("3", testList.getLast());
        assertEquals(6, testList.getSize());
    }

    @Test
    public void testRotateNegative() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        testList.addFirst("4");
        testList.addFirst("5");
        testList.addFirst("6");
        testList.rotate(-2);
        assertEquals("4", testList.getFirst());
        assertEquals("5", testList.getLast());
        assertEquals(6, testList.getSize());
    }

    @Test
    public void testToArray() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        testList.addFirst("4");
        String[] expected = new String[]{"4", "3", "2", "1"};
        Object[] actual = testList.toArray();
        for (int i = 0; i < 4; i++) {
            assertEquals(expected[i], actual[i]);
        }
        assertEquals(4, actual.length);
    }

    @Test
    public void testToString() {
        testList.addFirst("1");
        testList.addFirst("2");
        testList.addFirst("3");
        testList.addFirst("4");
        assertEquals("4, 3, 2, 1", testList.toString());
    }


}
