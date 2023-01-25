package bitcamp.util;

// 객체 목록을 다루는 기능을 규정한다.
// => List 인터페이스는 Iterable 규칙을 상속 받기 때문에
//    List를 구현하는 클래스는 Iterable 규칙도 함께 구현해야 한다.
public interface List extends Iterable {
  abstract void add(Object value);
  abstract Object[] toArray();
  abstract Object get(int index);
  abstract Object set(int index, Object value);
  abstract boolean remove(Object value);
  abstract int indexOf(Object value);
  int size();
}
