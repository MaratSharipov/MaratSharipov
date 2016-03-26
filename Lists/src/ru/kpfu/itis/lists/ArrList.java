package ru.kpfu.itis.lists;

import java.util.*;

/**
 * Created by Lenovo on 25.03.2016.
 */
public class ArrList<E> implements List {

    private int size=0;
    E[] array;
    private final Object[] DEFAULT_SIZE= new Object[10];



    public ArrList(){
        array= (E[]) DEFAULT_SIZE;
    }

    public ArrList(int size){
        array=(E[]) new Object[size];
    }

    @Override
    public boolean add(Object a){
        rangeCheck(size+1);
        array[size++]=(E) a;
        return true;
    }
    @Override
    public void add(int index, Object a){
        rangeCheck(size+1);
        System.arraycopy(array, index, array,index+1,size-index);
        array[index]=(E) a;
    }
    @Override
    public E get(int index){
        rangeCheck(size+1);
        return array[index];
    }


    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }


    @Override
    public E set(int index, Object element){
        return array[index]=(E) element;
    }
    @Override
    public int indexOf(Object element){
        if(element==null){
            for (int i = 0; i < size; i++) {
                if(array[i]==null)
                    return i;
            }
        }
        else
            for(int i=0;i<size;i++){
                if(array[i].equals(element)){
                    return i;
                }
            }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }
    @Override
    public boolean contains(Object element){
        return indexOf(element)>=0;
    }
    @Override
    public boolean containsAll(Collection c){
        boolean a=false;
        for(int i=0;i<size;i++){
            if(array[i].equals(c))
                a=true;

        }
        return a;
    }
    @Override
    public E remove(int index){
        for(int i=index+1;i<size;i++){
            array[i-1]=array[i];

        }
        size--;
        return array[index];
    }
    @Override
    public boolean remove(Object object){
        remove(indexOf(object));
        return true;
    }


    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {
        size=0;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection a){
        remove(a);
        return true;
    }
    @Override
    public boolean addAll(Collection c){
        rangeCheck(size+1);
        array[size++]= (E) c;

        return true;
    }

    @Override
    public Object[] toArray(){
        E[] arr=(E[]) new Object[size];
        for(int i=0;i<size;i++){
            arr[i]=array[i];
        }
        return arr;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    private void rangeCheck(int index){
        if(index>array.length){
            grow(array.length);
        }
    }

    private void grow(int size){
        int oldSize=size;
        int newSize=oldSize*2;
        array= Arrays.copyOf(array, newSize);
    }

    public java.util.Iterator iterator(){
        return (java.util.Iterator) new Iter();
    }


    private class Iter implements Iterator<E>{

        int cursor;
        int lastElem=-1;

        public boolean hasNext(){
            return cursor!=size;
        }

        public E next(){
            int i=cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrList.this.array;
            if (i >= array.length)
                throw new ConcurrentModificationException();
            cursor=i+1;
            return (E) array[lastElem=i];
        }




    }

}
