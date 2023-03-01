package springStudy.library.exception;

public class BookNotFoundException extends Exception{

    public BookNotFoundException(String s){
        System.out.println(s);
    }
}
