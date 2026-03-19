import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BEnhancementTest {
    @Test
    @DisplayName("IteratorTest")
    public void IteratorTest() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>(8);
        ArrayDeque61B<String> deque2 = new ArrayDeque61B<>(8);
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        for (String i : deque){
            deque2.addLast(i);
        }
        assertThat(deque2).containsExactly("a", "b", "c").inOrder();
    }

    @Test
    @DisplayName("equalTest")
    public void equalTest() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>(8);
        ArrayDeque61B<String> deque2 = new ArrayDeque61B<>(8);
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        deque2.addLast("a");
        deque2.addLast("b");
        deque2.addLast("c");
        assertThat(deque.equals(deque2)).isTrue();
        assertThat(deque2.equals(deque)).isTrue();
        assertThat(deque2).isEqualTo(deque);
    }

    @Test
    @DisplayName("toStringTest")
    public void toStringTest() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>(8);
        assertThat(deque.toString()).isEqualTo("[]");
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        assertThat(deque.toString()).isEqualTo("[a, b, c]");

        deque.removeLast();
        assertThat(deque.toString()).isEqualTo("[a, b]");
        deque.removeFirst();
        assertThat(deque.toString()).isEqualTo("[b]");
        deque.removeLast();
        assertThat(deque.toString()).isEqualTo("[]");

    }
}
