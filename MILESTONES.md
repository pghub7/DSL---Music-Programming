
A DSL to help users create their own music. We envision the user being able to construct a song using rhythms, which are themselves composed of notes. The user will be able to layer rhythms on top of one another, loop sections of the song if needed, change the tempo of songs, and possibly add effects like vibrato. Giving all of these options introduces sufficient complexity as well as gives creative liberty to the user. 

## Lexical Grammar
SONG_START: 'Create song';__
SONG_TITLE: 'With title ' -> mode(TEXT_MODE);__
TEMPO_START: 'With tempo ' -> mode(NUM_MODE);__

RHYTHM_START: 'Create rhythm ' -> mode(TEXT_MODE);__
USING: '~' -> mode(TEXT_MODE);__

NOTE: [A-G|R][1-9]?[WHQISTXO];__
LOOP_START: 'Loops: ' -> mode(NUM_MODE);__

AFTER_START: '<' WS* -> mode(NUM_MODE);__
AFTER_END: '>';__
PLAY: 'Play ' -> mode(TEXT_MODE);__
THEN: ', ';__

BEATS: ' beats ';__
AND: '^' WS* -> mode(TEXT_MODE);__
COLON: ':';__
WS: [\r\n\t ] -> channel(HIDDEN);__

COMMENT_START: '//' -> mode(TEXT_MODE);__

mode NUM_MODE;__
NUMBER: [0-9]+ -> mode(DEFAULT_MODE);__

mode TEXT_MODE;__
INSTRUMENT: [a-zA-Z_]+ -> mode(DEFAULT_MODE);__
QUOTED_NAME: ["a-zA-Z0-9_]+ -> mode(DEFAULT_MODE);__

## Parser Grammar
song: SONG_START title tempo loop? rhythm+ sequence+ ;__
title: SONG_TITLE QUOTED_NAME ;__
tempo: TEMPO_START NUMBER;__
rhythm: RHYTHM_START QUOTED_NAME USING INSTRUMENT COLON notes loop? ;__
notes: NOTE+ ;__
loop: LOOP_START NUMBER ;__
sequence: (beatsoffset)? PLAY QUOTED_NAME (parallelPlay)* (followupPlay)* ;__
beatsoffset: AFTER_START NUMBER BEATS AFTER_END THEN ;__
parallelPlay: AND QUOTED_NAME ;__
followupPlay: THEN PLAY QUOTED_NAME ;__
comment: COMMENT_START QUOTED_NAME ;__



## Contributors
Mac Iverson, Isabelle Lowe, Parth Garg, Joel Broek, Mark Balantzyan
