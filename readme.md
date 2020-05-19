# Java 

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
    
### Reading/Writing File (Character Stream)
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
