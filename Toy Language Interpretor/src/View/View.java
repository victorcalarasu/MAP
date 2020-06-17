//package View;

//import Controller.Controller;
//import Model.ADT.*;
//import Model.Exception.MyException;
//import Model.ProgramState.ProgramState;
//import Model.Statements.*;
//import Model.Expression.*;
//import Model.Value.*;
//import Model.Type.*;
//import Model.Statements.*;

//import java.io.BufferedReader;
//import java.util.Scanner;
/*
public class View {
    private Controller controller;
    private Scanner keyboard = new Scanner(System.in);
    private IStatement ex1= new CompoundStatement(new VariableDeclarationStatement("v",new IntType()), new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
    private IStatement ex2 = new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),  new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
            new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new ArithmeticExpression('*',new ValueExpression(new IntValue(3)),
                    new ValueExpression(new IntValue(5))))),  new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression('+',new VariableExpression("a"),
                    new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
    private IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()), new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true)))
            , new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
    IStatement ex4 = new CompoundStatement(
            new VariableDeclarationStatement("varf",new StringType()),new CompoundStatement(
            new AssignmentStatement("varf",new ValueExpression(new StringValue("test.in"))),new CompoundStatement(
            new OpenRFileStatement(new VariableExpression("varf")),new CompoundStatement(
            new VariableDeclarationStatement("varc",new IntType()),new CompoundStatement(
            new ReadFileStatement(new VariableExpression("varf"),"varc"),new CompoundStatement(
            new PrintStatement(new VariableExpression("varc")),new CompoundStatement(
            new ReadFileStatement(new VariableExpression("varf"),"varc") ,new CompoundStatement(new PrintStatement(new VariableExpression("varc")),new CloseRFileStatement(new VariableExpression("varf"))))))))));

    private ProgramState programState;
    private int printFlag;

    public View(Controller controller) {
        this.controller = controller;
        this.printFlag=1;
    }

    private void initProgram(IStatement statement){
        IStack<IStatement> exeStack = new MyStack<IStatement>();
        IDictionary<String,IValue> symbolTable = new MyDictionary<String,IValue>();
        IList<IValue> output = new MyList<IValue>();
        IDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<StringValue, BufferedReader>();
        this.programState = new ProgramState(exeStack,symbolTable,output,statement,fileTable);
        this.controller.addProgram(this.programState);
    }
    private void chooseProgram() throws MyException {
        System.out.println("1. 'int v;v=2;print(v)'\n" +
                "2. 'int a; int b; a=2+3*5; b=a+1; Print(b)'\n" +
                "3. 'bool a; int v; a=true;(If a then v=2 else v=3); Print(v)' \n"+
                "4. string varf;" +
                "varf=\"test.in\"; openRFile(varf);" +
                "int varc; readFile(varf,varc);print(varc); readFile(varf,varc);print(varc) closeRFile(varf)");
        int option;
        try {
            option = Integer.valueOf(this.keyboard.nextLine());
            switch (option) {
                case 1:
                    this.initProgram(ex1);
                    break;
                case 2:
                    this.initProgram(ex2);
                    break;
                case 3:
                    this.initProgram(ex3);
                    break;
                case 4:
                    this.initProgram(ex4);
                    break;
                default:
                    throw new MyException("Command does not exist!");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
    private void chooseExecMode() throws MyException{
        System.out.println("1. One-step\n" +
                "2. All steps\n" +
                "3. Change print flag\n " +
                "0.  Exit");
        int option;
        try {
            option = Integer.valueOf(this.keyboard.nextLine());
            switch (option) {
                case 1:
                    this.controller.executeOneStep(this.programState);
                    break;
                case 2:
                    this.controller.executeAllStep();
                    throw new MyException("Exit");
                case 3:
                    this.printFlag=1-this.printFlag;
                    break;
                case 0:
                    throw new MyException("Exit");
                default:
                    throw new MyException("Command does not exist!");
            }
        }
        catch(MyException exc){
            throw exc;
        }
    }

    public void run() {
        try {
            chooseProgram();
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return;
        }
        while (true){
            try{
                chooseExecMode();
            }
            catch (MyException e){
                System.out.println(e.getMessage());
                if (e.getMessage().equals("Exit"))
                    break;
            }
        }
    }

}
*/