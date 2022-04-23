package ast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MusicDslChecker implements MusicDslVisitor<HashSet<String>, String> {

    @Override
    public String visit(HashSet<String> uniqueRhythms, Song s) {
        StringBuilder sb = new StringBuilder();

        // check if each rhythm name is unique and if instrument is valid
        List<Rhythm> rhythms = s.getRhythms();
        for(Rhythm rhythmNode : rhythms) {
            sb.append(rhythmNode.accept(uniqueRhythms, this));
        }

        // early exit if errors already found
        if (sb.length() > 0) {
            return sb.toString();
        }

        // check if all sequences are referencing valid rhythms
        List<Sequence> sequences = s.getSequences();
        for(Sequence seqNode : sequences) {
            sb.append(seqNode.accept(uniqueRhythms, this));
        }

        return sb.toString();
    }

    @Override
    public String visit(HashSet<String> uniqueRhythms, Sequence s) {
        String err = "";

        if (!uniqueRhythms.contains(s.getStartingRhythm())) {
            err = "Error in Sequence: " + s.getStartingRhythm() + " is not a recognized rhythm name.\n";
        }

        List<String> parallelOnes = s.getParallelRhythms();
        for(String name : parallelOnes) {
            if (!uniqueRhythms.contains(name)) {
                err += "Error in Sequence: " + name + " is not a recognized rhythm name.\n";
            }
        }

        List<String> followupOnes = s.getFollowupRhythms();
        for(String name : followupOnes) {
            if (!uniqueRhythms.contains(name)) {
                err += "Error in Sequence: " + name + " is not a recognized rhythm name.\n";
            }
        }

        return err;
    }

    @Override
    public String visit(HashSet<String> uniqueRhythms, Rhythm r) {
        String err = "";
        if (uniqueRhythms.contains(r.title)) {
            err = "Rhythm name " + r.title + " is already used! Cannot create multiple rhythms with the same name.\n";
        } else {
            uniqueRhythms.add(r.title);
        }
        if (!SUPPORTED_INSTRUMENTS.contains(r.getInstrument())) {
            err += r.getInstrument() + " is not a recognized instrument\n";
        }
        return err;
    }

    @Override
    public String visit(HashSet<String> uniqueRhythms, Note n) {
        // What could go wrong??
        return "";
    }

    private static final String[] INSTRUMENT_NAME = new String[] {
            "Piano",
            "Bright_Acoustic",
            "Electric_Grand",
            "Honkey_Tonk",
            "Electric_Piano",
            "Electric_Piano_2",
            "Harpischord",
            "Clavinet",
            "Celesta",
            "Glockenspiel",

            "Music_Box",
            "Vibraphone",
            "Marimba",
            "Xylophone",
            "Tubular_Bells",
            "Dulcimer",
            "Drawbar_Organ",
            "Percussive_Organ",
            "Rock_Organ",
            "Church_Organ",

            "Reed_Organ",
            "Accordian",
            "Harmonica",
            "Tango_Accordian",
            "Guitar",
            "Steel_String_Guitar",
            "Electric_Jazz_Guitar",
            "Electric_Clean_Guitar",
            "Electric_muted_Guitar",
            "Overdriven_Guitar",
            "Distortion_Guitar",

            "Guitar_Harmonics",
            "Acoustic_Bass",
            "Electric_Bass_Finger",
            "Electric_Bass_Pick",
            "Fretless_Bass",
            "Slap_Bass_1",
            "Slap_Bass_2",
            "Synth_Bass_1",
            "Synth_Bass_2",

            "Violin",
            "Viola",
            "Cello",
            "Contrabass",
            "Tremolo_Strings",
            "Pizzicato_Strings",
            "Orchestral_Strings",
            "Timpani",
            "String_Ensemble_1",
            "String_Ensemble_2",

            "Synth_strings_1",
            "Synth_strings_2",
            "Choir_Aahs",
            "Voice_Oohs",
            "Synth_Voice",
            "Orchestra_Hit",
            "Trumpet",
            "Trombone",
            "Tuba",
            "Muted_Trumpet",

            "French_Horn",
            "Brass_Section",
            "Synth_brass_1",
            "Synth_brass_2",
            "Soprano_Sax",
            "Alto_Sax",
            "Tenor_Sax",
            "Baritone_Sax",
            "Oboe",
            "English_Horn",

            "Bassoon",
            "Clarinet",
            "Piccolo",
            "Flute",
            "Recorder",
            "Pan_Flute",
            "Blown_Bottle",
            "Skakuhachi",
            "Whistle",
            "Ocarina",

            "Square",
            "Sawtooth",
            "Calliope",
            "Chiff",
            "Charang",
            "Voice",
            "Fifths",
            "Basslead",
            "New_Age",
            "Warm",

            "Polysynth",
            "Choir",
            "Bowed",
            "Metallic",
            "Halo",
            "Sweep",
            "Rain",
            "Soundtrack",
            "Crystal",
            "Atmosphere",

            "Brightness",
            "Goblins",
            "Echoes",
            "Sci-fi",
            "Sitar",
            "Banjo",
            "Shamisen",
            "Koto",
            "Kalimba",
            "Bagpipe",

            "Fiddle",
            "Shanai",
            "Tinkle_Bell",
            "Agogo",
            "Steel_Drums",
            "Woodblock",
            "Taiko_Drum",
            "Melodic_Tom",
            "Synth_Drum",
            "Reverse_Cymbal",

            "Guitar_Fret_Noise",
            "Breath_Noise",
            "Seashore",
            "Bird_Tweet",
            "Telephone_Ring",
            "Helicopter",
            "Applause",
            "Gunshot" };

    private static final HashSet<String> SUPPORTED_INSTRUMENTS = new HashSet<>(Arrays.asList(INSTRUMENT_NAME));
}
