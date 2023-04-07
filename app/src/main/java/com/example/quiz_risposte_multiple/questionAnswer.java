package com.example.quiz_risposte_multiple;

public class questionAnswer {
    public static String question[] = {
            "Who owns Android?",
            "How old are you?",
            "Are you fine?"
    };

    public static String answers[][] = {
        {"Google", "Intel", "Microsoft", "Xilinx"},
        {"10", "21", "20", "38"},
        {"Thanks", "Yes", "No", "Prolly yes"}
    };

    public static String correct[] = {
        "Google",
            "21",
            "Yes"
    };
    public static boolean answered[] = {
            false, false, false
    };
}
