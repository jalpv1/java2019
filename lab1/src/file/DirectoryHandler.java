package file;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

public class DirectoryHandler implements Callable<File> {
    private File directoryPath;
    private String word;
    private String newWord;
    private ArrayList<Future<File>> resultFiles;

    public File call() {

        try {
            File[] files = converToList(directoryPath);
            for (File file : files) {
                if (file.isDirectory()) {
                    DirectoryHandler directoryHandler = new DirectoryHandler(file.getAbsolutePath(), word, newWord);
                    new Thread().start();
                    resultFiles.add(new FutureTask<File>(directoryHandler));
                    return file;

                } else {
                    searchAndReplace(file);

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new File("iii");
    }

    public void directoryHandlerMethod(File directoryPath) {
        try {
            File[] files = converToList(directoryPath);
            for (File file : files) {
                if (file.isDirectory()) {
                    directoryHandlerMethod(file);
                    new Thread().start();

                } else {
                    searchAndReplace(file);
                    ///&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&77
                    //resultFiles.add(new FutureTask<File>();
                }
            }


        } catch (Exception e) {
        }

    }

    public void searchAndReplace(File file) {

        String str = " ";
        try (Scanner scanner = new Scanner(new FileInputStream(file))) {

            while (scanner.hasNext()) {
                String string = scanner.nextLine();
                if (string.contains(word)) {
                    String newStr = string.replace(word, newWord);


                    str = str + newStr + "/n";
//String s1 = String.format("%s%n%s", "Ля-ля-ля ля-ля-ля", "Ля-ля-ля ля-ля-ля");

                } else {

                    str = str + string + "/n";
                }
            }
            FileOutputStream fos = new FileOutputStream(file, false);
            fos.write(str.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();

        }


    }

    public void searchAndReplace2(File file) {
        File f = new File("E:\\Documents\\javaTest\\file1\\result.txt");
        String str = " ";
        try (FileWriter fw = new FileWriter(file);
             FileReader fr = new FileReader(file)) {

            Scanner scanner = new Scanner(fr);

            while (scanner.hasNext()) {
                String string = scanner.nextLine();
                if (string.contains(word)) {
                    String newStr = string.replace(word, newWord);
                    fw.write(newStr);

                } else {
                    fw.write(string);
                }
            }
            fw.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void searchAndReplace1(File file) {
        File f = new File("E:\\Documents\\javaTest\\file1\\result.txt");
        String str = " ";
        try {
            FileReader fr = new FileReader(file); // поток чтения с консоли
            BufferedReader bufferedReader = new BufferedReader(fr);  // соединяем InputStreamReader с BufferedReader
            FileWriter fileReader = new FileWriter(file); // поток который подключается к текстовому файлу
            BufferedWriter bufferedWriter = new BufferedWriter(fileReader);


            while (bufferedReader.readLine() != null) {
                String bufString = bufferedReader.readLine();
                if (bufString.contains(word)) {
                    String newStr = bufString.replace(word, newWord);
                    bufferedWriter.write(bufString);

                } else {
                    bufferedWriter.write(bufString);
                }
            }
            bufferedReader.close(); // закрываем поток
            bufferedWriter.close();
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
