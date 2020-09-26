package org.ir.project.utils;

import java.util.List;

public class LanguageDetector {
    public boolean success;
    public List<Result> results;

    public static class Result{
        public String language_code;
        public String language_name;
        public double probability;
        public double percentage;
        public boolean reliable_result;
    }
}
