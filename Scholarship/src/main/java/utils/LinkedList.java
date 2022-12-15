package utils;

import java.util.Iterator;
/*
Implement all interface methods based on a linked list.
Do not use the java class LinkedList <E>, try to write all the logic yourself.
Additional methods and variables can be developed as needed.
*/
public class LinkedList<T> implements List<T> {    
    public class Node
    {
        public T Data;
        public Node Next;
        public Node() {};
        public Node(T data, Node address)
        {
            this.Data = data;
            this.Next = address;
        }
    }
    
    public Node head;
    
    public LinkedList()
    {
        this.head = null;
    }
    
    public int size()
    {
        int counter = 0;
        Node last = head;
        while(last != null)
        {
            counter++;
            last = last.Next;
        }
        return counter;
    }
    
    @Override
    public void add(T item) 
    {
        Node n = new Node();
        n.Data = item; 
        if(head == null)
        {
            head = n;   
        }
        else
        {
            Node last = head;
            while(last.Next != null)
            {
                last = last.Next;
            }
            last.Next = n;
        }
    }

    @Override
    public T get(int index) 
    {
        Node last = head;
        for(int i = 0; i < index; i++)
        {
            last = last.Next;
        }
        if(last != null)
            return last.Data;
        else
            return null;
    }

    @Override
    public boolean remove(T item) 
    {
        Node last = head;
        if(last.Data.equals(item))
        {
            head = head.Next;
            return true;
        }
        else
        {
            while(last.Next != null)
            {
                if(last.Next.Data.equals(item))
                {
                    last.Next = last.Next.Next;
                    return true;
                }
                 last = last.Next;
            }
            return false;
        }
    }
    
    @Override
    public Iterator<T> iterator() 
    {
        return new Iterator<T>() 
        {
            Node last = head;
            
            @Override
            public boolean hasNext() 
            {
                return last != null;
            }

            @Override
            public T next() 
            {
                if(hasNext())
                {
                    T data = last.Data;
                    last = last.Next;
                    return data;
                }
                return null;
            }
        };
    }
}
