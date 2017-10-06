This project is a simple implementation of C language compiler and is written is C++ language.
This folder consists of two main files: lexical_mod.cpp and parser_mod.cpp
lexica_mod.cpp behaves as the lexical part of a compiler while parser_mod.cpp behaves as the parser. 
For the symbol table implementation a 2D matrix has been used. 

The user can submit his/her C code to test if its a valid C code.
I have provided a sample file named sample_code.c

The grammar which I have considered for this implementation is as follows: 
// Program  -> main () \{  declarations   statement-list \}
// declarations-> data-type identifier-list; declarations | epsilon
// data-type -> int|char
// identifier-list -> id|id, identifier-list
// statement_list -> statement statement_list | epsilon
// statement -> assign-stat;
// assign_stat -> id = expn}
