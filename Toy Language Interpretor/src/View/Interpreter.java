package View;

import Controller.Controller;
import Model.ADT.*;
import Model.ProgramState.ProgramState;
import Model.Statements.IStatement;
import Model.Value.IValue;
import Model.Value.StringValue;
import Model.Statements.*;
import Model.Type.*;
import Model.Expression.*;
import Model.Value.*;
import Repository.IRepository;
import Repository.Repository;

import java.awt.print.Printable;
import java.io.BufferedReader;

public interface Interpreter {

    public static void main(String[] args) throws InterruptedException {
        IStatement ex1= new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
        IStatement ex2 = new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression((char) 1,new ValueExpression(new IntValue(2)),new ArithmeticExpression((char) 3,new ValueExpression(new IntValue(3)),
                                new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression((char) 1,new VariableExpression("a"),
                                        new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true)))
                                , new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
        IStatement ex4 = new CompoundStatement(
                new VariableDeclarationStatement("varf",new StringType()),new CompoundStatement(
                new AssignmentStatement("varf",new ValueExpression(new StringValue("test.in"))),
                new CompoundStatement(
                        new OpenRFileStatement(new VariableExpression("varf")),
                        new CompoundStatement(
                                new VariableDeclarationStatement("varc",new IntType()),
                                new CompoundStatement(
                                        new ReadFileStatement(new VariableExpression("varf"),"varc"),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("varc")),new CompoundStatement(
                                                new ReadFileStatement(new VariableExpression("varf"),"varc") ,
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),new CloseRFileStatement(new VariableExpression("varf"))))))))));

        IStatement ex5 = new CompoundStatement(new CompoundStatement(
                new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
                        new WhileStatement(
                                new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(0)),">"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new AssignmentStatement( "v",new ArithmeticExpression('-',new VariableExpression("v"),new ValueExpression(new IntValue(1))))
                                )
                        )
                )
        ), new PrintStatement(new VariableExpression("v")));
        IStatement ex6 = new CompoundStatement(
                new VariableDeclarationStatement("v",new RefType(new IntType())),
                new CompoundStatement(
                        new HeapAllocationStatement("v",new ValueExpression(new IntValue(20))),
                         new CompoundStatement(
                                new VariableDeclarationStatement("a",new RefType(new RefType(new  IntType()))), new CompoundStatement(
                                new HeapAllocationStatement("a",new VariableExpression("v")),new CompoundStatement(
                                new HeapAllocationStatement("v",new ValueExpression(new IntValue(30))),
                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression( new VariableExpression("a")))))))));
        IStatement ex7 = new CompoundStatement(
                new VariableDeclarationStatement("v",new RefType(new IntType())),
                new CompoundStatement(
                        new HeapAllocationStatement("v",new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))), new CompoundStatement(
                                new VariableDeclarationStatement("a",new RefType(new RefType(new  IntType()))), new CompoundStatement(
                                new HeapAllocationStatement("a",new VariableExpression("v")),new CompoundStatement(
                                new HeapWritingStatement("v",new ValueExpression(new IntValue(30))),
                                new PrintStatement(new ArithmeticExpression('+' ,new HeapReadingExpression(new HeapReadingExpression( new VariableExpression("a"))),new ValueExpression(new IntValue(5))))))))));



       // IStatement forked = new CompoundStatement(new HeapWritingStatement("a",new ValueExpression(new IntValue(30))),
         //       new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
           //             new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))));

        IStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a",new RefType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new HeapAllocationStatement("a",new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWritingStatement("a",new ValueExpression(new IntValue(30))),
                                                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))),new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))
                        )));

        ProgramState prg1 = new ProgramState(ex1);
        IRepository repo1 = new Repository( "log1.txt");
        Controller ctrl1 = new Controller(repo1);
        ctrl1.addProgram(prg1);



        ProgramState prg2 = new ProgramState(ex2);
        IRepository repo2 = new Repository( "log2.txt");
        Controller ctrl2 = new Controller(repo2);
        ctrl2.addProgram(prg2);



        ProgramState prg3 = new ProgramState(ex3);
        IRepository repo3 = new Repository("log3.txt");
        Controller ctrl3 = new Controller(repo3);
        ctrl3.addProgram(prg3);



        ProgramState prg4 = new ProgramState(ex4);
        IRepository repo4 = new Repository("log4.txt");
        Controller ctrl4 = new Controller(repo4);
        ctrl4.addProgram(prg4);


        ProgramState prg5 = new ProgramState(ex5);
        IRepository repo5 = new Repository( "log5.txt");
        Controller ctrl5 = new Controller(repo5);
        ctrl5.addProgram(prg5);


        ProgramState prg6 = new ProgramState(ex6);
        IRepository repo6 = new Repository( "log6.txt");
        Controller ctrl6 = new Controller(repo6);
        ctrl6.addProgram(prg6);


        ProgramState prg7 = new ProgramState(ex7);
        IRepository repo7 = new Repository( "log7.txt");
        Controller ctrl7 = new Controller(repo7);
        ctrl7.addProgram(prg7);

        ProgramState prg8 = new ProgramState(ex8);
        IRepository repo8 = new Repository( "log8.txt");
        Controller ctrl8 = new Controller(repo8);
        ctrl8.addProgram(prg8);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctrl1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctrl2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctrl3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctrl4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctrl5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctrl6));
        menu.addCommand(new RunExample("7", ex7.toString(), ctrl7));
        menu.addCommand(new RunExample("8", ex8.toString(), ctrl8));
        menu.show();

    }

}
