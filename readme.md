# Java 
# ------------------------------I/O---------------------------------
### Reading/Writing File (Byte Stream)
The most common method to read file (without using nio package) using io package is BufferedInputStream.
As invoking disk for each byte is so expensive, so we create a buffer which is nothing but an array of byte. 
So this buffer is of various size. you can choose 4000, 8000, or may be bigger.
- First Of All, create FileInputStream
- Now Create BufferedInputStream (decorate FileInputStream using BufferedInputStream)
- ```
    //File
    File inputFile = new File("path/to/file");
    File outputFile = new File("path/to/file");  
  
    //inputstream using ARM (automatic resource management)
    try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
    {
        byte[] buffer = new byte[8000];
        int numberOfBytesRead;
        while((numberOfBytesRead = inputStream.read(buffer) != 1))
        {
            out.write(buffer, 0, numberOfBytesRead);
        }
    } catch(IOException exp) {
        //print statement or else
    }
  ```  
- For writing, stead of writing one byte, we write a whole buffer. Using both of methods:
    - write(buffer)
    - write(buffer, offset, length)
    
### Reading (Character Stream)
- ```
    try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), 'UTF-8')))
    {
        String line;
        while((line = br.readLine()) != null)
        {
            //do with line
        }
    }
  ```

### Writing (Character Stream)
- ```
    try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), 'UTF-8')))
    {
        bw.write(string)
    }
  ```

# ------------------------------Data Structure---------------------------------
### Iterable 
- Iterable is an interface which has method iterator which return Iterator.
- A Class which implement Iterable interface, it gains the ability to iterate  

# -----------------------------Generics----------------------------------------
### Type Erasure
- Its Compile time concept 
- When Generic compile, then all its parameterized types removed in byte code and replaced with Object class (or bounded class)

### Type Parameter
- Class Level Type Parameter
    - ```public class Mamals<T extends Human> { } ```
- Method Level Type Parameter
    -   ```public <T extends Shareable> T share(T shareable) { }```
    
### Wild Card
- Upper Bound WildCard
    - For Subtypes
    - ```<? extends T>```
    - It is used to display data (or Getting Data)
- Lower Bound WildCard
    - For SuperTypes 
    - ```<? super T>```
    - It is used to putting data (or updating data)
- Unbounded wildcards 
    - ```<?>```
    
# -----------------------------Nested Classes----------------------------------------
### Types
- Static Nested Class
- Instance Nested Class
### Properties
- Can access any members including private
- Serve as helper
- Represents components

# Multi-threading
- ### Locking
    - Every Object has one lock
    - When we add ```synchronzied block or method``` 
        - then only one thread can access at a time
        - no other thread can access the other synchronized method when object is locked
        - other threads can access non synchronized blocks or methods when object is locked
- ### Volatile Variable
    - Volatile variable ensure the memory visibility. It stores in the main memory not in the cache or registers.
    - ```syncrhonization```  ensure both memory visibility and atomicity
- ### Atomic variables 
    - Atomic variables are not blocking while ```synchronize``` keyword is blocking. 
    - Use Atomic variables where we want to update a single value cause atomic variables are not blocking. 
        - Blocking is costly cause of thread suspension and then resumption
        
- ### Wait and Notify
    - Wait and Notify are the methods of Object Class
    - Each Object has a lock
    - When we call wait, then its mean we are locking the object
    - When we call notify then we are releasing the lock
    - Demo is in ```src/main/java/com/ubaid/concurrency/waitNotifyDemo/App.java```