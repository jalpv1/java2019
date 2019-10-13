package userinterface;

import file.DirectoryHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Controller {
    private DirectoryHandler directoryHandler;

    public void menu() {
        char c = '0';
        String dir;
        String word;
        String newWord;
        Scanner in = new Scanner(System.in);
        System.out.println("1");
        do         point:
                {

            Scanner scanner = new Scanner(System.in);
            c = scanner.next().charAt(0);

            switch (c) {
                case '1': {
                    System.out.println("word");
                    word = in.nextLine();
                    System.out.println("dir");
                    dir = in.nextLine();
                    if(!new File(dir).exists()){
                        System.out.println("not");
                       break point;
                    }
                    System.out.println("new word");
                    newWord = in.nextLine();
                    directoryHandler = new DirectoryHandler(dir, word,newWord);
                    ArrayList<Future<File>> resultFiles = new ArrayList<>();
                    try {
                        ExecutorService service = Executors.newCachedThreadPool();
                        //DirectoryHandler directoryHandler =
                          //      new DirectoryHandler("E:\\Documents\\javaTest\\file1", "if", "fi");
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
                    catch (InterruptedException n){ n.printStackTrace();}


                }

                break;


            case '2':{
                break;
            }}
        }
        while (c != 'q');
    }


}
