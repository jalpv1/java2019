import file.DirectoryHandler;
import userinterface.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
         /*ArrayList<Future<File>> resultFiles = new ArrayList<>();
        try {
            ExecutorService service = Executors.newCachedThreadPool();
            DirectoryHandler directoryHandler = new DirectoryHandler("E:\\Documents\\javaTest\\file1", "if", "fi");
           // FutureTask<File> result = new FutureTask<>(directoryHandler);
           // new Thread(result).start();

            Future<File> future =
                    service.submit(directoryHandler);
            resultFiles.add(future);
            service.shutdown();
            if(service.awaitTermination(10, TimeUnit.SECONDS)) {
                for (int i = 0; i < directoryHandler.getMyfiles().size(); i++) {
                    System.out.println("path " + directoryHandler.getMyfiles().get(i).getAbsolutePath());
                }
                System.out.println("Done");
            }
            else {
                System.out.println("Cannot finish in the given timeout");
                // print cannot finish in timeout 1 sec
            }
        }
        catch (Exception e){ e.printStackTrace();}*/
        Controller controller = new Controller();
        controller.menu();

    }
}
