import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {
    @Test
    @DisplayName("Get First Test")
    void getFirstTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>(8);
        assertThat(deque.getFirst()).isEqualTo(null);
        deque.addFirst(2);
        assertThat(deque.getFirst()).isEqualTo(2);
        deque.addFirst(1);
        assertThat(deque.getFirst()).isEqualTo(1);
        deque.addLast(3);
        assertThat(deque.getFirst()).isEqualTo(1);
    }

    @Test
    @DisplayName("Get Last Test")
    void getLastTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>(8);
        assertThat(deque.getLast()).isEqualTo(null);
        deque.addLast(2);
        assertThat(deque.getLast()).isEqualTo(2);
        deque.addLast(3);
        assertThat(deque.getLast()).isEqualTo(3);
        deque.addFirst(1);
        assertThat(deque.getLast()).isEqualTo(3);
    }

    @Test
    @DisplayName("Get Test")
    void getTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>(8);
        assertThat(deque.get(0)).isEqualTo(null);
        deque.addFirst(2);
        assertThat(deque.get(0)).isEqualTo(2);
        deque.addFirst(1);
        assertThat(deque.get(0)).isEqualTo(1);
        deque.addLast(3);
        assertThat(deque.get(2)).isEqualTo(3);

        assertThat(deque.get(23513515)).isEqualTo(null);
        assertThat(deque.get(-23513515)).isEqualTo(null);
    }

    @Test
    @DisplayName("size Test")
    void sizeTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>(8);
        assertThat(deque.size()).isEqualTo(0);
        deque.addFirst(2);
        assertThat(deque.size()).isEqualTo(1);
        deque.addFirst(1);
        assertThat(deque.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("isEmpty Test")
    void isEmptyTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>(8);
        assertThat(deque.isEmpty()).isTrue();
        deque.addFirst(2);
        assertThat(deque.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("toList Test")
    void toListTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>(8);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);
        assertThat(deque.toList()).containsExactly(1, 2, 3).inOrder();
        deque.addFirst(0);
        assertThat(deque.toList()).containsExactly(0, 1, 2, 3).inOrder();
    }

    @Test
    @DisplayName("removeFirst Test")
    void removeFirstTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>(8);
        assertThat(deque.removeFirst()).isEqualTo(null);
        deque.addFirst(2);
        assertThat(deque.removeFirst()).isEqualTo(2);
        assertThat(deque.removeFirst()).isEqualTo(null);
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        assertThat(deque.removeFirst()).isEqualTo(1);
        assertThat(deque.toList()).containsExactly(2, 3).inOrder();

    }

    @Test
    @DisplayName("removeLast Test")
    void removeLastTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>(8);
        assertThat(deque.removeLast()).isEqualTo(null);
        deque.addFirst(2);
        assertThat(deque.removeLast()).isEqualTo(2);
        assertThat(deque.removeLast()).isEqualTo(null);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);
        assertThat(deque.removeLast()).isEqualTo(3);
        assertThat(deque.toList()).containsExactly(1, 2).inOrder();

    }

    @Test
    @DisplayName("sizeUp Test")
    void sizeUpTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>(8);
        for (int i = 9999; i >= 0; i--) {
            deque.addFirst(i);
        }
        assertThat(deque.size()).isEqualTo(10000);
        assertThat(deque.get(1999)).isEqualTo(1999);

        for (int i = 8000; i > 0; i--) {
            deque.removeLast();
        }
        assertThat(deque.size()).isEqualTo(2000);
        assertThat(deque.get(51)).isEqualTo(51);

        for (int i = 1996; i > 0; i--) {
            deque.removeFirst();
        }
        assertThat(deque.size()).isEqualTo(4);
        assertThat(deque.toList()).containsExactly( 1996, 1997, 1998, 1999).inOrder();

        for (int i = 4; i > 0; i--) {
            deque.removeLast();
        }
        assertThat(deque.size()).isEqualTo(0);

        for (int i = 0; i < 10000; i++) {
            deque.addLast(i);
        }
        assertThat(deque.size()).isEqualTo(10000);
        assertThat(deque.get(1999)).isEqualTo(1999);
    }
}
