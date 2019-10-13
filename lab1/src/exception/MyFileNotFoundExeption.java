package exception;

import java.io.IOException;

public class MyFileNotFoundExeption extends IOException {
    private String message = "No files in this dir";

    public void method() {
        System.out.println(message);
    }

}
