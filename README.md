
A DSL to help users create their own music. We envision the user being able to construct a song using rhythms, which are themselves composed of notes. The user will be able to layer rhythms on top of one another, loop sections of the song if needed, change the tempo of songs, and possibly add effects like vibrato. Giving all of these options introduces sufficient complexity as well as gives creative liberty to the user. 

## Lexical Grammar
SONG_START: 'Create song';  
SONG_TITLE: 'With title ' -> mode(TEXT_MODE);  
TEMPO_START: 'With tempo ' -> mode(NUM_MODE);  
RHYTHM_START: 'Create rhythm ' -> mode(TEXT_MODE);  
USING: '~' -> mode(TEXT_MODE);  
NOTE: [A-G|R][1-9]?[WHQISTXO];  
LOOP_START: 'Loops: ' -> mode(NUM_MODE);  
AFTER_START: '<' WS* -> mode(NUM_MODE);  
AFTER_END: '>';  
PLAY: 'Play ' -> mode(TEXT_MODE);  
THEN: ', ';  
BEATS: ' beats ';  
AND: '^' WS* -> mode(TEXT_MODE);  
COLON: ':';  
WS: [\r\n\t ] -> channel(HIDDEN);  
COMMENT_START: '//' -> mode(TEXT_MODE);

mode NUM_MODE;  
NUMBER: [0-9]+ -> mode(DEFAULT_MODE);

mode TEXT_MODE;  
INSTRUMENT: [a-zA-Z_]+ -> mode(DEFAULT_MODE);  
QUOTED_NAME: ["a-zA-Z0-9_]+ -> mode(DEFAULT_MODE);

## Parser Grammar
song: SONG_START title tempo loop? rhythm+ sequence+ ;  
title: SONG_TITLE QUOTED_NAME ;  
tempo: TEMPO_START NUMBER;  
rhythm: RHYTHM_START QUOTED_NAME USING INSTRUMENT COLON notes loop? ;  
notes: NOTE+ ;  
loop: LOOP_START NUMBER ;  
sequence: (beatsoffset)? PLAY QUOTED_NAME (parallelPlay)* (followupPlay)* ;  
beatsoffset: AFTER_START NUMBER BEATS AFTER_END THEN ;  
parallelPlay: AND QUOTED_NAME ;  
followupPlay: THEN PLAY QUOTED_NAME ;  
comment: COMMENT_START QUOTED_NAME ;

## Contributors
Mac Iverson, Isabelle Lowe, Parth Garg, Joel Broek, Mark Balantzyan
