
//----------------------------------------------------
// The following code was generated by CUP v0.12for-WebitScript-only
//----------------------------------------------------

package webit.script.core;

import webit.script.core.ast.*;
import webit.script.core.ast.expressions.*;
import webit.script.core.ast.operators.*;
import webit.script.core.ast.statements.*;
import webit.script.exceptions.ParseException;
import webit.script.util.ALU;
import webit.script.util.ClassNameBand;
import webit.script.util.StatementUtil;
import webit.script.util.Stack;

/**
 * 
 * @version Wed Aug 13 08:09:19 CST 2014
 */
public class Parser extends AbstractParser {

    final Object doAction(int actionId) throws ParseException {
        Stack<Symbol> myStack = this._stack;

        /* select the action based on the action number */
        switch (actionId){
            case 1: // $START ::= templateAST EOF 
            {
                /* ACCEPT */
                this.goonParse = false;
                return myStack.peek(1).value;
            }
            case 156: // funcExecuteExpr ::= expression AT contextValueExpr LPAREN expressionList RPAREN 
            {
                Symbol funcExpr$Symbol = myStack.peek(3);
                return new MethodExecute((Expression) funcExpr$Symbol.value, ((ExpressionList) myStack.peek(1).value).addFirst((Expression) myStack.peek(5).value).toArray(), funcExpr$Symbol.line, funcExpr$Symbol.column);
            }
            case 155: // funcExecuteExpr ::= expression LPAREN expressionList RPAREN 
            {
                Symbol funcExpr$Symbol = myStack.peek(3);
                return new MethodExecute((Expression) funcExpr$Symbol.value, ((ExpressionList) myStack.peek(1).value).toArray(), funcExpr$Symbol.line, funcExpr$Symbol.column);
            }
            case 9: // identiferList ::= identiferList COMMA IDENTIFIER 
            {
                Symbol ident$Symbol = myStack.peek(0);
                return ((IdentiferList) myStack.peek(2).value).add((String) ident$Symbol.value, ident$Symbol.line, ident$Symbol.column);
            }
            case 49: // importPart2 ::= importPart1 IDENTIFIER 
            {
                Symbol ident$Symbol = myStack.peek(0);
                return ((ImportPart) myStack.peek(1).value).append((String) ident$Symbol.value, createContextValue(0, (String) ident$Symbol.value, ident$Symbol.line, ident$Symbol.column));
            }
            case 51: // importPart2 ::= importPart2 COMMA IDENTIFIER 
            {
                Symbol ident$Symbol = myStack.peek(0);
                return ((ImportPart) myStack.peek(2).value).append((String) ident$Symbol.value, createContextValue(0, (String) ident$Symbol.value, ident$Symbol.line, ident$Symbol.column));
            }
            case 136: // contextValueExpr ::= superCount IDENTIFIER 
            {
                Symbol ident$Symbol = myStack.peek(0);
                return createContextValue((Integer) myStack.peek(1).value, (String) ident$Symbol.value, ident$Symbol.line, ident$Symbol.column);
            }
            case 139: // contextValueExpr ::= superCount FOR DOT IDENTIFIER 
            {
                Symbol ident$Symbol = myStack.peek(0);
                return createContextValue((Integer) myStack.peek(3).value, ("for." + (String) ident$Symbol.value), ident$Symbol.line, ident$Symbol.column);
            }
            case 138: // contextValueExpr ::= FOR DOT IDENTIFIER 
            {
                Symbol ident$Symbol = myStack.peek(0);
                return createContextValue(0, ("for." + (String) ident$Symbol.value), ident$Symbol.line, ident$Symbol.column);
            }
            case 134: // contextValueExpr ::= IDENTIFIER 
            {
                Symbol ident$Symbol = myStack.peek(0);
                return createContextValue(0, (String) ident$Symbol.value, ident$Symbol.line, ident$Symbol.column);
            }
            case 137: // contextValueExpr ::= superCount THIS DOT IDENTIFIER 
            {
                Symbol ident$Symbol = myStack.peek(0);
                return createContextValueAtUpstair((Integer) myStack.peek(3).value, (String) ident$Symbol.value, ident$Symbol.line, ident$Symbol.column);
            }
            case 135: // contextValueExpr ::= THIS DOT IDENTIFIER 
            {
                Symbol ident$Symbol = myStack.peek(0);
                return createContextValueAtUpstair(0, (String) ident$Symbol.value, ident$Symbol.line, ident$Symbol.column);
            }
            case 8: // identiferList ::= IDENTIFIER 
            {
                Symbol ident$Symbol = myStack.peek(0);
                return new IdentiferList().add((String) ident$Symbol.value, ident$Symbol.line, ident$Symbol.column);
            }
            case 44: // varPart ::= varPart COMMA IDENTIFIER 
            {
                Symbol ident$Symbol = myStack.peek(0);
                varmgr.assignVariant((String) ident$Symbol.value,ident$Symbol.line,ident$Symbol.column); return (StatementList) myStack.peek(2).value;
            }
            case 42: // varPart ::= VAR IDENTIFIER 
            {
                Symbol ident$Symbol = myStack.peek(0);
                varmgr.assignVariant((String) ident$Symbol.value,ident$Symbol.line,ident$Symbol.column); return new StatementList();
            }
            case 63: // lambdaForHead1 ::= FOR LPAREN IDENTIFIER MINUSGT 
            case 161: // lambdaExprHead ::= IDENTIFIER MINUSGT 
            {
                Symbol ident$Symbol = myStack.peek(1);
                Symbol sym$Symbol = myStack.peek(0);
                return new LambdaPart(this.varmgr, sym$Symbol.line, sym$Symbol.column).appendArg((String) ident$Symbol.value, ident$Symbol.line, ident$Symbol.column);
            }
            case 40: // varAssign ::= IDENTIFIER EQ expression 
            case 41: // varAssign ::= IDENTIFIER EQ varAssign 
            {
                Symbol ident$Symbol = myStack.peek(2);
                Symbol sym$Symbol = myStack.peek(1);
                return new Assign(castToResetableValueExpression(createContextValue(varmgr.assignVariantAddress((String) ident$Symbol.value,ident$Symbol.line,ident$Symbol.column), ident$Symbol.line, ident$Symbol.column)), (Expression) myStack.peek(0).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 150: // funcStatementHead ::= FUNCTION IDENTIFIER LPAREN RPAREN 
            {
                Symbol ident$Symbol = myStack.peek(2);
                return new FunctionDeclarePart((String) ident$Symbol.value, this.varmgr, ident$Symbol.line, ident$Symbol.column);
            }
            case 23: // statement ::= CONST IDENTIFIER EQ expression SEMICOLON 
            {
                Symbol ident$Symbol = myStack.peek(3);
                assignConst((String) ident$Symbol.value, (Expression) myStack.peek(1).value, ident$Symbol.line, ident$Symbol.column); return NoneStatement.getInstance();
            }
            case 151: // funcStatementHead ::= FUNCTION IDENTIFIER LPAREN identiferList RPAREN 
            {
                Symbol ident$Symbol = myStack.peek(3);
                return new FunctionDeclarePart((String) ident$Symbol.value, this.varmgr, ident$Symbol.line, ident$Symbol.column).appendArgs((IdentiferList) myStack.peek(1).value);
            }
            case 162: // lambdaExprHead ::= LPAREN IDENTIFIER COMMA identiferList RPAREN MINUSGT 
            {
                Symbol ident$Symbol = myStack.peek(4);
                Symbol sym$Symbol = myStack.peek(0);
                return new LambdaPart(this.varmgr, sym$Symbol.line, sym$Symbol.column).appendArg((String) ident$Symbol.value, ident$Symbol.line, ident$Symbol.column).appendArgs((IdentiferList) myStack.peek(2).value);
            }
            case 158: // classNameList1 ::= classNameList1 COMMA className 
            {
                Symbol nameBand$Symbol = myStack.peek(0);
                return ((ClassNameList) myStack.peek(2).value).add((ClassNameBand) nameBand$Symbol.value, nameBand$Symbol.line, nameBand$Symbol.column);
            }
            case 127: // expression ::= NATIVE classPureName 
            {
                Symbol nameBand$Symbol = myStack.peek(0);
                return createNativeStaticValue((ClassNameBand) nameBand$Symbol.value, nameBand$Symbol.line, nameBand$Symbol.column);
            }
            case 157: // classNameList1 ::= className 
            {
                Symbol nameBand$Symbol = myStack.peek(0);
                return new ClassNameList(this.nativeImportMgr).add((ClassNameBand) nameBand$Symbol.value, nameBand$Symbol.line, nameBand$Symbol.column);
            }
            case 24: // statement ::= varPart SEMICOLON 
            {
                Symbol part$Symbol = myStack.peek(1);
                return ((StatementList) part$Symbol.value).popStatementGroup(part$Symbol.line, part$Symbol.column);
            }
            case 64: // lambdaForHead2 ::= lambdaForHead1 expression COLON 
            {
                Symbol part$Symbol = myStack.peek(2);
                return new ForInPart(((LambdaPart) part$Symbol.value).getArg(0), ((LambdaPart) part$Symbol.value).popFunctionDeclare((Expression) myStack.peek(1).value), this.varmgr, part$Symbol.line, part$Symbol.column);
            }
            case 65: // lambdaForHead2 ::= lambdaForHead1 LBRACE statementList RBRACE COLON 
            {
                Symbol part$Symbol = myStack.peek(4);
                return new ForInPart(((LambdaPart) part$Symbol.value).getArg(0), ((LambdaPart) part$Symbol.value).popFunctionDeclare((StatementList) myStack.peek(2).value), this.varmgr, part$Symbol.line, part$Symbol.column);
            }
            case 76: // caseBlockStat ::= blockPrepare 
            {
                Symbol prepare$Symbol = myStack.peek(0);
                return new StatementList().popIBlock(varmgr.pop(), prepare$Symbol.line, prepare$Symbol.column);
            }
            case 75: // caseBlockStat ::= blockPrepare statementList 
            {
                Symbol prepare$Symbol = myStack.peek(1);
                return ((StatementList) myStack.peek(0).value).popIBlock(varmgr.pop(), prepare$Symbol.line, prepare$Symbol.column);
            }
            case 56: // block ::= blockPrepare2 RBRACE 
            {
                Symbol prepare$Symbol = myStack.peek(1);
                return new StatementList().popIBlock(varmgr.pop(), prepare$Symbol.line, prepare$Symbol.column);
            }
            case 55: // block ::= blockPrepare2 statementList RBRACE 
            {
                Symbol prepare$Symbol = myStack.peek(2);
                return ((StatementList) myStack.peek(1).value).popIBlock(varmgr.pop(), prepare$Symbol.line, prepare$Symbol.column);
            }
            case 119: // expression ::= DIRECT_VALUE 
            {
                Symbol sym$Symbol = myStack.peek(0);
                return new DirectValue((Object) sym$Symbol.value, sym$Symbol.line, sym$Symbol.column);
            }
            case 152: // funcHead ::= FUNCTION 
            {
                Symbol sym$Symbol = myStack.peek(0);
                return new FunctionDeclarePart(this.varmgr, sym$Symbol.line, sym$Symbol.column);
            }
            case 87: // expression_statementable ::= expression MINUSMINUS 
            {
                Symbol sym$Symbol = myStack.peek(0);
                return new MinusMinusAfter(castToResetableValueExpression((Expression) myStack.peek(1).value), sym$Symbol.line, sym$Symbol.column);
            }
            case 85: // expression_statementable ::= expression PLUSPLUS 
            {
                Symbol sym$Symbol = myStack.peek(0);
                return new PlusPlusAfter(castToResetableValueExpression((Expression) myStack.peek(1).value), sym$Symbol.line, sym$Symbol.column);
            }
            case 22: // statement ::= TEXT_STATEMENT 
            {
                Symbol sym$Symbol = myStack.peek(0);
                return textStatementFactory.getTextStatement(template, (char[]) sym$Symbol.value, sym$Symbol.line, sym$Symbol.column);
            }
            case 92: // expression ::= COMP expression 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return StatementUtil.optimize(new BitNot((Expression) myStack.peek(0).value, sym$Symbol.line, sym$Symbol.column));
            }
            case 93: // expression ::= MINUS expression 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return StatementUtil.optimize(new Negative((Expression) myStack.peek(0).value, sym$Symbol.line, sym$Symbol.column));
            }
            case 94: // expression ::= NOT expression 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return StatementUtil.optimize(new Not((Expression) myStack.peek(0).value, sym$Symbol.line, sym$Symbol.column));
            }
            case 97: // expression ::= expression MULT expression 
            case 98: // expression ::= expression DIV expression 
            case 99: // expression ::= expression MOD expression 
            case 100: // expression ::= expression PLUS expression 
            case 101: // expression ::= expression MINUS expression 
            case 102: // expression ::= expression LSHIFT expression 
            case 103: // expression ::= expression RSHIFT expression 
            case 104: // expression ::= expression URSHIFT expression 
            case 105: // expression ::= expression LT expression 
            case 106: // expression ::= expression LTEQ expression 
            case 107: // expression ::= expression GT expression 
            case 108: // expression ::= expression GTEQ expression 
            case 109: // expression ::= expression EQEQ expression 
            case 110: // expression ::= expression NOTEQ expression 
            case 111: // expression ::= expression AND expression 
            case 112: // expression ::= expression OR expression 
            case 113: // expression ::= expression XOR expression 
            case 114: // expression ::= expression ANDAND expression 
            case 115: // expression ::= expression DOTDOT expression 
            case 116: // expression ::= expression OROR expression 
            case 117: // expression ::= expression QUESTION_COLON expression 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return createBinaryOperator((Expression) myStack.peek(2).value, (Integer) sym$Symbol.value, (Expression) myStack.peek(0).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 88: // expression_statementable ::= expression SELFEQ expression 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return createSelfOperator((Expression) myStack.peek(2).value, (Integer) sym$Symbol.value, (Expression) myStack.peek(0).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 83: // expression_statementable ::= expression EQ expression 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return new Assign(castToResetableValueExpression((Expression) myStack.peek(2).value), (Expression) myStack.peek(0).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 27: // statement ::= BREAK SEMICOLON 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return new Break(0, sym$Symbol.line, sym$Symbol.column);
            }
            case 29: // statement ::= CONTINUE SEMICOLON 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return new Continue(0, sym$Symbol.line, sym$Symbol.column);
            }
            case 46: // importPart1 ::= IMPORT expression 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return new ImportPart((Expression) myStack.peek(0).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 149: // mapValue ::= LBRACE RBRACE 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return new MapValue(new Object[0], new Expression[0], sym$Symbol.line,sym$Symbol.column);
            }
            case 86: // expression_statementable ::= MINUSMINUS expression 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return new MinusMinusBefore(castToResetableValueExpression((Expression) myStack.peek(0).value), sym$Symbol.line, sym$Symbol.column);
            }
            case 84: // expression_statementable ::= PLUSPLUS expression 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return new PlusPlusBefore(castToResetableValueExpression((Expression) myStack.peek(0).value), sym$Symbol.line, sym$Symbol.column);
            }
            case 95: // expression ::= expression DOT IDENTIFIER 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return new PropertyOperator((Expression) myStack.peek(2).value, (String) myStack.peek(0).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 89: // expression_statementable ::= funcExecuteExpr EQGT expression 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return new RedirectOutExpression((Expression) myStack.peek(2).value, castToResetableValueExpression((Expression) myStack.peek(0).value), sym$Symbol.line, sym$Symbol.column);
            }
            case 31: // statement ::= RETURN SEMICOLON 
            {
                Symbol sym$Symbol = myStack.peek(1);
                return new Return(null, sym$Symbol.line, sym$Symbol.column);
            }
            case 26: // statement ::= NATIVE_IMPORT classPureName SEMICOLON 
            {
                Symbol sym$Symbol = myStack.peek(2);
                nativeImportMgr.registClass((ClassNameBand) myStack.peek(1).value, sym$Symbol.line, sym$Symbol.column); return NoneStatement.getInstance();
            }
            case 148: // mapValue ::= LBRACE mapValuePart RBRACE 
            {
                Symbol sym$Symbol = myStack.peek(2);
                return ((MapValuePart) myStack.peek(1).value).pop(sym$Symbol.line, sym$Symbol.column);
            }
            case 123: // expression ::= LBRACK expressionList RBRACK 
            {
                Symbol sym$Symbol = myStack.peek(2);
                return new ArrayValue(((ExpressionList) myStack.peek(1).value).toArray(), sym$Symbol.line, sym$Symbol.column);
            }
            case 28: // statement ::= BREAK IDENTIFIER SEMICOLON 
            {
                Symbol sym$Symbol = myStack.peek(2);
                return new Break(getLabelIndex((String) myStack.peek(1).value), sym$Symbol.line, sym$Symbol.column);
            }
            case 30: // statement ::= CONTINUE IDENTIFIER SEMICOLON 
            {
                Symbol sym$Symbol = myStack.peek(2);
                return new Continue(getLabelIndex((String) myStack.peek(1).value), sym$Symbol.line, sym$Symbol.column);
            }
            case 25: // statement ::= ECHO expression SEMICOLON 
            {
                Symbol sym$Symbol = myStack.peek(2);
                return new Echo((Expression) myStack.peek(1).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 153: // funcHead ::= FUNCTION LPAREN RPAREN 
            {
                Symbol sym$Symbol = myStack.peek(2);
                return new FunctionDeclarePart(this.varmgr, sym$Symbol.line, sym$Symbol.column);
            }
            case 47: // importPart1 ::= IMPORT expression mapValue 
            {
                Symbol sym$Symbol = myStack.peek(2);
                return new ImportPart((Expression) myStack.peek(1).value, (MapValue) myStack.peek(0).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 35: // statement ::= INCLUDE expression SEMICOLON 
            {
                Symbol sym$Symbol = myStack.peek(2);
                return new Include((Expression) myStack.peek(1).value, null, this.template, sym$Symbol.line, sym$Symbol.column);
            }
            case 96: // expression ::= expression LBRACK expression RBRACK 
            {
                Symbol sym$Symbol = myStack.peek(2);
                return new IndexOperator((Expression) myStack.peek(3).value, (Expression) myStack.peek(1).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 21: // statement ::= block EQGT expression SEMICOLON 
            {
                Symbol sym$Symbol = myStack.peek(2);
                return new RedirectOut((IBlock) myStack.peek(3).value, castToResetableValueExpression((Expression) myStack.peek(1).value), sym$Symbol.line, sym$Symbol.column);
            }
            case 32: // statement ::= RETURN expression SEMICOLON 
            {
                Symbol sym$Symbol = myStack.peek(2);
                return new Return((Expression) myStack.peek(1).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 124: // expression ::= NATIVE LBRACK RBRACK className 
            {
                Symbol sym$Symbol = myStack.peek(3);
                Symbol nameBand$Symbol = myStack.peek(0);
                return this.createNativeNewArrayDeclareExpression(nativeImportMgr.toClass((ClassNameBand) nameBand$Symbol.value, nameBand$Symbol.line, nameBand$Symbol.column), sym$Symbol.line, sym$Symbol.column);
            }
            case 125: // expression ::= NATIVE LBRACK className RBRACK 
            {
                Symbol sym$Symbol = myStack.peek(3);
                Symbol nameBand$Symbol = myStack.peek(1);
                return this.createNativeNewArrayDeclareExpression(nativeImportMgr.toClass((ClassNameBand) nameBand$Symbol.value, nameBand$Symbol.line, nameBand$Symbol.column), sym$Symbol.line, sym$Symbol.column);
            }
            case 80: // switchPart0 ::= DEFAULT COLON caseBlockStat switchPart0 
            {
                Symbol sym$Symbol = myStack.peek(3);
                return ((SwitchPart) myStack.peek(0).value).appendCase(null, (IBlock) myStack.peek(1).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 154: // funcHead ::= FUNCTION LPAREN identiferList RPAREN 
            {
                Symbol sym$Symbol = myStack.peek(3);
                return new FunctionDeclarePart(this.varmgr, sym$Symbol.line, sym$Symbol.column).appendArgs((IdentiferList) myStack.peek(1).value);
            }
            case 118: // expression ::= expression QUESTION expression COLON expression 
            {
                Symbol sym$Symbol = myStack.peek(3);
                return new IfOperator((Expression) myStack.peek(4).value, (Expression) myStack.peek(2).value, (Expression) myStack.peek(0).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 36: // statement ::= INCLUDE expression mapValue SEMICOLON 
            {
                Symbol sym$Symbol = myStack.peek(3);
                return new Include((Expression) myStack.peek(2).value, (MapValue) myStack.peek(1).value, this.template, sym$Symbol.line, sym$Symbol.column);
            }
            case 78: // switchPart0 ::= CASE DIRECT_VALUE COLON caseBlockStat switchPart0 
            {
                Symbol sym$Symbol = myStack.peek(4);
                return ((SwitchPart) myStack.peek(0).value).appendCase((Object) myStack.peek(3).value, (IBlock) myStack.peek(1).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 57: // ifPart ::= IF LPAREN expression RPAREN block 
            {
                Symbol sym$Symbol = myStack.peek(4);
                return new IfPart((Expression) myStack.peek(2).value, (IBlock) myStack.peek(0).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 48: // importPart1 ::= IMPORT expression LBRACE expression RBRACE 
            {
                Symbol sym$Symbol = myStack.peek(4);
                return new ImportPart((Expression) myStack.peek(3).value, (Expression) myStack.peek(1).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 73: // whilePart ::= WHILE LPAREN expression RPAREN block 
            {
                Symbol sym$Symbol = myStack.peek(4);
                return new WhilePart((Expression) myStack.peek(2).value, (IBlock) myStack.peek(0).value, true, sym$Symbol.line, sym$Symbol.column);
            }
            case 128: // expression ::= NATIVE NEW classPureName LPAREN classNameList RPAREN 
            {
                Symbol sym$Symbol = myStack.peek(5);
                Symbol nameBand$Symbol = myStack.peek(3);
                return this.createNativeConstructorDeclareExpression(nativeImportMgr.toClass((ClassNameBand) nameBand$Symbol.value, nameBand$Symbol.line, nameBand$Symbol.column), (ClassNameList) myStack.peek(1).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 79: // switchPart0 ::= CASE MINUS DIRECT_VALUE COLON caseBlockStat switchPart0 
            {
                Symbol sym$Symbol = myStack.peek(5);
                return ((SwitchPart) myStack.peek(0).value).appendCase(ALU.negative((Object) myStack.peek(3).value), (IBlock) myStack.peek(1).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 61: // forInHead ::= FOR LPAREN IDENTIFIER COLON expression RPAREN 
            {
                Symbol sym$Symbol = myStack.peek(5);
                return new ForInPart((String) myStack.peek(3).value, (Expression) myStack.peek(1).value, this.varmgr, sym$Symbol.line, sym$Symbol.column);
            }
            case 37: // statement ::= INCLUDE expression LBRACE expression RBRACE SEMICOLON 
            {
                Symbol sym$Symbol = myStack.peek(5);
                return new Include((Expression) myStack.peek(4).value, (Expression) myStack.peek(2).value, this.template, sym$Symbol.line, sym$Symbol.column);
            }
            case 126: // expression ::= NATIVE classPureName DOT IDENTIFIER LPAREN classNameList RPAREN 
            {
                Symbol sym$Symbol = myStack.peek(6);
                Symbol nameBand$Symbol = myStack.peek(5);
                return this.createNativeMethodDeclareExpression(nativeImportMgr.toClass((ClassNameBand) nameBand$Symbol.value, nameBand$Symbol.line, nameBand$Symbol.column), (String) myStack.peek(3).value, (ClassNameList) myStack.peek(1).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 81: // switchPart ::= SWITCH LPAREN expression RPAREN LBRACE switchPart0 RBRACE 
            {
                Symbol sym$Symbol = myStack.peek(6);
                return ((SwitchPart) myStack.peek(1).value).setSwitchExpr((Expression) myStack.peek(4).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 74: // whilePart ::= DO block WHILE LPAREN expression RPAREN SEMICOLON 
            {
                Symbol sym$Symbol = myStack.peek(6);
                return new WhilePart((Expression) myStack.peek(2).value, (IBlock) myStack.peek(5).value, false, sym$Symbol.line, sym$Symbol.column);
            }
            case 82: // switchPart ::= SWITCH LPAREN expression RPAREN LBRACE TEXT_STATEMENT switchPart0 RBRACE 
            {
                Symbol sym$Symbol = myStack.peek(7);
                return ((SwitchPart) myStack.peek(1).value).setSwitchExpr((Expression) myStack.peek(5).value, sym$Symbol.line, sym$Symbol.column);
            }
            case 62: // forInHead ::= FOR LPAREN IDENTIFIER COMMA IDENTIFIER COLON expression RPAREN 
            {
                Symbol sym$Symbol = myStack.peek(7);
                return new ForMapPart((String) myStack.peek(5).value, (String) myStack.peek(3).value, (Expression) myStack.peek(1).value, this.varmgr, sym$Symbol.line, sym$Symbol.column);
            }
            case 19: // statement ::= forInPart 
            {
                return ((AbstractForInPart) myStack.peek(0).value).pop();
            }
            case 20: // statement ::= IDENTIFIER COLON forInPart 
            {
                return ((AbstractForInPart) myStack.peek(0).value).pop(getLabelIndex((String) myStack.peek(2).value));
            }
            case 72: // forInPart ::= forInBody ELSE block 
            {
                return ((AbstractForInPart) myStack.peek(2).value).setElse((IBlock) myStack.peek(0).value);
            }
            case 68: // forInBody ::= forInHead LBRACE RBRACE 
            {
                return ((AbstractForInPart) myStack.peek(2).value).setStatementList(new StatementList());
            }
            case 67: // forInBody ::= forInHead LBRACE statementList RBRACE 
            {
                return ((AbstractForInPart) myStack.peek(3).value).setStatementList((StatementList) myStack.peek(1).value);
            }
            case 5: // classPureName ::= classPureName DOT IDENTIFIER 
            {
                return ((ClassNameBand) myStack.peek(2).value).append((String) myStack.peek(0).value);
            }
            case 7: // className ::= className LBRACK RBRACK 
            {
                return ((ClassNameBand) myStack.peek(2).value).plusArrayDepth();
            }
            case 141: // expressionList1 ::= expressionList1 COMMA expression 
            {
                return ((ExpressionList) myStack.peek(2).value).add((Expression) myStack.peek(0).value);
            }
            case 66: // lambdaForHead ::= lambdaForHead2 expression RPAREN 
            {
                return ((ForInPart) myStack.peek(2).value).setCollectionExpr((Expression) myStack.peek(1).value);
            }
            case 70: // forInBody ::= lambdaForHead LBRACE RBRACE 
            {
                return ((ForInPart) myStack.peek(2).value).setStatementList(new StatementList());
            }
            case 69: // forInBody ::= lambdaForHead LBRACE statementList RBRACE 
            {
                return ((ForInPart) myStack.peek(3).value).setStatementList((StatementList) myStack.peek(1).value);
            }
            case 39: // statement ::= funcStatementHead LBRACE RBRACE 
            case 122: // expression ::= funcHead LBRACE RBRACE 
            {
                return ((FunctionDeclarePart) myStack.peek(2).value).pop(new StatementList());
            }
            case 38: // statement ::= funcStatementHead LBRACE statementList RBRACE 
            case 121: // expression ::= funcHead LBRACE statementList RBRACE 
            {
                return ((FunctionDeclarePart) myStack.peek(3).value).pop((StatementList) myStack.peek(1).value);
            }
            case 58: // ifStat ::= ifPart 
            {
                return ((IfPart) myStack.peek(0).value).pop();
            }
            case 59: // ifStat ::= ifPart ELSE block 
            {
                return ((IfPart) myStack.peek(2).value).pop((IBlock) myStack.peek(0).value);
            }
            case 60: // ifStat ::= ifPart ELSE ifStat 
            {
                return ((IfPart) myStack.peek(2).value).pop((Statement) myStack.peek(0).value);
            }
            case 33: // statement ::= importPart1 SEMICOLON 
            case 34: // statement ::= importPart2 SEMICOLON 
            {
                return ((ImportPart) myStack.peek(1).value).pop(this.template);
            }
            case 50: // importPart2 ::= importPart1 contextValueExpr EQ IDENTIFIER 
            {
                return ((ImportPart) myStack.peek(3).value).append((String) myStack.peek(0).value, (Expression) myStack.peek(2).value);
            }
            case 52: // importPart2 ::= importPart2 COMMA contextValueExpr EQ IDENTIFIER 
            {
                return ((ImportPart) myStack.peek(4).value).append((String) myStack.peek(0).value, (Expression) myStack.peek(2).value);
            }
            case 163: // lambdaExpr ::= lambdaExprHead expression 
            {
                return ((LambdaPart) myStack.peek(1).value).pop((Expression) myStack.peek(0).value);
            }
            case 164: // lambdaExpr ::= lambdaExprHead LBRACE statementList RBRACE 
            {
                return ((LambdaPart) myStack.peek(3).value).pop((StatementList) myStack.peek(1).value);
            }
            case 146: // mapValuePart ::= mapValuePart COMMA DIRECT_VALUE COLON expression 
            {
                return ((MapValuePart) myStack.peek(4).value).add((Object) myStack.peek(2).value, (Expression) myStack.peek(0).value);
            }
            case 147: // mapValuePart ::= mapValuePart COMMA MINUS DIRECT_VALUE COLON expression 
            {
                return ((MapValuePart) myStack.peek(5).value).add(ALU.negative((Object) myStack.peek(2).value), (Expression) myStack.peek(0).value);
            }
            case 0: // templateAST ::= statementList 
            {
                return ((StatementList) myStack.peek(0).value).popTemplateAST(varmgr.pop());
            }
            case 3: // statementList ::= statementList statement 
            {
                return ((StatementList) myStack.peek(1).value).add((Statement) myStack.peek(0).value);
            }
            case 45: // varPart ::= varPart COMMA varAssign 
            {
                return ((StatementList) myStack.peek(2).value).add((Expression) myStack.peek(0).value);
            }
            case 15: // statement ::= switchPart 
            {
                return ((SwitchPart) myStack.peek(0).value).pop();
            }
            case 16: // statement ::= IDENTIFIER COLON switchPart 
            {
                return ((SwitchPart) myStack.peek(0).value).pop(getLabelIndex((String) myStack.peek(2).value));
            }
            case 17: // statement ::= whilePart 
            {
                return ((WhilePart) myStack.peek(0).value).pop();
            }
            case 18: // statement ::= IDENTIFIER COLON whilePart 
            {
                return ((WhilePart) myStack.peek(0).value).pop(getLabelIndex((String) myStack.peek(2).value));
            }
            case 71: // forInPart ::= forInBody 
            {
                return (AbstractForInPart) myStack.peek(0).value;
            }
            case 6: // className ::= classPureName 
            {
                return (ClassNameBand) myStack.peek(0).value;
            }
            case 160: // classNameList ::= classNameList1 
            {
                return (ClassNameList) myStack.peek(0).value;
            }
            case 90: // expression_statementable ::= funcExecuteExpr 
            case 91: // expression ::= expression_statementable 
            case 129: // expression ::= contextValueExpr 
            case 131: // expression ::= lambdaExpr 
            {
                return (Expression) myStack.peek(0).value;
            }
            case 10: // statement ::= expression_statementable SEMICOLON 
            case 120: // expression ::= LPAREN expression RPAREN 
            {
                return (Expression) myStack.peek(1).value;
            }
            case 143: // expressionList ::= expressionList1 
            {
                return (ExpressionList) myStack.peek(0).value;
            }
            case 13: // statement ::= block 
            {
                return (IBlock) myStack.peek(0).value;
            }
            case 133: // superCount ::= superCount SUPER DOT 
            {
                return (Integer) myStack.peek(2).value + 1;
            }
            case 130: // expression ::= mapValue 
            {
                return (MapValue) myStack.peek(0).value;
            }
            case 14: // statement ::= ifStat 
            {
                return (Statement) myStack.peek(0).value;
            }
            case 132: // superCount ::= SUPER DOT 
            {
                return 1;
            }
            case 11: // statement ::= SEMICOLON 
            {
                return NoneStatement.getInstance();
            }
            case 12: // statement ::= expression INTERPOLATION_END 
            {
                return interpolationFactory.createInterpolation((Expression) myStack.peek(1).value);
            }
            case 4: // classPureName ::= IDENTIFIER 
            {
                return new ClassNameBand((String) myStack.peek(0).value);
            }
            case 159: // classNameList ::= 
            {
                return new ClassNameList(this.nativeImportMgr);
            }
            case 140: // expressionList1 ::= expression 
            {
                return new ExpressionList().add((Expression) myStack.peek(0).value);
            }
            case 142: // expressionList ::= 
            {
                return new ExpressionList();
            }
            case 144: // mapValuePart ::= DIRECT_VALUE COLON expression 
            {
                return new MapValuePart().add((Object) myStack.peek(2).value, (Expression) myStack.peek(0).value);
            }
            case 145: // mapValuePart ::= MINUS DIRECT_VALUE COLON expression 
            {
                return new MapValuePart().add(ALU.negative((Object) myStack.peek(2).value), (Expression) myStack.peek(0).value);
            }
            case 43: // varPart ::= VAR varAssign 
            {
                return new StatementList().add((Expression) myStack.peek(0).value);
            }
            case 2: // statementList ::= statement 
            {
                return new StatementList().add((Statement) myStack.peek(0).value);
            }
            case 77: // switchPart0 ::= 
            {
                return new SwitchPart();
            }
            case 53: // blockPrepare ::= 
            case 54: // blockPrepare2 ::= LBRACE 
            {
                varmgr.push(); return null;
            }
            default:
                throw new ParseException("Invalid action number found in internal parse table");
        }
    }

}
