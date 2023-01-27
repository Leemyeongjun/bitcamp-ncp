package bitcamp.util;

// List 인터페이스를 구현하는 시점에서 다룰 데이터의 타입을 지정한다.
// => 예) List<Board>
public interface List<E> extends Iterable<E> {
  abstract void add(E value);
  abstract Object[] toArray();
  abstract E get(int index);
  abstract E set(int index, E value);
  abstract boolean remove(E value);
  abstract int indexOf(E value);
  int size();
}
