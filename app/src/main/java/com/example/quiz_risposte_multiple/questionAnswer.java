package com.example.quiz_risposte_multiple;

public class questionAnswer {
    public static String[] question = {
            "Who owns Android?",
            "What's this letter: A",
            "What's this letter: B",
            "What's this letter: C",
            "What's this letter: D"
    };

    public static String[][] answers = {
        {"Google", "Intel", "Microsoft", "Xilinx"},
        {"A", "B", "C", "D"},
        {"A", "B", "C", "D"},
        {"A", "B", "C", "D"},
        {"A", "B", "C", "D"}
    };

    public static String[] correct = {
        "Google",
            "A",
            "B",
            "C",
            "D"
    };
    public static int[] correct_idx = {
            0,
            0,
            1,
            2,
            3
    };
}
