package View;

import Controller.Controller;
import Model.Exception.MyException;

import java.io.IOException;

public class RunExample extends Command{
    private Controller c;

    public RunExample(String key, String description, Controller contrl){
        super(key,description);
        this.c=contrl;
    }

    @Override
    public void execute() throws InterruptedException {
        try
        {
            this.c.executeAllStep();
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
