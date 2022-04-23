package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingValidator {
    public static List<String> errorMessages = new ArrayList<>();

    public static String checkAndGetSongTitle(MusicDslParser.SongContext ctx) {
        Pattern p = Pattern.compile("With title \"[a-zA-Z0-9_]+\"");
        Matcher m = p.matcher(ctx.title().getText());
        if (m.matches()) {
            return ctx.title().QUOTED_NAME().getText();
        } else {
            errorMessages.add("Ensure Song Title is wrapped in quotes and has no spaces");
            return "";
        }
    }

    public static int checkAndGetTempo(MusicDslParser.SongContext ctx) {
        String tempo = ctx.tempo().getText();
        if (tempo.length() > 11 && tempo.startsWith("With tempo ") && Character.isDigit(tempo.charAt(11))) {
            Integer tempoNum = Integer.parseInt(ctx.tempo().NUMBER().getText());
            if (tempoNum >= 60 && tempoNum <= 250) {
                return tempoNum;
            } else {
                errorMessages.add("Ensure tempo is a number between 60 and 250");
                return 120; // default
            }
        } else {
            errorMessages.add("Ensure tempo is a number");
            return 120; // default
        }
    }

    public static void checkRhythm(MusicDslParser.RhythmContext ctx) {
        Pattern pattern = Pattern.compile("Create rhythm \"[a-zA-Z0-9_]+\"~");
        Matcher matcher = pattern.matcher(ctx.getText());
        boolean matchFound = matcher.find();
        if (!matchFound) {
            errorMessages.add("Ensure all rhythms are declared correctly");
        }
    }
}
