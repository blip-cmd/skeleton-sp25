import deque.ArrayDeque61B;
import deque.Maximizer61B;
import java.util.Comparator;


import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static junit.framework.TestCase.assertEquals;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

    @Test
    public void testAddFirstAndAddLast(){
        Deque61B<String> aDeque = new ArrayDeque61B<>();
        aDeque.addFirst("b");
        aDeque.addLast("c");
        aDeque.addFirst("a");

        assertThat(aDeque.size()).isEqualTo(3);
        assertThat(aDeque.toList()).containsExactly("a","b","c").inOrder();
     }

     @Test
    public void test_isEmpty(){
         Deque61B<String> aDeque = new ArrayDeque61B<>();
         assertThat(aDeque.isEmpty()).isTrue();
         aDeque.addLast("d");
         assertThat(aDeque.isEmpty()).isFalse();
     }

    @Test
    public void test_size(){
        Deque61B<String> aDeque = new ArrayDeque61B<>();
        assertThat(aDeque.size()).isEqualTo(0);
        aDeque.addLast("d");
        assertThat(aDeque.size()).isEqualTo(1);
    }

    @Test
    public void test_get(){
        Deque61B<String> aDeque = new ArrayDeque61B<>();
        assertThat(aDeque.get(1)).isNull();

        aDeque.addFirst("a");
        aDeque.addLast("b");
        aDeque.addLast("d");

        assertThat(aDeque.get(0)).contains("a");
        assertThat(aDeque.get(1)).contains("b");
        assertThat(aDeque.get(2)).contains("d");
    }

    @Test
    public void test_removeFirst(){
        Deque61B<String> aDeque = new ArrayDeque61B<>();
        assertThat(aDeque.removeFirst()).isNull();

        aDeque.addFirst("a");
        aDeque.addLast("b");
        aDeque.addLast("d");

        assertThat(aDeque.removeFirst()).isEqualTo("a");
        assertThat(aDeque.removeFirst()).isEqualTo("b");
        assertThat(aDeque.removeFirst()).isEqualTo("d");
    }

    public void test_removeLast(){
        Deque61B<String> aDeque = new ArrayDeque61B<>();
        assertThat(aDeque.removeLast()).isNull();

        aDeque.addFirst("a");
        aDeque.addLast("b");
        aDeque.addLast("d");

        assertThat(aDeque.removeLast()).isEqualTo("d");
        assertThat(aDeque.removeLast()).isEqualTo("b");
        assertThat(aDeque.removeLast()).isEqualTo("a");
    }
    @Test
    public void testResizeWithoutIterator() {
        ArrayDeque61B<Integer> aDeque = new ArrayDeque61B<>();

        // Fill to capacity (default = 8)
        for (int i = 0; i < 8; i++) {
            aDeque.addLast(i);
        }

        // Trigger resize
        aDeque.addLast(8);

        // Check size
        assertEquals(9, aDeque.size());

        // Check contents in order using get()
        for (int i = 0; i <= 8; i++) {
            assertEquals((Integer) i, aDeque.get(i));
        }

        // Check front and back removal
        assertEquals((Integer) 0, aDeque.removeFirst());
        assertEquals((Integer) 8, aDeque.removeLast());
    }

    @Test
    // Copilot wrote
    public void testResizeDown() {
        ArrayDeque61B<Integer> aDeque = new ArrayDeque61B<>();

        // Fill to trigger resize up
        for (int i = 0; i < 32; i++) {
            aDeque.addLast(i);
        }

        // Remove enough to trigger shrink
        for (int i = 0; i < 25; i++) {
            aDeque.removeFirst();
        }

        // Remaining elements should be correct
        for (int i = 25; i < 32; i++) {
            assertEquals((Integer) i, aDeque.get(i - 25));
        }
    }

//    Copilot wrote this test
    @Test
    public void testIteratorWorks() {
        Deque61B<String> aDeque = new ArrayDeque61B<>();
        aDeque.addLast("a");
        aDeque.addLast("b");
        aDeque.addLast("c");

        StringBuilder result = new StringBuilder();
        for (Object s : aDeque) {
            result.append(s);
        }

        assertThat(result.toString()).isEqualTo("abc");
    }

//    This test is not exhaustive of the 3 operations equals() has.
    @Test
    public void testEqualsBasic() {
        Deque61B<String> d1 = new ArrayDeque61B<>();
        Deque61B<String> d2 = new ArrayDeque61B<>();
        Deque61B<String> d3 = new ArrayDeque61B<>();

        // Add elements to both
        d1.addLast("a");
        d1.addLast("b");
        d2.addLast("a");
        d2.addLast("b");
        d3.addFirst("r");

        // Compare
        assertThat(d1.equals(d2)).isTrue();
        assertThat(d3.equals(d1)).isFalse();
    }

@Test
    public void testToStringWithIntegers() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(3);
        deque.addLast(3);
        deque.addLast(2);

        String expected = "[3, 3, 2]";
        assertThat(deque.toString()).isEqualTo(expected);
    }

    @Test
    public void testMaxWithComparable() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(3);
        deque.addLast(7);
        deque.addLast(5);

        Integer result = Maximizer61B.<Integer>max(deque);
        assertThat(result).isEqualTo(7);
    }

    @Test
    public void testMaxWithComparator() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("hi");
        deque.addLast("hello");
        deque.addLast("hey");

        Comparator<String> byLength = (a, b) -> a.length() - b.length();
        String result = Maximizer61B.max(deque, byLength);
        assertThat(result).isEqualTo("hello");
    }


}
