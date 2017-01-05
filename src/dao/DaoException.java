package dao;

/**
 * Created by Rebeka on 2015.04.12..
 */
public class DaoException extends Exception {

    public DaoException() {
    }

    public DaoException(String s) {
        super(s);
    }

    public DaoException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DaoException(Throwable throwable) {
        super(throwable);
    }

    public DaoException(String s, Throwable throwable, boolean b, boolean b2) {
        super(s, throwable, b, b2);
    }
}
