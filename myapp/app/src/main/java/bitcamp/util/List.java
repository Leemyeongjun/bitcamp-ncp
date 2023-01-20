package bitcamp.util;

// 객체 목록을 다루는 기능을 규정한다.
public interface List {
  abstract void add(Object value);
  abstract Object[] toArray();
  abstract Object get(int index);
  abstract Object set(int index, Object value);
  abstract boolean remove(Object value);
  abstract int indexOf(Object value);
  int size();
}
