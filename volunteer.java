import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class volunteer extends Frame 
{
	Button submit;
	TextField name,area;
	//TextArea errorText;
	Connection connection;
	Statement statement;
	public volunteer() 
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (Exception e) 
		{
			System.err.println("Cannot find and load driver");
			System.exit(1);
		}
		connectToDB();
	}

	public void connectToDB() 
    {
		try 
		{
		  connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","akhila","vasavi");
		  statement = connection.createStatement();

		} 
		catch (SQLException connectException) 
		{
		  System.out.println(connectException.getMessage());
		  System.out.println(connectException.getSQLState());
		  System.out.println(connectException.getErrorCode());
		  System.exit(1);
		}
    }
	public void buildGUI() 
	{		
		submit = new Button("submit");
		submit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{			  
				  String query= "INSERT INTO volunteers VALUES(" + name.getText() + ", " + area.getText() + ")";
				  int i = statement.executeUpdate(query);
				 // errorText.append("\nInserted " + i + " rows successfully");
				}
				catch (SQLException insertException) 
				{
				 // displaySQLErrors(insertException);
				}
			}
		});

		name = new TextField(15);
		area = new TextField(15);
		
				
		
		//errorText = new TextArea(10, 40);
		//errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("NAME:"));
		first.add(name);
		first.add(new Label("ADDRESS:"));
		first.add(area);
		
		
		first.setBounds(125,90,200,100);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(submit);
        second.setBounds(125,220,150,100);         
				
		setLayout(null);

		add(first);
		add(second);
	    
		setTitle("Volunteer Application");
		Color clr = new Color(100, 100, 140);
		setBackground(clr); 
		setSize(500, 600);
		setVisible(true);
		
	}
	
	
	
	

	public static void main(String[] args) 
	{
		volunteer v = new volunteer();

		v.addWindowListener(new WindowAdapter(){
		  public void windowClosing(WindowEvent e) 
		  {
			System.exit(0);
		  }
		});
		
		v.buildGUI();
	}
}
