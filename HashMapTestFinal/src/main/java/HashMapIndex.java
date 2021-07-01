
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashMapIndex {
    private static final double load_factor = 0.7;
    private static final int capacity = 10;
    private  int initialCapacity;
    private  int size = 0;
    public  Entry[] table;

    public  HashMapIndex(){
        initialCapacity = capacity;
        table  = new Entry[initialCapacity];
    }

    private  int Position(Integer key){
        return  key == null ? 0: Math.abs(key.hashCode() % table.length);
    }
    private  void  PutOnEmptySpace(int position,Entry entry){
        size++;
        table[position] = entry;
    }

    public  void  put(int key,long value){
        resize();
        Entry newEntry = new Entry(Integer.valueOf(key),Long.valueOf(value));
        int position = Position(newEntry.getKey());
        Entry temp = table[position];
        if(temp == null){
            PutOnEmptySpace(position,newEntry);
            return;
        }
        while (position < table.length){
            if(position == table.length-1){
                position = 0;
            }
            temp = table[position];
            if(temp==null){
                PutOnEmptySpace(position,new Entry(key,value));
                return;
            }
            if(newEntry.getKey()==(temp.getKey())){
                temp.setValue(value);
                return;
            }
            position++;
        }
    }
    public  void resize() {
        if(size == initialCapacity * load_factor){
            size = 0;
            Entry[] tempTable = table;
            initialCapacity *=2;
            if(initialCapacity >(Integer.MAX_VALUE-1)) throw  new RuntimeException("Map cannot be resized, it has maximum capacity");
            table = new Entry[table.length*2];
            for (int i = 0;i<tempTable.length;i++)
            {
                if(tempTable[i]!=null){
                    put(tempTable[i].getKey(),tempTable[i].getValue());
                }
            }
        }
    }
    public  long getValue(int key){
        int position = Position(Integer.valueOf(key));
        Entry temp = table[position];
        while (position< table.length && temp!=null){
            if(position == table.length-1){
                position = 0;
            }
            if(temp.getKey()==key){
                return  temp.getValue();
            }
            temp = table[position];
            position++;
        }
        throw  new NoSuchElementException("No value with such a key");
    }

        public  int getSize(){return  size;}





    private static class  Entry {
        private Integer key;
        private Long value;


        public Entry(Integer key, Long value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object object) {
            if (object == this) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Entry entry = (Entry) object;
            return key.intValue() == entry.key.intValue() && value.intValue() == entry.value.intValue();

        }

        public  int getKey(){return  key.intValue();}
        public long getValue(){return value.longValue();}
        public  void setValue(long value ){
            this.value = Long.valueOf(value);
        }
        @Override
        public  int hashCode(){return  Math.abs(Objects.hash(key) ^ Objects.hash(value));}

    }
}



