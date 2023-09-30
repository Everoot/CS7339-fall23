package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final int[] PC1 = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    };
    private static final int[] PC2 = {
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32,
    };

    // Initial Permutation
    private static final int[] IP = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };


    private static final int[] expand = {
            32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1,
    };

    private static final int[][][] box = {
            {
                    {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13},
            },
            {
                    {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9},
            },
            {
                    {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12},
            },
            {
                    {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14},
            },
            {
                    {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3},

            },
            {
                    {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13},
            },
            {
                    {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12},
            },
            {
                    {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11},
            }


    };

    private static final int[] permutation = {
            16, 7, 20, 21, 29, 12, 28, 17,
            1, 15, 23, 26, 5, 18, 31, 10,
            2, 8, 24, 14, 32, 27, 3, 9,
            19, 13, 30, 6, 22, 11, 4, 25,
    };

    private static final int[] rotation = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2};

    public static void main(String[] args) {
        String filePath = "output.txt";
        PrintStream originalOut = System.out;
        try {
            PrintStream fileOut = new PrintStream(new FileOutputStream(filePath, true));
            System.setOut(new PrintStream(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    originalOut.write(b);
                    fileOut.write(b);
                }
            }));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        int[] initial = new int[]{
                0, 0, 0, 0, 0, 0, 0, 1,
                0, 0, 1, 0, 0, 0, 1, 1,
                0, 1, 0, 0, 0, 1, 0, 1,
                0, 1, 1, 0, 0, 1, 1, 1,
                1, 0, 0, 0, 1, 0, 0, 1,
                1, 0, 1, 0, 1, 0, 1, 1,
                1, 1, 0, 0, 1, 1, 0, 1,
                1, 1, 1, 0, 1, 1, 1, 1
        };
        System.out.println("Problem: 1 ");

        int[] Solution1 = solution1(initial);
        System.out.println("Solution1a:" + Arrays.toString(Solution1));
        //[1, 1, 1, 1, 0, 0, 0,
        // 0, 1, 1, 0, 0, 1, 1,
        // 0, 0, 1, 0, 1, 0, 1,
        // 0, 1, 0, 0, 0, 0, 0,
        // 1, 0, 1, 0, 1, 0, 1,
        // 0, 1, 1, 0, 0, 1, 1,
        // 0, 0, 1, 1, 1, 1, 0,
        // 0, 0, 0, 0, 0, 0, 0]

        System.out.println("=====================================");
        System.out.println("Solution2b:");
        int[] solution2 = solution2(Solution1);
        System.out.println(Arrays.toString(solution2));

        System.out.println("=====================================");
        System.out.println("Solution3c:");
        int[] solution3 = solution3(solution2);
        System.out.println(Arrays.toString(solution3));
        //[0, 0, 0, 0, 1, 0,
        // 1, 1, 0, 0, 0, 0,
        // 0, 0, 1, 0, 0, 1,
        // 1, 0, 0, 1, 1, 1,
        // 1, 0, 0, 1, 1, 0,
        // 1, 1, 0, 1, 0, 0,
        // 1, 0, 0, 1, 1, 0,
        // 1, 0, 0, 1, 0, 1]

        System.out.println("=====================================");
        System.out.println("=====================================");
        System.out.println("Problem 2:");
        System.out.println("Solution4a:");
        String input = "MESSAGES";
        int[] solution4 = solution4(input);
        System.out.println(Arrays.toString(solution4));


        System.out.println("=====================================");
        System.out.println("Solution5b:");
        System.out.println(solution4.length);
        List<int[]> solution5 = solution5(solution4);
        //[1, 1, 1, 1, 1, 1, 1, 1,
        // 1, 0, 0, 0, 1, 1, 0, 0,
        // 0, 1, 1, 0, 0, 0, 1, 1,
        // 1, 1, 1, 1, 1, 1, 1, 1,
        // 0, 0, 0, 0, 0, 0, 0, 0,
        // 0, 0, 0, 0, 0, 0, 0, 0,
        // 0, 0, 0, 0, 0, 0, 0, 1,
        // 1, 0, 1, 0, 1, 1, 0, 0]

        System.out.println("=====================================");
        System.out.println("Solution6 c:");
        int[] solution6 = solution6(solution5.get(1));
        System.out.println(Arrays.toString(solution6));

        System.out.println("=====================================");
        System.out.println("Solution7 d:");
        System.out.println(Arrays.toString(solution6));
        System.out.println(Arrays.toString(solution3));
        System.out.println("XOR");
        int[] solution7 = solution7(solution6, solution3);
        System.out.println(Arrays.toString(solution7));
        //[0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1]

        System.out.println("=====================================");
        System.out.println("Solution8 e:");
        int[] solution8 = solution8(solution7);


        System.out.println("=====================================");
        System.out.println("Solution9 f:");
        int[] solution9 = solution9(solution8);
        System.out.println(Arrays.toString(solution9));


        System.out.println("=====================================");
        System.out.println("Solution10 g:");
        int[] solution10 = solution10(solution9);
        System.out.println(Arrays.toString(solution10));
        // [0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0,
        // 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0

        System.out.println("=====================================");
        // Calculate R1 = P(B) ⊕ L0.
        System.out.println("Solution11 h:");
        int[] solution11 = solution11(solution10, solution5.get(0));

    }

    public static void writeToBoth(String str, PrintWriter writer) {
        System.out.println(str);
        writer.println(str);
    }

    // Calculate R1 = P(B) ⊕ L0.
    private static int[] solution11(int[] array, int[] L){
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++){
            // 异或
            result[i] = array[i]^L[i];
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    // Apply the permutation to get P(B).
    private static int[] solution10 (int[] array){
        int[] result = new int[IP.length];
        result = pick(array, permutation);
        return result;
    }

    // Concatenate the results of e) to get a 32-bit result B.
    private static int[] solution9 (int[] array){
        int[] result = new int[array.length*4];
        for (int i = 0; i < array.length; i++){
            int[] temp = new int[4];
            int num = array[i];
            int j = 0;
            while (num > 0){
                temp[j] = num % 2;
                num = num / 2;
                j++;
            }
            for (int k = 0; k < temp.length; k++){
                result[i*4+k] = temp[temp.length-1-k];
            }
        }
        return result;
    }

    // Group the 48-bit result A into sets of 6 bits and evaluate the corresponding S-box substitutions
    private static int[] solution8 (int[] array){
        System.out.println(Arrays.toString(array));
        int[] result2 = new int[8];
        List<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < array.length; i++){
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < 6; j++){
                temp.add(array[i+j]);
            }
            result.add(temp);
            i = i + 5;
        }
        System.out.println("result: " + result);
        for (int i = 0; i < result.size();i++){
            int row = result.get(i).get(0)*2 + result.get(i).get(5);
            // row is binary change to decimal
            int col = result.get(i).get(1)*8 + result.get(i).get(2)*4 + result.get(i).get(3)*2 + result.get(i).get(4);
            // col is binary change to decimal
            System.out.println("row: " + row + " col: " + col);
            int num = box[i][row][col];
            System.out.println("num: " + num + " binary: " + Integer.toBinaryString(num));
            result2[i] = num;
        }
        return result2;
    }


    private static int[] solution7 (int[] array, int[] K){
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++){
            // 异或
            result[i] = array[i]^K[i];
        }
        return result;

    }

    private static int[] solution6 (int[] array){
        int[] result = new int[expand.length];
        result = pick(array, expand);
        return result;
    }

    private static List<int[]> solution5(int[] solution4){
        int[] result = new int[IP.length];
        result = pick(solution4, IP);
        System.out.println(Arrays.toString(result));
        System.out.println("Divided and shift");
        List<int[]> newresult = divided(result);
        return newresult;
    }
    private static int[] solution4(String input){
        // Covert the plaintext into binary
        int[] result = new int[input.length()*8];
        for (int i = 0; i < input.length(); i++){
            int[] temp = new int[8];
            int num = (int)input.charAt(i);
            System.out.println(input.charAt(i) + " Decimal: " + num + " = Hex: " + Integer.toHexString(num) + " = Binary: " + Integer.toBinaryString(num));
            int j = 0;
            while (num > 0){
                temp[j] = num % 2;
                num = num / 2;
                j++;
            }
            for (int k = 0; k < temp.length; k++){
                result[i*8+k] = temp[temp.length-1-k];
            }
        }
        return result;
    }

    private static int[] solution3(int[] solution2){
        int[] result = new int[PC2.length];
        result = pick(solution2, PC2);
        System.out.println(Arrays.toString(result));
        System.out.println("Divided and shift");
        return DividedAndShift(result);
    }

    private static int[] solution2(int[] solution1){
        return DividedAndShift(solution1);
    }

    private static int[] solution1(int[] initial){
        return pick(initial, PC1);
    }

    private static List<int[]> divided(int[] array){
        int[] LK = new int[array.length/2];
        for (int i = 0; i < array.length/2; i++){
            LK[i] = array[i];
        }
        System.out.println("LKorigin:" + Arrays.toString(LK));

        int[] RK = new int[array.length/2];
        int i = 0;
        for (int j = array.length/2; j < array.length; j++){
            RK[i] = array[j];
            i++;
        }
        System.out.println("RKorigin:" + Arrays.toString(RK));

        List<int[]> result = new ArrayList<>();
        result.add(LK);
        result.add(RK);
        return result;
    }

    private static int[] DividedAndShift(int[] array){
        int[] LK = new int[array.length/2];
        for (int i = 0; i < array.length/2; i++){
            LK[i] = array[i];
        }
        System.out.println("LKorigin:" + Arrays.toString(LK));
        getLeftShift(LK, rotation[0]);
        System.out.println("LK shift:" + Arrays.toString(LK));

        int[] RK = new int[array.length/2];
        int i = 0;
        for (int j = array.length/2; j < array.length; j++){
            RK[i] = array[j];
            i++;
        }
        System.out.println("RKorigin:" + Arrays.toString(RK));
        getLeftShift(RK, rotation[0]);
        System.out.println("RK shift:" + Arrays.toString(RK));

        int[] result = new int[RK.length+LK.length];
        for (int k = 0; k < result.length; k++){
            if (k < LK.length){
                result[k] = LK[k];
            }else{
                result[k] = RK[k-LK.length];
            }

        }
        return result;
    }

    private static void getLeftShift(int[] array, int shift) {
        int[] rem = new int[shift];
        for (int i = 0; i < shift; i++) {
            rem[i] = array[i];
        }

        for (int i = 0; i < array.length - shift; i++) {
            array[i] = array[i + shift];
        }

        for (int j = 0; j < shift; j++) {
            array[array.length - shift + j] = rem[j];
        }
    }

    private static int[] pick(int[] array, int[] pc){
        int[] result = new int[pc.length];
        for (int i = 0; i < pc.length; i++) {
            result[i] = array[pc[i]-1];
        }
        return result;
    }

}