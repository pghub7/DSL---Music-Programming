package ast;

import org.jfugue.pattern.Pattern;

import java.util.HashMap;
import java.util.List;
import java.lang.StringBuilder;

public class Sequence extends Node {
    private String startingRhythm;
    private int offsetBeats;
    private List<String> parallelRhythms;
    private List<String> followupRhythms;
    private int voiceCounter = -1;
    private HashMap<String, Pattern> rhythmMap;

    public Sequence(String startingRhythm, int beats, List<String> followupRhythms, List<String> parallelRhythms) {
        this.startingRhythm = removeTrailingSpaces(startingRhythm);
        this.offsetBeats = beats;
        this.followupRhythms = followupRhythms;
        this.parallelRhythms = parallelRhythms;

        printSequence();
    }


    // TODO fix lexer to remove trailing space
    private String removeTrailingSpaces(String s) {
        StringBuilder temp = new StringBuilder(s);
        for(int i = temp.length() - 1; i >= 0; i--) {
            if (temp.charAt(i) == ' ') {
                temp.deleteCharAt(i);
            } else {
                break;
            }
        }
        return temp.toString();
    }

    public void setVoiceCounter(int counter) {
        this.voiceCounter = counter;
    }

    public void setRhythmMap(HashMap<String, Pattern> m) {
        this.rhythmMap = m;
    }


    public int getVoiceCounter() {
        return this.voiceCounter;
    }

    public List<String> getParallelRhythms() { return this.parallelRhythms; }

    public List<String> getFollowupRhythms() { return this.followupRhythms; }

    public String getStartingRhythm() { return this.startingRhythm; }

    private void printSequence() {
        System.out.println("=============================");
        System.out.println("starting rhythm: " + startingRhythm);
        System.out.println("beat offset: " + offsetBeats);
        System.out.println("Number of parallel sequences: " + parallelRhythms.size());
        for(String s : parallelRhythms) {
            System.out.println(s);
        }

        System.out.println("Followup rhythms: ");
        for(String s : followupRhythms) {
            System.out.println(s);
        }
        System.out.println("=============================");
    }

    @Override
    public void evaluate(StringBuilder sb) {
        Pattern sequence = new Pattern();

        if (rhythmMap.get(this.startingRhythm) != null) {
            Pattern p = new Pattern();
            // add rests for the number of offsetBeats
            if (this.offsetBeats > 0) {
                p.add("RQ", this.offsetBeats);
            }
            p.add(rhythmMap.get(this.startingRhythm));
            this.voiceCounter++;
            p.setVoice(this.voiceCounter);
            sequence.add(p);
        }

        // adding parallel rhythms
        for(String rhythmName : this.parallelRhythms) {
            if(rhythmMap.containsKey(rhythmName)) {
                Pattern parallelPattern = new Pattern();
                if (this.offsetBeats > 0) {
                    parallelPattern.add("RQ", this.offsetBeats);
                }
                parallelPattern.add(rhythmMap.get(rhythmName));
                this.voiceCounter++;
                parallelPattern.setVoice(this.voiceCounter);
                sequence.add(parallelPattern);
            }
        }

        // adding followUp rhythms
        for (String rhythmName : this.followupRhythms) {
            if (rhythmMap.containsKey(rhythmName)) {
                sequence.add(rhythmMap.get(rhythmName));
            }
        }
        sb.append(sequence.toString());
    }

    public <C,T> T accept(C context, MusicDslVisitor<C,T> v) {
        return v.visit(context, this);
    }
}
