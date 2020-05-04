package acceptance_tests.helper;

import java.util.ArrayList;
import java.util.List;

public class ExceptionHandler {
    private boolean expectException;
    private List<RuntimeException> exceptions = new ArrayList<RuntimeException>();

    public void expectException() {
        expectException = true;
    }

    public void add(RuntimeException e) {
        if (!expectException) {
            throw e;
        }
        exceptions.add(e);
    }

    public List<RuntimeException> getExceptions() {
        return exceptions;
    }

}
