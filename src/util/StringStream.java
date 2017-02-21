package util;

import java.util.StringTokenizer;

public class StringStream {
    private StringTokenizer st;

    public StringStream(String source) {
        String in = source;
        st = new StringTokenizer(in);

    }


    public String next() {//считать следующую последовательность символов
        if (st == null || !st.hasMoreTokens()) {
            return null;
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
