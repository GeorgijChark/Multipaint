package util;

import java.io.*;
import java.util.StringTokenizer;

public class Input {  //ввод

    private BufferedReader in;
    private StringTokenizer st;

    public Input(String name) {
        try {
            in = new BufferedReader(new FileReader(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Wrong input file");
        }
    }

    public Input(InputStream inputStream) {
        in = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String nextLine() { //считать строку
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String next() {//считать следующую последовательность символов
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    public int nextInt() { //считать следующее целое число
        return Integer.parseInt(next());
    }

    public double nextDouble() { //считать следующее вещественное число
        return Double.parseDouble(next());
    }
}