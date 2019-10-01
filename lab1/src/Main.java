import file.DirectoryHandler;

import java.io.File;
import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) {
        try {
            DirectoryHandler directoryHandler = new DirectoryHandler("E:\\Documents\\javaTest\\file1", "if", "fi");
            FutureTask<File> result = new FutureTask<>(directoryHandler);
            new Thread(result).start();
            System.out.println("path "+result.get().getAbsolutePath());
        }
        catch (Exception e){ e.printStackTrace();}

    }
}
