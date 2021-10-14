 import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class BasicDoubleLinkedList<T> implements java.lang.Iterable<T> {
protected class Node {
protected T item;
protected Node next, previous;

protected Node(T item, Node next, Node previous) {
this.item = item;
this.next = next;
this.previous = previous;
}
}

protected int size;
protected Node header, tail;

public BasicDoubleLinkedList() {

size = 0;
header = tail = null;

}
public BasicDoubleLinkedList<T> addToEnd(T elem) {

Node tmp = new Node(elem, null, tail);
if (tail != null) {
tail.next = tmp;
}
tail = tmp;
if (header == null) {
header = tmp;
}
size++;
return this;
}
public BasicDoubleLinkedList<T> addToFront(T elem) {

Node tmp = new Node(elem, header, null);
if (header != null) {
header.previous = tmp;
}
header = tmp;
if (tail == null) {
tail = tmp;
}
size++;
return this;
}

public int getSize() {
return size;
}
public T getLast() {
return tail.item;
}
public T getFirst() {
return header.item;
}
public BasicDoubleLinkedList<T> remove(T elem, java.util.Comparator<T> comparator) {
Node prev = null, curr = header;
while (curr != null) {
if (comparator.compare(curr.item, elem) == 0) {
if (curr == header) {
header = header.next;
curr = header;
} else if (curr == tail) {
curr = null;
tail = prev;
prev.next = null;
} else {
prev.next = curr.next;
curr = curr.next;
}
size--;
} else {
prev = curr;
curr = curr.next;
}
}
return this;
}

public T retrieveFirstElement() {
if (size == 0) {
throw new NoSuchElementException("Linked list is empty");
}
Node tmp = header;
header = header.next;
header.previous = null;
size--;
return tmp.item;
}

public T retrieveLastElement() {

if (header == null) {
throw new NoSuchElementException("Linked list is empty");

}
Node currentNode = header;
Node previousNode = null;

while (currentNode != null) {
if (currentNode.equals(tail)) {
tail = previousNode;
break;
}
previousNode = currentNode;
currentNode = currentNode.next;
}
size--;
return currentNode.item;
}

public ArrayList<T> toArrayList() {
ArrayList<T> temp = new ArrayList<T>();
ListIterator<T> iterator1 = new iter();

while (iterator1.hasNext()) {
temp.add(iterator1.next());
}
return temp;
}
public class iter implements ListIterator<T> {
private Node current;
private Node last;
public iter()
{
current = header;
last = null;
}
public T next()
{
if(current != null)
{
T returnData = current.item;
last = current;
current = current.next;
if(current != null) { current.previous = last;}
return returnData;
}
else
throw new NoSuchElementException();
}
public boolean hasNext()
{
return current!=null;
}
public T previous()
{
if(last != null)
{
current = last;
last= current.previous;
T returnData = current.item;
return returnData;
}
else
throw new NoSuchElementException();
}
public boolean hasPrevious()
{
return last!=null;
}
public void set(T elem) {
current.item = elem;
}
@Override
public int nextIndex() {
throw new UnsupportedOperationException();
}

@Override
public int previousIndex() {
throw new UnsupportedOperationException();
}

@Override
public void remove() {
throw new UnsupportedOperationException();
}

@Override
public void add(T e) {
throw new UnsupportedOperationException();

}
}

public ListIterator<T> iterator() {
return new iter();
}

}