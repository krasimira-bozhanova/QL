## QL

Language Workbench Challenge 2013 implementation.
The challenge was solved during the Software Construction course at UvA.

## Description

The lab assignment is based on the Language Workbench Challenge 2013
([LWC'13](http://www.languageworkbenches.net/past-editions/)).
The goal of that assignment is to build two DSL for questionnaires.
The first, called QL allows you to define simple forms with conditions and computed values. See
[this document](http://www.languageworkbenches.net/wp-content/uploads/2013/11/Ql.pdf)
for more information.
The second, called QLS, is a styling language, which can be used to rearrange questions, paginate them, introduce sectioning, font styles and widget types. 

*NB*: you will work in teams of 2.

##### QL Requirements

- Questions are enabled and disabled when different values are
  entered.
  
- The type checker detects:
   * reference to undefined questions
   * duplicate question declarations with different types
   * conditions that are not of the type boolean
   * operands of invalid type to operators
   * cyclic dependencies between questions
   * duplicate labels (warning)

- The language supports booleans, integers and string values.

- Different data types in QL map to different (default) GUI widgets.   

Requirements on the implementation:

- The parser of the DSL is implemented using a grammar-based parser
  generator. 

- The internal structure of a DSL program is represented using
  abstract syntax trees.

- QL programs are executed as GUI programs, not command-line
  dialogues. 

- QL programs are executed by interpretation, not code generation.


Here's a simple questionnaire in QL from the domain of tax filing:

```
form taxOfficeExample { 
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean
    
  if (hasSoldHouse) {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money = 
        (sellingPrice - privateDebt)
  }
  
}
```

#### QLS Requirements

- QLS allows you to place questions of a base QL program in pages and
  sections.  Furthermore, you can define default widget types and
  styles for questions of a particular type (e.g. boolean
  questions). Such default styles can be overridden on a per widget
  basis.

- The type checker detects:

   * no references to questions that are not in the QL program

   * all questions of the QL program are placed by the QLS program.

   * (default) widget assignments are compatible with question types
     (e.g. no radio button for integer widgets).

   * you cannot place a single question multiple times.

- The execution of a QL + QLS program should be the same as executing
  the QL program individually, _except_ for where questions appear
  (page/secion), what font-styles etc. are used, and what widget types
  are used.
  
- As widget types you're supposed to support at least: slider, spinbox (for numbers), text (for numbers and strings), yesno-radios, checkbox, yesno-dropdown (for booleans).
  
Requirements on the implementation:

- You could say QLS defines an "aspect" of questionnaires, i.e. its appearance. The challenge is to implement QLS without (invasively) changing the code pertaining to QL, and without duplicating or reimplementing the QL code. (Copying and/or duplicating is not allowed, but some changes might be needed at certain join points...). 

- The QL code, and especially, the QL ASTs should be oblivious to the QLS code. Think about how you can achieve that. 

  
Here's an example QLS description for the simple Tax Form:

```
stylesheet taxOfficeExample 
  page Housing {
    section "Buying"
      question hasBoughtHouse  
        widget checkbox 
    section "Loaning"  
      question hasMaintLoan
  }

  page Selling { 
    section "Selling" {
      question hasSoldHouse
        widget radio("Yes", "No") 
      section "You sold a house" {
        question sellingPrice
          widget spinbox
        question privateDebt
          widget spinbox 
        question valueResidue
        default money {
          width: 400
          font: "Arial" 
          fontsize: 14
          color: #999999
          widget spinbox
        }        
      }
    }
    default boolean widget radio("Yes", "No")
  }  

```


## Realizing the assignment.

You are encouraged to be creative in terms of libraries or frameworks
that you use, but be aware of impending bloat and or a huge number of
dependencies (all in good measure). 

As to programming language, you may choose any of the following
languages: Java, C#, Javascript, Haskell, Scala, Clojure, Erlang,
Smalltalk, Ruby, Python, Go, Dart, Swift, Objective-C, F#. Feel free to take
the opportunity to learn a new language, but be aware that your code
will be graded as if you're proficient in it and be aware of idiomatic
coding styles. For Java we provide grammar skeleton code for the
parser generators ANTLR, Jacc and Rats! These grammars are
*incomplete*. You may copy one of the skeleton projects and complete
it by adding the following features:

- Syntax for booleans, string literals. Don't forget to take care of
  keyword reservation: true and false should be parsed as boolean
  literals, not as identifiers. 

- Add single-line comments (a la Java: //).

- Add syntax productions for forms, questions, computed quetsions,
  types (int, bool, and string) and if-then and if-then-else
  statements. Use string literals for question labels. See the LWC'13
  link above for an example questionnaire. 

- Add tests to check your syntax extensions.

- Add AST classes for the provided expression categories, and for you
  syntactic extensions. Make sure the parser creates objects of the
  appropriate type. 
  
- Change the start symbol of the parser to parse forms, instead of
  Expressions.

Note: don't be seduced by the provided example code and start
copy-pasting grammar rules around. It is important to have a basic
understanding of the parser technology involved. ANTLR, Rats! and Jacc
are well-documented on the web. Please use this information to fulfill
the above requirements.

The QLS languages does not feature expressions. But your experience in developing the QL language directly feeds into the design of QLS. 
