package com.jungsub.rc_management;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/*
참교 자료
https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
https://docs.google.com/presentation/d/1OaEa_D1EHtrqE_eN890IXA7VzCXD_-hfTTZ_8tR2N0I/edit#slide=id.p53
https://commons.apache.org/proper/commons-io/javadocs/api-2.5/org/apache/commons/io/FileUtils.html
 */

public class FileManager {
    public static void save(String filename, Student[] data) throws IOException {
        // 기존 오브젝트(Student 배열) 을 오브젝트 아웃풋을 하기 위한 스트림으로 만드는 작업.
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(data);
        out.flush();
        byte[] yourBytes = bos.toByteArray();
        bos.close(); // Can cause error.

        // 만든 바이트 바이너리를 저장한다.
        File file = new File(filename);
        FileUtils.writeByteArrayToFile(file, yourBytes);
    }

    public static ArrayList<Student> load(String filename) throws IOException, ClassNotFoundException, FileNotFoundException {
        File file = new File(filename);
        if(!file.exists()) {
            throw new FileNotFoundException();
        }

        // 파일 불러오기
        byte[] yourBytes = FileUtils.readFileToByteArray(file);

        ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);
        ObjectInput in = null;
        in = new ObjectInputStream(bis);
        Student[] o = (Student[]) in.readObject();
//        if (in != null) {
//            in.close();
//        }
        return new ArrayList<Student>(Arrays.asList(o));
    }
}
