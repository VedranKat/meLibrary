package springStudy.library.exception;

public class CannotDeleteForeignKeyException extends Throwable {
    public CannotDeleteForeignKeyException(String s) {
        System.out.println(s);
    }
}
