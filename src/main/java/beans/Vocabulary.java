package beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Vocabulary {
    private int id;
    private String shortName;
    private String description;
    private Set<Word> wordList = new HashSet<>();

}
