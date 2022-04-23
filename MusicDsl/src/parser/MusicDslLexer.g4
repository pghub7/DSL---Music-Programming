lexer grammar MusicDslLexer;

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




// TEXT: [a-zA-Z0-9]+;
// WS_TEXT : [\r\n ]+ -> channel(HIDDEN);
// RHYTHM_END: ':' -> mode(TEXT_MODE);
// LOOP_END: 'times\n' -> mode(TEXT_MODE);
// TEXT_IN_QUOTES: '"'TEXT'"';
// QUOTE: '"';
// INSTRUMENT: TEXT;