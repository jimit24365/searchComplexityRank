package com.jimit24365.searchcomplexity.models;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jimit24365 on 5/4/17.
 */

public class SearchItem {

    private static final double LEVEL_ONE_POINT = 0.25;
    private static final double LEVEL_TWO_POINT = 0.5;
    private static final double WORD_LENGTH_MORE_THAN_FOUR_CHAR_POINT = 0.5;
    private static final double LONG_WORD_POINT = 1;
    private static final double ABOVE_NORMAL_WORDS_POINT = 0.25;
    private static final double ABOVE_NORMAL_WORDS_THRESHOLD = 8;
    @SerializedName("title")
    String title;

    @SerializedName("link")
    String displayLink;

    @SerializedName("snippet")
    String description;

    ArrayList<String> level1Conjunction = new ArrayList<String>(Arrays.asList("for", "and", "nor", "but", "or", "yet", "so"));
    ArrayList<String> level2Conjunction = new ArrayList<String>(Arrays.asList("after", "although", "as", "because", "before", "even though", "if", "since", "though", "unless", "until", "when", "whenever", "whereas", "wherever", "while"));

    int level1Count;
    int level2Count;
    int complexityScore;

    int oneCharacterWord;

    int twoCharacterWord;

    int threeCharacterWord;

    int fourCharacterWord;

    int fiveCharacterWord;

    int longWord;

    int sentenceLength;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayLink() {
        return displayLink;
    }

    public void setDisplayLink(String displayLink) {
        this.displayLink = displayLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Formula :
     * <p>
     * aboveNormalWords = sentence length - words with two character length
     * ComplexityScore = (compound conjunction *0.25)+(complex conjunction * 0.5)+(word larger than 4 character * 0.5) + (words larger than 6 character * 1) + ((aboveNormalWords > 8) *0.25)
     *
     * @return
     */
    public long getComplexityScore() {
        description = description.toLowerCase();
        long currentScore = 0;
        if (!TextUtils.isEmpty(description)) {
            String[] words = description.split(" ");
            int aboveNormalWords = 0;
            sentenceLength = words.length;
            for (int i = 0; i < sentenceLength; i++) {
                String currentWord = words[i];
                int currentWordLength = currentWord.length();
                if (level1Conjunction.contains(currentWord)) {
                    level1Count++;
                }
                if (level2Conjunction.contains(currentWord)) {
                    level2Count++;
                }
                switch (currentWordLength) {
                    case 1:
                        oneCharacterWord++;
                        break;
                    case 2:
                        twoCharacterWord++;
                        break;
                    case 3:
                        threeCharacterWord++;
                        break;
                    case 4:
                        fourCharacterWord++;
                        break;
                    case 5:
                        fiveCharacterWord++;
                        break;
                    default:
                        longWord++;
                        break;
                }
            }
            aboveNormalWords = sentenceLength - twoCharacterWord;
            currentScore
                    += (level1Count * LEVEL_ONE_POINT) +
                    (level2Count * LEVEL_TWO_POINT) +
                    (fourCharacterWord * WORD_LENGTH_MORE_THAN_FOUR_CHAR_POINT) +
                    (longWord * LONG_WORD_POINT);
            currentScore += aboveNormalWords > ABOVE_NORMAL_WORDS_THRESHOLD ? (aboveNormalWords * ABOVE_NORMAL_WORDS_POINT) : 0;
        }
        return currentScore;
    }

}
