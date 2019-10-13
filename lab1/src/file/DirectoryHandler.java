package file;

import exception.MyFileNotFoundExeption;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class DirectoryHandler implements Callable<File> {
    private File directoryPath;
    private String word;
    private String newWord;

    public ArrayList<Future<File>> getResultFiles() {
        return resultFiles;
    }

    public void setResultFiles(ArrayList<Future<File>> resultFiles) {
        this.resultFiles = resultFiles;
    }

    private ArrayList<Future<File>> resultFiles;
    private ArrayList<File> myfiles;

    public ArrayList<File> getMyfiles() {
        return myfiles;
    }

    public void setMyfiles(ArrayList<File> myfiles) {
        this.myfiles = myfiles;
    }

    public File call() {
        ExecutorService service = Executors.newCachedThreadPool();

        try {

            // if I am is file
            // then search and replace and return new file
            // else
            // get list of files
            // create Handler for each
            // submit future
            // wait until funure finishes, .get()
            if (!directoryPath.isDirectory()) {
                searchAndReplace(directoryPath);
                myfiles.add(directoryPath);
                return directoryPath;
            } else {
                File[] files = converToList(directoryPath);
                for (File file : files) {
                  //  if (file.isDirectory()) {
                        DirectoryHandler directoryHandler = new DirectoryHandler(file.getAbsolutePath(), word, newWord);
                        Future<File> future = service.submit(directoryHandler);
                        resultFiles.add(future);
                        File f = future.get();
                        myfiles.add(f);
                        //   return file;

                   // } else {
                   //     searchAndReplace(file);
                 //       return file;
                   // }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directoryPath;
    }


    public void searchAndReplace(File file) {

        String str = " ";
        try (Scanner scanner = new Scanner(new FileInputStream(file))) {

            while (scanner.hasNext()) {
                String string = scanner.nextLine();
                if (string.contains(word)) {
                    String newStr = string.replace(word, newWord);


                    str = str + newStr + "\r\n";

                } else {

                    str = str + string + "\r\n";
                }
            }
            FileOutputStream fos = new FileOutputStream(file, false);
            fos.write(str.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();

        }


    }


    public static File[] converToList(File dir) {
        return dir.listFiles();
    }

    public DirectoryHandler(String directoryPath, String word, String newWord) {
        this.directoryPath = new File(directoryPath);
        this.word = word;
        this.newWord = newWord;
        resultFiles = new ArrayList<>();
        myfiles = new ArrayList<>();

    }
    public boolean CheckWord(String theWord, File theFile) {
        try{
        return (new Scanner(theFile).useDelimiter("\\Z").next()).contains(theWord);}
        catch (FileNotFoundException e){
            return false;
        }
    }

    public File getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(File directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getNewWord() {
        return newWord;
    }

    public void setNewWord(String newWord) {
        this.newWord = newWord;
    }


}
